package com.codingmentorteam3.controllers;

import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Invitation;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.FeedbackType;
import com.codingmentorteam3.exceptions.query.BadRequestException;
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
    private UserService userService;

    @Inject
    private EventService eventService;

    public void setInvitationFeedback(FeedbackType type, Long invitationId) {
        Invitation currenInvitation = loadEntity(invitationId);
        if (null != currenInvitation) {
            if (type.equals(FeedbackType.ACCEPTED)) {
                currenInvitation.setFeedback(type);
                setEntity(currenInvitation);
                saveEntity();
                User currentReceiver = currenInvitation.getReceiver();
                Event currentEvent = currenInvitation.getEvent();
                currentReceiver.getEvents().add(currentEvent);
                currentEvent.getUsers().add(currentReceiver);
                userService.editUser(currentReceiver);
                eventService.editEvent(currentEvent);
            } else {
                invitationService.deleteInvitation(currenInvitation);
            }
        }
        throw new BadRequestException(getNoEntityMessage());
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
        return null;
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
