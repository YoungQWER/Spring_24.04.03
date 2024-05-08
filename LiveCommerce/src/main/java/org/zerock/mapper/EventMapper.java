package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.EventVO;

public interface EventMapper {

    // Create
    public void createEvent(EventVO event);

    // Read
    public EventVO getEvent(int eventID);

    public List<EventVO> getAllEvents();

    // Update
    public void updateEvent(EventVO event);

    // Delete
    public void deleteEvent(int eventID);
}