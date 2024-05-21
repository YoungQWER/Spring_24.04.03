package org.zerock.domain;

import java.sql.Timestamp;

public class UserLiveChatVO {
    
    private int chatID; // 채팅 ID
    private String username; // 사용자 이름
    private String message; // 채팅 메시지
    private Timestamp chatTime; // 채팅 시간
    
    // 생성자, Getter 및 Setter
    public UserLiveChatVO() {}

    public UserLiveChatVO(int chatID, String username, String message, Timestamp chatTime) {
        this.chatID = chatID;
        this.username = username;
        this.message = message;
        this.chatTime = chatTime;
    }

    public int getChatID() {
        return chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getChatTime() {
        return chatTime;
    }

    public void setChatTime(Timestamp chatTime) {
        this.chatTime = chatTime;
    }
}
