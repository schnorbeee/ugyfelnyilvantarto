package com.codingmentorteam3.controllers;

import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.interceptors.BeanValidation;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author istvan.mosonyi
 */
@BeanValidation
@SessionScoped
@ManagedBean(name = "personController")
public class PersonController {
    
    public List<ConnectionChannel> getConnectionChannels() {
        // TODO
        return new ArrayList<>();
    }
    
}
