package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.ConnectionChannelBean;
import com.codingmentorteam3.dtos.ConnectionChannelDTO;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.EntityAlreadyExistsException;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.ConnectionChannelService;
import com.codingmentorteam3.services.UserService;
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
public class ConnectionChannelController {

    @Inject
    private ConnectionChannelService connectionChannelService;

    @Inject
    private UserService userService;

    public String createNewConnectionChannel(ConnectionChannelBean newConnectionChannel, Long ownerId) {
        ConnectionChannel regConnectionChannel = new ConnectionChannel(newConnectionChannel);
        for (ConnectionChannel cc : connectionChannelService.getConnectionChannelListByOwnerId(ownerId)) {
            ConnectionChannelBean connectionChannelBean = new ConnectionChannelBean(cc.getType(), cc.getValue(), cc.getOwner());
            if (newConnectionChannel.equals(connectionChannelBean)) {
                throw new EntityAlreadyExistsException("This connection channel already exists in database.");
            }
        }
        regConnectionChannel.setOwner(userService.getUser(1L));
        connectionChannelService.createConnectionChannel(regConnectionChannel);
        return "";
    }

    public ConnectionChannelDTO updateConnectionChannel(ConnectionChannelBean newConnectionChannelBean, Long connectionChannelId) {
        ConnectionChannel newConnectionChannel = new ConnectionChannel(newConnectionChannelBean);
        ConnectionChannel currentConnectionChannel = connectionChannelService.getConnectionChannel(connectionChannelId);
        if (null != currentConnectionChannel) {
            currentConnectionChannel.setValue(newConnectionChannel.getValue());
            connectionChannelService.editConnectionChannel(currentConnectionChannel);
            return new ConnectionChannelDTO(currentConnectionChannel);
        }
        throw new BadRequestException("No connection channel found in database!");
    }

    public List<ConnectionChannelDTO> deleteConnectionChannelById(Long connectionChannelId, Long ownerId) {
        ConnectionChannel deleteConnectionChannel = connectionChannelService.getConnectionChannel(connectionChannelId);
        if (null != deleteConnectionChannel) {
            connectionChannelService.deleteConnectionChannel(deleteConnectionChannel);
            List<ConnectionChannelDTO> connectionChannelDTOs = new ArrayList<>();
            for (ConnectionChannel cc : connectionChannelService.getConnectionChannelListByOwnerId(ownerId)) {
                ConnectionChannelDTO connectionChannelDTO = new ConnectionChannelDTO(cc);
                connectionChannelDTOs.add(connectionChannelDTO);
            }
            return connectionChannelDTOs;
        }
        throw new BadRequestException("No connection channel found in database!");
    }

}
