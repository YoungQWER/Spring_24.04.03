package org.zerock.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.zerock.domain.CartProductVO;
import org.zerock.domain.ProductVO;
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
    @PostMapping("/checkout")
    public String checkout(@RequestParam("selectedProducts") String selectedProducts, 
                           @RequestParam("selectedAmount") String selectedAmount, 
                           Model model) {
        log.info("Selected Products: " + selectedProducts);
        log.info("selectedAmount: " + selectedAmount);

        // 선택된 상품의 총 가격을 부동 소수점 숫자로 파싱
        double totalPrice = Double.parseDouble(selectedAmount);
        
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
        log.info("productName: " + selectedProducts);
        log.info("amount: " + selectedAmount);
        log.info("지역번호  " + buyer_ShippingPostalCode);
        log.info("구매자 주소 " + buyer_ShippingAddress);
         
         // productName과 amount 값을 모델에 추가하여 kakaoPay.jsp로 전달
         model.addAttribute("productName", selectedProducts);
         model.addAttribute("amount", selectedAmount);
         model.addAttribute("ShippingPostalCode" , buyer_ShippingPostalCode);
         model.addAttribute("ShippingAddress" , buyer_ShippingAddress);
         model.addAttribute("Name",buyer_Name);
         model.addAttribute("Email",buyer_Email);
        
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
    @PostMapping("/complete")
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
    // 아임포트 API를 통해 결제 정보를 조회하는 메서드
    private boolean callImpApiToCheckPayment(String impUid) {
        // 아임포트 API를 호출하여 결제 정보를 조회하는 URL
        String apiUrl = "https://api.iamport.kr/payments/" + impUid;

        // 아임포트 API 토큰 발급을 위한 URL
        String tokenUrl = "https://api.iamport.kr/users/getToken";

        // 아임포트 API 토큰 발급 요청
        RestTemplate restTemplate = new RestTemplate();
        String tokenResponse = restTemplate.postForObject(tokenUrl, null, String.class);

        // 아임포트 API를 호출하여 결제 정보 조회
        String paymentResponse = restTemplate.getForObject(apiUrl + "?_token=" + tokenResponse, String.class);

        // 결제 정보 확인 후 처리
        // 여기서는 간단히 성공 여부만 반환하도록 예시를 작성했습니다.
        // 실제로는 API 응답을 파싱하여 결제 정보를 확인하고 적절히 처리해야 합니다.
        return paymentResponse.contains("\"status\":\"paid\"");
    }
    
    @GetMapping("/paySuccess")
    public String showPaymentSuccessPage(Model model) {
    	
    	  
        // 결제 성공 시 필요한 데이터를 모델에 추가
        model.addAttribute("merchant_uid", "결제 거래 ID");
        model.addAttribute("paid_amount", "결제 금액");
        model.addAttribute("apply_num", "카드 승인번호");

        return "paySuccess"; // paySuccess.jsp를 렌더링
    }

    @GetMapping("/payFail")
    public String showPaymentFailurePage(Model model) {
        // 결제 실패 시 필요한 데이터를 모델에 추가
        model.addAttribute("error_msg", "실패 메시지");

        return "payFail"; // payFail.jsp를 렌더링
    }
}