package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.VisitorCount;
import com.codingmentorteam3.exceptions.BadRequestException;
import com.codingmentorteam3.exceptions.NoMatchForFilterException;
import java.util.Date;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class VisitorDaoImpl extends AbstractDao<VisitorCount> {

    public VisitorDaoImpl() {
        super(VisitorCount.class);
    }

    public Integer getCountVisitorsPerDay(Date day) {
        if (null != day) {
            int query = em.createNamedQuery("visitors.of.day", VisitorCount.class).setParameter("day", day).getMaxResults();
            if (query == 0) {
                throw new NoMatchForFilterException("In this day homepage haven't any visitors.");
            }
            return query;
        }
        throw new BadRequestException("The date of what you want is bad.");
    }

}
