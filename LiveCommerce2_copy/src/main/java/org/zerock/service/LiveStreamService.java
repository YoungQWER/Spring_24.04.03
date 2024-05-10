package org.zerock.service;

import java.util.List;

import org.zerock.domain.LiveStreamVO;

public interface LiveStreamService {
    
    List<LiveStreamVO> getAllLiveStreams(); // 모든 라이브 스트림 가져오기
    
    LiveStreamVO getLiveStream(int streamID); // 특정 라이브 스트림 가져오기
    
    void registerLiveStream(LiveStreamVO liveStream); // 새로운 라이브 스트림 추가
    
    void modifyLiveStream(LiveStreamVO liveStream); // 라이브 스트림 수정
    
    void removeLiveStream(int streamID); // 라이브 스트림 삭제
}