package org.zerock.service;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.KakaoPaymentVO;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class KakaoPaymentServiceTest {

    @Autowired
    private KakaoPaymentService kakaoPaymentService;

    @Test
    public void testRegisterKakaoPayment() {
        // 현재 시간을 가져와 등록 날짜로 사용
        Timestamp registrationDate = new Timestamp(System.currentTimeMillis());

        // 카카오페이 결제 정보 생성
        KakaoPaymentVO kakaoPayment = KakaoPaymentVO.builder()
                .orderID(3)
                .paymentMethod("카카오페이444")
                .amount(5000)
                .kakaoTransactionID("KAKAO1234")
                .status("결제완료")
                .paymentDate(registrationDate) // 등록 날짜 추가
                .build();

        // 카카오페이 결제 정보 등록
        kakaoPaymentService.registerKakaoPayment(kakaoPayment);

    }


    @Test
    public void testGetKakaoPayment() {
        // 여기서는 이미 등록된 결제 정보를 조회하므로 별도의 등록 작업은 필요하지 않습니다.
        int paymentID = 3; // 조회할 결제 정보의 ID

        // 결제 정보 조회
        KakaoPaymentVO retrievedKakaoPayment = kakaoPaymentService.getKakaoPayment(paymentID);
        assertEquals(paymentID, retrievedKakaoPayment.getPaymentID());
        
        log.info(retrievedKakaoPayment);
    }

    @Test
    public void testModifyKakaoPayment() {
        int paymentID = 3; // 수정할 결제 정보의 ID

        // 수정할 정보 가져오기
        KakaoPaymentVO retrievedKakaoPayment = kakaoPaymentService.getKakaoPayment(paymentID);
        // 수정하기
        retrievedKakaoPayment.setStatus("결제취소");
        // 수정된 정보 업데이트
        kakaoPaymentService.modifyKakaoPayment(retrievedKakaoPayment);
        // 수정된 정보 확인
        KakaoPaymentVO modifiedKakaoPayment = kakaoPaymentService.getKakaoPayment(paymentID);
        assertEquals("결제취소", modifiedKakaoPayment.getStatus());
        
        log.info(modifiedKakaoPayment);
    }

    @Test
    public void testRemoveKakaoPayment() {
        int paymentID = 3; // 삭제할 결제 정보의 ID

        // 삭제 전에 해당 정보가 존재하는지 확인
        KakaoPaymentVO kakaoPayment = kakaoPaymentService.getKakaoPayment(paymentID);
        assertEquals(paymentID, kakaoPayment.getPaymentID());

        // 결제 정보 삭제
        kakaoPaymentService.removeKakaoPayment(paymentID);

        // 삭제 후 해당 정보가 더 이상 존재하지 않는지 확인
        KakaoPaymentVO deletedKakaoPayment = kakaoPaymentService.getKakaoPayment(paymentID);
        assertNull(deletedKakaoPayment);
        
        log.info(deletedKakaoPayment);
    }

    @Test
    public void testGetAllKakaoPayments() {

        // 모든 카카오페이 결제 정보 조회
        List<KakaoPaymentVO> kakaoPayments = kakaoPaymentService.getAllKakaoPayments();
        
        log.info(kakaoPayments);
    }
}
