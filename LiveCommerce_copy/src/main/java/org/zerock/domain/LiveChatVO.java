package org.zerock.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
//라이브 채팅 VO 클래스
public class LiveChatVO {
 private int chatID; // 채팅 ID
 private int userID; // 사용자 ID
 private String message; // 채팅 메시지
 private Timestamp chatTime; // 채팅 시간
 
 // 생성자, Getter 및 Setter
}
