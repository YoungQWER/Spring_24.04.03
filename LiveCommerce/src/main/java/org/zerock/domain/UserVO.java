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
    private int userID; // 사용자 ID
    private String username; // 사용자 이름
    private String email; // 사용자 이메일
    private String password; // 사용자 비밀번호
    private String shippingAddress; // 배송 주소
    private String shippingPostalCode; // 배송 우편번호
    
    // 생성자, Getter 및 Setter
}
