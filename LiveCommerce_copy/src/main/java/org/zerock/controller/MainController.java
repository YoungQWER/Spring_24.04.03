package org.zerock.controller;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.Criteria;
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
    public String main(Model model, Authentication authentication, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "") String productName) {
        // 카테고리 리스트를 모델에 추가
        model.addAttribute("categories", categoryService.getAllCategories());

        // 검색 조건을 Criteria 객체로 생성하여 모델에 추가
        Criteria criteria = new Criteria(pageNum, 10);
        criteria.setProductName(productName);
        model.addAttribute("criteria", criteria);

        // 페이징 처리된 상품 목록을 가져와서 모델에 추가
        model.addAttribute("products", productService.getProductsPaging(criteria));

        // 현재 로그인한 사용자의 정보를 가져와 모델에 추가
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            UserVO user = userService.selectUserByUserName(username);
            model.addAttribute("user", user);
        }

        return "/live/main";
    }

    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        // 현재 로그인한 사용자의 정보를 가져와 모델에 추가
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            UserVO user = userService.selectUserByUserName(username);
            model.addAttribute("user", user);
        }

        return "/live/profile";
    }
}
