package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.ContactPerson;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Note;
import com.codingmentorteam3.entities.Project;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class CompanyDaoImpl extends AbstractDao<Company> {

    private static final String COMPANY_LIST = "company.list";
    
    public CompanyDaoImpl() {
        super(Company.class);
    }

    public List<Company> getCompaniesListByNameFilter(String name, int limit, int offset) {
        if (null != name) {
            TypedQuery<Company> query = em.createNamedQuery("company.by.name.filter", Company.class);
            query.setParameter("name", "%" + name + "%");
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.getResultList();
        }
        TypedQuery<Company> query = em.createNamedQuery(COMPANY_LIST, Company.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<Company> getCompaniesListByTaxFilter(String tax, int limit, int offset) {
        if (null != tax) {
            TypedQuery<Company> query = em.createNamedQuery("company.by.tax.number.filter", Company.class);
            query.setParameter("tax", "%" + tax + "%");
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.getResultList();
        }
        TypedQuery<Company> query = em.createNamedQuery(COMPANY_LIST, Company.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<Company> getCompaniesList(int limit, int offset) {
        TypedQuery<Company> query = em.createNamedQuery(COMPANY_LIST, Company.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<Company> getCompaniesListWithoutLimit() {
        TypedQuery<Company> query = em.createNamedQuery(COMPANY_LIST, Company.class);
        return query.getResultList();
    }

    public List<Event> getEventsListByCompanyId(Long companyId) {
        TypedQuery<Event> query = em.createNamedQuery("company.list.events.by.id", Event.class);
        query.setParameter("id", companyId);
        return query.getResultList();
    }

    public List<Project> getProjectsListByCompanyId(Long companyId) {
        TypedQuery<Project> query = em.createNamedQuery("company.list.projects.by.id", Project.class);
        query.setParameter("id", companyId);
        return query.getResultList();
    }

    public List<ContactPerson> getContactersListByCompanyId(Long companyId) {
        TypedQuery<ContactPerson> query = em.createNamedQuery("company.list.contacters.by.id", ContactPerson.class);
        query.setParameter("id", companyId);
        return query.getResultList();
    }

    public List<Note> getNotesListByAllEventsByCompanyId(Long companyId, int limit, int offset) {
        TypedQuery<Note> query = em.createNamedQuery("company.list.events.notes.by.id", Note.class);
        query.setParameter("id", companyId);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    //returns the list of companies, who did not have events in "n" months
    public List<Company> getInactiveCompaniesList(Integer n, int limit, int offset) {
        TypedQuery<Company> query = em.createNamedQuery("company.list.n.month.no.event", Company.class);
        query.setParameter("n", n);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

}
