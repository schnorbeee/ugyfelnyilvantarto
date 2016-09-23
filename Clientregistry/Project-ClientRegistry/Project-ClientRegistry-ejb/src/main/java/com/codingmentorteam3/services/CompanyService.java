package com.codingmentorteam3.services;

import com.codingmentorteam3.daos.CompanyDaoImpl;
import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.ContactPerson;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Note;
import com.codingmentorteam3.entities.Project;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class CompanyService {

    @Inject
    private CompanyDaoImpl companyDao;

    public void createCompany(Company company) {
        companyDao.create(company);
    }

    public Company getCompany(Long companyId) {
        return companyDao.read(companyId);
    }

    public Company editCompany(Company company) {
        return companyDao.update(company);
    }

    public Company deleteCompany(Company company) {
        return companyDao.delete(company);
    }

    public List<Company> getCompaniesList(Integer limit, Integer offset) {
        return companyDao.getCompaniesList(limit, offset);
    }

    public List<Company> getCompaniesListByTaxFilter(String taxNumber, Integer limit, Integer offset) {
        return companyDao.getCompaniesListByTaxFilter(taxNumber, limit, offset);
    }

    public List<Project> getProjectsListByCompanyId(Long companyId) {
        return companyDao.getProjectsListByCompanyId(companyId);
    }

    public List<Event> getEventsListByCompanyId(Long companyId) {
        return companyDao.getEventsListByCompanyId(companyId);
    }

    public List<ContactPerson> getContactersListByCompanyId(Long companyId) {
        return companyDao.getContactersListByCompanyId(companyId);
    }

    public List<Company> getInactiveCompaniesList(Integer n, Integer limit, Integer offset) {
        return companyDao.getInactiveCompaniesList(n, limit, offset);
    }

    public List<Note> getNotesListByAllEventsByCompanyId(Long companyId, Integer limit, Integer offset) {
        return companyDao.getNotesListByAllEventsByCompanyId(companyId, limit, offset);
    }

    public List<Company> getCompaniesListWithoutLimit() {
        return companyDao.getCompaniesListWithoutLimit();
    }

}
