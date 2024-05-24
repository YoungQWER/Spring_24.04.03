package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.LiveChatVO;
import org.zerock.domain.UserLiveChatVO;
import org.zerock.mapper.LiveChatMapper;

import java.util.List;

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
    public void registerLiveChat(LiveChatVO liveChat) {
        liveChatMapper.insertLiveChat(liveChat);
    }

    @Override
    public void modifyLiveChat(LiveChatVO liveChat) {
        liveChatMapper.updateLiveChat(liveChat);
    }

    @Override
    public void removeLiveChat(int chatID) {
        liveChatMapper.deleteLiveChat(chatID);
    }
    @Override
    public LiveChatVO saveMessage(LiveChatVO chatVO) {
        liveChatMapper.saveMessage(chatVO);
		return chatVO;
    }
    @Override
    public List<UserLiveChatVO> getChatRecords() {
        return liveChatMapper.getChatRecords();
    }
    @Override
    public String getUsernameByUserId(int userID) {
        return liveChatMapper.getUsernameByUserId(userID);
    }
    @Override
    public List<LiveChatVO> getChatHistoryByUserID(int userID) {
        return liveChatMapper.getChatHistoryByUserID(userID);
    }
    @Override
    public List<LiveChatVO> getChatHistoryByProductID(int productId) {
        // 해당 상품에 대한 채팅 기록을 데이터베이스에서 조회하는 로직을 구현
        // 실제 데이터베이스 조회 등의 로직을 여기에 작성
        return liveChatMapper.getChatHistoryByProductID(productId);
    }
    
}
