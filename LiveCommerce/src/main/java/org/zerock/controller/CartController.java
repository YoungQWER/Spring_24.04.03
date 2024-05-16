package org.zerock.controller;

import java.sql.Timestamp;
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
import org.zerock.domain.OrderVO;
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
    private final ProductService productservice;
    private final OrderService orderservice;

    @Autowired
    public CartController(CartService cartService, UserService userService, ProductService productservice, OrderService orderservice) {
        this.cartService = cartService;
        this.userService = userService;
        this.productservice = productservice;
        this.orderservice = orderservice;
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
    public String placeOrder(@RequestParam("productId") List<Integer> productIds,
                             @RequestParam("quantities") List<Integer> quantities,
                             Model model) {

        // 현재 로그인한 사용자의 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        // 사용자의 ID로 배송 주소와 우편번호 가져오기
        UserVO userVO = userService.selectUserByUserName(username);
        int userID = userVO.getUserID();
        String shippingAddress = userVO.getShippingAddress();
        String shippingPostalCode = userVO.getShippingPostalCode();

        // 모든 제품에 대해 주문 처리
        for (int i = 0; i < productIds.size(); i++) {
            int productId = productIds.get(i);
            int quantity = quantities.get(i);

            // 상품 정보 조회
            ProductVO product = productservice.getProduct(productId);

            // 이미 존재하는 주문 정보 가져오기
            OrderVO existingOrder = orderservice.getOrderByUserIDAndProductID
            		(userID, productId);

            if(existingOrder != null) {
                // 기존 주문 정보 업데이트
                existingOrder.setQuantity(existingOrder.getQuantity() + quantity);
                existingOrder.setShippingAddress(shippingAddress);
                existingOrder.setShippingPostalCode(shippingPostalCode);
                existingOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));

                orderservice.updateOrder(existingOrder);
            } else {
                // 새로운 주문 생성
                OrderVO newOrder = OrderVO.builder()
                        .userID(userID)
                        .productID(productId)
                        .quantity(quantity)
                        .shippingAddress(shippingAddress)
                        .shippingPostalCode(shippingPostalCode)
                        .orderDate(new Timestamp(System.currentTimeMillis())) // 현재 시간으로 주문 일자 설정
                        .build();

                orderservice.createOrder(newOrder);
            }
            
            // 로깅
            log.info("productId: " + productId);
            log.info("quantity: " + quantity);
        }

        // 모델에 주문 정보 추가
        model.addAttribute("productIds", productIds);
        model.addAttribute("quantities", quantities);
        model.addAttribute("shippingAddress", shippingAddress);
        model.addAttribute("shippingPostalCode", shippingPostalCode);

        return "/live/cart";
    }

}