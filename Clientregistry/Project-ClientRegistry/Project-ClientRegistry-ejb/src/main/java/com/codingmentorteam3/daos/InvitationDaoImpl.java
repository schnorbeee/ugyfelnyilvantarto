package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Invitation;
import com.codingmentorteam3.enums.FeedbackType;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class InvitationDaoImpl extends AbstractDao<Invitation> {

    public InvitationDaoImpl() {
        super(Invitation.class);
    }

    public List<Invitation> getInvitationsListByFeedbackFilter(String feedback) {
        try {
            List<Invitation> query = em.createNamedQuery("invitation.by.feedback.filter", Invitation.class).setParameter("feedback", feedback).getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Invitation> getInvitationsList() {
        try {
            List<Invitation> query = em.createNamedQuery("invitation.list", Invitation.class).getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Invitation> getInvitationsListByEventId(Long eventId) {
        Event current = em.find(Event.class, eventId);
        if (null != current) {
            try {
                List<Invitation> query = em.createNamedQuery("invitation.list.by.event.id", Invitation.class).setParameter("id", eventId).getResultList();
                return query; 
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException("We haven't got this event in database.");
    }

    public List<Invitation> getInvitationsListByEventIdAndFeedbackStatus(Long eventId, FeedbackType ftype) {
        Event current = em.find(Event.class, eventId);
        if (null != current) {
            try {
                List<Invitation> query = em.createNamedQuery("invitation.list.by.event.id.and.feedback", Invitation.class).setParameter("id", eventId).setParameter("feedback", ftype).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException("We haven't got this event in database.");
    }

}
