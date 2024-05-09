package org.zerock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.PaymentVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    public void testGetAllPayments() {
        List<PaymentVO> paymentList = paymentService.getAllPayments();

        assertNotNull(paymentList);
        log.info("All payments retrieved: " + paymentList);
    }

    @Test
    public void testGetPayment() {
        int paymentID = 2; // 예시로 결제 ID를 2로 설정
        PaymentVO payment = paymentService.getPayment(paymentID);

        assertNotNull(payment);
        log.info("Payment retrieved: " + payment);
    }

    @Test
    public void testCreatePayment() {
        PaymentVO payment = new PaymentVO();
        // 결제 정보 설정
        payment.setOrderID(3); // 예시로 주문 ID를 1로 설정
        payment.setPaymentMethod("Credit Card");
        payment.setAmount(50000);
        // 이하 필요한 정보 설정

        paymentService.createPayment(payment);

        assertNotNull(payment.getPaymentID());
        log.info("New payment created: " + payment);
    }

    @Test
    public void testUpdatePayment() {
        int paymentID = 4; // 예시로 결제 ID를 3로 설정
        PaymentVO payment = paymentService.getPayment(paymentID);

        // 수정할 내용 설정
        payment.setAmount(60000);

        paymentService.updatePayment(payment);

        PaymentVO updatedPayment = paymentService.getPayment(paymentID);
        assertEquals(60000, updatedPayment.getAmount());
        log.info("Payment updated: " + updatedPayment);
    }

    @Test
    public void testDeletePayment() {
        int paymentID = 4; // 예시로 결제 ID를 3로 설정
        paymentService.deletePayment(paymentID);

        PaymentVO deletedPayment = paymentService.getPayment(paymentID);
        assertEquals(null, deletedPayment);
        log.info("Payment deleted: " + paymentID);
    }
}