package org.zerock.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

/*-- 라이브 채팅 테이블
CREATE TABLE LiveChat (
    ChatID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    Message TEXT,
    ChatTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);*/

@Data
@AllArgsConstructor
public class LiveChatVO {

	private int chatID;
	private int userID;
	private String message;
	private Timestamp chatTime;
}
