package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.AddressBean;
import com.codingmentorteam3.beans.CompanyBean;
import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.dtos.CompanyDTO;
import com.codingmentorteam3.dtos.ContactPersonDTO;
import com.codingmentorteam3.entities.Address;
import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.ContactPerson;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.EntityAlreadyExistsException;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.AddressService;
import com.codingmentorteam3.services.CompanyService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author istvan.mosonyi
 */
@BeanValidation
@SessionScoped
@ManagedBean(name = "companyController")
public class CompanyController extends PageableEntityController<Company> {

    @Inject
    private CompanyService companyService;

    @Inject
    private AddressService addressService;

    //user method/admin accepted
    public String createCompany(CompanyBean regCompany, AddressBean regAddress) {
        Company newCompany = new Company(regCompany);
        Address newAddress = new Address(regAddress);
        if (!companyService.getCompaniesListByTaxFilter(newCompany.getTaxNumber(), getLimit(), getOffset()).isEmpty()) {
            throw new EntityAlreadyExistsException("This company is already exist in our database.");
        }
        Address oldAddress = addressService.getAddressByAllParameters(newAddress.getCity(), newAddress.getCountry(), newAddress.getZipCode(), newAddress.getStreet(), newAddress.getHouseNumber());
        if (null != oldAddress) {
            newCompany.setAddress(oldAddress);
            companyService.createCompany(newCompany);
            return "";
        }
        addressService.createAddress(newAddress);
        newCompany.setAddress(newAddress);
        companyService.createCompany(newCompany);
        return "";
    }

    //user method
    public String getCompanyById(Long companyId) {
        Company company = companyService.getCompany(companyId);
        if (null != company) {
            return "";
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method
    public CompanyDTO updateCompany(CompanyBean updateCompany, AddressBean updateAddress, Long companyId) {
        Company oldCompany = companyService.getCompany(companyId);
        if(null != oldCompany) {
            Company currentCompany = new Company(updateCompany);
            Address currentAddress = new Address(updateAddress);
            Address oldAddress = oldCompany.getAddress();
            oldCompany = modifiedCheckerCompany(oldCompany, currentCompany, oldAddress, currentAddress);
            companyService.editCompany(oldCompany);
            return new CompanyDTO(oldCompany);
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //admin method
    public List<CompanyDTO> deleteCompanyById(Long companyId) {
        Company deleteCompany = companyService.getCompany(companyId);
        if (null != deleteCompany) {
            companyService.deleteCompany(deleteCompany);
            List<CompanyDTO> companyDTOs = new ArrayList<>();
            for(Company c : getEntities()) {
                CompanyDTO companyDTO = new CompanyDTO(c);
                companyDTOs.add(companyDTO);
            }
            return companyDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method
    public List<CompanyDTO> getCompaniesList() {
        List<CompanyDTO> companyDTOs = new ArrayList();
        for (Company c : getEntities()) {
            CompanyDTO companyDTO = new CompanyDTO(c);
            companyDTOs.add(companyDTO);
        }
        return companyDTOs;
    }
    
    //user method
    public List<ContactPersonDTO> getContactersListByCompanyId(Long companyId) {
        Company currentCompany = companyService.getCompany(companyId);
        if (null != currentCompany) {
            List<ContactPersonDTO> contactPersonDTOs = new ArrayList<>();
            List<ContactPerson> contactPersons = companyService.getContactersListByCompanyId(companyId);
            for (ContactPerson cp : contactPersons) {
                ContactPersonDTO contactPersonDTO = new ContactPersonDTO(cp);
                contactPersonDTOs.add(contactPersonDTO);
            }
            return contactPersonDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    @Override
    public List<Company> getEntities() {
        return companyService.getCompaniesList(getLimit(), getOffset());
    }

    @Override
    protected Company loadEntity(Long entityId) {
        if (entityId != null) {
            return companyService.getCompany(entityId);
        }
        return new Company();
    }

    @Override
    protected Company doUpdateEntity() {
        companyService.editCompany(getEntity());
        return getEntity();
    }

    @Override
    protected void doPersistEntity() {
        companyService.createCompany(getEntity());
    }

    //at kell nezni hogy helyesen legyen beirva
    @Override
    protected String getNoEntityMessage() {
        return "No company found in database!";
    }

    @Override
    public String getListPage() {
        return "companies";
    }

    @Override
    public String getNewItemOutcome() {
        return "edit/editCompany.xhtml";
    }

    public Company modifiedCheckerCompany(Company oldCompany, Company currentCompany, Address oldAddress, Address currentAddress) {
        if (!currentCompany.getName().equals("")) {
            oldCompany.setName(currentCompany.getName());
        }
        if (!currentCompany.getTaxNumber().equals("")) {
            oldCompany.setTaxNumber(currentCompany.getTaxNumber());
        }
        Address newAddress = modifiedCheckerAddress(oldAddress, currentAddress);
        if (!currentCompany.getAddress().equals(newAddress)) {
            oldCompany.setAddress(newAddress);
        }
        return oldCompany;
    }

    public Address modifiedCheckerAddress(Address oldAddress, Address currentAddress) {
        if (!currentAddress.getCity().equals("")) {
            oldAddress.setCity(currentAddress.getCity());
        }
        if (!currentAddress.getCountry().equals("")) {
            oldAddress.setCountry(currentAddress.getCountry());
        }
        if (!currentAddress.getZipCode().equals("")) {
            oldAddress.setZipCode(currentAddress.getZipCode());
        }
        if (!currentAddress.getStreet().equals("")) {
            oldAddress.setStreet(currentAddress.getStreet());
        }
        if (!currentAddress.getHouseNumber().equals("")) {
            oldAddress.setHouseNumber(currentAddress.getHouseNumber());
        }
        addressService.editAddress(oldAddress);
        return oldAddress;
    }

}
