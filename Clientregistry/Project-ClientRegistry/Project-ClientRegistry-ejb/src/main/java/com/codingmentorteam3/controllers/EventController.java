package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.AddressBean;
import com.codingmentorteam3.beans.EventBean;
import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.dtos.AddressDTO;
import com.codingmentorteam3.dtos.EventDTO;
import com.codingmentorteam3.entities.Address;
import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.AddressService;
import com.codingmentorteam3.services.CompanyService;
import com.codingmentorteam3.services.EventService;
import java.util.List;
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
@ManagedBean(name = "eventController")
public class EventController extends PageableEntityController<Event> {

    @Inject
    private EventService eventService;
    
    @Inject
    private CompanyService companyService;
    
    @Inject
    private AddressService addressService;
    
    public Response createEvent(EventBean regEvent, AddressBean regAddress, @QueryParam("companyId") Long companyId) {
        Event newEvent = new Event(regEvent);
        Address newAddress = new Address(regAddress);
        Company oldCompany = companyService.getCompany(companyId);
        if(!eventService.getEventsListByTitleFilter(newEvent.getTitle()).isEmpty()) {
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }
        if(null != addressService.getAddressByAllParameters(newAddress.getCity(), newAddress.getCountry(), newAddress.getZipCode(), newAddress.getStreet(), newAddress.getHouseNumber())) {
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }
        if(null != oldCompany) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        addressService.createAddress(newAddress);
        newEvent.setAddress(newAddress);
        newEvent.setCompany(oldCompany);
        eventService.createEvent(newEvent);
        AddressDTO addressDto = new AddressDTO(newAddress);
        EventDTO eventDto = new EventDTO(newEvent);
        return Response.status(Response.Status.CREATED).entity(eventDto).entity(addressDto).type(MediaType.APPLICATION_JSON).build();
    }
    
    public Response getEventById(@QueryParam("eventId") Long eventId) {
        Event event = eventService.getEvent(eventId);
        if (null != event) {
            EventDTO dto = new EventDTO(event);
            return Response.status(Response.Status.FOUND).entity(dto).type(MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    public Response updateEvent(@QueryParam("eventId") Long eventId) {
        return Response.accepted().build();
        //TODO
    }
    
    public Response deleteEventById(@QueryParam("eventId") Long eventId) {
        Event deleteEvent = eventService.getEvent(eventId);
        if(null != deleteEvent) {
            eventService.deleteEvent(deleteEvent);
            EventDTO dto = new EventDTO(deleteEvent);
            return Response.status(Response.Status.ACCEPTED).entity(dto).type(MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    
    @Override
    public List<Event> getEntities() {       
        return eventService.getEventsList(getLimit(), getOffset());
    }

    @Override
    protected Event loadEntity(Long entityId) {
        if (entityId != null) {
            return eventService.getEvent(entityId);
        }
        return new Event();
    }

    @Override
    protected Event doUpdateEntity() {
        eventService.editEvent(getEntity());
        return getEntity();
    }

    @Override
    protected void doPersistEntity() {
        eventService.createEvent(getEntity());
    }

    //at kell nezni hogy helyesen legyen beirva
    @Override
    protected String getNoEntityMessage() {
        return "No event found in database!";
    }

    @Override
    public String getListPage() {
        return "events";
    }

    @Override
    public String getNewItemOutcome() {
        return "edit/editEvent.xhtml";
    }

}
