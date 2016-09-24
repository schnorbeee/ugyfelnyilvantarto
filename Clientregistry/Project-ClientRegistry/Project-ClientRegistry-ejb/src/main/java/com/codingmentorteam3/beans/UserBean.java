package com.codingmentorteam3.beans;

import com.codingmentorteam3.annotations.Validate;
import com.codingmentorteam3.constraints.Password;
import com.codingmentorteam3.constraints.Username;
import com.codingmentorteam3.enums.PositionType;
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
@ManagedBean(name = "user")
public class UserBean extends PersonBean {
    
    @Username
    private String username;
    
    @Password
    private String password;

    public UserBean() {
        // Default constructor
    }

    public UserBean(String username, String password, String firstName, String lastName, PositionType rank, String avatar) {
        super(firstName, lastName, rank, avatar);
        this.username = username;
        this.password = password;
    }

    public UserBean(String username, String password, String firstName, String lastName) {
        super(firstName, lastName);
        this.username = username;
        this.password = password;
    }

    public UserBean(String username, String password) {
        this.username = username;
        this.password = password;
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
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.username);
        hash = 53 * hash + Objects.hashCode(this.password);
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
        final UserBean other = (UserBean) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserBean{" + "username=" + username + ", password=" + password + '}';
    }
    
}
