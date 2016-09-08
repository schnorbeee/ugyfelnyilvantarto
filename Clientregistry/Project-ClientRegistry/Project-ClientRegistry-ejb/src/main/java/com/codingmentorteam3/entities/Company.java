package com.codingmentorteam3.entities;

import com.codingmentorteam3.dtos.CompanyDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity(name = "company_table")
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "tax_number", nullable = false)
    private String taxNumber;

    @Column(nullable = false)
    private String logo;

    @OneToMany(mappedBy = "company", targetEntity = Event.class)
    private List<Event> events = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "company_project_table",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "company", targetEntity = ContactPerson.class)
    private List<ContactPerson> contacters = new ArrayList<>();

    public Company() {
        //Default constructor
    }

    public Company(String name, Address address, String taxNumber, String logo) {
        this.name = name;
        this.address = address;
        this.taxNumber = taxNumber;
        this.logo = logo;
    }
    
    public Company(CompanyDTO companyDTO) {
        this.name = companyDTO.getName();
        this.address = companyDTO.getAddress();
        this.taxNumber = companyDTO.getTaxNumber();
        this.logo = companyDTO.getLogo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<ContactPerson> getContacters() {
        return contacters;
    }

    public void setContacters(List<ContactPerson> contacters) {
        this.contacters = contacters;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.taxNumber);
        hash = 53 * hash + Objects.hashCode(this.logo);
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
        final Company other = (Company) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.logo, other.logo)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.taxNumber, other.taxNumber)) {
            return false;
        }
        return true;
    }

}
