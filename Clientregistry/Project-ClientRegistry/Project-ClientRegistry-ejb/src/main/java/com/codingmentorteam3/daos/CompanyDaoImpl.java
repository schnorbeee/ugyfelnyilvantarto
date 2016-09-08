package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.ContactPerson;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Note;
import com.codingmentorteam3.entities.Project;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import javax.ws.rs.BadRequestException;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class CompanyDaoImpl extends AbstractDao<Company> {

    private static final String BADREQUESTMESSAGE = "We haven't got this company in database.";

    public CompanyDaoImpl() {
        super(Company.class);
    }

    public List<Company> getCompaniesListByNameFilter(String name) {
        try {
            List<Company> query = em.createNamedQuery("company.by.name.filter", Company.class).setParameter("name", "%" + name + "%").getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Company> getCompaniesListByTaxFilter(String tax) {
        try {
            List<Company> query = em.createNamedQuery("company.by.tax.number.filter", Company.class).setParameter("tax", "%" + tax + "%").getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Company> getCompaniesList() {
        try {
            List<Company> query = em.createNamedQuery("company.list", Company.class).getResultList();
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Event> getEventsListByCompanyId(Long companyId) {
        Company current = read(companyId);
        if (null != current) {
            try {
                List<Event> query = em.createNamedQuery("company.list.events.by.id", Event.class).setParameter("id", companyId).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<Project> getProjectsListByCompanyId(Long companyId) {
        Company current = read(companyId);
        if (null != current) {
            try {
                List<Project> query = em.createNamedQuery("company.list.projects.by.id", Project.class).setParameter("id", companyId).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<ContactPerson> getContactersListByCompanyId(Long companyId) {
        Company current = read(companyId);
        if (null != current) {
            try {
                List<ContactPerson> query = em.createNamedQuery("company.list.contacters.by.id", ContactPerson.class).setParameter("id", companyId).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<Note> getNotesListByAllEventsByCompanyId(Long companyId) {
    Company current = read(companyId);
        if (null != current) {
            try {
                List<Note> query = em.createNamedQuery("company.list.events.notes.by.id", Note.class).setParameter("id", companyId).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }
    
    //jobb nevet kellene neki adni
    public List<Company> getCompaniesListWhoHaveEarlierEventLikeWePut(Long n) {
        Date now = Date.valueOf(LocalDate.now());
        LocalDate pastLocal = LocalDate.now().minusMonths(n);
        Date past = Date.valueOf(pastLocal);        
        try {
                List<Company> query = em.createNamedQuery("company.list.n.mounth.no.event", Company.class).setParameter("now", now, TemporalType.DATE).setParameter("past", past, TemporalType.DATE).getResultList();
                return query;
            } catch (Exception e) {
                return null;
            }
    }
    
}
