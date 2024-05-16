package org.zerock.controller;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderController {

    private final ProductService productService;

    @Autowired
    public OrderController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/order")
    public String showOrderPage(@RequestParam("productId") int productId, Model model) {
        // productId를 사용하여 상품 정보를 조회합니다.
        ProductVO product = productService.getProduct(productId);

        // 조회한 상품 정보를 모델에 추가합니다.
        model.addAttribute("product", product);

        return "/live/order"; // 주문 페이지의 뷰 이름을 리턴합니다.
    }
}