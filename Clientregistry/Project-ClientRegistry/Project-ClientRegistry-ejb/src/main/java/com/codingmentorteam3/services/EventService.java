package com.codingmentorteam3.services;

import com.codingmentorteam3.daos.EventDaoImpl;
import com.codingmentorteam3.entities.Event;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class EventService {

    @Inject
    private EventDaoImpl eventDao;
    
    public void createEvent(Event event) {
        eventDao.create(event);
    }
    
    public Event getEvent(Long eventId){
        return eventDao.read(eventId);
    }
    
    public Event editEvent(Event event){
        return eventDao.update(event);
    }
    
    public Event deleteEvent(Event event) {
        return eventDao.delete(event);
    }
    
    public List<Event> getEventsList(Integer limit, Integer offset) {
        return eventDao.getEventsList(limit, offset);
    }
    
    public List<Event> getEventsListByTitleFilter(String title, Integer limit, Integer offset) {
        return eventDao.getEventsListByTitleFilter(title, limit, offset);
    }
    
}
