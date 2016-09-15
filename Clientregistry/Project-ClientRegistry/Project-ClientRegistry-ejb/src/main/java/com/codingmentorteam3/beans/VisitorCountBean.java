package com.codingmentorteam3.beans;

import com.codingmentorteam3.annotations.Validate;
import java.util.Date;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author istvan.mosonyi
 */
@Validate
@SessionScoped
@ManagedBean(name = "visitorCount")
public class VisitorCountBean {
    
    private static final Integer DEFAULT_COUNT = 0;
    
    @NotNull
    private Date day;
    
    @NotNull
    @Min(0)
    private Integer count = DEFAULT_COUNT;

    public VisitorCountBean() {
        // Default constructor
    }

    public VisitorCountBean(Date day) {
        this.day = day;
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
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.day);
        hash = 59 * hash + Objects.hashCode(this.count);
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
        final VisitorCountBean other = (VisitorCountBean) obj;
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
        return "VisitorCountBean{" + "day=" + day + ", count=" + count + '}';
    }
    
}
