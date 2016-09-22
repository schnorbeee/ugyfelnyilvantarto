package com.codingmentorteam3.services;

import com.codingmentorteam3.daos.VisitorCountDaoImpl;
import com.codingmentorteam3.entities.VisitorCount;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author schno sch.norbeee@gmail.com
 */
@Stateless
public class VisitorCountService {

    @Inject
    private VisitorCountDaoImpl visitorCountDao;

    public void createInvitation(VisitorCount visitorCount) {
        visitorCountDao.create(visitorCount);
    }

    public VisitorCount getInvitation(Long visitorCountId) {
        return visitorCountDao.read(visitorCountId);
    }

    public VisitorCount editInvitation(VisitorCount visitorCount) {
        return visitorCountDao.update(visitorCount);
    }

    public VisitorCount deleteInvitation(VisitorCount visitorCount) {
        return visitorCountDao.delete(visitorCount);
    }

    public Integer getCountVisitorsPerDay(Date day) {
        return visitorCountDao.getCountVisitorsPerDay(day);
    }

}
