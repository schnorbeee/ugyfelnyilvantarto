package com.codingmentorteam3.controllers;

import com.codingmentorteam3.services.VisitorCountService;
import java.time.LocalDate;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@RequestScoped
@ManagedBean(name = "visitorCountController")
public class VisitorCountController {

    @Inject
    private VisitorCountService visitorCountService;

    public void setCountVisitorsPerDay() {
        
    }
    
    public Integer getCountVisitorsPerDay() {
        LocalDate now = LocalDate.now();
        Date currentDay = java.sql.Date.valueOf(now);
        return visitorCountService.getCountVisitorsPerDay(currentDay);
    }
    
}
