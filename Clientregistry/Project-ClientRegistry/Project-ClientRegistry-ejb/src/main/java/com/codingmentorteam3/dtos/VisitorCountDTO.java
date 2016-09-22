package com.codingmentorteam3.dtos;

import com.codingmentorteam3.entities.VisitorCount;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author istvan.mosonyi
 */
public class VisitorCountDTO {
    
    private Long id;

    private Date day;

    private Integer count;

    public VisitorCountDTO() {
        // Default constructor
    }

    public VisitorCountDTO(VisitorCount visitorCount) {
        this.id = visitorCount.getId();
        this.day = visitorCount.getDay();
        this.count = visitorCount.getCount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.day);
        hash = 29 * hash + Objects.hashCode(this.count);
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
        final VisitorCountDTO other = (VisitorCountDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.day, other.day)) {
            return false;
        }
        if (!Objects.equals(this.count, other.count)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VisitorCountDTO{" + "id=" + id + ", day=" + day + ", count=" + count + '}';
    }

}
