package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.LiveStreamVO;

public interface LiveStreamMapper {
    
    // 모든 라이브 스트림 가져오기
    public List<LiveStreamVO> getLiveStreams();
    
    // 특정 라이브 스트림 가져오기
    public LiveStreamVO getLiveStream(int streamID);
    
    // 새로운 라이브 스트림 추가
    public void insertLiveStream(LiveStreamVO liveStream);
    
    // 라이브 스트림 수정
    public void updateLiveStream(LiveStreamVO liveStream);
    
    // 라이브 스트림 삭제
    public void deleteLiveStream(int streamID);
}
