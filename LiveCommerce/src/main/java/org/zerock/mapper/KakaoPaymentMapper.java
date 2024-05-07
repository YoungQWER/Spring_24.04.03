package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.KakaoPaymentVO;

import java.util.List;

public interface KakaoPaymentMapper {

    // Create
    void insertKakaoPayment(KakaoPaymentVO kakaoPayment);

    // Read
    KakaoPaymentVO selectKakaoPayment(@Param("paymentID") int paymentID);
    List<KakaoPaymentVO> selectAllKakaoPayments();

    // Update
    void updateKakaoPayment(KakaoPaymentVO kakaoPayment);

    // Delete
    void deleteKakaoPayment(@Param("paymentID") int paymentID);
    
}