package org.zerock.service;

import java.util.List;

import org.zerock.domain.LiveChatVO;

public interface LiveStreamService {

	List<LiveChatVO> getLiveStreams();
    void startLiveStream(int streamId);
    void endLiveStream(int streamId);
}