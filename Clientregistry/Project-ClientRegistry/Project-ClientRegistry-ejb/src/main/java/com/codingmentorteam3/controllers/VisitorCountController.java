package com.codingmentorteam3.controllers;

import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.VisitorCountService;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@BeanValidation
@RequestScoped
@ManagedBean(name = "visitorCountController")
public class VisitorCountController {

    @Inject
    private VisitorCountService visitorCountService;

    public Integer getCountVisitorsPerDay() {
        return visitorCountService.getCountVisitorsPerDay(new Date());
    }
    
}
