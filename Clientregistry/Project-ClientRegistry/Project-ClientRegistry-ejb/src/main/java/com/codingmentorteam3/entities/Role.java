package com.codingmentorteam3.entities;

import com.codingmentorteam3.enums.RoleEnum;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity
@Table(name = "role_table")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleEnum roleType;
    
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    public Role() {
        //Default constructor
    }

    public Role(RoleEnum roleType) {
        this.roleType = roleType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleEnum roleType) {
        this.roleType = roleType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.roleType);
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
        final Role other = (Role) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.roleType != other.roleType) {
            return false;
        }
        return true;
    }
    
}
