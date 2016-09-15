package com.codingmentorteam3.controllers;

import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Person;
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
public class PersonController extends PageableEntityController<Person> {
    
    public List<ConnectionChannel> getConnectionChannels() {
        // TODO
        return new ArrayList<>();
    }

    @Override
    public List<Person> getEntities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Person loadEntity(Long entityId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Person doUpdateEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPersistEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getNoEntityMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListPage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNewItemOutcome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
