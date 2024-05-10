package org.zerock.mapper;

import java.util.List;
import org.zerock.domain.LiveChatVO;

public interface LiveChatMapper {

    // 라이브 채팅 정보 조회
    LiveChatVO getLiveChat(int chatID);

    // 모든 라이브 채팅 정보 조회
    List<LiveChatVO> getAllLiveChats();

    // 라이브 채팅 정보 추가
    void insertLiveChat(LiveChatVO liveChat);

    // 라이브 채팅 정보 수정
    void updateLiveChat(LiveChatVO liveChat);

    // 라이브 채팅 정보 삭제
    void deleteLiveChat(int chatID);
}
