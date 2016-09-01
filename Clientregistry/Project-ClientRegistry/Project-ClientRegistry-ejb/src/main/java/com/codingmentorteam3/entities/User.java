package com.codingmentorteam3.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity
@Table(name = "user_table")
public class User extends Person implements Serializable {

    @Column(name = "user_name", nullable = false, length = 30)
    protected String username;
    
    @Column(nullable = false, length = 20)
    protected String password;
    
    @OneToMany(mappedBy = "owner", targetEntity = ConnectionChannel.class)
    protected List<ConnectionChannel> channels;
    
    @OneToMany(mappedBy = "user", targetEntity = Role.class)
    protected List<Role> roles;
    
    @OneToMany(mappedBy = "user", targetEntity = Invitation.class)
    protected List<Invitation> invitationsSent;
    
    @OneToMany(mappedBy = "user", targetEntity = Note.class)
    protected List<Note> notes;
    
    @ManyToMany
    @JoinTable(name = "user_event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    protected List<Event> events;

    public User() {
        //Default constructor
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ConnectionChannel> getChannels() {
        return channels;
    }

    public void setChannels(List<ConnectionChannel> channels) {
        this.channels = channels;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Invitation> getInvitationsSent() {
        return invitationsSent;
    }

    public void setInvitationsSent(List<Invitation> invitationsSent) {
        this.invitationsSent = invitationsSent;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.username);
        hash = 41 * hash + Objects.hashCode(this.password);
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
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

}
