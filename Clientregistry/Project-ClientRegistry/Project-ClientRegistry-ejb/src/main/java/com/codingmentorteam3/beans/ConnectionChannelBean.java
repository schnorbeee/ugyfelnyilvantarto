package com.codingmentorteam3.beans;

import com.codingmentorteam3.annotations.Validate;
import com.codingmentorteam3.constraints.ValidConnectionChannelValue;
import com.codingmentorteam3.entities.Person;
import com.codingmentorteam3.enums.ConnectionChannelType;
import java.io.Serializable;
import java.util.Objects;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.NotNull;

/**
 *
 * @author istvan.mosonyi
 */
@Validate
@ValidConnectionChannelValue
@SessionScoped
@ManagedBean(name="connectionChannelBean")
public class ConnectionChannelBean implements Serializable {
    
    @NotNull
    private ConnectionChannelType type;
    
    @NotNull
    private String value;
    
    @NotNull
    private Person owner;

    public ConnectionChannelBean() {
        // Default constructor
    }

    public ConnectionChannelBean(ConnectionChannelType type, String value, Person owner) {
        this.type = type;
        this.value = value;
        this.owner = owner;
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
        hash = 89 * hash + Objects.hashCode(this.type);
        hash = 89 * hash + Objects.hashCode(this.value);
        hash = 89 * hash + Objects.hashCode(this.owner);
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
        final ConnectionChannelBean other = (ConnectionChannelBean) obj;
        if (!Objects.equals(this.value, other.value)) {
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
        return "ConnectionChannelBean{" + "type=" + type + ", value=" + value + ", owner=" + owner + '}';
    }
    
}
