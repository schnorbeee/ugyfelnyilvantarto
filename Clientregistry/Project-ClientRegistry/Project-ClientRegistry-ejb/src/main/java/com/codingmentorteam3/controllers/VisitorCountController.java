package com.codingmentorteam3.controllers;

import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.services.VisitorCountService;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class VisitorCountController {

    @Inject
    private VisitorCountService visitorCountService;

    public Integer getCountVisitorsPerDay(Date day) {
        if (null != day) {
            return visitorCountService.getCountVisitorsPerDay(day);
        }
        throw new BadRequestException("Bad day format.");
    }
}
