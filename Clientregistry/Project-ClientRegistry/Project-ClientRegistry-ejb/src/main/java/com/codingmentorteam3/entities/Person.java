package com.codingmentorteam3.entities;

import com.codingmentorteam3.enums.PositionType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity(name = "person_table")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "person.list.channels.by.id", query = "SELECT ch FROM person_table p INNER JOIN p.channels ch WHERE p.id =:id")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    protected Long id;

    @Column(name = "first_name", nullable = false, length = 30)
    protected String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    protected String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected PositionType position;
    
    @Column(nullable = false)
    protected String avatar;

    @OneToMany(mappedBy = "owner", targetEntity = ConnectionChannel.class, cascade = CascadeType.REMOVE)
    protected List<ConnectionChannel> channels = new ArrayList<>();

    public Person() {
        //Default constructor
    }

    public Person(String firstName, String lastName, PositionType position, String avatar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.avatar = avatar;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PositionType getPosition() {
        return position;
    }

    public void setPosition(PositionType position) {
        this.position = position;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<ConnectionChannel> getChannels() {
        return channels;
    }

    public void setChannels(List<ConnectionChannel> channels) {
        this.channels = channels;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.firstName);
        hash = 19 * hash + Objects.hashCode(this.lastName);
        hash = 19 * hash + Objects.hashCode(this.position);
        hash = 19 * hash + Objects.hashCode(this.avatar);
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
        final Person other = (Person) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.avatar, other.avatar)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.position != other.position) {
            return false;
        }
        return true;
    }

}
