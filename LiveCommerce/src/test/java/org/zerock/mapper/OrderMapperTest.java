package org.zerock.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.OrderVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testCreateOrder() {
        OrderVO order = OrderVO.builder()
                .userID(3)
                .productID(1)
                .quantity(2)
                .shippingAddress("123 Main St, City, Country")
                .shippingPostalCode("12345")
                .orderDate(new Timestamp(System.currentTimeMillis()))
                .build();

        log.info("Before insert: " + order);

        orderMapper.createOrder(order);

        log.info("After insert: " + order);
    }

    @Test
    public void testGetOrder() {
        OrderVO order = orderMapper.getOrder(3);
        log.info(order);
    }

    @Test
    public void testUpdateOrder() {
        OrderVO order = orderMapper.getOrder(3);
        order.setQuantity(2);

        log.info("Before update: " + order);

        orderMapper.updateOrder(order);

        log.info("After update: " + order);
    }

    @Test
    public void testDeleteOrder() {
        orderMapper.deleteOrder(1);
    }

    @Test
    public void testGetAllOrders() {
        List<OrderVO> orders = orderMapper.getAllOrders();
        orders.forEach(order -> log.info(order));
    }
}