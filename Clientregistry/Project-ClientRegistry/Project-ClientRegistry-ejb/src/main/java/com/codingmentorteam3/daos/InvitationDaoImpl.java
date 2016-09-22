package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Invitation;
import com.codingmentorteam3.enums.FeedbackType;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class InvitationDaoImpl extends AbstractDao<Invitation> {

    public InvitationDaoImpl() {
        super(Invitation.class);
    }

    public List<Invitation> getInvitationsList(int limit, int offset) {
        TypedQuery<Invitation> query = em.createNamedQuery("invitation.list", Invitation.class);
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
