package org.zerock.mapper;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.EventVO;

import lombok.extern.log4j.Log4j;
import net.bytebuddy.asm.Advice.Local;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class EventMapperTests {
    
    @Autowired
    private EventMapper eventMapper;
    
   

    @Test  //C
    public void testCreateEvent() {
        // 테스트용 이벤트 데이터 생성
        EventVO event = EventVO.builder()
                        .eventName("이벤트")
                        .description("이것은 이벤트")
                        .eventDate(new Timestamp(System.currentTimeMillis()))
                        .location("워싱턴")
                        .photo("백악관")
                        .build();

        // 이벤트 생성 쿼리 실행
        eventMapper.createEvent(event);
        
    }
    @Test //R
    public void testGetEvent() {
    	
    	EventVO ev = eventMapper.getEvent(2);
    	log.info(ev);
    }
    @Test //U
    public void testUpdateEvent() {
    	
    	EventVO ev = EventVO.builder()
    			.eventName("오픈1주년")
    			.description("수정된 묘사")
    			.eventDate(new Timestamp(System.currentTimeMillis()))
    			.location("수정된 위치")
    			.photo("수정된 사진")
    			.eventID(1)
    			.productID(1)
    			.build();
    	
    	eventMapper.updateEvent(ev);
    	log.info(ev);
    }
    @Test //D
    public void testDelete() {
    	eventMapper.deleteEvent(2);
    }
    @Test  //전체 리스트 조회
    public void testGetEventList() {
    	List<EventVO> list = eventMapper.getAllEvents();
    	log.info(list);
    }
}
