package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.LiveChatVO;
import org.zerock.mapper.LiveChatMapper;

@Service
public class LiveChatServiceImpl implements LiveChatService {

    private final LiveChatMapper liveChatMapper;

    @Autowired
    public LiveChatServiceImpl(LiveChatMapper liveChatMapper) {
        this.liveChatMapper = liveChatMapper;
    }

    @Override
    public LiveChatVO getLiveChat(int chatID) {
        return liveChatMapper.getLiveChat(chatID);
    }

    @Override
    public List<LiveChatVO> getAllLiveChats() {
        return liveChatMapper.getAllLiveChats();
    }

    @Override
    public void registerLiveChat(String userID, String message) {
        // 데이터베이스에 채팅 내용을 저장하는 로직
        LiveChatVO chatMessage = new LiveChatVO(Integer.parseInt(userID), message);
        liveChatMapper.insertLiveChat(chatMessage);
    }

    @Override
    public void modifyLiveChat(LiveChatVO liveChat) {
        liveChatMapper.updateLiveChat(liveChat);
    }

    @Override
    public void removeLiveChat(int chatID) {
        liveChatMapper.deleteLiveChat(chatID);
    }

}
	