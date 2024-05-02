package org.zerock.domain;

import lombok.AllArgsConstructor;
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
public class CartVO {

	private int cartID;
	private int	userID;
	private int productID;
	private int quantity;
}
