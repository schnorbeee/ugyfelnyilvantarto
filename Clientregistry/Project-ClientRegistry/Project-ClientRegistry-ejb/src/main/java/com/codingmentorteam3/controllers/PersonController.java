package com.codingmentorteam3.controllers;

import com.codingmentorteam3.dtos.ConnectionChannelDTO;
import com.codingmentorteam3.entities.ConnectionChannel;
import com.codingmentorteam3.entities.Person;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.ConnectionChannelService;
import com.codingmentorteam3.services.PersonService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author istvan.mosonyi
 */
@BeanValidation
@SessionScoped
@ManagedBean(name = "personController")
public class PersonController {
    
    @Inject
    private PersonService personService;
    
    @Inject
    private ConnectionChannelService connectionChannelService;
    
    //user method
    public List<ConnectionChannelDTO> getConnectionChannelListByUserId(Long personId) {
        Person currentPerson = personService.getPerson(personId);
        if (null != currentPerson) {
            List<ConnectionChannelDTO> connectionChannelDTOs = new ArrayList<>();
            for (ConnectionChannel connectionChannel : connectionChannelService.getConnectionChannelListByOwnerId(personId)) {
                ConnectionChannelDTO connectionChannelDTO = new ConnectionChannelDTO(connectionChannel);
                connectionChannelDTOs.add(connectionChannelDTO);
            }
            return connectionChannelDTOs;
        }
        throw new BadRequestException("No person found in database!");
    }
    
}
