package com.codingmentorteam3.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity(name = "contactperson_table")
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
