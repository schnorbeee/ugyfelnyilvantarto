package com.codingmentorteam3.entities;

import com.codingmentorteam3.beans.RoleBean;
import com.codingmentorteam3.enums.RoleType;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity(name = "role_table")
@NamedQueries({
        @NamedQuery(name = "role.list", query = "SELECT r FROM role_table r"),
        @NamedQuery(name = "role.list.by.role.type", query = "SELECT r FROM role_table r WHERE r.roleType =:rtype")
})
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", nullable = false)
    private RoleType roleType;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    public Role() {
        //Default constructor
    }

    public Role(RoleType roleType, User user) {
        this.roleType = roleType;
        this.user = user;
    }

    public Role(RoleBean roleBean) {
        this.roleType = roleBean.getType();
        this.user = roleBean.getUser();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
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
