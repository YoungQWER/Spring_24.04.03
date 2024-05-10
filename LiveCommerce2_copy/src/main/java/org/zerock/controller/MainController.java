package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.service.CategoryService;
import org.zerock.service.ProductService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/live/*")

public class MainController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public MainController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/main")
    public String main(Model model) {
        // 카테고리 리스트와 프로덕트 리스트를 가져와서 모델에 추가
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProducts());
        
        // 메인 페이지 뷰로 이동
        return "/live/main";
    }
}