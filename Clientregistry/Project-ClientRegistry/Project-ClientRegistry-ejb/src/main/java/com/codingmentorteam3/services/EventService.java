package com.codingmentorteam3.services;

import com.codingmentorteam3.daos.EventDaoImpl;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Note;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.FeedbackType;
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

    public Event getEvent(Long eventId) {
        return eventDao.read(eventId);
    }

    public Event editEvent(Event event) {
        return eventDao.update(event);
    }

    public Event deleteEvent(Event event) {
        return eventDao.delete(event);
    }

    public List<Event> getEventsList(Integer limit, Integer offset) {
        return eventDao.getEventsList(limit, offset);
    }

    public List<Note> getNotesListByEventId(Long eventId) {
        return eventDao.getNotesListByEventId(eventId);
    }

    public List<User> getUsersListByEventIdAndFeedbackIsAccepted(Long eventId, FeedbackType feedback, Integer limit, Integer offset) {
        return eventDao.getUsersListByEventIdAndFeedbackIsAccepted(eventId, feedback, limit, offset);
    }

    public List<Event> getEventsListWithoutLimit() {
        return eventDao.getEventsListWithoutLimit();
    }

    public List<Event> getEventsListByStringFilter(String title, Integer limit, Integer offset) {
        return eventDao.getEventsListByStringFilter(title, limit, offset);
    }

    public List<Event> getEventsListByTypeFilter(String type, Integer limit, Integer offset) {
        return eventDao.getEventsListByTypeFilter(type, limit, offset);
    }

}
