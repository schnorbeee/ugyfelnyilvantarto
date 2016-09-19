package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.VisitorCount;
import java.util.Date;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class VisitorCountDaoImpl extends AbstractDao<VisitorCount> {

    public VisitorCountDaoImpl() {
        super(VisitorCount.class);
    }

    public Integer getCountVisitorsPerDay(Date currentDay) {
        VisitorCount query = em.createNamedQuery("visitors.of.day", VisitorCount.class).setParameter("day", currentDay).getSingleResult();
        return query.getCount();
    }

}
