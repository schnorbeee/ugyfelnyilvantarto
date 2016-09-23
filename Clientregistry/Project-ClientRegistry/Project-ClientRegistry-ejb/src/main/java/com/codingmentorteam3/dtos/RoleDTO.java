package com.codingmentorteam3.dtos;

import com.codingmentorteam3.entities.Role;
import com.codingmentorteam3.enums.RoleType;
import java.util.Objects;

/**
 *
 * @author istvan.mosonyi
 */
public class RoleDTO {

    private Long id;

    private RoleType type;

    private String username;

    public RoleDTO() {
        // Default constructor
    }

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.type = role.getRoleType();
        this.username = role.getUser().getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.type);
        hash = 71 * hash + Objects.hashCode(this.username);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RoleDTO{" + "id=" + id + ", type=" + type + ", username=" + username + '}';
    }

}
