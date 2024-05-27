package org.zerock.controller;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.UserVO;
import org.zerock.service.CategoryService;
import org.zerock.service.ProductService;
import org.zerock.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/live/*")
@RequiredArgsConstructor
public class MainController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/main")
    public String main(Model model, Authentication authentication) {
        // 카테고리 리스트와 프로덕트 리스트를 가져와서 모델에 추가
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProducts());
        
        // 현재 로그인한 사용자의 정보를 가져옴
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName(); // 현재 사용자의 아이디(username)
            // 특정 아이디(username)를 기반으로 사용자 데이터를 가져와 모델에 추가
            UserVO user = userService.selectUserByUserName(username);
            model.addAttribute("user", user);
        }
        
        // 메인 페이지 뷰로 이동
        return "/live/main";
    }
    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        // 현재 로그인한 사용자의 정보를 가져옴
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName(); // 현재 사용자의 아이디(username)
            // 특정 아이디(username)를 기반으로 사용자 데이터를 가져와 모델에 추가
            UserVO user = userService.selectUserByUserName(username);
            model.addAttribute("user", user);
        }

        // 내 프로필 페이지 뷰로 이동
        return "/live/profile";
    }
}