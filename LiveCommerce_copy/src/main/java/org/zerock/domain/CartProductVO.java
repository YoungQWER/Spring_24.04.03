package org.zerock.domain;

import lombok.Data;

@Data
public class CartProductVO {

	 private int cartID; // 장바구니 ID
	 private int userID; // 사용자 ID
	 private int productID; // 제품 ID
	 private int quantity; // 수량
	 
	 private String productName; // 제품명
	 private int price; // 제품 가격
}
