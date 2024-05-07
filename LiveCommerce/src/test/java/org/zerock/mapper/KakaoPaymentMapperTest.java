import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.KakaoPaymentVO;
import org.zerock.mapper.KakaoPaymentMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class KakaoPaymentMapperTest {
    
    @Autowired
    private KakaoPaymentMapper kakaoPaymentMapper;

    @Test // Create
    public void testCreateKakaoPayment() {
        KakaoPaymentVO kakaoPaymentVO = KakaoPaymentVO.builder()
                .orderID(12345)
                .paymentMethod("Credit Card")
                .amount(50000)
                .kakaoTransactionID("KAKAO123456")
                .status("PAID")
                .build();

        assertNotNull(kakaoPaymentVO);
        
        log.info("Before Insert: " + kakaoPaymentVO);

        kakaoPaymentMapper.insertKakaoPayment(kakaoPaymentVO);

        assertNotNull(kakaoPaymentVO.getPaymentID()); // Insert 후에 kakaoPaymentVO의 PK가 설정되었는지 확인

        log.info("After Insert: " + kakaoPaymentVO);
    }

    @Test // Read
    public void testSelectKakaoPayment() {
        KakaoPaymentVO kakaoPaymentVO = kakaoPaymentMapper.selectKakaoPayment(1);

        assertNotNull(kakaoPaymentVO);
        
        log.info(kakaoPaymentVO);
    }

    @Test // Update
    public void testUpdateKakaoPayment() {
        KakaoPaymentVO kakaoPaymentVO = KakaoPaymentVO.builder()
                .paymentID(1)
                .orderID(54321)
                .paymentMethod("Bank Transfer")
                .amount(60000)
                .kakaoTransactionID("KAKAO654321")
                .status("REFUNDED")
                .build();

        assertNotNull(kakaoPaymentVO);

        log.info("Before Update: " + kakaoPaymentVO);

        kakaoPaymentMapper.updateKakaoPayment(kakaoPaymentVO);

        KakaoPaymentVO updatedKakaoPaymentVO = kakaoPaymentMapper.selectKakaoPayment(1);

        assertNotNull(updatedKakaoPaymentVO);
        assertEquals(kakaoPaymentVO.getOrderID(), updatedKakaoPaymentVO.getOrderID());

        log.info("After Update: " + updatedKakaoPaymentVO);
    }

    @Test // Delete
    public void testDeleteKakaoPayment() {
        int paymentID = 1;

        KakaoPaymentVO kakaoPaymentVO = kakaoPaymentMapper.selectKakaoPayment(paymentID);

        assertNotNull(kakaoPaymentVO);

        kakaoPaymentMapper.deleteKakaoPayment(paymentID);

        KakaoPaymentVO deletedKakaoPaymentVO = kakaoPaymentMapper.selectKakaoPayment(paymentID);

        assertNull(deletedKakaoPaymentVO);

        log.info("KakaoPayment with ID " + paymentID + " has been deleted.");
    }
}