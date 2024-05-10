package org.zerock.service;

import java.sql.Timestamp;

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
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Test
    public void testCreateEvent() {
        EventVO event = EventVO.builder()
                .eventName("Spring Event")
                .description("Test event for Spring framework")
                .eventDate(new Timestamp(System.currentTimeMillis()))
                .location("Virtual")
                .photo("spring_event_photo.jpg")
                .productID(1)
                .build();

        eventService.createEvent(event);
        
        log.info("Created Event: " + event);
    }

    @Test
    public void testGetEvent() {
        int eventID = 9; // 가져올 이벤트의 ID를 설정하세요.

        EventVO retrievedEvent = eventService.getEvent(eventID);

        log.info("Retrieved Event: " + retrievedEvent);
    }

    @Test
    public void testGetAllEvents() {
        log.info("All Events:");
        eventService.getAllEvents().forEach(event -> log.info(event));
    }

    @Test
    public void testUpdateEvent() {
        EventVO event = EventVO.builder()
                .eventID(9) // 업데이트할 이벤트의 ID를 설정하세요.
                .eventName("Updated Spring Event")
                .description("Updated test event for Spring framework")
                .eventDate(new Timestamp(System.currentTimeMillis()))
                .location("Virtual")
                .photo("updated_spring_event_photo.jpg")
                .productID(1)
                .build();

        eventService.updateEvent(event);

        log.info("Updated Event: " + event);
    }

    @Test
    public void testDeleteEvent() {
        int eventID = 9; // 삭제할 이벤트의 ID를 설정하세요.

        eventService.deleteEvent(eventID);

        log.info("Deleted Event with ID: " + eventID);
    }
}