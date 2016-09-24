package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Note;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.FeedbackType;
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

    public List<Event> getEventsListByStringFilter(String title, int limit, int offset) {
        if (null != title) {
            TypedQuery<Event> query = em.createNamedQuery("event.by.string.filter", Event.class).setParameter("title", "%" + title + "%");
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.getResultList();
        }
        TypedQuery<Event> query = em.createNamedQuery("event.list", Event.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<Event> getEventsListByTypeFilter(String type, int limit, int offset) {
        if (null != type) {
            TypedQuery<Event> query = em.createNamedQuery("event.by.type.filter", Event.class);
            query.setParameter("type", "%" + type + "%");
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.getResultList();
        }
        TypedQuery<Event> query = em.createNamedQuery("event.list", Event.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<Event> getEventsList(int limit, int offset) {
        TypedQuery<Event> query = em.createNamedQuery("event.list", Event.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<Event> getEventsListWithoutLimit() {
        TypedQuery<Event> query = em.createNamedQuery("event.list", Event.class);
        return query.getResultList();
    }

    public List<Note> getNotesListByEventId(Long eventId) {
        TypedQuery<Note> query = em.createNamedQuery("event.list.notes.by.id", Note.class);
        query.setParameter("id", eventId);
        return query.getResultList();
    }

    public List<User> getUsersListByEventIdAndFeedbackIsAccepted(Long eventId, FeedbackType feedback, int limit, int offset) {
        TypedQuery<User> query = em.createNamedQuery("event.list.users.by.id.and.feedback.accepted", User.class);
        query.setParameter("id", eventId);
        query.setParameter("feedback", feedback);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

}
