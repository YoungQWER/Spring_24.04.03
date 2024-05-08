package org.zerock.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.KakaoPaymentVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class KakaoPaymentMapperTest {

    @Autowired
    private KakaoPaymentMapper kakaoPaymentMapper;

    @Test // Create
    public void testCreate() {
        KakaoPaymentVO kakaoPayment = KakaoPaymentVO.builder()
                .orderID(1)
                .paymentMethod("Credit Card")
                .amount(50000)
                .kakaoTransactionID("KAKAOTRANS12345")
                .status("Completed")
                .build();

        log.info("Before insert: " + kakaoPayment);

        kakaoPaymentMapper.insertKakaoPayment(kakaoPayment);

        log.info("After insert: " + kakaoPayment);
    }

    @Test // Read
    public void testRead() {
        KakaoPaymentVO kakaoPayment = kakaoPaymentMapper.selectKakaoPayment(1);
        log.info(kakaoPayment);
    }

    @Test // Update
    public void testUpdate() {
        KakaoPaymentVO kakaoPayment = KakaoPaymentVO.builder()
                .paymentID(1)
                .orderID(2)
                .paymentMethod("Bank Transfer")
                .amount(70000)
                .kakaoTransactionID("KAKAOTRANS54321")
                .status("Pending")
                .build();

        log.info("Before update: " + kakaoPayment);

        kakaoPaymentMapper.updateKakaoPayment(kakaoPayment);

        log.info("After update: " + kakaoPayment);
    }

    @Test // Delete
    public void testDelete() {
        kakaoPaymentMapper.deleteKakaoPayment(1);
    }

    @Test // 전체 리스트 조회
    public void testGetAllKakaoPayments() {
        List<KakaoPaymentVO> kakaoPayments = kakaoPaymentMapper.selectAllKakaoPayments();
        for (KakaoPaymentVO kakaoPayment : kakaoPayments) {
            log.info(kakaoPayment);
        }
    }
}