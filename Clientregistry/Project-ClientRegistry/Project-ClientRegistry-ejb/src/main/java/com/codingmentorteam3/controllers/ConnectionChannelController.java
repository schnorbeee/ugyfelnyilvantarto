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
@ManagedBean(name = "connectionChannelController")
public class ConnectionChannelController {
    
    public void newItemOutcome() {
        // TODO
    }

}
