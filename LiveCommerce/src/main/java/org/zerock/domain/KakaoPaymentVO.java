package org.zerock.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/*-- 카카오톡 결제 정보 테이블
CREATE TABLE KakaoPayments (
    PaymentID INT PRIMARY KEY AUTO_INCREMENT,
    OrderID INT,
    PaymentMethod VARCHAR(50) NOT NULL,
    Amount INT NOT NULL,
    PaymentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    KakaoTransactionID VARCHAR(100) NOT NULL,
    Status VARCHAR(50),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);*/

@Data
@AllArgsConstructor
@Builder
//카카오톡 결제 정보 VO 클래스
public class KakaoPaymentVO {
 private int paymentID; // 결제 ID
 private int orderID; // 주문 ID
 private String paymentMethod; // 결제 수단
 private int amount; // 결제 금액
 private Timestamp paymentDate; // 결제 일자
 private String kakaoTransactionID; // 카카오 거래 ID
 private String status; // 결제 상태
 
 // 생성자, Getter 및 Setter
}
