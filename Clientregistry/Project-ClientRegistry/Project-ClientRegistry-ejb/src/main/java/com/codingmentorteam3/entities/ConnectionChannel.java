package com.codingmentorteam3.entities;

import com.codingmentorteam3.enums.Type;
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
@Entity(name = "connection_channal_table")
public class ConnectionChannel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "connection_channal_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "connection_value", nullable = false, length = 30)
    private String value;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "owner_id")
    private User owner;

    public ConnectionChannel() {
        //Default constuctor
    }

    public ConnectionChannel(Type type, String value, User owner) {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
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
