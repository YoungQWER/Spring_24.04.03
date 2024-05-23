package org.zerock.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/*-- 후기 정보 테이블
CREATE TABLE Reviews (
    ReviewID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    ProductID INT,
    Rating INT NOT NULL,
    Comment TEXT,
    ReviewDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);*/


@Data
@AllArgsConstructor
@Builder
//후기 정보 VO 클래스
public class ReviewVO {
 private int reviewID; // 후기 ID
 private int userID; // 사용자 ID
 private int productID; // 제품 ID
 private int rating; // 평점
 private String comment; // 후기 내용
 private Timestamp reviewDate; // 후기 작성 일자
 
 // 생성자, Getter 및 Setter
}
