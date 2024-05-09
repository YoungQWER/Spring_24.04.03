package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.LiveChatVO;
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
}
