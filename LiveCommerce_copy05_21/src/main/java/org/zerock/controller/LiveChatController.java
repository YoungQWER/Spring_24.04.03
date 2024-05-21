package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.ChatMessageDTO;
import org.zerock.service.LiveChatService;

@RestController
public class LiveChatController {

//    @Autowired
//    private LiveChatService liveChatService;
//
//    @MessageMapping("/sendMessage")
//    public void saveMessage(@Payload ChatMessageDTO chatMessageDTO) {
//        liveChatService.saveMessage(chatMessageDTO);
//    }
}