package com.codingmentorteam3.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity(name = "contactperson_table")
@NamedQuery(name = "contact.person.channels.by.id", query = "SELECT ch FROM contactperson_table c INNER JOIN c.channels ch WHERE c.id =:id")
public class ContactPerson extends Person implements Serializable {

    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_id")
    private Company company;

    public ContactPerson() {
        //Default constructor
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    
}
