package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.VisitorCount;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.NoMatchForFilterException;
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

    public Integer getCountVisitorsPerDay(Date day) {
        if (null != day) {
            int query = em.createNamedQuery("visitors.of.day", VisitorCount.class).setParameter("day", day).getMaxResults();
            if (query == 0) {
                throw new NoMatchForFilterException("Our page did not have any visitors on the given day");
            }
            return query;
        }
        throw new BadRequestException("The requested date is incorrect");
    }

}
