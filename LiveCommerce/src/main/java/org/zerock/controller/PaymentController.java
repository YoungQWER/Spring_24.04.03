package org.zerock.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.UserVO;
import org.zerock.service.UserService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/live/*")
@Log4j
public class PaymentController {
    
    private final UserService userService;
    
    @Autowired
    public PaymentController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/kakaoPay")
    public String goToKakaoPay(Model model) {
        // 현재 로그인한 사용자의 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        // 여기서 사용자 정보를 필요한 형태로 가공하여 모델에 추가합니다.
        UserVO user = userService.selectUserByUserName(username);
        model.addAttribute("user", user);

        // 다른 필요한 정보도 추가할 수 있습니다.

        return "/kakaoPay";
    }
    
    
    @PostMapping("/kakaoPay")
    public String processKakaoPay(HttpServletRequest request) {
    	
        String productName = request.getParameter("productName");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String shippingAddress = request.getParameter("shippingAddress");
        String shippingPostalCode = request.getParameter("shippingPostalCode");
    	
        
        
    	// 현재 로그인한 사용자의 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        // 사용자 이름으로 사용자 정보 가져오기
        UserVO user = userService.selectUserByUserName(username);

        // 사용자의 이메일과 이름 가져오기
        String buyer_Email = user.getEmail();
        String buyer_Name = user.getUsername();

        log.info("구매자 이름: " + buyer_Name);
        log.info("구매자 이메일: " + buyer_Email);
        log.info("productName: " + productName);
        log.info("가격: " + price);
        log.info("수량: " + quantity);
        log.info("배송 주소: " + shippingAddress);
        log.info("우편번호: " + shippingPostalCode);
//        log.info("amount: " + amount);
        
        return "/kakaoPay";
    }
}
