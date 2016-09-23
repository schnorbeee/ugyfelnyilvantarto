package com.codingmentorteam3.entities;

import com.codingmentorteam3.beans.ContactPersonBean;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity(name = "contactperson_table")
@NamedQueries({
    @NamedQuery(name = "contact.person.list", query = "SELECT cp FROM contactperson_table cp"),
    @NamedQuery(name = "contact.person.channels.by.id", query = "SELECT ch FROM contactperson_table cp INNER JOIN cp.channels ch WHERE cp.id =:id")
})
public class ContactPerson extends Person implements Serializable {

    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public ContactPerson() {
        //Default constructor
    }

    public ContactPerson(ContactPersonBean contactPersonBean) {
        super(contactPersonBean.getFirstName(), contactPersonBean.getLastName(), contactPersonBean.getPosition(), contactPersonBean.getAvatar());
        this.company = contactPersonBean.getCompany();
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
        hash = 71 * hash + Objects.hashCode(this.company);
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
        final ContactPerson other = (ContactPerson) obj;
        if (!super.equals(other)) {
            return false;
        }
        if (!Objects.equals(this.company, other.company)) {
            return false;
        }
        return true;
    }

}
