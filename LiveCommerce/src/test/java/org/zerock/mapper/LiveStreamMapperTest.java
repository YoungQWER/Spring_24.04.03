package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.LiveStreamVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class LiveStreamMapperTest {
    
    @Autowired
    LiveStreamMapper live;

    @Test
    public void testGetLiveStreams() {
       // 모든 라이브 스트림 리스트 가져오기
       List<LiveStreamVO> list = live.getLiveStreams();
       log.info(list);
    }

    @Test
    public void testGetLiveStream() {
        // 특정 라이브 스트림 가져오기
        int streamId = 1; // 여기에 존재하는 유효한 StreamID 입력
        LiveStreamVO stream = live.getLiveStream(streamId);
        log.info("특정 라이브 스트림: " + stream);
    }
    
    @Test
    public void testStartLiveStream() {
        // 특정 라이브 스트림 시작하기
        int streamId = 1; // 여기에 존재하는 유효한 StreamID 입력
        live.startLiveStream(streamId);
        log.info("라이브 스트림 시작: StreamID=" + streamId);
    }

    @Test
    public void testEndLiveStream() {
        // 특정 라이브 스트림 종료하기
        int streamId = 1; // 여기에 존재하는 유효한 StreamID 입력
        live.endLiveStream(streamId);
        log.info("라이브 스트림 종료: StreamID=" + streamId);
    }
}