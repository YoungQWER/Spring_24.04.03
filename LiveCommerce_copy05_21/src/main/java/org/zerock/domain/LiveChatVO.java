package org.zerock.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LiveChatVO {
    private int chatID; // 채팅 ID
    private int userID; // 사용자 ID
    private String message; // 채팅 메시지
    private Timestamp chatTime; // 채팅 시간

    // 생성자 추가
    public LiveChatVO(int userID, String message) {
        this.userID = userID;
        this.message = message;
        // chatTime은 DB에서 자동으로 설정되므로 여기에서는 설정하지 않음
    }
}
