package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.ConnectionChannelBean;
import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.dtos.ConnectionChannelDTO;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.ConnectionChannelService;
import com.codingmentorteam3.services.PersonService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@BeanValidation
@RequestScoped
@ManagedBean(name = "connectionChannelController")
public class ConnectionChannelController extends PageableEntityController<ConnectionChannel> {

    @Inject
    private ConnectionChannelService connectionChannelService;

    @Inject
    private PersonService personService;

    public ConnectionChannelDTO updateConnectionChannel(ConnectionChannelBean newConnectionChannelBean, Long connectionChannelId) {
        ConnectionChannel newConnectionChannel = new ConnectionChannel(newConnectionChannelBean);
        ConnectionChannel currentConnectionChannel = loadEntity(connectionChannelId);
        if (null != currentConnectionChannel) {
            currentConnectionChannel.setValue(newConnectionChannel.getValue());
            setEntity(newConnectionChannel);
            saveEntity();
            return new ConnectionChannelDTO(currentConnectionChannel);
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    public List<ConnectionChannelDTO> deleteConnectionChannelById(Long connectionChannelId) {
        ConnectionChannel deleteConnectionChannel = loadEntity(connectionChannelId);
        if (null != deleteConnectionChannel) {
            connectionChannelService.deleteConnectionChannel(deleteConnectionChannel);
            List<ConnectionChannelDTO> connectionChannelDTOs = new ArrayList<>();
            for (ConnectionChannel cc : personService.getConnectionChannelsListByPersonId(deleteConnectionChannel.getOwner().getId())) {
                ConnectionChannelDTO connectionChannelDTO = new ConnectionChannelDTO(cc);
                connectionChannelDTOs.add(connectionChannelDTO);
            }
            return connectionChannelDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    @Override
    public List<ConnectionChannel> getEntities() {
        return connectionChannelService.getConnectionChannelList(getLimit(), getOffset());
    }

    @Override
    protected ConnectionChannel loadEntity(Long entityId) {
        if (entityId != null) {
            return connectionChannelService.getConnectionChannel(entityId);
        }
        return null;
    }

    @Override
    protected ConnectionChannel doUpdateEntity() {
        setEntity(connectionChannelService.editConnectionChannel(getEntity()));
        return getEntity();
    }

    @Override
    protected void doPersistEntity() {
        connectionChannelService.createConnectionChannel(getEntity());
    }

    //at kell nezni hogy helyesen legyen beirva
    @Override
    public String getNoEntityMessage() {
        return "No connection channel found in database!";
    }

    @Override
    public String getListPage() {
        return "connectionchannels";
    }

    @Override
    public String getNewItemOutcome() {
        return "edit/editConnectionChannel.xhtml";
    }

}
