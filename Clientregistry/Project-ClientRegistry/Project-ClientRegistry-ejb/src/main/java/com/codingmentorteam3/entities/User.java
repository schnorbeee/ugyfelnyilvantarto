package com.codingmentorteam3.entities;

import com.codingmentorteam3.beans.UserBean;
import com.codingmentorteam3.enums.NumItemsPerPageType;
import com.codingmentorteam3.enums.PageableTablesType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity(name = "user_table")
@AttributeOverride(name = "user_id", column = @Column(name = "person_id"))
@NamedQueries({
    @NamedQuery(name = "user.by.username.filter", query = "SELECT u FROM user_table u WHERE u.username LIKE :name"),
    @NamedQuery(name = "user.by.firstname.filter", query = "SELECT u FROM user_table u WHERE u.firstName LIKE :first"),
    @NamedQuery(name = "user.by.lastname.filter", query = "SELECT u FROM user_table u WHERE u.lastName LIKE :last"),
    @NamedQuery(name = "user.by.rank.filter", query = "SELECT u FROM user_table u WHERE u.position LIKE :position"), 
    @NamedQuery(name = "user.list", query = "SELECT u FROM user_table u ORDER BY u.username"),
    @NamedQuery(name = "user.by.username", query = "SELECT u FROM user_table u WHERE u.username =:name"),
    @NamedQuery(name = "user.list.roles.by.id", query = "SELECT r FROM user_table u INNER JOIN u.roles r WHERE u.id =:id"),
    @NamedQuery(name = "user.list.invitation.sent.by.id", query = "SELECT i FROM user_table u INNER JOIN u.invitationsSent i WHERE u.id =:id"),
    @NamedQuery(name = "user.list.invitation.received.by.id", query = "SELECT i FROM user_table u INNER JOIN u.invitationsReceived i WHERE u.id =:id"),
    @NamedQuery(name = "user.list.notes.by.id", query = "SELECT n FROM user_table u INNER JOIN u.notes n WHERE u.id =:id"),
    @NamedQuery(name = "user.list.events.by.id", query = "SELECT e FROM user_table u INNER JOIN u.events e WHERE u.id =:id"),
    @NamedQuery(name = "user.list.channels.by.id", query = "SELECT ch FROM user_table u INNER JOIN u.channels ch WHERE u.id =:id")
})
public class User extends Person implements Serializable {

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @XmlTransient
    private String password;

    @MapKeyEnumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "num_item_per_page_table")
    @MapKeyColumn(name = "table_enum")
    @Column(name = "num_enum")
    private Map<PageableTablesType, NumItemsPerPageType> numItemPerPage = new EnumMap<>(PageableTablesType.class);

    @OneToMany(mappedBy = "user", targetEntity = Role.class, cascade = CascadeType.REMOVE)
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "sender", targetEntity = Invitation.class)
    private List<Invitation> invitationsSent = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", targetEntity = Invitation.class)
    private List<Invitation> invitationsReceived = new ArrayList<>();

    @OneToMany(mappedBy = "user", targetEntity = Note.class)
    private List<Note> notes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_event_table",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> events = new ArrayList<>();

    public User() {
        //Default constructor
    }

    public User(UserBean user) {
        super(user.getFirstName(), user.getLastName(), user.getRank(), user.getAvatar());
        this.username = user.getUsername();
        this.password = user.getPassword();
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

    @XmlTransient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<PageableTablesType, NumItemsPerPageType> getNumItemPerPage() {
        return numItemPerPage;
    }

    public void setNumItemPerPage(Map<PageableTablesType, NumItemsPerPageType> numItemPerPage) {
        this.numItemPerPage = numItemPerPage;
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

    public List<Invitation> getInvitationsReceived() {
        return invitationsReceived;
    }

    public void setInvitationsReceived(List<Invitation> invitationsReceived) {
        this.invitationsReceived = invitationsReceived;
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
        int hash = super.hashCode();
        hash = 41 * hash + Objects.hashCode(this.username);
        hash = 41 * hash + Objects.hashCode(this.password);
        hash = 41 * hash + Objects.hashCode(this.numItemPerPage);
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
        if (!super.equals(other)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.numItemPerPage, other.numItemPerPage)) {
            return false;
        }
        return true;
    }

}
