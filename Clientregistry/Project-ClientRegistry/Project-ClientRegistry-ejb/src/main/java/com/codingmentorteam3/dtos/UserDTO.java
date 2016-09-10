package com.codingmentorteam3.dtos;

import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.RankType;
import java.util.Objects;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private RankType rank;
    private String avatar;
    private String username;
    private String password;

    public UserDTO() {
        //Default constructor
    }
    
    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.rank = user.getRank();
        this.avatar = user.getAvatar();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RankType getRank() {
        return rank;
    }

    public void setRank(RankType rank) {
        this.rank = rank;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.firstName);
        hash = 41 * hash + Objects.hashCode(this.lastName);
        hash = 41 * hash + Objects.hashCode(this.rank);
        hash = 41 * hash + Objects.hashCode(this.avatar);
        hash = 41 * hash + Objects.hashCode(this.username);
        hash = 41 * hash + Objects.hashCode(this.password);
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
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.avatar, other.avatar)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.rank != other.rank) {
            return false;
        }
        return true;
    }
    
}
