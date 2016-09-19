package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.AddressBean;
import com.codingmentorteam3.beans.CompanyBean;
import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.dtos.AddressDTO;
import com.codingmentorteam3.dtos.CompanyDTO;
import com.codingmentorteam3.dtos.ContactPersonDTO;
import com.codingmentorteam3.dtos.EventDTO;
import com.codingmentorteam3.dtos.ProjectDTO;
import com.codingmentorteam3.entities.Address;
import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.ContactPerson;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Project;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.EntityAlreadyExistsException;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.AddressService;
import com.codingmentorteam3.services.CompanyService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author istvan.mosonyi
 */
@BeanValidation
@RequestScoped
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
            throw new EntityAlreadyExistsException("This company already exists in our database.");
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
    public CompanyDTO updateCompanyPersonalInfos(CompanyBean updateCompany, Long companyId) {
        Company oldCompany = companyService.getCompany(companyId);
        if(null != oldCompany) {
            Company currentCompany = new Company(updateCompany);
            oldCompany = modifiedCheckerCompany(oldCompany, currentCompany);
            companyService.editCompany(oldCompany);
            return new CompanyDTO(oldCompany);
        }
        throw new BadRequestException(getNoEntityMessage());
    }
    
    //user method
    public AddressDTO updateCompanyAddress(AddressBean updateAddress, Long companyId) {
        Company currentCompany = companyService.getCompany(companyId);
        if (null != currentCompany) {
            Address currentAddress = addressService.getAddressByAllParameters(updateAddress.getCity(), updateAddress.getCountry(), updateAddress.getZipCode(), updateAddress.getStreet(), updateAddress.getHouseNumber());
            Address oldAddress = currentCompany.getAddress();
            if (!oldAddress.equals(currentAddress)) {
                if (null != currentAddress) {
                    currentCompany.setAddress(currentAddress);
                    return new AddressDTO(currentAddress);
                }
                Address newAddress = new Address(updateAddress);
                addressService.createAddress(newAddress);
                currentCompany.setAddress(addressService.getAddressByAllParameters(newAddress.getCity(), newAddress.getCountry(), newAddress.getZipCode(), newAddress.getStreet(), newAddress.getHouseNumber()));
                return new AddressDTO(newAddress);
            }
            return new AddressDTO(oldAddress);
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
        List<CompanyDTO> companyDTOs = new ArrayList<>();
        for (Company c : getEntities()) {
            CompanyDTO companyDTO = new CompanyDTO(c);
            companyDTOs.add(companyDTO);
        }
        return companyDTOs;
    }
    
    //user method
    public List<ProjectDTO> getProjectsListByCompanyId(Long companyId) {
        Company currentCompany = companyService.getCompany(companyId);
        if (null != currentCompany) {
            List<ProjectDTO> projectDTOs = new ArrayList<>();
            for (Project p : companyService.getProjectsListByCompanyId(companyId)) {
                ProjectDTO projectDTO = new ProjectDTO(p);
                projectDTOs.add(projectDTO);
            }
            return projectDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }
    
    //user method
    public List<EventDTO> getEventsListByCompanyId(Long companyId) {
        Company currentCompany = companyService.getCompany(companyId);
        if (null != currentCompany) {
            List<EventDTO> eventDTOs = new ArrayList<>();
            for (Event e : companyService.getEventsListByCompanyId(companyId)) {
                EventDTO eventDTO = new EventDTO(e);
                eventDTOs.add(eventDTO);
            }
            return eventDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }
    
    //user method
    public List<ContactPersonDTO> getContactersListByCompanyId(Long companyId) {
        Company currentCompany = companyService.getCompany(companyId);
        if (null != currentCompany) {
            List<ContactPersonDTO> contactPersonDTOs = new ArrayList<>();
            for (ContactPerson cp : companyService.getContactersListByCompanyId(companyId)) {
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

    public Company modifiedCheckerCompany(Company oldCompany, Company currentCompany) {
        if (!currentCompany.getName().equals("")) {
            oldCompany.setName(currentCompany.getName());
        }
        if (!currentCompany.getTaxNumber().equals("")) {
            oldCompany.setTaxNumber(currentCompany.getTaxNumber());
        }
        return oldCompany;
    }

}
