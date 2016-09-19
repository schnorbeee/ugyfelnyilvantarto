package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.ContactPerson;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Note;
import com.codingmentorteam3.entities.Project;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.EmptyListException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class CompanyDaoImpl extends AbstractDao<Company> {

    private static final String BAD_REQUEST_MESSAGE = "We haven't got this company in database.";

    public CompanyDaoImpl() {
        super(Company.class);
    }

    public List<Company> getCompaniesListByNameFilter(String name) {
        if (null != name) {
            List<Company> query = em.createNamedQuery("company.by.name.filter", Company.class).setParameter("name", "%" + name + "%").getResultList();
            return query;
        }
        return em.createNamedQuery("company.list", Company.class).getResultList();
    }

    public List<Company> getCompaniesListByTaxFilter(String tax, int limit, int offset) {
        if (null != tax) {
            TypedQuery<Company> query = em.createNamedQuery("company.by.tax.number.filter", Company.class).setParameter("tax", "%" + tax + "%");
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.getResultList();
        }
        return em.createNamedQuery("company.list", Company.class).getResultList();
    }

    public List<Company> getCompaniesList(int limit, int offset) {
        TypedQuery<Company> query = em.createNamedQuery("company.list", Company.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<Event> getEventsListByCompanyId(Long companyId) {
        TypedQuery<Event> query = em.createNamedQuery("company.list.events.by.id", Event.class).setParameter("id", companyId);
        return query.getResultList();
    }

    public List<Project> getProjectsListByCompanyId(Long companyId) {
        TypedQuery<Project> query = em.createNamedQuery("company.list.projects.by.id", Project.class).setParameter("id", companyId);
        return query.getResultList();
    }

    public List<ContactPerson> getContactersListByCompanyId(Long companyId) {
        TypedQuery<ContactPerson> query = em.createNamedQuery("company.list.contacters.by.id", ContactPerson.class).setParameter("id", companyId);
        return query.getResultList();
    }

    public List<Note> getNotesListByAllEventsByCompanyId(Long companyId) {
        Company current = read(companyId);
        if (null != current) {
            List<Note> query = em.createNamedQuery("company.list.events.notes.by.id", Note.class).setParameter("id", companyId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("This company haven't any notes for his events.");
            }
            return query;
        }
        throw new BadRequestException(BAD_REQUEST_MESSAGE);
    }

    //returns the list of companies, who did not have events in "n" months
    public List<Company> getInactiveCompaniesList(Integer n) {
        if (null != n) {
            List<Company> query = em.createNamedQuery("company.list.n.month.no.event", Company.class).setParameter("n", n).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("In this interval there are no companies, who are inactive.");
            }
            return query;
        }
        throw new BadRequestException("Invalid number in parameter query.");
    }

}
