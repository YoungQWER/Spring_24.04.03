package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/*-- 제품 정보 테이블
CREATE TABLE Products (
    ProductID INT PRIMARY KEY AUTO_INCREMENT,
    ProductName VARCHAR(50) NOT NULL,
    Description TEXT,
    Price INT NOT NULL,
    Photo VARCHAR(255) -- 사진의 경로를 저장할 열
);
*/

@Data
@AllArgsConstructor
public class ProductVO {
	
	private int productID;
	private String productName;
	private String description;
	private int price;
	private int photo;
}
