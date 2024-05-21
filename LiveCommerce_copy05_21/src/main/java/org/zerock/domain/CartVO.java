package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/*-- 장바구니 테이블
CREATE TABLE ShoppingCart (
    CartID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    ProductID INT,
    Quantity INT,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);
*/


@Data
@AllArgsConstructor
@Builder
//장바구니 VO 클래스
public class CartVO {
 private int cartID; // 장바구니 ID
 private int userID; // 사용자 ID
 private int productID; // 제품 ID
 private int quantity; // 수량
 // 생성자, Getter 및 Setter
}
