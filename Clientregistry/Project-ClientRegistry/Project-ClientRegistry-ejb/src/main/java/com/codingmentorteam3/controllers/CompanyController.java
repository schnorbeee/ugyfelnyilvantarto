package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.AddressBean;
import com.codingmentorteam3.beans.CompanyBean;
import com.codingmentorteam3.beans.ProjectBean;
import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.dtos.AddressDTO;
import com.codingmentorteam3.dtos.CompanyDTO;
import com.codingmentorteam3.dtos.ContactPersonDTO;
import com.codingmentorteam3.dtos.EventDTO;
import com.codingmentorteam3.dtos.NoteDTO;
import com.codingmentorteam3.dtos.ProjectDTO;
import com.codingmentorteam3.entities.Address;
import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.ContactPerson;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Note;
import com.codingmentorteam3.entities.Project;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.EntityAlreadyExistsException;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.AddressService;
import com.codingmentorteam3.services.CompanyService;
import com.codingmentorteam3.services.EventService;
import com.codingmentorteam3.services.ProjectService;
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

    @Inject
    private EventService eventService;

    @Inject
    private ProjectService projectService;

    //user method/admin accepted MUKODIK
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
            return getListPage();
        }
        addressService.createAddress(newAddress);
        newCompany.setAddress(newAddress);
        companyService.createCompany(newCompany);
        return getListPage();
    }

