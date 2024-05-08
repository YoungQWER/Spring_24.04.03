package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.log4j.Log4j;
import org.zerock.domain.OrderVO;
import org.zerock.domain.ProductVO;
import org.zerock.domain.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testInsertOrder() {
        // 사용자 정보 삽입
        UserVO user = UserVO.builder()
                .username("John Doe")
                .email("john@example.com")
                .password("password")
                .shippingAddress("123 Main St, Anytown, USA")
                .shippingPostalCode("12345")
                .build();
        userMapper.registerUser(user);

        // 사용자 정보와 제품 정보 가져오기
        user = userMapper.selectUserByEmail("john@example.com");
        ProductVO product = ProductVO.builder()
                .productName("Test Product")
                .description("Test Description")
                .price(10000)
                .photo("test_photo.jpg")
                .build();
        productMapper.insertProduct(product);

        // 주문 정보 삽입
        OrderVO order = OrderVO.builder()
                .userID(user.getUserID())
                .productID(product.getProductID())
                .quantity(1)
                .shippingAddress(user.getShippingAddress())
                .shippingPostalCode(user.getShippingPostalCode())
                .build();

        orderMapper.insertOrder(order);

        assertNotNull(order.getOrderID()); // 주문 ID가 생성되었는지 확인
    }

    @Test
    public void testSelectOrder() {
        List<OrderVO> orderList = orderMapper.selectAllOrders();

        assertNotNull(orderList);
        assertTrue(orderList.size() > 0);
    }

    @Test
    public void testUpdateOrder() {
        // 수정할 주문 정보 가져오기
        OrderVO order = orderMapper.selectOrder(1);
        assertNotNull(order);

        // 주문 정보 수정
        order.setQuantity(2);

        // 주문 정보 업데이트
        orderMapper.updateOrder(order);

        // 수정된 주문 정보 가져오기
        OrderVO updatedOrder = orderMapper.selectOrder(1);
        assertNotNull(updatedOrder);
        assertEquals(2, updatedOrder.getQuantity());
    }

    @Test
    public void testDeleteOrder() {
        // 삭제할 주문 정보 가져오기
        OrderVO order = orderMapper.selectOrder(1);
        assertNotNull(order);

        // 주문 정보 삭제
        orderMapper.deleteOrder(1);

        // 삭제된 주문 정보 가져오기
        OrderVO deletedOrder = orderMapper.selectOrder(1);
        assertNull(deletedOrder);
    }

}
