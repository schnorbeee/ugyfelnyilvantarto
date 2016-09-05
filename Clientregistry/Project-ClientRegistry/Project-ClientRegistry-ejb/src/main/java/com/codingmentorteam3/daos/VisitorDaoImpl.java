package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Visitor;
import java.util.Date;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class VisitorDaoImpl extends AbstractDao<Visitor> {

    public VisitorDaoImpl() {
        super(Visitor.class);
    }

    public Integer getCountVisitorsPerDay(Date day) {
        try {
            int query = em.createNamedQuery("visitors.of.day", Visitor.class).setParameter("day", day).getMaxResults();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

}
