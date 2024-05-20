package org.zerock.service;

import org.zerock.domain.LiveChatVO;

import java.util.List;

public interface LiveChatService {

    // 라이브 채팅 정보 조회
    LiveChatVO getLiveChat(int chatID);

    // 모든 라이브 채팅 정보 조회
    List<LiveChatVO> getAllLiveChats();

    // 라이브 채팅 정보 추가
    void registerLiveChat(LiveChatVO liveChat);

    // 라이브 채팅 정보 수정
    void modifyLiveChat(LiveChatVO liveChat);

    // 라이브 채팅 정보 삭제
    void removeLiveChat(int chatID);
}
