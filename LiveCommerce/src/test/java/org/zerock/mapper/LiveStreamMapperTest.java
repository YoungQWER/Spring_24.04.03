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
    private LiveStreamMapper mapper;

    @Test
    public void testGetLiveStreams() {
        // 모든 라이브 스트림 리스트 가져오기 테스트
        List<LiveStreamVO> list = mapper.getLiveStreams();
        assertNotNull(list);
        list.forEach(stream -> log.info(stream));
    }

    @Test
    public void testGetLiveStream() {
        // 특정 라이브 스트림 가져오기 테스트
        int streamID = 2; // 존재하는 유효한 StreamID 입력
        LiveStreamVO stream = mapper.getLiveStream(streamID);
        assertNotNull(stream);
        log.info("특정 라이브 스트림: " + stream);
    }

    @Test
    public void testInsertLiveStream() {
        // 새로운 라이브 스트림 추가 테스트
        LiveStreamVO newStream = LiveStreamVO.builder()
                .title("새로운 라이브 스트림")
                .url("http://example.com/newstream")
                .description("새로운 라이브 스트림 설명")
                .startTime(new java.sql.Timestamp(System.currentTimeMillis()))
                .build();
        mapper.insertLiveStream(newStream);
        assertNotNull(newStream.getStreamID());
        log.info("새로운 라이브 스트림 추가: " + newStream);
    }

    @Test
    public void testUpdateLiveStream() {
        // 기존 라이브 스트림 수정 테스트
        int streamID = 2; // 수정할 라이브 스트림의 ID 입력
        LiveStreamVO updatedStream = mapper.getLiveStream(streamID);
        updatedStream.setTitle("수정된 라이브 스트림 제목");
        updatedStream.setDescription("수정된 라이브 스트림 설명");
        mapper.updateLiveStream(updatedStream);
        log.info("라이브 스트림 수정: " + updatedStream);
    }

    @Test
    public void testDeleteLiveStream() {
        // 특정 라이브 스트림 삭제 테스트
        int streamID = 1; // 삭제할 라이브 스트림의 ID 입력
        mapper.deleteLiveStream(streamID);
        log.info("라이브 스트림 삭제: StreamID=" + streamID);
    }
}
