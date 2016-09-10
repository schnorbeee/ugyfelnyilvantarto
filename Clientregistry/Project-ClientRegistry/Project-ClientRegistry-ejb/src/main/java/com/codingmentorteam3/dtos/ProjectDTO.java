package com.codingmentorteam3.dtos;

import com.codingmentorteam3.entities.Project;
import com.codingmentorteam3.enums.StatusType;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author istvan.mosonyi
 */
public class ProjectDTO {
    
    private static final StatusType DEFAULT_TYPE = StatusType.INACTIVE;

    private String name;

    private String description;

    private StatusType status = DEFAULT_TYPE;
    
    private Date startDate;
    
    private Date deadline;

    public ProjectDTO() {
        // Default constructor
    }

    public ProjectDTO(Project project) {
        this.name = project.getName();
        this.description = project.getDescription();
        this.startDate = project.getStartDate();
        this.deadline = project.getDeadline();
        this.status = project.getStatus();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType type) {
        this.status = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + Objects.hashCode(this.status);
        hash = 89 * hash + Objects.hashCode(this.startDate);
        hash = 89 * hash + Objects.hashCode(this.deadline);
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
        final ProjectDTO other = (ProjectDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.deadline, other.deadline)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" + "name=" + name + ", description=" + description + ", type=" + status + ", startDate=" + startDate + ", deadline=" + deadline + '}';
    }
    
}
