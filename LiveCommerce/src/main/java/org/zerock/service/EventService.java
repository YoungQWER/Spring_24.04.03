package org.zerock.service;

import java.util.List;

import org.zerock.domain.EventVO;

public interface EventService {
	
	List<EventVO> getEvents();
    EventVO getEventDetail(int eventId);
}
	