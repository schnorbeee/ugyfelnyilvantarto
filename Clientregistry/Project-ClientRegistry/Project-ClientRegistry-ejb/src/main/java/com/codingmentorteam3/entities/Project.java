package com.codingmentorteam3.entities;

import com.codingmentorteam3.enums.StatusType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Entity(name = "project_table")
@NamedNativeQuery(name = "project.list.in.this.week",query = "SELECT p FROM project_table p WHERE EXTRACT(WEEK FROM CURRENT_DATE) = EXTRACT(WEEK FROM p.deadline)")
@NamedQueries({
    @NamedQuery(name = "project.by.name.filter", query = "SELECT p FROM project_table p WHERE p.name LIKE :name"),
    @NamedQuery(name = "project.by.status.filter", query = "SELECT p FROM project_table p WHERE p.status LIKE :status"),
    @NamedQuery(name = "project.list", query = "SELECT p FROM project_table p"),
    @NamedQuery(name = "project.list.companies.by.id", query = "SELECT c FROM project_table p INNER JOIN p.companies c WHERE p.id =:id"),
    @NamedQuery(name = "project.list.contacter.connection.channel.by.id", query = "SELECT ch FROM project_table p INNER JOIN p.companies c INNER JOIN c.contacters con INNER JOIN con.channels ch WHERE p.id =:id")
})
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusType status;

    @Column(nullable = false, length = 1500)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date deadline;

    @ManyToMany(mappedBy = "projects", targetEntity = Company.class)
    private List<Company> companies = new ArrayList<>();

    public Project() {
        //Default constructor
    }

    public Project(String name, Date startDate, StatusType status, String description, Date deadline) {
        this.name = name;
        this.startDate = startDate;
        this.status = status;
        this.description = description;
        this.deadline = deadline;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanys(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.startDate);
        hash = 29 * hash + Objects.hashCode(this.status);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.deadline);
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
        final Project other = (Project) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.deadline, other.deadline)) {
            return false;
        }
        return true;
    }

}
