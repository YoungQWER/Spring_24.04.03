package org.zerock.controller;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.OrderVO;
import org.zerock.domain.ProductVO;
import org.zerock.domain.UserVO;
import org.zerock.service.OrderService;
import org.zerock.service.ProductService;
import org.zerock.service.UserService;

import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping("/live/*")
@Log4j
public class OrderController {

    private final ProductService productService;
    private final OrderService orderservice;
    private final UserService userservice;

    @Autowired
    public OrderController(ProductService productService, OrderService orderservice, UserService userservice) {
        this.productService = productService;
        this.orderservice = orderservice;
        this.userservice = userservice;
    }

    @GetMapping("/order")   
    public String showOrderPage(@RequestParam("productId") int productId, Model model) {
        // productId를 사용하여 상품 정보를 조회합니다.
        ProductVO product = productService.getProduct(productId);

        // 조회한 상품 정보를 모델에 추가합니다.
        model.addAttribute("product", product);

        return "/live/order"; // 주문 페이지의 뷰 이름을 리턴합니다.
    }
    
    @PostMapping("/order")
    public String placeOrder(@RequestParam("productId") int productId,
                             @RequestParam("quantity") int quantity,
                             Model model) {

       // 상품 정보 조회
        ProductVO product = productService.getProduct(productId);
        
        // 현재 로그인한 사용자의 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        // 사용자의 ID로 배송 주소와 우편번호 가져오기
        UserVO userVO = userservice.selectUserByUserName(username);
        int userID = userVO.getUserID();
        String shippingAddress = userVO.getShippingAddress();
        String shippingPostalCode = userVO.getShippingPostalCode();

        // 이미 존재하는 주문 정보 가져오기
        OrderVO existingOrder = orderservice.getOrder(userID);
        
        if(existingOrder != null) {
            // 기존 주문 정보 업데이트
            existingOrder.setProductID(productId);
            existingOrder.setQuantity(quantity);
            existingOrder.setShippingAddress(shippingAddress);
            existingOrder.setShippingPostalCode(shippingPostalCode);
            existingOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));

            orderservice.updateOrder(existingOrder);
        } else {
            // 새로운 주문 생성
            OrderVO newOrder = OrderVO.builder()
                    .userID(userID)
                    .productID(productId)
                    .quantity(quantity)
                    .shippingAddress(shippingAddress)
                    .shippingPostalCode(shippingPostalCode)
                    .orderDate(new Timestamp(System.currentTimeMillis())) // 현재 시간으로 주문 일자 설정
                    .build();

            orderservice.createOrder(newOrder);
        }
        // 주문 정보를 모델에 추가
        OrderVO order = orderservice.getOrder(userID);
        model.addAttribute("order", order);
        
        // 모델에 주문 정보 추가
        
        model.addAttribute("productId", productId);
        model.addAttribute("quantity", quantity);
        model.addAttribute("shippingAddress", shippingAddress);
        model.addAttribute("shippingPostalCode", shippingPostalCode);
        model.addAttribute("product", product);
        
        // 로깅
        log.info("productId: " + productId);
        log.info("quantity: " + quantity);
        log.info("shippingAddress: " + shippingAddress);
        log.info("shippingPostalCode: " + shippingPostalCode);
        
        return "/live/order";
    }
    


}