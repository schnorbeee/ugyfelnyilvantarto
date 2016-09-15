package com.codingmentorteam3.dtos;

import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.RoleType;
import java.util.Objects;

/**
 *
 * @author istvan.mosonyi
 */
public class RoleDTO {
    
    private static final RoleType DEFAULT_ROLE = RoleType.VISITOR;

    private RoleType type = DEFAULT_ROLE;

    private String username;

    public RoleDTO() {
        // Default constructor
    }

    public RoleDTO(Role role) {
        this.type = role.getRoleType();
        this.username = role.getUser().getUsername();
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
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
        final RoleDTO other = (RoleDTO) obj;
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
        return "RoleDTO{" + "type=" + type + ", username=" + username + '}';
    }
    
}
