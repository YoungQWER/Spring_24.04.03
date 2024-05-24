package org.zerock.mapper;

import java.util.List;
import org.zerock.domain.OrderVO;

public interface OrderMapper {

    // Create
    public void createOrder(OrderVO order);

    // Read
    public OrderVO getOrder(int orderID);

    public List<OrderVO> getAllOrders();

    // Update
    public void updateOrder(OrderVO order);

    // Delete
    public void deleteOrder(int orderID);
    
    OrderVO getOrderByUserIDAndProductID(int userID, int productID);
}