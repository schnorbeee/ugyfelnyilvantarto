package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.AddressBean;
import com.codingmentorteam3.beans.EventBean;
import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.dtos.AddressDTO;
import com.codingmentorteam3.dtos.EventDTO;
import com.codingmentorteam3.entities.Address;
import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.exceptions.query.EntityAlreadyExistsException;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.AddressService;
import com.codingmentorteam3.services.CompanyService;
import com.codingmentorteam3.services.EventService;
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
@ManagedBean(name = "eventController")
public class EventController extends PageableEntityController<Event> {

    @Inject
    private EventService eventService;

    @Inject
    private CompanyService companyService;

    @Inject
    private AddressService addressService;

    public String createEvent(EventBean regEvent, AddressBean regAddress, Long companyId) {
        Event newEvent = new Event(regEvent);
        Address newAddress = new Address(regAddress);
        Company oldCompany = companyService.getCompany(1L);
        if (!eventService.getEventsListByTitleFilter(newEvent.getTitle(), getLimit(), getOffset()).isEmpty()) {
            throw new EntityAlreadyExistsException("This event already exists in our database.");
        }
        Address oldAddress = addressService.getAddressByAllParameters(newAddress.getCity(), newAddress.getCountry(), newAddress.getZipCode(), newAddress.getStreet(), newAddress.getHouseNumber());
        if (null != oldAddress) {
            newEvent.setAddress(oldAddress);
            newEvent.setCompany(oldCompany);
            eventService.createEvent(newEvent);
            return "events.xhtml";
        }
        addressService.createAddress(newAddress);
        newEvent.setAddress(newAddress);
        newEvent.setCompany(oldCompany);
        eventService.createEvent(newEvent);
        return "events.xhtml";
    }

    public String getEventById(Long eventId) {
        Event event = eventService.getEvent(eventId);
        if (null != event) {
            return "";
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method
    public EventDTO updateEvent(EventBean updateEvent, Long eventId) {
        Event oldEvent = eventService.getEvent(eventId);
        if(null != oldEvent) {
            Event currentEvent = new Event(updateEvent);
            oldEvent = modifiedCheckerEvent(oldEvent, currentEvent);
            eventService.editEvent(oldEvent);
            return new EventDTO(oldEvent);
        }
        throw new BadRequestException(getNoEntityMessage());
    }
    
    //user method
    public AddressDTO updateEventAddress(AddressBean updateAddress, Long eventId) {
        Event currentEvent = eventService.getEvent(eventId);
        if(null != currentEvent) {
            Address currentAddress = addressService.getAddressByAllParameters(updateAddress.getCity(), updateAddress.getCountry(), updateAddress.getZipCode(), updateAddress.getStreet(), updateAddress.getHouseNumber());
            Address oldAddress = currentEvent.getAddress();
            if (!oldAddress.equals(currentAddress)) {
                if (null != currentAddress) {
                    currentEvent.setAddress(currentAddress);
                    return new AddressDTO(currentAddress);
                }
                Address newAddress = new Address(updateAddress);
                addressService.createAddress(newAddress);
                currentEvent.setAddress(addressService.getAddressByAllParameters(newAddress.getCity(), newAddress.getCountry(), newAddress.getZipCode(), newAddress.getStreet(), newAddress.getHouseNumber()));
                return new AddressDTO(newAddress);
            }
            return new AddressDTO(oldAddress);
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    public List<EventDTO> deleteEventById(Long eventId) {
        Event deleteEvent = eventService.getEvent(eventId);
        if (null != deleteEvent) {
            eventService.deleteEvent(deleteEvent);
            List<EventDTO> eventDTOs = new ArrayList<>();
            for(Event e : getEntities()) {
                EventDTO eventDTO = new EventDTO(e);
                eventDTOs.add(eventDTO);
            }
            return eventDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
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

    public Event modifiedCheckerEvent(Event oldEvent, Event currentEvent) {
        if (!currentEvent.getTitle().equals("")) {
            oldEvent.setTitle(currentEvent.getTitle());
        }
        if (!currentEvent.getDescription().equals("")) {
            oldEvent.setDescription(currentEvent.getDescription());
        }
        if (!currentEvent.getType().equals(oldEvent.getType())) {
            oldEvent.setType(currentEvent.getType());
        }
        if (!currentEvent.getStartDate().equals(oldEvent.getStartDate())) {
            oldEvent.setStartDate(currentEvent.getStartDate());
        }
        if (!currentEvent.getEndDate().equals(oldEvent.getEndDate())) {
            oldEvent.setEndDate(currentEvent.getEndDate());
        }
        return oldEvent;
    }
}
