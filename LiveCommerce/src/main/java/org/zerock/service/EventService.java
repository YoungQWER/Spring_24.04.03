package org.zerock.service;

import java.util.List;

import org.zerock.domain.EventVO;

public interface EventService {

    void createEvent(EventVO event);

    EventVO getEvent(int eventID);

    List<EventVO> getAllEvents();

    void updateEvent(EventVO event);

    void deleteEvent(int eventID);
}