package org.zerock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Timestamp;
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
public class LiveStreamServiceTest {

    @Autowired
    private LiveStreamService service;

    @Test
    public void testGetAllLiveStreams() {
        List<LiveStreamVO> streams = service.getAllLiveStreams();
        assertNotNull(streams);
        log.info("모든 라이브 스트림: " + streams);
    }

    @Test
    public void testGetLiveStream() {
        int streamID = 1; // 기존의 streamID로 변경
        LiveStreamVO stream = service.getLiveStream(streamID);
        assertNotNull(stream);
        log.info("라이브 스트림: " + stream);
    }

    @Test
    public void testRegisterLiveStream() {
        LiveStreamVO newStream = LiveStreamVO.builder()
                .title("샘플 스트림")
                .url("https://samplestream.com")
                .description("샘플 설명")
                .startTime(new Timestamp(System.currentTimeMillis()))
                .endTime(new Timestamp(System.currentTimeMillis()))
                .photo("apple.p")
                .productID(1)
                .chatID(1)
                .build();
        service.registerLiveStream(newStream);
        assertNotNull(newStream.getStreamID());
        log.info("새로운 라이브 스트림 추가: " + newStream);
    }

    @Test
    public void testModifyLiveStream() {
        int streamID = 1; // 수정할 라이브 스트림의 ID로 변경
        LiveStreamVO stream = service.getLiveStream(streamID);
        assertNotNull(stream);
        
        String updatedDescription = "수정된 설명";
        stream.setDescription(updatedDescription);
        
        service.modifyLiveStream(stream);
        
        LiveStreamVO modifiedStream = service.getLiveStream(streamID);
        assertEquals(updatedDescription, modifiedStream.getDescription());
        log.info("수정된 라이브 스트림: " + modifiedStream);
    }

    @Test
    public void testRemoveLiveStream() {
        int streamID = 1; // 삭제할 라이브 스트림의 ID로 변경
        service.removeLiveStream(streamID);
        LiveStreamVO removedStream = service.getLiveStream(streamID);
        assertNull(removedStream);
        log.info("라이브 스트림 삭제 완료: streamID=" + streamID);
    }
}