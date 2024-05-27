package org.zerock.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.zerock.domain.LiveChatVO;
import org.zerock.service.LiveChatService;

@Controller
public class LiveChatWebSocketHandler {
    
    private final LiveChatService liveChatService;

    @Autowired
    public LiveChatWebSocketHandler(LiveChatService liveChatService) {
        this.liveChatService = liveChatService;
    }

    @MessageMapping("/live.chat")
    @SendTo("/topic/messages")
    public LiveChatVO sendMessage(LiveChatVO chatVO) {
        // 받은 메시지를 DB에 저장하고, 저장된 메시지를 다시 클라이언트에게 전송합니다.
        return liveChatService.saveMessage(chatVO);
    }
}
