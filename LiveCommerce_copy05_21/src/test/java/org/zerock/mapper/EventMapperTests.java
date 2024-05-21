package org.zerock.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.EventVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class EventMapperTests {

    @Autowired
    private EventMapper eventMapper;

    @Test // C
    public void testC() {
        EventVO event = EventVO.builder()
                .eventName("Spring Event")
                .description("Test event for Spring framework")
                .eventDate(new Timestamp(System.currentTimeMillis()))
                .location("Virtual")
                .photo("spring_event_photo.jpg")
                .productID(1)
                .build();

        log.info("Before insert: " + event);

        eventMapper.createEvent(event);

        log.info("After insert: " + event);
    }

    @Test // R
    public void testR() {
        EventVO event = eventMapper.getEvent(8);
        log.info(event);
    }

    @Test // U
    public void testU() {
        EventVO event = EventVO.builder()
                .eventID(8)
                .eventName("Updated Spring Event")
                .description("Updated test event for Spring framework")
                .eventDate(new Timestamp(System.currentTimeMillis()))
                .location("Virtual")
                .photo("updated_spring_event_photo.jpg")
                .productID(1)
                .build();

        log.info("Before update: " + event);

        eventMapper.updateEvent(event);

        log.info("After update: " + event);
    }

    @Test // D
    public void testD() {
        eventMapper.deleteEvent(7);
    }

    @Test // 전체 리스트 조회
    public void testGetAllEvents() {
        List<EventVO> events = eventMapper.getAllEvents();
        for (EventVO event : events) {
            log.info(event);
        }
    }
}