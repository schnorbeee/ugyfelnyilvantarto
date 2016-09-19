package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.InvitationBean;
import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Invitation;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.EventService;
import com.codingmentorteam3.services.InvitationService;
import com.codingmentorteam3.services.UserService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@BeanValidation
@RequestScoped
@ManagedBean(name = "invitationController")
public class InvitationController extends PageableEntityController<Invitation> {

    @Inject
    private InvitationService invitationService;
    
    @Inject
    private EventService eventService;

    @Inject
    private UserService userService;
    
    public String createInvitation(InvitationBean createInvitation, Long eventId, Long senderId, List<Long> receiverList) {
        Event event = eventService.getEvent(eventId);
        User sender = userService.getUser(senderId);
        Invitation invitation = new Invitation(createInvitation);
        for(Long receiverId : receiverList) {
            User receiver = userService.getUser(receiverId);
            if (null != event && null != sender && null != receiver) {
                
            }
        }
        return "";
    }
    
    
    @Override
    protected void doPersistEntity() {
        invitationService.createInvitation(getEntity());
    }

    @Override
    protected Invitation doUpdateEntity() {
        setEntity(invitationService.editInvitation(getEntity()));
        return getEntity();
    }

    @Override
    public List<Invitation> getEntities() {
        return invitationService.getInvitationsList(getLimit(), getOffset());
    }

    @Override
    protected Invitation loadEntity(Long entityId) {
        if (entityId != null) {
            return invitationService.getInvitation(entityId);
        }
        return new Invitation();
    }

    //atnezni a stringek helyesek-e az alabbi 3 override-nal
    @Override
    public String getListPage() {
        return "invitation-list";
    }

    @Override
    public String getNewItemOutcome() {
        return "composite/user.xhtml";
    }

    @Override
    public String getNoEntityMessage() {
        return "No invitation found in database!";
    }
}
