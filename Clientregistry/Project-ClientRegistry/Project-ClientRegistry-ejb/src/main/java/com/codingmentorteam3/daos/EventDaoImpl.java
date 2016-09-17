package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Note;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.EmptyListException;
import com.codingmentorteam3.exceptions.query.NoMatchForFilterException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class EventDaoImpl extends AbstractDao<Event> {

    public EventDaoImpl() {
        super(Event.class);
    }

    public List<Event> getEventsListByTitleFilter(String title, int limit, int offset) {
        if (null != title) {
            TypedQuery<Event> query = em.createNamedQuery("event.by.title.filter", Event.class).setParameter("title", "%" + title + "%");
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.getResultList();
        }
        return em.createNamedQuery("event.list", Event.class).getResultList();
    }

    public List<Event> getEventsListByTypeFilter(String type) {
        if (null != type) {
            List<Event> query = em.createNamedQuery("event.by.type.filter", Event.class).setParameter("type", "%" + type + "%").getResultList();
            if (query.isEmpty()) {
                throw new NoMatchForFilterException("The results can not be found with this parameter: " + type);
            }
            return query;
        }
        return em.createNamedQuery("event.list", Event.class).getResultList();
    }

    public List<Event> getEventsList(int limit, int offset) {
        TypedQuery<Event> query = em.createNamedQuery("event.list", Event.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<User> getUsersListByEventId(Long eventId) {
        Event current = read(eventId);
        if (null != current) {
            List<User> query = em.createNamedQuery("event.list.users.by.id", User.class).setParameter("id", eventId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("There are no users connected to this event : " + current.getTitle());
            }
            return query;
        }
        throw new BadRequestException("We haven't got this event in database.");
    }

    public List<Note> getNotesListByEventId(Long eventId) {
        Event current = read(eventId);
        if (null != current) {
            List<Note> query = em.createNamedQuery("event.list.notes.by.id", Note.class).setParameter("id", eventId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("There are no notes connected to this event : " + current.getTitle());
            }
            return query;
        }
        throw new BadRequestException("We haven't got this event in database.");
    }

}
