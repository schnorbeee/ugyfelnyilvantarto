package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Invitation;
import com.codingmentorteam3.enums.FeedbackType;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.EmptyListException;
import com.codingmentorteam3.exceptions.query.NoMatchForFilterException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class InvitationDaoImpl extends AbstractDao<Invitation> {

    @Inject
    private EventDaoImpl eventDao;

    public InvitationDaoImpl() {
        super(Invitation.class);
    }

    public List<Invitation> getInvitationsListByFeedbackFilter(String feedback) {
        if (null != feedback) {
            List<Invitation> query = em.createNamedQuery("invitation.by.feedback.filter", Invitation.class).setParameter("feedback", "%" + feedback + "%").getResultList();
            if (query.isEmpty()) {
                throw new NoMatchForFilterException("We did not find any invitation with the given feedback: " + feedback);
            }
            return query;
        }
        return em.createNamedQuery("invitation.list", Invitation.class).getResultList();
    }

    public List<Invitation> getInvitationsList(int limit, int offset) {
        TypedQuery<Invitation> query = em.createNamedQuery("invitation.list", Invitation.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<Invitation> getInvitationsListByEventId(Long eventId) {
        Event current = eventDao.read(eventId);
        if (null != current) {
            List<Invitation> query = em.createNamedQuery("invitation.list.by.event.id", Invitation.class).setParameter("id", eventId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("There are no invitations connected to this event : " + current.getTitle());
            }
            return query;
        }
        throw new BadRequestException("We haven't got this event in database.");
    }

    public List<Invitation> getInvitationsListByEventIdAndFeedbackStatus(Long eventId, FeedbackType ftype, int limit, int offset) {
        TypedQuery<Invitation> query = em.createNamedQuery("invitation.list.by.event.id.and.feedback", Invitation.class);
        query.setParameter("id", eventId);
        query.setParameter("feedback", ftype);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<Invitation> getInvitationsListByReceiverIdAndFeedbackStatus(Long receiverId, FeedbackType ftype, int limit, int offset) {
        TypedQuery<Invitation> query = em.createNamedQuery("invitation.list.by.receiver.id.and.feedback", Invitation.class);
        query.setParameter("id", receiverId);
        query.setParameter("feedback", ftype);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }
    
    public List<Invitation> getInvitationsListBySenderId(Long senderId, int limit, int offset) {
        TypedQuery<Invitation> query = em.createNamedQuery("invitation.list.by.sender.id", Invitation.class);
        query.setParameter("id", senderId);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }
    
}
