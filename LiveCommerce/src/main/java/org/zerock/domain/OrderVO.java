package org.zerock.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/*-- 주문 정보 테이블
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    ProductID INT,
    Quantity INT,
    OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);*/

@Data
@AllArgsConstructor
@Builder
//주문 정보 VO 클래스
public class OrderVO {
 private int orderID; // 주문 ID
 private int userID; // 주문한 사용자의 ID
 private int productID; // 주문한 제품의 ID
 private int quantity; // 주문 수량
 private String shippingAddress; // 배송 주소
 private String shippingPostalCode; // 배송 우편번호
 private Timestamp orderDate; // 주문 일자
 
 // 생성자, Getter 및 Setter
}
