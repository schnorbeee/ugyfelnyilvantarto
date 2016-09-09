package com.codingmentorteam3.dtos;

import com.codingmentorteam3.entities.User;
import java.util.Objects;

/**
 *
 * @author istvan.mosonyi
 */
public class UserDTO extends PersonDTO {
    
    private static final String DEFAULT_AVATAR = "";

    private String username;

    private String avatar = DEFAULT_AVATAR;

    public UserDTO() {
        // Default constructor
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.avatar = user.getAvatar();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 23 * hash + Objects.hashCode(this.username);
        hash = 23 * hash + Objects.hashCode(this.avatar);
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
        final UserDTO other = (UserDTO) obj;
        if(!super.equals(other)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.avatar, other.avatar)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "username=" + username + ", avatar=" + avatar + '}';
    }
    
}
