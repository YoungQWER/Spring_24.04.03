package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.LiveStreamVO;
import org.zerock.mapper.LiveStreamMapper;

@Service
public class LiveStreamServiceImpl implements LiveStreamService {

    @Autowired
    private LiveStreamMapper mapper;

    @Override
    public List<LiveStreamVO> getAllLiveStreams() {
        return mapper.getLiveStreams();
    }

    @Override
    public LiveStreamVO getLiveStream(int streamID) {
        return mapper.getLiveStream(streamID);
    }

    @Override
    public void registerLiveStream(LiveStreamVO liveStream) {
        mapper.insertLiveStream(liveStream);
    }

    @Override
    public void modifyLiveStream(LiveStreamVO liveStream) {
        mapper.updateLiveStream(liveStream);
    }

    @Override
    public void removeLiveStream(int streamID) {
        mapper.deleteLiveStream(streamID);
    }
}