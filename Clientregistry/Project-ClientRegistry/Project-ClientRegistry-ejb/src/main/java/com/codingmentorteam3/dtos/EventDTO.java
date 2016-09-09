package com.codingmentorteam3.dtos;

import com.codingmentorteam3.entities.Address;
import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.enums.EventType;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author istvan.mosonyi
 */
public class EventDTO {
    
    private static final EventType DEFAULT_TYPE = EventType.MEETING;

    private String title;

    private String description;

    private Address address;
    
    private EventType type = DEFAULT_TYPE;
    
    private Date startDate;
    
    private Date endDate;
    
    private Company company;

    public EventDTO() {
        // Default constructor
    }

    public EventDTO(Event event) {
        this.title = event.getTitle();
        this.description = event.getDescription();
        this.address = event.getAddress();
        this.type = event.getType();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.company = event.getCompany();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.title);
        hash = 41 * hash + Objects.hashCode(this.description);
        hash = 41 * hash + Objects.hashCode(this.address);
        hash = 41 * hash + Objects.hashCode(this.type);
        hash = 41 * hash + Objects.hashCode(this.startDate);
        hash = 41 * hash + Objects.hashCode(this.endDate);
        hash = 41 * hash + Objects.hashCode(this.company);
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
        final EventDTO other = (EventDTO) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (!Objects.equals(this.company, other.company)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EventDTO{" + "title=" + title + ", description=" + description + ", address=" + address + ", type=" + type + ", startDate=" + startDate + ", endDate=" + endDate + ", company=" + company + '}';
    }
    
}
