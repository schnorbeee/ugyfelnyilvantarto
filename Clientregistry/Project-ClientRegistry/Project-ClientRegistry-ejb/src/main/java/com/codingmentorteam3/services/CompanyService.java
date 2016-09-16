package com.codingmentorteam3.services;

import com.codingmentorteam3.daos.CompanyDaoImpl;
import com.codingmentorteam3.entities.Company;
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
    
    public Company getCompany(Long companyId){
        return companyDao.read(companyId);
    }
    
    public Company editCompany(Company company){
        return companyDao.update(company);
    }
    
    public Company deleteCompany(Company company) {
        return companyDao.delete(company);
    }
    
    public List<Company> getCompaniesList(Integer limit, Integer offset) {
        return companyDao.getCompaniesList(limit, offset);
    }
    
    public List<Company> getCompaniesListByNameFilter(String companyName) {
        return companyDao.getCompaniesListByNameFilter(companyName);
    }
    
}
