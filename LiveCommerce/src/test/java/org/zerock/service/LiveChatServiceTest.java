package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.LiveChatVO;
import org.zerock.service.LiveChatService;

import lombok.extern.log4j.Log4j;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class LiveChatServiceTest {

    @Autowired
    private LiveChatService liveChatService;

    @Test
    public void testRegisterAndGetLiveChat() {
        // 채팅 정보 생성
        LiveChatVO liveChat = LiveChatVO.builder()
                .userID(3)
                .message("안녕하세요!")
                .chatTime(new Timestamp(System.currentTimeMillis()))
                .build();

        // 채팅 정보 등록
        liveChatService.registerLiveChat(liveChat);

        // 등록된 채팅 정보 조회
        LiveChatVO retrievedLiveChat = liveChatService.getLiveChat(liveChat.getChatID());
        log.info(liveChat);

    }

    @Test
    public void testGetAllLiveChats() {
        // 모든 채팅 정보 조회
        List<LiveChatVO> liveChats = liveChatService.getAllLiveChats();
        log.info(liveChats);
        
        // 조회된 채팅 정보가 null이 아닌지 확인
        assertNotNull(liveChats);
    }

    @Test
    public void testModifyLiveChat() {
        // 기존 채팅 정보 조회
        int chatID = 1; // 수정할 채팅 정보의 chatID
        LiveChatVO liveChat = liveChatService.getLiveChat(chatID);

        // 수정할 메시지
        String updatedMessage = "안녕하세요! 반가워요!";

        // 새로운 채팅 정보 생성
        LiveChatVO modifiedLiveChat = LiveChatVO.builder()
                .chatID(chatID) // 수정할 채팅 정보의 chatID 지정
                .userID(liveChat.getUserID()) // 기존 userID 유지
                .message(updatedMessage) // 수정된 메시지
                .chatTime(liveChat.getChatTime()) // 채팅 시간은 유지하거나 변경 가능
                .build();

        // 채팅 정보 수정
        liveChatService.modifyLiveChat(modifiedLiveChat);

        // 수정된 채팅 정보 조회
        LiveChatVO retrievedModifiedLiveChat = liveChatService.getLiveChat(chatID);
        log.info(retrievedModifiedLiveChat);
    }



    @Test
    public void testRemoveLiveChat() {
        // 삭제할 채팅 정보의 chatID
        int chatIDToRemove = 1;

        // 채팅 정보 삭제
        liveChatService.removeLiveChat(chatIDToRemove);

        // 삭제된 채팅 정보 조회
        LiveChatVO removedLiveChat = liveChatService.getLiveChat(chatIDToRemove);

    }
}
