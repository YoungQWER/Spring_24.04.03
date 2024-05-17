package org.zerock.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public String processKakaoPay(HttpServletRequest request, Model model) {
       
        String productName = request.getParameter("productName");
        int amount = Integer.parseInt(request.getParameter("amount"));
       
       // 현재 로그인한 사용자의 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        // 사용자 이름으로 사용자 정보 가져오기
        UserVO user = userService.selectUserByUserName(username);

        
        // 사용자의 이메일과 이름 주소 지역번호 가져오기
        String buyer_Email = user.getEmail();
        String buyer_Name = user.getUsername();
        String buyer_ShippingAddress = user.getShippingAddress();
        String buyer_ShippingPostalCode = user.getShippingPostalCode();

        log.info("구매자 이름: " + buyer_Name);
        log.info("구매자 이메일: " + buyer_Email);
        log.info("productName: " + productName);
        log.info("amount: " + amount);
        log.info("지역번호  " + buyer_ShippingPostalCode);
        log.info("구매자 주소 " + buyer_ShippingAddress);
         
         // productName과 amount 값을 모델에 추가하여 kakaoPay.jsp로 전달
         model.addAttribute("productName", productName);
         model.addAttribute("amount", amount);
         model.addAttribute("ShippingPostalCode" , buyer_ShippingPostalCode);
         model.addAttribute("ShippingAddress" , buyer_ShippingAddress);
         model.addAttribute("Name",buyer_Name);
         model.addAttribute("Email",buyer_Email);
        
        return "/kakaoPay";
    }
    
    @GetMapping("/paySuccess")
    public String paymentSuccess() {
    	// 결제 성공 시 처리할 로직을 구현합니다.
    	return "/live/paySuccess"; // 결제 성공 페이지로 이동
    }
    
    @PostMapping("/paySuccess")
    public ResponseEntity<String> completePayment(@RequestBody String impUid) {
        // 아임포트나 PG사의 API를 사용하여 impUid를 이용해 결제 정보 조회 및 처리하는 로직을 작성
        // 예를 들어, 아임포트 API를 사용하여 결제 정보를 조회하고 처리하는 로직을 구현
        
        // 결제 정보를 확인하고 처리한 결과에 따라 적절한 응답을 반환
        boolean paymentSuccess = true; // 결제 성공 여부를 가정하고 간단히 표시
        if (paymentSuccess) {
            return ResponseEntity.ok("Payment successfully processed.");
        } else {
            return ResponseEntity.badRequest().body("Payment processing failed.");
        }
    }
    
    @GetMapping("/payFail")
    public String paymentFail() {
        // 결제 실패 시 처리할 로직을 구현합니다.
        return "/live/payFail"; // 결제 실패 페이지로 이동
    }
}