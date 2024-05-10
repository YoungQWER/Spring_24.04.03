package org.zerock.service;

import java.util.List;
import org.zerock.domain.PaymentVO;

public interface PaymentService {
    
    void createPayment(PaymentVO payment); // 결제 정보 추가
    
    PaymentVO getPayment(int paymentID); // 특정 결제 정보 조회
    
    void updatePayment(PaymentVO payment); // 결제 정보 수정
    
    void deletePayment(int paymentID); // 결제 정보 삭제
    
    List<PaymentVO> getAllPayments(); // 모든 결제 정보 조회
}