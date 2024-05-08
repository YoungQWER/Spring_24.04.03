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
       assertNotNull(list);
       list.forEach(stream -> log.info(stream));
    }

    @Test
    public void testGetLiveStream() {
        // 특정 라이브 스트림 가져오기
        int streamId = 1; // 여기에 존재하는 유효한 StreamID 입력
        LiveStreamVO stream = live.getLiveStream(streamId);
        assertNotNull(stream);
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

    @Test
    public void testInsertLiveStream() {
        // 새로운 라이브 스트림 추가
        LiveStreamVO newStream = new LiveStreamVO();
        newStream.setTitle("새로운 라이브 스트림");
        newStream.setUrl("http://example.com/newstream");
        newStream.setDescription("새로운 라이브 스트림 설명");
        live.insertLiveStream(newStream);
        log.info("새로운 라이브 스트림 추가: " + newStream);
    }

    @Test
    public void testUpdateLiveStream() {
        // 기존 라이브 스트림 수정
        int streamId = 1; // 수정할 라이브 스트림의 ID 입력
        LiveStreamVO updatedStream = live.getLiveStream(streamId);
        updatedStream.setTitle("수정된 라이브 스트림 제목");
        updatedStream.setDescription("수정된 라이브 스트림 설명");
        live.updateLiveStream(updatedStream);
        log.info("라이브 스트림 수정: " + updatedStream);
    }

    @Test
    public void testDeleteLiveStream() {
        // 특정 라이브 스트림 삭제
        int streamId = 1; // 삭제할 라이브 스트림의 ID 입력
        live.deleteLiveStream(streamId);
        log.info("라이브 스트림 삭제: StreamID=" + streamId);
    }
}
