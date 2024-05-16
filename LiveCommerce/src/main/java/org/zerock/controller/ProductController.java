package org.zerock.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.ProductVO;
import org.zerock.domain.UserVO; // 사용자 정보를 담는 UserVO 클래스 import
import org.zerock.service.ProductService;
import org.zerock.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/live/*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/product")
    public String showProductDetails(@RequestParam("id") int productId, Model model) {
        // productService를 사용하여 productId에 해당하는 제품 정보를 가져옵니다.
        ProductVO product = productService.getProduct(productId);

        // 현재 로그인한 사용자의 정보를 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int currentUser = userService.getUserIDByUsername(authentication.getName());

        // 제품 정보와 현재 사용자 정보를 모델에 추가하여 JSP 페이지로 전달합니다.
        model.addAttribute("product", product);
        model.addAttribute("currentUser", currentUser);

        // 해당하는 JSP 페이지의 경로를 반환합니다.
        return "/live/shopping"; // shopping.jsp와 같은 JSP 페이지의 이름을 반환합니다.
    }

}