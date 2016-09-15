package com.codingmentorteam3.controllers;

import com.codingmentorteam3.controllers.general.PageableEntityController;
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
@ManagedBean(name = "connectionChannelController")
public class ConnectionChannelController extends PageableEntityController<ConnectionChannel> {
    
    public void newItemOutcome() {
        // TODO
    }

    @Override
    protected ConnectionChannel loadEntity(Long entityId) {
        return new ConnectionChannel();
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ConnectionChannel doUpdateEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPersistEntity() {
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

    @Override
    public List<ConnectionChannel> getEntities() {
        return new ArrayList<>();
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNoEntityMessage() {
        return new String();
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
