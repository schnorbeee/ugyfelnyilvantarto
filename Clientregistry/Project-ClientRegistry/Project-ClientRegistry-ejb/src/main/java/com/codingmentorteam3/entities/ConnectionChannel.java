package com.codingmentorteam3.entities;

import com.codingmentorteam3.enums.ConnectionChannelType;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity(name = "connectionchannel_table")
public class ConnectionChannel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "connection_channel_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConnectionChannelType type;

    @Column(name = "connection_value", nullable = false)
    private String value;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "owner_id", nullable = false)
    private Person owner;

    public ConnectionChannel() {
        //Default constuctor
    }

    public ConnectionChannel(ConnectionChannelType type, String value, User owner) {
        this.type = type;
        this.value = value;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConnectionChannelType getType() {
        return type;
    }

    public void setType(ConnectionChannelType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.type);
        hash = 43 * hash + Objects.hashCode(this.value);
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
        final ConnectionChannel other = (ConnectionChannel) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

}
