package com.codingmentorteam3.dtos;

import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Invitation;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.FeedbackType;
import java.util.Objects;

/**
 *
 * @author istvan.mosonyi
 */
public class InvitationDTO {
    
    private static final FeedbackType DEFAULT_FEEDBACK = FeedbackType.UNANSWERED;

    private User sender;

    private User receiver;

    private Event event;

    private String message;

    private FeedbackType feedback = DEFAULT_FEEDBACK;

    public InvitationDTO() {
        // Default constructor
    }

    public InvitationDTO(Invitation invitation) {
        this.sender = invitation.getSender();
        this.receiver = invitation.getReceiver();
        this.event = invitation.getEvent();
        this.message = invitation.getMessage();
        this.feedback = invitation.getFeedback();
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FeedbackType getFeedback() {
        return feedback;
    }

    public void setFeedback(FeedbackType feedback) {
        this.feedback = feedback;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.sender);
        hash = 23 * hash + Objects.hashCode(this.receiver);
        hash = 23 * hash + Objects.hashCode(this.event);
        hash = 23 * hash + Objects.hashCode(this.message);
        hash = 23 * hash + Objects.hashCode(this.feedback);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InvitationDTO other = (InvitationDTO) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.sender, other.sender)) {
            return false;
        }
        if (!Objects.equals(this.receiver, other.receiver)) {
            return false;
        }
        if (!Objects.equals(this.event, other.event)) {
            return false;
        }
        if (this.feedback != other.feedback) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InvitationDTO{" + "sender=" + sender + ", receiver=" + receiver + ", event=" + event + ", message=" + message + ", feedback=" + feedback + '}';
    }
    
}
