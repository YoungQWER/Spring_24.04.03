package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/*-- 카테고리 정보 테이블
CREATE TABLE Categories (
    CategoryID INT PRIMARY KEY AUTO_INCREMENT,
    CategoryName VARCHAR(50) NOT NULL
);*/

@Data
@AllArgsConstructor
@Builder
public class CategoryVO {
	
	private int categoryID;
	private String categoryName;
}
