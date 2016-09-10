package com.codingmentorteam3.beans;

import com.codingmentorteam3.annotations.Validate;
import com.codingmentorteam3.enums.RankType;
import java.io.Serializable;
import java.util.Objects;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author istvan.mosonyi
 */
@Validate
@SessionScoped
@ManagedBean(name = "person")
public class PersonBean implements Serializable {
    
    private static final RankType DEFAULT_RANK = RankType.VISITOR;
    
    @NotNull 
    @Size(min = 2, max = 30)
    protected String firstName;
    
    @NotNull 
    @Size(min = 2, max = 30)
    protected String lastName;
    
    @NotNull
    protected RankType rank = DEFAULT_RANK;

    public PersonBean() {
        // Default constructor
    }

    public PersonBean(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.firstName);
        hash = 29 * hash + Objects.hashCode(this.lastName);
        hash = 29 * hash + Objects.hashCode(this.rank);
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
        final PersonBean other = (PersonBean) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (this.rank != other.rank) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersonBean{" + "firstName=" + firstName + ", lastName=" + lastName + ", rank=" + rank + '}';
    }
    
}
