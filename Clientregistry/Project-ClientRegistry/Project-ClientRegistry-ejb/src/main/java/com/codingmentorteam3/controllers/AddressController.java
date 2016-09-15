package com.codingmentorteam3.controllers;

import com.codingmentorteam3.interceptors.BeanValidation;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author istvan.mosonyi
 */
@BeanValidation
@SessionScoped
@ManagedBean(name = "addressController")
public class AddressController { // Kell-e ennek ez az öröklődés?

}
