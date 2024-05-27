package org.zerock.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.CategoryVO;
import org.zerock.domain.ProductVO;
import org.zerock.service.CategoryService;
import org.zerock.service.LiveChatService;
import org.zerock.service.ProductService;
import org.zerock.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/live/*")
@RequiredArgsConstructor
@Log4j
public class ProductController {

    private final ProductService productService;
    private final UserService userService;
    private final LiveChatService liveChatService;
    private final CategoryService categoryService;

    @GetMapping("/product")
    public String showProductDetails(@RequestParam("id") int productId, Model model, RedirectAttributes redirectAttributes) {
        // 현재 로그인한 사용자의 정보를 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName(); // 현재 사용자의 이름 가져오기
        
        // 사용자가 로그인하지 않은 경우
        if ("anonymousUser".equals(currentUsername)) {
            // 알림을 설정하여 모델에 추가합니다.
            redirectAttributes.addFlashAttribute("message", "로그인 후 이용 가능합니다.");
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        // userService를 사용하여 현재 사용자의 ID를 가져옵니다.
        int currentUser = userService.getUserIDByUsername(currentUsername);

        // productService를 사용하여 productId에 해당하는 제품 정보를 가져옵니다.
        ProductVO product = productService.getProduct(productId);
        log.info("productId에 해당하는 제품 정보 " +product);
        
        Integer categoryId = productService.getCategoryIdByProductId(productId);
        log.info(categoryId);
        
        List<ProductVO> products = productService.getProductsByCategory(categoryId);
        log.info(products);
        // 제품 정보와 현재 사용자 정보를 모델에 추가하여 JSP 페이지로 전달합니다.
        model.addAttribute("product", product);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentUsername", currentUsername);
        model.addAttribute("categoryproducts", products);
        
        log.info("categoryproducts : " + products);

        // 채팅 기록을 가져와 모델에 추가
        model.addAttribute("chatHistory", liveChatService.getChatHistoryByProductID(productId));

        // 해당하는 JSP 페이지의 경로를 반환합니다.
        return "/live/shopping"; // shopping.jsp와 같은 JSP 페이지의 이름을 반환합니다.
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("productName") String productName, Model model) {
        List<ProductVO> products = productService.searchProductsByName(productName);
        model.addAttribute("products", products);
        return "/live/main"; // 검색 결과를 표시하는 뷰의 이름
    }
}
