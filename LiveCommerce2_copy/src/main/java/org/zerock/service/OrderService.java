package org.zerock.service;

import java.util.List;
import org.zerock.domain.OrderVO;

public interface OrderService {

    void createOrder(OrderVO order);

    OrderVO getOrder(int orderID);

    void updateOrder(OrderVO order);

    void deleteOrder(int orderID);

    List<OrderVO> getAllOrders();
}