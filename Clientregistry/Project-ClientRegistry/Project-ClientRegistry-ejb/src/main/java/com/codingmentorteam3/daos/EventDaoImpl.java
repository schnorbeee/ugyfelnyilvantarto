package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Note;
import com.codingmentorteam3.entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class EventDaoImpl extends AbstractDao<Event> {

    public EventDaoImpl() {
        super(Event.class);
    }

    public List<Event> getEventsListByTitleFilter(String title) {
        try {
            List<Event> query = em.createNamedQuery("event.by.title.filter", Event.class).setParameter("title", title).getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Event> getEventsListByTypeFilter(String type) {
        try {
            List<Event> query = em.createNamedQuery("event.by.type.filter", Event.class).setParameter("type", type).getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Event> getEventsList() {
        try {
            List<Event> query = em.createNamedQuery("event.list", Event.class).getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> getUsersListByEventId(Long eventId) {
        Event current = read(eventId);
        if (null != current) {
            try {
                List<User> query = em.createNamedQuery("event.list.users.by.id", User.class).setParameter("id", eventId).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException("We haven't got this event in datebase.");
    }

    public List<Note> getNotesListByEventId(Long eventId) {
        Event current = read(eventId);
        if (null != current) {
            try {
                List<Note> query = em.createNamedQuery("event.list.notes.by.id", Note.class).setParameter("id", eventId).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException("We haven't got this event in datebase.");
    }

}
