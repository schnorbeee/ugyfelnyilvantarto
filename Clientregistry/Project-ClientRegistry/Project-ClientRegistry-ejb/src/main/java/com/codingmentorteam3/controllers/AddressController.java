package com.codingmentorteam3.controllers;

import com.codingmentorteam3.daos.AddressDaoImpl;
import com.codingmentorteam3.interceptors.BeanValidation;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

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

//    public AddressDTO getAddressById() {
//        Address address = addressDao.read(id);
//        if (null != address) {
//            return new AddressDTO(address);
//        }
//        throw new BadRequestException("No address found in database!");
//    }

}
