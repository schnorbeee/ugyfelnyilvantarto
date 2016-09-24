package com.codingmentorteam3.beans;

import com.codingmentorteam3.annotations.Validate;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.FeedbackType;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author istvan.mosonyi
 */
@Validate
@SessionScoped
@ManagedBean(name = "invitationBean")
public class InvitationBean {

    private static final FeedbackType DEFAULT_FEEDBACK = FeedbackType.UNANSWERED;

    @NotNull
    private User sender;

    @NotNull
    private User receiver;

    @NotNull
    private Event event;

    @Size(max = 1500)
    private String message;

    @NotNull
    private FeedbackType feedback = DEFAULT_FEEDBACK;

    public InvitationBean() {
        // Default constructor
    }

    public InvitationBean(User sender, User receiver, Event event, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.event = event;
        this.message = message;
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
        final InvitationBean other = (InvitationBean) obj;
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
        return "InvitationBean{" + "sender=" + sender + ", receiver=" + receiver + ", event=" + event + ", message=" + message + ", feedback=" + feedback + '}';
    }

}
