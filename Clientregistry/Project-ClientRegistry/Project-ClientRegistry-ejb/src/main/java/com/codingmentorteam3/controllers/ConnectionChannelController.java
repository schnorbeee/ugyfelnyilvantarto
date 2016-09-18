package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.ConnectionChannelBean;
import com.codingmentorteam3.dtos.ConnectionChannelDTO;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.ConnectionChannelService;
import com.codingmentorteam3.services.UserService;
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
@ManagedBean(name = "connectionChannelController")
public class ConnectionChannelController {

    @Inject
    private ConnectionChannelService connectionChannelService;
    
    @Inject
    private UserService userService;
    
    public Response createNewConnectionChannel(ConnectionChannelBean newConnectionChannel, Long ownerId) {
        ConnectionChannel regConnectionChannel = new ConnectionChannel(newConnectionChannel);
        
        for (ConnectionChannel cc : connectionChannelService.getConnectionChannelListByOwnerId(ownerId)) {
            ConnectionChannelBean connectionChannelBean = new ConnectionChannelBean(cc.getType(), cc.getValue(), cc.getOwner());
            if (newConnectionChannel.equals(connectionChannelBean)) {
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
            }
        }
        regConnectionChannel.setOwner(userService.getUser(1L));
        connectionChannelService.createConnectionChannel(regConnectionChannel);
        ConnectionChannelDTO dto = new ConnectionChannelDTO(regConnectionChannel);
        return Response.status(Response.Status.CREATED).entity(dto).type(MediaType.APPLICATION_JSON).build();
    }
    
    public Response getConnectionChannelById(@QueryParam("connectionChannelId") Long connectionChannelId) {
        ConnectionChannel connectionChannel = connectionChannelService.getConnectionChannel(connectionChannelId);
        if (null != connectionChannel) {
            ConnectionChannelDTO dto = new ConnectionChannelDTO(connectionChannel);
            return Response.ok(dto, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    
    public Response updateConnectionChannel(ConnectionChannelBean newConnectionChannelBean, @QueryParam("connectionChannelId") Long connectionChannelId) {
        if (null != newConnectionChannelBean) {
            ConnectionChannel newConnectionChannel = new ConnectionChannel(newConnectionChannelBean);
            ConnectionChannel currentConnectionChannel = connectionChannelService.getConnectionChannel(connectionChannelId);
            if(null != currentConnectionChannel) {
                currentConnectionChannel.setValue(newConnectionChannel.getValue());
                connectionChannelService.editConnectionChannel(currentConnectionChannel);
                ConnectionChannelDTO dto = new ConnectionChannelDTO(currentConnectionChannel);
                return Response.ok(dto, MediaType.APPLICATION_JSON).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    public Response deleteConnectionChannelById(@QueryParam("connectionChannelId") Long connectionChannelId) {
        ConnectionChannel deleteConnectionChannel = connectionChannelService.getConnectionChannel(connectionChannelId);
        if (null != deleteConnectionChannel) {
            connectionChannelService.deleteConnectionChannel(deleteConnectionChannel);
            ConnectionChannelDTO dto = new ConnectionChannelDTO(deleteConnectionChannel);
            return Response.ok(dto, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    
}
