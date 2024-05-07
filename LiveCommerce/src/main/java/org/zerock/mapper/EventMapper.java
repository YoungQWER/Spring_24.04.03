package org.zerock.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.EventVO;

@Mapper
public interface EventMapper {

    void createEvent(EventVO event);

    EventVO getEvent(int eventId);

    void updateEvent(EventVO event);

    void deleteEvent(int eventId);

    List<EventVO> getAllEvents();
}