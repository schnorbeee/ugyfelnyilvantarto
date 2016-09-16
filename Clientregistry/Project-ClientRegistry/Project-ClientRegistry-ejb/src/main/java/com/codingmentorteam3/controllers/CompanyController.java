package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.AddressBean;
import com.codingmentorteam3.beans.CompanyBean;
import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.dtos.AddressDTO;
import com.codingmentorteam3.dtos.CompanyDTO;
import com.codingmentorteam3.entities.Address;
import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.AddressService;
import com.codingmentorteam3.services.CompanyService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response registrateCompany(CompanyBean regCompany, AddressBean regAddress) {
        Company newCompany = new Company(regCompany);
        Address newAddress = new Address(regAddress);
        if(!companyService.getCompaniesListByTaxFilter(newCompany.getTaxNumber()).isEmpty()) {
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }
        if(null != addressService.getAddressByAllParameters(newAddress.getCity(), newAddress.getCountry(), newAddress.getZipCode(), newAddress.getStreet(), newAddress.getHouseNumber())) {
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }
        addressService.createAddress(newAddress);
        newCompany.setAddress(newAddress);
        companyService.createCompany(newCompany);
        AddressDTO addressDto = new AddressDTO(newAddress);
        CompanyDTO companyDto = new CompanyDTO(newCompany);
        return Response.status(Response.Status.CREATED).entity(addressDto).entity(companyDto).type(MediaType.APPLICATION_JSON).build();       
    }
    
    //user method
    public Response getCompanyById(@QueryParam("companyId") Long companyId) {
        Company company = companyService.getCompany(companyId);
        if (null != company) {
            CompanyDTO dto = new CompanyDTO(company);
            return Response.status(Response.Status.FOUND).entity(dto).type(MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    //user method
    public Response updateCompany(CompanyBean updateCompany, AddressBean updateAddress, @QueryParam("companyId") Long id) {
        if (null != updateCompany || null != updateAddress) {
            Company currentCompany = new Company(updateCompany);
            Company oldCompany = companyService.getCompany(id);
            Address currentAddress = new Address(updateAddress);
            Address oldAddress = oldCompany.getAddress();
            if (null != companyService.getCompany(id)) {
                oldCompany = modifiedCheckerCompany(oldCompany, currentCompany, oldAddress, currentAddress);
                companyService.editCompany(oldCompany);
                CompanyDTO companyDto = new CompanyDTO(oldCompany);
                AddressDTO addressDto = new AddressDTO(oldCompany.getAddress());
                return Response.status(Response.Status.ACCEPTED).entity(companyDto).entity(addressDto).type(MediaType.APPLICATION_JSON).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    
    //admin method
    public Response deleteCompanyById(@QueryParam("companyId") Long id) {
        Company deleteCompany = companyService.getCompany(id);
        if (null != deleteCompany) {
            companyService.deleteCompany(deleteCompany);
            CompanyDTO dto = new CompanyDTO(deleteCompany);
            return Response.status(Response.Status.ACCEPTED).entity(dto).type(MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
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
