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
public class KakaoPaymentVO {

	private int paymentID;
	private int orderID;
	private String paymentMethod;
	private int amount;
	private String kakaoTransactionID;
	private String status;
	private Timestamp paymentDate;
	
}
