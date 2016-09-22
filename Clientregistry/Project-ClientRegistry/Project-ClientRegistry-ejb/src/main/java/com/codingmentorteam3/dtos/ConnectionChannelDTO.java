package com.codingmentorteam3.dtos;

import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Person;
import com.codingmentorteam3.enums.ConnectionChannelType;
import java.util.Objects;

/**
 *
 * @author istvan.mosonyi
 */
public class ConnectionChannelDTO {

    private Long id;

    private ConnectionChannelType type;

    private String value;

    private Person owner;

    public ConnectionChannelDTO() {
        // Default constructor
    }

    public ConnectionChannelDTO(ConnectionChannel connectionChannel) {
        this.id = connectionChannel.getId();
        this.type = connectionChannel.getType();
        this.value = connectionChannel.getValue();
        this.owner = connectionChannel.getOwner();
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
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.type);
        hash = 59 * hash + Objects.hashCode(this.value);
        hash = 59 * hash + Objects.hashCode(this.owner);
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
        final ConnectionChannelDTO other = (ConnectionChannelDTO) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.owner, other.owner)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConnectionChannelDTO{" + "id=" + id + ", type=" + type + ", value=" + value + ", owner=" + owner + '}';
    }

}
