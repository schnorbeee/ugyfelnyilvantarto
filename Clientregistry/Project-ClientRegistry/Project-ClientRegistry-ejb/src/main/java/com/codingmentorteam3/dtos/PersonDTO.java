package com.codingmentorteam3.dtos;

import com.codingmentorteam3.annotations.Validate;
import com.codingmentorteam3.entities.Person;
import com.codingmentorteam3.enums.RankType;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author istvan.mosonyi
 */
@Validate
public class PersonDTO {
    
    private static final RankType DEFAULT_RANK = RankType.VISITOR;
    
    @NotNull 
    @Size(min = 2)
    protected String firstName;
    
    @NotNull 
    @Size(min = 2)
    protected String lastName;
    
    @NotNull
    protected RankType rank = DEFAULT_RANK;

    public PersonDTO() {
        // Default constructor
    }

    public PersonDTO(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.rank = person.getRank();
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
        final PersonDTO other = (PersonDTO) obj;
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
        return "PersonDTO{" + "firstName=" + firstName + ", lastName=" + lastName + ", rank=" + rank + '}';
    }
    
}
