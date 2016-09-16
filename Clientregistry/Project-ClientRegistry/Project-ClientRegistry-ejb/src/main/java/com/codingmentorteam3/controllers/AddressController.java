package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.AddressBean;
import com.codingmentorteam3.daos.AddressDaoImpl;
import com.codingmentorteam3.dtos.AddressDTO;
import com.codingmentorteam3.entities.Address;
import com.codingmentorteam3.interceptors.BeanValidation;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@BeanValidation
@RequestScoped
@ManagedBean(name = "addressController")
public class AddressController {

    @Inject
    private AddressDaoImpl addressDao;

    public Response createNewAddress(AddressBean newAddress) {
        if (null != newAddress) {
            Address address = new Address(newAddress);
            for (Address a : addressDao.getAddressList()) {
                AddressBean addressBean = new AddressBean(a.getCountry(), a.getCity(), a.getStreet(), a.getHouseNumber(), a.getZipCode());
                if (newAddress.equals(addressBean)) {
                    return Response.status(Response.Status.PRECONDITION_FAILED).build();
                }
            }
            addressDao.create(address);
            AddressDTO dto = new AddressDTO(address);
            return Response.ok(dto, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    public Response getAddressById(@QueryParam("address_id") Long id) {
        Address address = addressDao.read(id);
        if (null != address) {
            AddressDTO dto = new AddressDTO(address);
            return Response.ok(dto, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    public Response updateAddress(AddressBean updateAddress) {
        if (null != updateAddress) {
            Address currentAddress = new Address(updateAddress);
            for (Address a : addressDao.getAddressList()) {
                AddressBean addressBean = new AddressBean(a.getCountry(), a.getCity(), a.getStreet(), a.getHouseNumber(), a.getZipCode());
                if (updateAddress.equals(addressBean)) {
                    Address oldAddress = addressDao.read(a.getId());
                    oldAddress.setCity(currentAddress.getCity());
                    oldAddress.setCountry(currentAddress.getCountry());
                    oldAddress.setHouseNumber(currentAddress.getHouseNumber());
                    oldAddress.setStreet(currentAddress.getStreet());
                    oldAddress.setZipCode(currentAddress.getZipCode());
                    addressDao.update(oldAddress);
                    AddressDTO dto = new AddressDTO(oldAddress);
                    return Response.ok(dto, MediaType.APPLICATION_JSON).build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    public Response deleteAddressById(@QueryParam("address_id") Long id) {
        Address deleteAddress = addressDao.read(id);
        if (null != deleteAddress) {
            addressDao.delete(deleteAddress);
            AddressDTO dto = new AddressDTO(deleteAddress);
            return Response.ok(dto, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
