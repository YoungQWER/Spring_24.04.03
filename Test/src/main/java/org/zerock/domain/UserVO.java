package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*CREATE TABLE Users (
	    UserID INT PRIMARY KEY AUTO_INCREMENT,
	    Username VARCHAR(20) NOT NULL,
	    Email VARCHAR(50) NOT NULL,
	    Password VARCHAR(50) NOT NULL,
	    ShippingAddress VARCHAR(1000) NOT NULL,
	    ShippingPostalCode VARCHAR(20) NOT NULL
	);
*/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String shippingAddress;
    private String shippingPostalCode;
}
