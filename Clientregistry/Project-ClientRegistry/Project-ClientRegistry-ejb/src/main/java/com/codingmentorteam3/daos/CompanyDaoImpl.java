package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.ContactPerson;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Note;
import com.codingmentorteam3.entities.Project;
import com.codingmentorteam3.exceptions.BadRequestException;
import com.codingmentorteam3.exceptions.EmptyListException;
import com.codingmentorteam3.exceptions.NoMatchForFilterException;
import java.util.List;
import javax.ejb.Stateless;

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
        if (null != name) {
            List<Company> query = em.createNamedQuery("company.by.name.filter", Company.class).setParameter("name", "%" + name + "%").getResultList();
            if (query.isEmpty()) {
                throw new NoMatchForFilterException("The results can not be found with this parameter: " + name);
            }
            return query;
        }
        return em.createNamedQuery("company.list", Company.class).getResultList();
    }

    public List<Company> getCompaniesListByTaxFilter(String tax) {
        if (null != tax) {
            List<Company> query = em.createNamedQuery("company.by.tax.number.filter", Company.class).setParameter("tax", "%" + tax + "%").getResultList();
            if (query.isEmpty()) {
                throw new NoMatchForFilterException("The results can not be found with this parameter: " + tax);
            }
            return query;
        }
        return em.createNamedQuery("company.list", Company.class).getResultList();
    }

    public List<Company> getCompaniesList() {
        List<Company> query = em.createNamedQuery("company.list", Company.class).getResultList();
        if (query.isEmpty()) {
            throw new EmptyListException("The company list is empty now.");
        }
        return query;
    }

    public List<Event> getEventsListByCompanyId(Long companyId) {
        Company current = read(companyId);
        if (null != current) {
            List<Event> query = em.createNamedQuery("company.list.events.by.id", Event.class).setParameter("id", companyId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("This company haven't any event.");
            }
            return query;
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<Project> getProjectsListByCompanyId(Long companyId) {
        Company current = read(companyId);
        if (null != current) {
            List<Project> query = em.createNamedQuery("company.list.projects.by.id", Project.class).setParameter("id", companyId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("This company haven't any project.");
            }
            return query;
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    public List<ContactPerson> getContactersListByCompanyId(Long companyId) {
        Company current = read(companyId);
        if (null != current) {
            List<ContactPerson> query = em.createNamedQuery("company.list.contacters.by.id", ContactPerson.class).setParameter("id", companyId).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("This company haven't any contact person.");
            }
            return query;
        }
        throw new BadRequestException(BADREQUESTMESSAGE);
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
        throw new BadRequestException(BADREQUESTMESSAGE);
    }

    //returns the list of companies, who did not have events in "n" months
    public List<Company> getInactiveCompaniesList(Integer n) {
        if (null != n) {
            List<Company> query = em.createNamedQuery("company.list.n.mounth.no.event", Company.class).setParameter("n", n).getResultList();
            if (query.isEmpty()) {
                throw new EmptyListException("In this interval we haven't company who is inactive.");
            }
            return query;
        }
        throw new BadRequestException("Invalid number in parameter query.");
    }

}
