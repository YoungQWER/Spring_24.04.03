package org.zerock.mapper;

import java.util.List;
import org.zerock.domain.LiveStreamVO;

public interface LiveStreamMapper {

    List<LiveStreamVO> getLiveStreams();

    LiveStreamVO getLiveStream(int streamId);

    void startLiveStream(int streamId);

    void endLiveStream(int streamId);

    void insertLiveStream(LiveStreamVO liveStream);

    void updateLiveStream(LiveStreamVO liveStream);

    void deleteLiveStream(int streamId);
}
