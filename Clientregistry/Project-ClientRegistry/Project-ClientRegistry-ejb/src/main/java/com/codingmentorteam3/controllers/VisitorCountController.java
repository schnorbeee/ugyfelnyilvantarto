package com.codingmentorteam3.controllers;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@RequestScoped
@ManagedBean(name = "visitorCountController")
public class VisitorCountController {

    public Integer getVisitorsCountByDay(Date currentDay) {
        //TODO
        return 1;
    } 
    
}
