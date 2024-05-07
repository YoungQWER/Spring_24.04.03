package org.zerock.service;

import java.util.List;

import org.zerock.domain.LiveStreamVO;

public interface LiveStreamService {

	List<LiveStreamVO> getLiveStreams();
    void startLiveStream(int streamId);
    void endLiveStream(int streamId);
}
