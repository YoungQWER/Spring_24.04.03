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
//카테고리 정보 VO 클래스
public class CategoryVO {
 private int categoryID; // 카테고리 ID
 private String categoryName; // 카테고리명
 
 // 생성자, Getter 및 Setter
}
