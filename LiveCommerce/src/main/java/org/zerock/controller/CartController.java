package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.CartProductVO;
import org.zerock.service.CartService;
import org.zerock.service.UserService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/cart")
@Log4j
public class CartController {

   private final CartService cartService;
    private final UserService userService;

    @Autowired
    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
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
}