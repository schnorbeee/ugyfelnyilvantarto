package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.VisitorCount;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

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
            TypedQuery<Integer> query = em.createNamedQuery("visitors.of.day", Integer.class);
            query.setParameter("day", day, TemporalType.DATE);
            return query.getSingleResult();
        }
        throw new BadRequestException("The requested date is incorrect");
    }
    
    public void setCountVisitorsPerDay() {
        //meg nem kell
    }

}
