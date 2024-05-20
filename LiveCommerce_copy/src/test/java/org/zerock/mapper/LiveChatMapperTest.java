package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.LiveChatVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class LiveChatMapperTest {

    @Autowired
    private LiveChatMapper mapper;

    @Test
    public void testGetLiveChat() {
        // 특정 라이브 채팅 정보 조회
        int chatID = 1; // 여기에 존재하는 유효한 ChatID 입력
        LiveChatVO chat = mapper.getLiveChat(chatID);
        assertNotNull(chat);
        log.info("라이브 채팅 정보 조회: " + chat);
    }

    @Test
    public void testGetAllLiveChats() {
        // 모든 라이브 채팅 정보 조회
        List<LiveChatVO> chats = mapper.getAllLiveChats();
        assertNotNull(chats);
        chats.forEach(chat -> log.info(chat));
    }

    @Test
    public void testInsertLiveChat() {
        // 새로운 라이브 채팅 정보 추가
        LiveChatVO newChat = LiveChatVO.builder()
                .userID(1) // 사용자 ID 입력
                .message("테스트 채팅 메시지") // 채팅 메시지 입력
                .build();
        mapper.insertLiveChat(newChat);
        assertNotNull(newChat.getChatID()); // ChatID가 제대로 생성되었는지 확인
        log.info("새로운 라이브 채팅 정보 추가: " + newChat);
    }

    @Test
    public void testUpdateLiveChat() {
        // 기존 라이브 채팅 정보 수정
        int chatID = 1; // 수정할 라이브 채팅의 ID 입력
        LiveChatVO updatedChat = mapper.getLiveChat(chatID);
        updatedChat.setMessage("수정된 채팅 메시지"); // 수정할 내용 입력
        mapper.updateLiveChat(updatedChat);
        log.info("라이브 채팅 정보 수정: " + updatedChat);
    }

    @Test
    public void testDeleteLiveChat() {
        // 특정 라이브 채팅 정보 삭제
        int chatID = 1; // 삭제할 라이브 채팅의 ID 입력
        mapper.deleteLiveChat(chatID);
        log.info("라이브 채팅 정보 삭제: ChatID=" + chatID);
    }
}
