package org.zerock.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.OrderVO;
import org.zerock.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public void createOrder(OrderVO order) {
        orderMapper.createOrder(order);
    }

    @Override
    public OrderVO getOrder(int orderID) {
        return orderMapper.getOrder(orderID);
    }

    @Override
    public void updateOrder(OrderVO order) {
        orderMapper.updateOrder(order);
    }

    @Override
    public void deleteOrder(int orderID) {
        orderMapper.deleteOrder(orderID);
    }

    @Override
    public List<OrderVO> getAllOrders() {
        return orderMapper.getAllOrders();
    }
    
    @Override
    public OrderVO getOrderByUserIDAndProductID(int userID, int productID) {
        return orderMapper.getOrderByUserIDAndProductID(userID, productID);
    }
}