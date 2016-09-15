package com.codingmentorteam3.beans;

import com.codingmentorteam3.annotations.Validate;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.RoleType;
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
@SessionScoped
@ManagedBean(name = "role")
public class RoleBean implements Serializable {
    
    private static final RoleType DEFAULT_ROLE = RoleType.VISITOR;
    
    @NotNull
    private RoleType type = DEFAULT_ROLE;
    
    @NotNull
    private User username;

    public RoleBean() {
        // Default constructor
    }

    public RoleBean(RoleType type, User username) {
        this.username = username;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.type);
        hash = 47 * hash + Objects.hashCode(this.username);
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
        final RoleBean other = (RoleBean) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RoleBean{" + "type=" + type + ", username=" + username + '}';
    }
    
}
