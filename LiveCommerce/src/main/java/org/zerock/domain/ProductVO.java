package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//제품 정보 VO 클래스
public class ProductVO {
 private int productId; // 제품 ID
 private String productName; // 제품명
 private String description; // 제품 설명
 private int price; // 제품 가격
 private String photo; // 제품 사진 경로
 
 // 생성자, Getter 및 Setter
}
