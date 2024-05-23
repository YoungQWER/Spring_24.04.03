package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.KakaoPaymentVO;
import org.zerock.mapper.KakaoPaymentMapper;

import java.util.List;

@Service
public class KakaoPaymentServiceImpl implements KakaoPaymentService {

    @Autowired
    private KakaoPaymentMapper kakaoPaymentMapper;

    @Override
    public void registerKakaoPayment(KakaoPaymentVO kakaoPayment) {
        // 카카오페이 결제 정보를 데이터베이스에 등록
        kakaoPaymentMapper.insertKakaoPayment(kakaoPayment);
    }

    @Override
    public KakaoPaymentVO getKakaoPayment(int paymentID) {
        // 주어진 결제 ID에 해당하는 카카오페이 결제 정보를 조회
        return kakaoPaymentMapper.selectKakaoPayment(paymentID);
    }

    @Override
    public List<KakaoPaymentVO> getAllKakaoPayments() {
        // 모든 카카오페이 결제 정보를 조회
        return kakaoPaymentMapper.selectAllKakaoPayments();
    }

    @Override
    public void modifyKakaoPayment(KakaoPaymentVO kakaoPayment) {
        // 카카오페이 결제 정보를 수정
        kakaoPaymentMapper.updateKakaoPayment(kakaoPayment);
    }

    @Override
    public void removeKakaoPayment(int paymentID) {
        // 주어진 결제 ID에 해당하는 카카오페이 결제 정보를 삭제
        kakaoPaymentMapper.deleteKakaoPayment(paymentID);
    }
}
