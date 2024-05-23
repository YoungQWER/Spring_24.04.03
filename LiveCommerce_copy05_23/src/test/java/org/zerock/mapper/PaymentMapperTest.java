package org.zerock.mapper;

import java.sql.Timestamp;
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
public class PaymentMapperTest {

    @Autowired
    private PaymentMapper paymentMapper;

    @Test // Create
    public void testCreatePayment() {
        PaymentVO payment = PaymentVO.builder()
                .orderID(3)
                .paymentMethod("Credit Card")
                .amount(10000)
                .paymentDate(new Timestamp(System.currentTimeMillis()))
                .status("Paid")
                .build();

        log.info("Before insert: " + payment);

        paymentMapper.createPayment(payment);

        log.info("After insert: " + payment);
    }

    @Test // Read
    public void testGetPayment() {
        PaymentVO payment = paymentMapper.getPayment(1);
        log.info(payment);
    }

    @Test // Update
    public void testUpdatePayment() {
        PaymentVO payment = PaymentVO.builder()
                .paymentID(1)
                .orderID(1)
                .paymentMethod("Credit Card")
                .amount(200000)
                .paymentDate(new Timestamp(System.currentTimeMillis()))
                .status("Paid")
                .build();

        log.info("Before update: " + payment);

        paymentMapper.updatePayment(payment);

        log.info("After update: " + payment);
    }

    @Test // Delete
    public void testDeletePayment() {
        paymentMapper.deletePayment(1);
    }

    @Test // Read all payments
    public void testGetAllPayments() {
        List<PaymentVO> payments = paymentMapper.getAllPayments();
        for (PaymentVO payment : payments) {
            log.info(payment);
        }
    }
}