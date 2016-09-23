package com.codingmentorteam3.services;

import com.codingmentorteam3.daos.InvitationDaoImpl;
import com.codingmentorteam3.entities.Invitation;
import com.codingmentorteam3.enums.FeedbackType;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Bicsak Daniel
 */
@Stateless
public class InvitationService {

    @Inject
    private InvitationDaoImpl invitationDao;

    public void createInvitation(Invitation invitation) {
        invitationDao.create(invitation);
    }

    public Invitation getInvitation(Long invitationId) {
        return invitationDao.read(invitationId);
    }

    public Invitation editInvitation(Invitation invitation) {
        return invitationDao.update(invitation);
    }

    public Invitation deleteInvitation(Invitation invitation) {
        return invitationDao.delete(invitation);
    }
    
    public List<Invitation> getInvitationsList(Integer limit, Integer offset) {
        return invitationDao.getInvitationsList(limit, offset);
    }
    
    public List<Invitation> getInvitationsListByReceiverIdAndFeedbackStatus(Long receiverId, FeedbackType ftype, Integer limit, Integer offset) {
        return invitationDao.getInvitationsListByReceiverIdAndFeedbackStatus(receiverId, ftype, limit, offset);       
    }
    
    public List<Invitation> getInvitationsListBySenderId(Long senderId, Integer limit, Integer offset) {
        return invitationDao.getInvitationsListBySenderId(senderId, limit, offset);
    }
    
}
