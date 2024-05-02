package org.zerock.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

/*-- 결제 정보 테이블
CREATE TABLE Payments (
    PaymentID INT PRIMARY KEY AUTO_INCREMENT,
    OrderID INT,
    PaymentMethod VARCHAR(50) NOT NULL,
    Amount INT NOT NULL,
    PaymentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);*/

@Data
@AllArgsConstructor
public class PaymentVO {
	
	private int paymentID;
	private int orderID;
	private String paymentMethod;
	private int amount;
	private Timestamp paymentDate;
}
