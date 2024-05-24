package org.zerock.service;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.OrderVO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testCreateOrder() {
        OrderVO newOrder = OrderVO.builder()
            .userID(11) // 예시로 사용자 ID를 1로 설정
            .productID(1) // 예시로 제품 ID를 1로 설정
            .quantity(5) // 예시로 수량을 5으로 설정
            .shippingAddress("123 Main St, City, Country") // 배송 주소 설정
            .shippingPostalCode("12345") // 배송 우편번호 설정
            .build();

        orderService.createOrder(newOrder);

        assertNotNull(newOrder.getOrderID());
        log.info("New order created: " + newOrder);
    }



    @Test
    public void testGetOrder() {
        int orderId = 3; // 조회할 주문 ID

        OrderVO order = orderService.getOrder(orderId);

        assertNotNull(order);
        assertEquals(orderId, order.getOrderID());
        log.info("Retrieved order: " + order);
    }

    @Test
    public void testUpdateOrder() {
        int orderId = 3; // 업데이트할 주문 ID

        OrderVO order = orderService.getOrder(orderId);
        order.setQuantity(5); // 예시로 수량을 5로 변경

        orderService.updateOrder(order);

        OrderVO updatedOrder = orderService.getOrder(orderId);

        assertEquals(5, updatedOrder.getQuantity());
        log.info("Order updated: " + updatedOrder);
    }

    @Test
    public void testDeleteOrder() {     
        int orderId = 8; // 삭제할 주문 ID, kakaopayments, payments에서 사용중이면 삭제불가

        orderService.deleteOrder(orderId);

       log.info("Order with ID " + orderId + " deleted.");
    }

    @Test
    public void testGetAllOrders() {
        List<OrderVO> orderList = orderService.getAllOrders();

        assertNotNull(orderList);
        // 주문이 존재하는지 확인
        assertFalse(orderList.isEmpty()); 
        log.info("All orders retrieved: " + orderList);
    }

}