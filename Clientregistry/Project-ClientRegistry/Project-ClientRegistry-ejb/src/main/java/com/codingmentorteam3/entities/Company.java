package com.codingmentorteam3.entities;

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
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity(name = "company_table")
@NamedNativeQuery(name = "company.list.n.month.no.event", query = "SELECT c FROM company_table c, event_table e WHERE c.company_id = e.company_id AND EXTRACT(MONTH FROM CURRENT_DATE) >= EXTRACT(MONTH FROM e.start_date) AND EXTRACT(MONTH FROM e.start_date) >= (EXTRACT(MONTH FROM CURRENT_DATE) - :n)")
@NamedQueries({
    @NamedQuery(name = "company.by.name.filter", query = "SELECT c FROM company_table c WHERE c.name LIKE :name"),
    @NamedQuery(name = "company.by.tax.number.filter", query = "SELECT c FROM company_table c WHERE c.taxNumber LIKE :tax"),
    @NamedQuery(name = "company.list", query = "SELECT c FROM company_table c ORDER BY c.name"),
    @NamedQuery(name = "company.list.events.by.id", query = "SELECT e FROM company_table c INNER JOIN c.events e WHERE c.id =:id"),
    @NamedQuery(name = "company.list.projects.by.id", query = "SELECT p FROM company_table c INNER JOIN c.projects p WHERE c.id =:id"),
    @NamedQuery(name = "company.list.contacters.by.id", query = "SELECT con FROM company_table c INNER JOIN c.contacters con WHERE c.id =:id"),
    @NamedQuery(name = "company.list.events.notes.by.id", query = "SELECT n FROM company_table c INNER JOIN c.events e INNER JOIN e.notes n WHERE c.id =:id")
})
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
