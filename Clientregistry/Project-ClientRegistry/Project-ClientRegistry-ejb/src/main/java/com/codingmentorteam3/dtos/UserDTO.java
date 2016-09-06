package com.codingmentorteam3.dtos;

import com.codingmentorteam3.annotations.Validate;
import com.codingmentorteam3.constraints.Username;
import com.codingmentorteam3.entities.User;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 *
 * @author istvan.mosonyi
 */
@Validate
public class UserDTO extends PersonDTO {
    
    private static final String DEFAULT_AVATAR = "";
    
    @NotNull
    @Username
    private String username;
    
//    @NotNull
//    @Password
//    private String password;
    
    @NotNull
    private String avatar = DEFAULT_AVATAR;

    public UserDTO() {
        // Default constructor
    }

    public UserDTO(User user) {
        super(user);
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
        int hash = 7;
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
