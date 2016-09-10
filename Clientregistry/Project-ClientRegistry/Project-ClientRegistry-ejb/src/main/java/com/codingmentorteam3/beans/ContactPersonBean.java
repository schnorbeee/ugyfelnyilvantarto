package com.codingmentorteam3.beans;

import com.codingmentorteam3.annotations.Validate;
import com.codingmentorteam3.entities.Company;
import java.io.Serializable;
import java.util.Objects;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.NotNull;

/**
 *
 * @author istvan.mosonyi
 */
@Validate
@SessionScoped
@ManagedBean(name = "contactPerson")
public class ContactPersonBean extends PersonBean implements Serializable {
    
    @NotNull
    private Company company;
 
    public ContactPersonBean() {
        // Default constructor
    }

    public ContactPersonBean(Company company) {
        this.company = company;
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
        final ContactPersonBean other = (ContactPersonBean) obj;
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
        return "ContactPersonBean{" + "company=" + company + '}';
    }
    
}
