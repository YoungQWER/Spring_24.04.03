package org.zerock.handler;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.zerock.domain.LiveChatVO;
import org.zerock.service.LiveChatService;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    private final LiveChatService chatService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ChatWebSocketHandler(LiveChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);

        // 기존 채팅 메시지 로드
        List<LiveChatVO> chatMessages = chatService.getAllLiveChats();
        for (LiveChatVO chatMessage : chatMessages) {
            String chatMessageJson = objectMapper.writeValueAsString(chatMessage);
            session.sendMessage(new TextMessage(chatMessageJson));
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            // 메시지를 파싱하여 VO 객체로 변환
            LiveChatVO chatMessage = objectMapper.readValue(message.getPayload(), LiveChatVO.class);
            
            // 사용자 ID와 메시지 추출
            String userID = String.valueOf(chatMessage.getUserID());
            String messageContent = chatMessage.getMessage();
            
            // 메시지를 데이터베이스에 저장
            chatService.registerLiveChat(userID, messageContent);
            
            // 모든 세션에 메시지 전송
            for (WebSocketSession sess : sessions) {
                // 클라이언트가 보낸 메시지를 보낸 클라이언트에게도 전송
                if (sess.equals(session)) {
                    sess.sendMessage(new TextMessage("나: " + messageContent));
                } else {
                    sess.sendMessage(new TextMessage(messageContent));
                }
            }
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            // 필요에 따라 클라이언트에게 오류 메시지를 전송할 수 있음
        }
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}
