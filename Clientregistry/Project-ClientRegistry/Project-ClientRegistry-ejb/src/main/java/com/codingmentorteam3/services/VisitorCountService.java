package com.codingmentorteam3.services;

import com.codingmentorteam3.daos.VisitorCountDaoImpl;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class VisitorCountService {

    @Inject
    private VisitorCountDaoImpl visitorCountDaoImpl;
    
    public Integer getCountVisitorsPerDay(Date currentDay) {
        return visitorCountDaoImpl.getCountVisitorsPerDay(currentDay);
    }
    
}
