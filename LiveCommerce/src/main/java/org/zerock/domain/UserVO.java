package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/*-- 사용자 정보 테이블
CREATE TABLE Users (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(20) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    Password VARCHAR(50) NOT NULL,
    ShippingAddress VARCHAR(1000) NOT NULL,
    ShippingPostalCode VARCHAR(20) NOT NULL
);*/

@Data
@AllArgsConstructor
@Builder
public class UserVO {

	private int UserID;  //사용자 번호
	private String username;
	private String email;
	private String password;
	private String shippingAddress;
	private String shippingPostalCode;
	
}
