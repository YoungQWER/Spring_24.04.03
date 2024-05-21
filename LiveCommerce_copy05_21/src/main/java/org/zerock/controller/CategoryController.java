package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.ProductVO;
import org.zerock.service.ProductService;
import org.zerock.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/live/*")
@RequiredArgsConstructor
public class CategoryController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/category")
    public String showProductsByCategory(@RequestParam("categoryId") int categoryId, Model model) {
        // categoryId를 사용하여 해당 카테고리에 속하는 상품 목록을 가져옵니다.
        List<ProductVO> products = productService.getProductsByCategory(categoryId);

        // 상품 목록을 모델에 추가하여 JSP 페이지로 전달합니다.
        model.addAttribute("products", products);

        // 해당하는 JSP 페이지의 경로를 반환합니다.
        return "/live/category"; // category.jsp와 같은 JSP 페이지의 이름을 반환합니다.
    }
}
