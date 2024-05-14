package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.CartVO;
import org.zerock.domain.ProductVO;
import org.zerock.service.CartService;
import org.zerock.service.OrderService;
import org.zerock.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public CartController(CartService cartService, ProductService productService, OrderService orderService) {
        this.cartService = cartService;
        this.productService = productService;
        this.orderService = orderService;
    }

    // 모든 카트 목록을 조회하는 페이지
    @GetMapping("/list")
    public String getAllCarts(Model model) {
        List<CartVO> carts = cartService.getAllCarts();
        model.addAttribute("carts", carts);
        
        // 상품 정보를 가져와서 모델에 추가
        List<ProductVO> products = productService.getAllProducts();
        model.addAttribute("products", products);
        
        return "/live/cart"; // 카트 목록을 보여주는 뷰로 이동
    }

    // 특정 카트의 상세 정보 조회 페이지
    @GetMapping("/{cartID}")
    public String getCart(@PathVariable("cartID") int cartID, Model model) {
        CartVO cart = cartService.getCart(cartID);
        model.addAttribute("cart", cart);
        
        // 해당 카트에 속한 상품 정보를 가져와서 모델에 추가
        ProductVO product = productService.getProduct(cart.getProductID());
        model.addAttribute("product", product);
        
        return "cartDetail"; // 카트 상세 정보를 보여주는 뷰로 이동
    }

    // 카트에 새로운 상품 추가
    @PostMapping
    public String insertCart(CartVO cart) {
        cartService.insertCart(cart);
        return "redirect:/carts"; // 카트 목록 페이지로 리다이렉트
    }

    // 카트 정보 업데이트
    @PostMapping("/{cartID}")
    public String updateCart(@PathVariable("cartID") int cartID, CartVO cart) {
        cart.setCartID(cartID);
        cartService.updateCart(cart);
        return "redirect:/carts/" + cartID; // 업데이트된 카트 상세 정보 페이지로 리다이렉트
    }

    // 카트 삭제
    @PostMapping("/{cartID}/delete")
    public String deleteCart(@PathVariable("cartID") int cartID) {
        cartService.deleteCart(cartID);
        return "redirect:/carts"; // 카트 목록 페이지로 리다이렉트
    }
}