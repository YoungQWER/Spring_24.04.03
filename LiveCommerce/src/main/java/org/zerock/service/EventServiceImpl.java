package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.EventVO;
import org.zerock.mapper.EventMapper;
import org.zerock.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{
   
   private final EventMapper eventMapper;

   @Override
   public void createEvent(EventVO event) {
      eventMapper.createEvent(event);
      
   }

   @Override
   public EventVO getEvent(int eventID) {
      
      return eventMapper.getEvent(eventID);
   }

   @Override
   public List<EventVO> getAllEvents() {
      
      return eventMapper.getAllEvents();
   }

   @Override
   public void updateEvent(EventVO event) {
      
      eventMapper.updateEvent(event);
   }

   @Override
   public void deleteEvent(int eventID) {
      eventMapper.deleteEvent(eventID);
      
   }


}