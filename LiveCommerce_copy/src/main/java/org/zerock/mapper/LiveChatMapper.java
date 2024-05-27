package org.zerock.mapper;

import java.util.List;
import org.zerock.domain.LiveChatVO;
import org.zerock.domain.UserLiveChatVO;

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
    
    
    List<UserLiveChatVO> getChatRecords();
    
    String getUsernameByUserId(int userID);

	List<LiveChatVO> getChatHistoryByUserID(int userID);

	void saveMessage(LiveChatVO chatVO);
    // 특정 상품에 대한 채팅 기록을 가져오는 메서드
    List<LiveChatVO> getChatHistoryByProductID(int productID);
}
