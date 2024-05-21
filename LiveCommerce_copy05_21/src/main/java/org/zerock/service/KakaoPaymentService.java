package org.zerock.service;

import org.zerock.domain.KakaoPaymentVO;

import java.util.List;

public interface KakaoPaymentService {

    // 카카오페이 결제 정보 등록
    void registerKakaoPayment(KakaoPaymentVO kakaoPayment);

    // 주어진 결제 ID에 해당하는 카카오페이 결제 정보 조회
    KakaoPaymentVO getKakaoPayment(int paymentID);

    // 모든 카카오페이 결제 정보 조회
    List<KakaoPaymentVO> getAllKakaoPayments();

    // 카카오페이 결제 정보 수정
    void modifyKakaoPayment(KakaoPaymentVO kakaoPayment);

    // 카카오페이 결제 정보 삭제
    void removeKakaoPayment(int paymentID);
}
