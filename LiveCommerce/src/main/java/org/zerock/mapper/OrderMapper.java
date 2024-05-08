package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.OrderVO;

@Mapper
public interface OrderMapper {

    // 주문 등록
    void insertOrder(OrderVO order);
    
    // 주문 조회
    OrderVO selectOrder(int orderID);
    
    // 모든 주문 조회
    List<OrderVO> selectAllOrders();
    
    // 주문 수정
    void updateOrder(OrderVO order);
    
    // 주문 삭제
    void deleteOrder(int orderID);
}
