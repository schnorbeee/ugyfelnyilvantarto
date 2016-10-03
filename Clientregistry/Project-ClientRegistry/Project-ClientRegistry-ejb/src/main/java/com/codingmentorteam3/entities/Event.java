package com.codingmentorteam3.entities;

import com.codingmentorteam3.beans.EventBean;
import com.codingmentorteam3.enums.EventType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity(name = "event_table")
@NamedQueries({
    @NamedQuery(name = "event.by.string.filter", query = "SELECT e FROM event_table e WHERE e.title LIKE :title OR e.description LIKE :title"),
    @NamedQuery(name = "event.by.type.filter", query = "SELECT e FROM event_table e WHERE e.type LIKE :type"),
    @NamedQuery(name = "event.list", query = "SELECT e FROM event_table e ORDER BY e.startDate"),
    @NamedQuery(name = "event.list.users.by.id.and.feedback", query = "SELECT r FROM event_table e INNER JOIN e.invitations i INNER JOIN i.receiver r WHERE e.id =:id AND i.feedback =:feedback"),
    @NamedQuery(name = "event.list.notes.by.id", query = "SELECT n FROM event_table e INNER JOIN e.notes n WHERE e.id =:id")
})
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @ManyToOne(targetEntity = Address.class)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(nullable = false, length = 30, unique = true)
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Date endDate;

    @Column(length = 1500)
    private String description;

    @Enumerated(EnumType.STRING)
    private EventType type;

    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToMany(mappedBy = "events", targetEntity = User.class)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "event", targetEntity = Invitation.class)
    private List<Invitation> invitations = new ArrayList<>();

    @OneToMany(mappedBy = "event", targetEntity = Note.class)
    private List<Note> notes = new ArrayList<>();

    public Event() {
        //Default constructor
    }

    public Event(EventBean eventBean) {
        this.title = eventBean.getTitle();
        this.description = eventBean.getDescription();
        this.address = eventBean.getAddress();
        this.type = eventBean.getType();
        this.startDate = eventBean.getStartDate();
        this.endDate = eventBean.getEndDate();
        this.company = eventBean.getCompany();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.title);
        hash = 79 * hash + Objects.hashCode(this.startDate);
        hash = 79 * hash + Objects.hashCode(this.endDate);
        hash = 79 * hash + Objects.hashCode(this.description);
        hash = 79 * hash + Objects.hashCode(this.type);
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
        final Event other = (Event) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

}
