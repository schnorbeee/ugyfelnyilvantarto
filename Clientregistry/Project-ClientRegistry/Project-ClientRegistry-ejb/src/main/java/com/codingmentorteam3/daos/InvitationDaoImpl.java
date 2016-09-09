package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Invitation;
import com.codingmentorteam3.enums.FeedbackType;
import com.codingmentorteam3.exceptions.BadRequestException;
import com.codingmentorteam3.exceptions.EmptyListException;
import com.codingmentorteam3.exceptions.NoMatchForFilterException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class InvitationDaoImpl extends AbstractDao<Invitation> {

    @EJB
    private EventDaoImpl eventDao;

    public InvitationDaoImpl() {
        super(Invitation.class);
    }

    public List<Invitation> getInvitationsListByFeedbackFilter(String feedback) {
        if (null != feedback) {
            List<Invitation> query = em.createNamedQuery("invitation.by.feedback.filter", Invitation.class).setParameter("feedback", "%" + feedback + "%").getResultList();
            if (query.isEmpty()) {
                throw new NoMatchForFilterException("The results can not be found with this parameter: " + feedback);
            }
            return query;
        }
        return em.createNamedQuery("invitation.list", Invitation.class).getResultList();
    }

    public List<Invitation> getInvitationsList() {
        List<Invitation> query = em.createNamedQuery("invitation.list", Invitation.class).getResultList();
        if (query.isEmpty()) {
            throw new EmptyListException("The event list is empty now.");
        }
        return query;
    }

    public List<Invitation> getInvitationsListByEventId(Long eventId) {
        Event current = eventDao.read(eventId);
        if (null != current) {
            List<Invitation> query = em.createNamedQuery("invitation.list.by.event.id", Invitation.class).setParameter("id", eventId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("This event haven't any invitation.");
            }
            return query;
        }
        throw new BadRequestException("We haven't got this event in database.");
    }

    public List<Invitation> getInvitationsListByEventIdAndFeedbackStatus(Long eventId, FeedbackType ftype) {
        Event current = eventDao.read(eventId);
        if (null != current) {
            if (null != ftype) {
                List<Invitation> query = em.createNamedQuery("invitation.list.by.event.id.and.feedback", Invitation.class).setParameter("id", eventId).setParameter("feedback", ftype).getResultList();
                if (query.isEmpty()) {
                    throw new NoMatchForFilterException("We haven't invitaion on this event and this feedback.");
                }
                return query;
            }
            throw new BadRequestException("The feedback you want is incorrect.");
        }
        throw new BadRequestException("We haven't got this event in database.");
    }

}
