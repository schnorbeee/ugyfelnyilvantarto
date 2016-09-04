package com.codingmentorteam3.entities;

import com.codingmentorteam3.enums.FeedbackType;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity(name = "invitation_table")
public class Invitation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invitation_id")
    private Long id;
    
    private String message;
    
    private FeedbackType feedback;
    
    @ManyToOne(targetEntity = Event.class)
    @JoinColumn(name = "event_id")
    private Event event;
    
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "sender_id")
    private User sender;
    
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    public Invitation() {
        //Default constructor
    }

    public Invitation(String message, FeedbackType feedback) {
        this.message = message;
        this.feedback = feedback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.message);
        hash = 41 * hash + Objects.hashCode(this.feedback);
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
        final Invitation other = (Invitation) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.feedback, other.feedback)) {
            return false;
        }
        return true;
    }

}