//    //user method EGYENLORE MARAD HATHA SZUKSEG LESZ RA DTOS
//    public String getCompanyById(Long companyId) {
//        Company company = companyService.getCompany(companyId);
//        if (null != company) {
//            return getListPage();
//        }
//        throw new BadRequestException(getNoEntityMessage());
//    }
    //user method FASZA
    public CompanyDTO updateCompanyPersonalInfos(CompanyBean updateCompany) {
        Company oldCompany = getEntity();
        if (null != oldCompany) {
            Company currentCompany = new Company(updateCompany);
            oldCompany = modifiedCheckerCompany(oldCompany, currentCompany);
            setEntity(oldCompany);
            return new CompanyDTO(oldCompany);
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method EGYENLORE FASZA
    public AddressDTO updateCompanyAddress(AddressBean updateAddress) {
        Company currentCompany = getEntity();
        if (null != currentCompany) {
            Address currentAddress = addressService.getAddressByAllParameters(updateAddress.getCity(), updateAddress.getCountry(), updateAddress.getZipCode(), updateAddress.getStreet(), updateAddress.getHouseNumber());
            Address oldAddress = currentCompany.getAddress();
            if (oldAddress.equals(currentAddress)) {
                return new AddressDTO(oldAddress);
            }
            if (null == currentAddress) {
                Address newAddress = new Address(updateAddress);
                addressService.createAddress(newAddress);
                currentCompany.setAddress(newAddress);
                setEntity(currentCompany);
                return new AddressDTO(newAddress);
            } else {
                currentCompany.setAddress(currentAddress);
                setEntity(currentCompany);
                return new AddressDTO(currentAddress);
            }
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method
    public CompanyDTO changeAvatar(String newLogo) {
        Company currentCompany = getEntity();
        if (null != currentCompany) {
            currentCompany.setLogo(newLogo);
            companyService.editCompany(currentCompany);
            return new CompanyDTO(currentCompany);
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //admin method
    public List<CompanyDTO> deleteCompanyById(Long companyId) {
        Company deleteCompany = companyService.getCompany(companyId);
        if (null != deleteCompany) {
            companyService.deleteCompany(deleteCompany);
            if (!isAnyCompanyAndEventUseThisAddress(deleteCompany.getAddress())) {
                addressService.deleteAddress(deleteCompany.getAddress());
            }
            List<CompanyDTO> companyDTOs = new ArrayList<>();
            for (Company c : getEntities()) {
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
    public List<ProjectDTO> createProject(ProjectBean regProject) {
        Project newProject = new Project(regProject);
        Company currentCompany = getEntity();
        for (Project p : currentCompany.getProjects()) {
            if (newProject.getName().equals(p.getName()) && newProject.getDescription().equals(p.getDescription())) {
                throw new EntityAlreadyExistsException("This project already exists in our database.");
            }
        }
        newProject.getCompanies().add(currentCompany);
        projectService.createProject(newProject);
        currentCompany.getProjects().add(newProject);
        companyService.editCompany(currentCompany);
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for (Project p : projectService.getProjectsList(getLimit(), getOffset())) {
            ProjectDTO projectDTO = new ProjectDTO(p);
            projectDTOs.add(projectDTO);
        }
        return projectDTOs;
    }

    //user method
    public List<ProjectDTO> getProjectsListByCompanyId() {
        Company currentCompany = getEntity();
        if (null != currentCompany) {
            List<ProjectDTO> projectDTOs = new ArrayList<>();
            for (Project p : companyService.getProjectsListByCompanyId(getEntityId())) {
                ProjectDTO projectDTO = new ProjectDTO(p);
                projectDTOs.add(projectDTO);
            }
            return projectDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method FASZA EDDIG
    public List<EventDTO> getEventsListByCompanyId() {
        Company currentCompany = getEntity();
        if (null != currentCompany) {
            List<EventDTO> eventDTOs = new ArrayList<>();
            for (Event e : companyService.getEventsListByCompanyId(getEntityId())) {
                EventDTO eventDTO = new EventDTO(e);
                eventDTOs.add(eventDTO);
            }
            return eventDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method
    public List<ContactPersonDTO> getContactersListByCompanyId() {
        Company currentCompany = getEntity();
        if (null != currentCompany) {
            List<ContactPersonDTO> contactPersonDTOs = new ArrayList<>();
            for (ContactPerson cp : companyService.getContactersListByCompanyId(getEntityId())) {
                ContactPersonDTO contactPersonDTO = new ContactPersonDTO(cp);
                contactPersonDTOs.add(contactPersonDTO);
            }
            return contactPersonDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    public List<NoteDTO> getNotesListByAllEventsByCompanyId() {
        Company currentCompany = getEntity();
        if (null != currentCompany) {
            List<NoteDTO> noteDTOs = new ArrayList<>();
            for (Note n : companyService.getNotesListByAllEventsByCompanyId(getEntityId(), getLimit(), getOffset())) {
                NoteDTO noteDTO = new NoteDTO(n);
                noteDTOs.add(noteDTO);
            }
            return noteDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    public List<CompanyDTO> getInactiveCompaniesList(Integer n) {
        if (null != n) {
            List<CompanyDTO> companyDTOs = new ArrayList<>();
            for (Company c : companyService.getInactiveCompaniesList(n, getLimit(), getOffset())) {
                CompanyDTO companyDTO = new CompanyDTO(c);
                companyDTOs.add(companyDTO);
            }
            return companyDTOs;
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
        setEntity(companyService.editCompany(getEntity()));
        return getEntity();
    }

    @Override
    protected void doPersistEntity() {
        companyService.createCompany(getEntity());
    }

    //at kell nezni hogy helyesen legyen beirva
    @Override
    public String getNoEntityMessage() {
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

    private Company modifiedCheckerCompany(Company oldCompany, Company currentCompany) {
        if (!currentCompany.getName().equals("")) {
            oldCompany.setName(currentCompany.getName());
        }
        if (!currentCompany.getTaxNumber().equals("")) {
            oldCompany.setTaxNumber(currentCompany.getTaxNumber());
        }
        return oldCompany;
    }

    private boolean isAnyCompanyAndEventUseThisAddress(Address address) {
        for (Company c : getEntities()) {
            if (address.equals(c.getAddress())) {
                return true;
            }
        }
        for (Event e : eventService.getEventsListWithoutLimit()) {
            if (address.equals(e.getAddress())) {
                return true;
            }
        }
        return false;
    }

}
