package org.zerock.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
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
public class ReviewVO {
	
	private int reviewID;
	private int userID;
	private int productID;
	private int rating;
	private String comment;
	private Timestamp reviewDate;
	
}
