package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.AddressBean;
import com.codingmentorteam3.beans.CompanyBean;
import com.codingmentorteam3.beans.ContactPersonBean;
import com.codingmentorteam3.beans.EventBean;
import com.codingmentorteam3.beans.ProjectBean;
import com.codingmentorteam3.beans.SaveBean;
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
import com.codingmentorteam3.services.ContactPersonService;
import com.codingmentorteam3.services.EventService;
import com.codingmentorteam3.services.ProjectService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author istvan.mosonyi
 */
@BeanValidation
@ViewScoped
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

    @Inject
    private ContactPersonService contactPersonService;

    @ManagedProperty(value = "#{saveBean}")
    private SaveBean saveBean;

    public SaveBean getSaveBean() {
        return saveBean;
    }

    public void setSaveBean(SaveBean saveBean) {
        this.saveBean = saveBean;
    }
    
    private final List<CompanyDTO> companyDTOs = new ArrayList<>();

    public List<CompanyDTO> getCompanyDTOs() {
        return companyDTOs;
    }

    public String createCompany(CompanyBean regCompany, AddressBean regAddress) {
        Company newCompany = new Company(regCompany);
        if (!companyService.getCompaniesListByTaxFilter(newCompany.getTaxNumber(), 
                getLimit(), getOffset()).isEmpty()) {
            throw new EntityAlreadyExistsException("This company already exists "
                    + "in our database.");
        }
        Address address = needCorrectAddress(regAddress);
        newCompany.setAddress(address);
        setEntity(newCompany);
        return saveEntity();
    }
    
    public Address needCorrectAddress(AddressBean regAddress) {
        Address newAddress = new Address(regAddress);
        Address oldAddress = addressService.getAddressByAllParameters(newAddress
                .getCity(), newAddress.getCountry(), newAddress.getZipCode(), 
                newAddress.getStreet(), newAddress.getHouseNumber());
        if (null != oldAddress) {
            return oldAddress;
        }
        addressService.createAddress(newAddress);
        return newAddress;
    }
    
    public CompanyBean getCompanyBeanById() {
        Company company = loadEntity(getEntityId());
        if (null != company) {
            return new CompanyBean(company.getName(), company.getTaxNumber());
        }
        throw new BadRequestException(getNoEntityMessage());
    }
    
    public AddressBean getAddressBeanByCompanyId() {
        Company company = loadEntity(getEntityId());
        if (null != company) {
            return new AddressBean(company.getAddress().getCountry(), company.getAddress().getCity(), company.getAddress().getStreet(), company.getAddress().getHouseNumber(), company.getAddress().getZipCode());
        }
        throw new BadRequestException(getNoEntityMessage());
    }
    
    public CompanyDTO getCompanyById() {
        Company company = loadEntity(getEntityId());
        if (null != company) {
            return new CompanyDTO(company);
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    public AddressDTO getAddressByCompanyId() {
        Address address = addressService.getAddress(getEntity().getAddress().getId());
        if (null != address) {
            return new AddressDTO(address);
        }
        throw new BadRequestException("No address found in database!");
    }

    //user method FASZA
    public String updateCompanyPersonalInfos(CompanyBean updateCompany, AddressBean updateAddress, Long companyId) {
        Company oldCompany = loadEntity(companyId);
        if (null != oldCompany) {
            Company currentCompany = new Company(updateCompany);
            oldCompany = modifiedCheckerCompany(oldCompany, currentCompany);
            setEntity(oldCompany);
            Address modifiedAddress = updateCompanyAddress(updateAddress, oldCompany);
            oldCompany.setAddress(modifiedAddress);
            return saveEntity();
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method SZEDJED SEJEL
    public Address updateCompanyAddress(AddressBean updateAddress, Company oldCompany) {
        Address currentAddress = addressService.getAddressByAllParameters(updateAddress.getCity(), updateAddress.getCountry(), updateAddress.getZipCode(), updateAddress.getStreet(), updateAddress.getHouseNumber());
        Address oldAddress = oldCompany.getAddress();
        if (oldAddress.equals(currentAddress)) {
            return oldAddress;
        }
        if (null != currentAddress && oldAddress.getCompanies().size() == 1 && oldAddress.getEvents().isEmpty()) {
            addressService.deleteAddress(oldAddress);
            return currentAddress;
        }
        if (oldAddress.getCompanies().size() == 1 && oldAddress.getEvents().isEmpty()) {
            oldAddress = new Address(updateAddress);
            addressService.editAddress(oldAddress);
            return oldAddress;
        } else {
            Address newAddress = new Address(updateAddress);
            addressService.createAddress(newAddress);
            return newAddress;
        }
    }

    //user method
    public CompanyDTO changeLogo(String newLogo, Long companyId) {
        Company currentCompany = loadEntity(companyId);
        if (null != currentCompany) {
            currentCompany.setLogo(newLogo);
            setEntity(currentCompany);
            saveEntity();
            return new CompanyDTO(currentCompany);
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //admin method
    public List<CompanyDTO> deleteCompanyById(Long companyId) {
        companyDTOs.clear();
        Company deleteCompany = loadEntity(companyId);
        if (null != deleteCompany) {
            Address deleteAddress = deleteCompany.getAddress();
            deleteAddress.getCompanies().remove(deleteCompany);
            addressService.editAddress(deleteAddress);
            companyService.deleteCompany(deleteCompany);
            if (!isAnyCompanyAndEventUseThisAddress(deleteAddress)) {
                addressService.deleteAddress(deleteAddress);
            }
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
        companyDTOs.clear();
        for (Company c : getEntities()) {
            CompanyDTO companyDTO = new CompanyDTO(c);
            companyDTOs.add(companyDTO);
        }
        return companyDTOs;
    }

    public List<EventDTO> createEvent(EventBean regEvent, AddressBean regAddress, Long companyId) {
        Event newEvent = new Event(regEvent);
        Address newAddress = new Address(regAddress);
        Company oldCompany = loadEntity(companyId);
        setEntity(oldCompany);
        if (!eventService.getEventsListByStringFilter(newEvent.getTitle(), getLimit(), getOffset()).isEmpty()) {
            throw new EntityAlreadyExistsException("This event already exists in our database.");
        }
        Address oldAddress = addressService.getAddressByAllParameters(newAddress.getCity(), newAddress.getCountry(), newAddress.getZipCode(), newAddress.getStreet(), newAddress.getHouseNumber());
        if (null != oldAddress) {
            newEvent.setAddress(oldAddress);
            newEvent.setCompany(oldCompany);
            eventService.createEvent(newEvent);
            companyService.getEventsListByCompanyId(getEntityId(), getLimit(), getOffset()).add(newEvent);
            saveEntity();
        } else {
            addressService.createAddress(newAddress);
            newEvent.setAddress(newAddress);
            newEvent.setCompany(oldCompany);
            eventService.createEvent(newEvent);
            companyService.getEventsListByCompanyId(getEntityId(), getLimit(), getOffset()).add(newEvent);
            saveEntity();
        }
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event e : oldCompany.getEvents()) {
            EventDTO eventDTO = new EventDTO(e);
            eventDTOs.add(eventDTO);
        }
        return eventDTOs;
    }

    public List<ContactPersonDTO> addContactPerson(ContactPersonBean regContactPerson, Long companyId) {
        ContactPerson newContactPerson = new ContactPerson(regContactPerson);
        Company currentCompany = loadEntity(companyId);
        for (ContactPerson cp : currentCompany.getContacters()) {
            if (newContactPerson.getFirstName().equals(cp.getFirstName()) && newContactPerson.getLastName().equals(cp.getLastName())) {
                throw new EntityAlreadyExistsException("This contact person is already added to this company.");
            }
        }
        newContactPerson.setCompany(currentCompany);
        contactPersonService.createContactPerson(newContactPerson);
        companyService.getContactersListByCompanyId(getEntityId(), getLimit(), getOffset()).add(newContactPerson);
        setEntity(currentCompany);
        saveEntity();
        List<ContactPersonDTO> contactPersonDTOs = new ArrayList<>();
        for (ContactPerson cp : currentCompany.getContacters()) {
            ContactPersonDTO contactPersonDTO = new ContactPersonDTO(cp);
            contactPersonDTOs.add(contactPersonDTO);
        }
        return contactPersonDTOs;
    }

    //user method SZEDJED EZT IS
    public List<ProjectDTO> createProject(ProjectBean regProject, Long companyId) {
        Project newProject = new Project(regProject);
        Company currentCompany = loadEntity(companyId);
        boolean haveProject = false;
        for (Project p : companyService.getProjectsListByCompanyId(getEntityId(), getLimit(), getOffset())) {
            if (newProject.getName().equals(p.getName()) && newProject.getDescription().equals(p.getDescription())) {
                throw new EntityAlreadyExistsException("This project is already added to this company.");
            }
        }
        for (Project p : projectService.getProjectsList(getLimit(), getOffset())) {
            if (newProject.getName().equals(p.getName()) && newProject.getStartDate().equals(p.getStartDate()) && newProject.getDeadline().equals(p.getDeadline())) {
                projectService.getCompaniesListByProjectId(newProject.getId()).add(currentCompany);
                projectService.editProject(newProject);
                companyService.getProjectsListByCompanyId(getEntityId(), getLimit(), getOffset()).add(newProject);
                setEntity(currentCompany);
                saveEntity();
                haveProject = true;
            }
        }
        if (!haveProject) {
            projectService.createProject(newProject);
            newProject.getCompanies().add(currentCompany);
            projectService.editProject(newProject);
            companyService.getProjectsListByCompanyId(getEntityId(), getLimit(), getOffset()).add(newProject);
            setEntity(currentCompany);
            saveEntity();
        }
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for (Project p : currentCompany.getProjects()) {
            ProjectDTO projectDTO = new ProjectDTO(p);
            projectDTOs.add(projectDTO);
        }
        return projectDTOs;
    }

    //user method
    public List<ProjectDTO> getProjectsListByCompanyId(Long companyId) {
        Company currentCompany = loadEntity(companyId);
        if (null != currentCompany) {
            List<ProjectDTO> projectDTOs = new ArrayList<>();
            for (Project p : companyService.getProjectsListByCompanyId(getEntityId(), getLimit(), getOffset())) {
                ProjectDTO projectDTO = new ProjectDTO(p);
                projectDTOs.add(projectDTO);
            }
            return projectDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method FASZA EDDIG
    public List<EventDTO> getEventsListByCompanyId(Long companyId) {
        Company currentCompany = loadEntity(companyId);
        if (null != currentCompany) {
            List<EventDTO> eventDTOs = new ArrayList<>();
            for (Event e : companyService.getEventsListByCompanyId(getEntityId(), getLimit(), getOffset())) {
                EventDTO eventDTO = new EventDTO(e);
                eventDTOs.add(eventDTO);
            }
            return eventDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method
    public List<ContactPersonDTO> getContactersListByCompanyId(Long companyId) {
        Company currentCompany = loadEntity(companyId);
        if (null != currentCompany) {
            List<ContactPersonDTO> contactPersonDTOs = new ArrayList<>();
            for (ContactPerson cp : companyService.getContactersListByCompanyId(getEntityId(), getLimit(), getOffset())) {
                ContactPersonDTO contactPersonDTO = new ContactPersonDTO(cp);
                contactPersonDTOs.add(contactPersonDTO);
            }
            return contactPersonDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    public List<NoteDTO> getNotesListByAllEventsByCompanyId(Long companyId) {
        Company currentCompany = loadEntity(companyId);
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
        companyDTOs.clear();
        if (null != n) {
            for (Company c : companyService.getInactiveCompaniesList(n, getLimit(), getOffset())) {
                CompanyDTO companyDTO = new CompanyDTO(c);
                companyDTOs.add(companyDTO);
            }
            return companyDTOs;
        }
        throw new BadRequestException("The month number, what you want is invalid.");
    }

    public List<CompanyDTO> getCompaniesListByNameFilter(String name) {
        companyDTOs.clear();
        for (Company c : companyService.getCompaniesListByNameFilter(name, getLimit(), getOffset())) {
            CompanyDTO companyDTO = new CompanyDTO(c);
            companyDTOs.add(companyDTO);
        }
        return companyDTOs;
    }

    public List<CompanyDTO> getCompaniesListByTaxFilter(String taxNumber) {
        companyDTOs.clear();
        for (Company c : companyService.getCompaniesListByNameFilter(taxNumber, getLimit(), getOffset())) {
            CompanyDTO companyDTO = new CompanyDTO(c);
            companyDTOs.add(companyDTO);
        }
        return companyDTOs;
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
        return null;
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
        return "manageCompanies";
    }

    @Override
    public String getNewItemOutcome() {
        return "edit/editCompany.xhtml";
    }

    private Company modifiedCheckerCompany(Company oldCompany, Company currentCompany) {
        if (!"".equals(currentCompany.getName()) || !currentCompany.getName().equals(oldCompany.getName())) {
            oldCompany.setName(currentCompany.getName());
        }
        if (!"".equals(currentCompany.getTaxNumber()) || !currentCompany.getTaxNumber().equals(oldCompany.getTaxNumber())) {
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
