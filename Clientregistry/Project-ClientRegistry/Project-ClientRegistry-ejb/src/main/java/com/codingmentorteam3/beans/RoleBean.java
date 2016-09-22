package com.codingmentorteam3.beans;

import com.codingmentorteam3.annotations.Validate;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.RoleType;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;

/**
 *
 * @author istvan.mosonyi
 */
@Validate
@SessionScoped
@ManagedBean(name = "roleBean")
public class RoleBean {

    private static final RoleType DEFAULT_ROLE = RoleType.USER;

    @NotNull
    private RoleType type = DEFAULT_ROLE;

    @NotNull
    private User user;

    public RoleBean() {
        // Default constructor
    }

    public RoleBean(User user) {
        this.user = user;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.type);
        hash = 47 * hash + Objects.hashCode(this.user);
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
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RoleBean{" + "type=" + type + ", user=" + user + '}';
    }

}
