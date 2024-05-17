package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.CartProductVO;
import org.zerock.domain.CartVO;
import org.zerock.domain.ProductVO;
import org.zerock.domain.UserVO;
import org.zerock.service.CartService;
import org.zerock.service.OrderService;
import org.zerock.service.ProductService;
import org.zerock.service.UserService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/cart")
@Log4j
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public CartController(CartService cartService, UserService userService, ProductService productService, OrderService orderService) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/list")
    public String viewCart(Model model) {
        // 현재 로그인한 사용자의 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        // 사용자의 ID로 장바구니 정보 가져오기
        int userID = userService.getUserIDByUsername(username);
        List<CartProductVO> cartDetails = cartService.getCartDetails(userID);
        
        // 장바구니 정보와 사용자 정보를 모델에 추가
        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("userID", userID);
        
        return "/live/cart";
    }
    
    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") List<Integer> productIds,
                            @RequestParam("quantity") List<Integer> quantities,
                            Model model) {

        // 현재 로그인한 사용자의 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        // 사용자의 ID로 배송 주소와 우편번호 가져오기
        UserVO userVO = userService.selectUserByUserName(username);
        int userID = userVO.getUserID();
        String shippingAddress = userVO.getShippingAddress();
        String shippingPostalCode = userVO.getShippingPostalCode();

        // 모든 제품에 대해 장바구니에 추가
        for (int i = 0; i < productIds.size(); i++) {
            int productId = productIds.get(i);
            int quantity = quantities.get(i);

            // 장바구니에 추가할 상품 정보 조회
            ProductVO product = productService.getProduct(productId);

            // 장바구니에 이미 존재하는 상품인지 확인
            CartVO existingCart = cartService.getCartByUserID(userID).stream()
                    .filter(cart -> cart.getProductID() == productId)
                    .findFirst()
                    .orElse(null);

            if (existingCart != null) {
                // 이미 존재하는 상품이면 수량만 업데이트
                existingCart.setQuantity(existingCart.getQuantity() + quantity);
                cartService.updateCart(existingCart);
            } else {
                // 새로운 상품인 경우 장바구니에 추가
                CartVO newCart = CartVO.builder()
                        .userID(userID)
                        .productID(productId)
                        .quantity(quantity)
                        .build();
                cartService.insertCart(newCart);
            }
            
            // 로깅
            log.info("productId: " + productId);
            log.info("quantity: " + quantity);
        }

        // 모델에 추가할 데이터 설정
        model.addAttribute("productIds", productIds);
        model.addAttribute("quantities", quantities);
        model.addAttribute("shippingAddress", shippingAddress);
        model.addAttribute("shippingPostalCode", shippingPostalCode);

        // 장바구니 페이지로 리다이렉트
        return "redirect:/cart/list";
    }
}
