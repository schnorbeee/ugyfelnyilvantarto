package com.codingmentorteam3.controllers;

import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.dtos.ConnectionChannelDTO;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.ContactPerson;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.ContactPersonService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author schno sch.norbeee@gmail.com
 */
@BeanValidation
@RequestScoped
@ManagedBean(name = "contactPersonController")
public class ContactPersonController extends PageableEntityController<ContactPerson> {

    @Inject
    private ContactPersonService contactPersonService;
    
    public List<ConnectionChannelDTO> getChannelsByContacterId() {
        ContactPerson currentContactPerson = getEntity();
        if(null!= currentContactPerson) {
            List<ConnectionChannelDTO> connectionChannelDTOs = new ArrayList<>();
            for(ConnectionChannel ch : contactPersonService.getChannelsByContacterId(getEntityId())) {
                ConnectionChannelDTO connectionChannelDTO = new ConnectionChannelDTO(ch);
                connectionChannelDTOs.add(connectionChannelDTO);
            }
            return connectionChannelDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }
    
    @Override
    public List<ContactPerson> getEntities() {
        return contactPersonService.getContactPersonList(getLimit(), getOffset());
    }

    @Override
    protected ContactPerson loadEntity(Long entityId) {
        if (entityId != null) {
            return contactPersonService.getContactPerson(entityId);
        }
        return new ContactPerson();
    }

    @Override
    protected ContactPerson doUpdateEntity() {
        setEntity(contactPersonService.editContactPerson(getEntity()));
        return getEntity();
    }

    @Override
    protected void doPersistEntity() {
        contactPersonService.createContactPerson(getEntity());
    }

    //at kell nezni hogy helyesen legyen beirva
    @Override
    public String getNoEntityMessage() {
        return "No contact person found in database!";
    }

    @Override
    public String getListPage() {
        return "contactPerson";
    }

    @Override
    public String getNewItemOutcome() {
        return "edit/editContactPerson.xhtml";
    }
    
}
