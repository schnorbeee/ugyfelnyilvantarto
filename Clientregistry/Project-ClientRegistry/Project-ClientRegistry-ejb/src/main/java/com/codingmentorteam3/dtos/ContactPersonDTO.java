package com.codingmentorteam3.dtos;

import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.ContactPerson;
import java.util.Objects;
 
/**
 *
 * @author istvan.mosonyi
 */
public class ContactPersonDTO extends PersonDTO {
    
    private Company company;
 
    public ContactPersonDTO() {
        // Default constructor
    }

    public ContactPersonDTO(ContactPerson contactPerson) {
        this.company = contactPerson.getCompany();
    }
 
    public Company getCompany() {
        return company;
    }
 
    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 83 * hash + Objects.hashCode(this.company);
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
        final ContactPersonDTO other = (ContactPersonDTO) obj;
        if (!super.equals(other)) {
            return false;
        }
        if (!Objects.equals(this.company, other.company)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContactPersonDTO{" + "company=" + company + '}';
    }
    
}