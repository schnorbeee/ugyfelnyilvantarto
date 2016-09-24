package com.codingmentorteam3.controllers;

import com.codingmentorteam3.beans.AddressBean;
import com.codingmentorteam3.beans.EventBean;
import com.codingmentorteam3.beans.InvitationBean;
import com.codingmentorteam3.beans.NoteBean;
import com.codingmentorteam3.controllers.general.PageableEntityController;
import com.codingmentorteam3.dtos.AddressDTO;
import com.codingmentorteam3.dtos.EventDTO;
import com.codingmentorteam3.dtos.NoteDTO;
import com.codingmentorteam3.dtos.UserDTO;
import com.codingmentorteam3.entities.Address;
import com.codingmentorteam3.entities.Company;
import com.codingmentorteam3.entities.Event;
import com.codingmentorteam3.entities.Invitation;
import com.codingmentorteam3.entities.Note;
import com.codingmentorteam3.entities.User;
import com.codingmentorteam3.enums.FeedbackType;
import com.codingmentorteam3.exceptions.query.BadRequestException;
import com.codingmentorteam3.interceptors.BeanValidation;
import com.codingmentorteam3.services.AddressService;
import com.codingmentorteam3.services.CompanyService;
import com.codingmentorteam3.services.EventService;
import com.codingmentorteam3.services.InvitationService;
import com.codingmentorteam3.services.NoteService;
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
@ManagedBean(name = "eventController")
public class EventController extends PageableEntityController<Event> {

    @Inject
    private EventService eventService;

    @Inject
    private CompanyService companyService;

    @Inject
    private AddressService addressService;

    @Inject
    private UserService userService;

    @Inject
    private NoteService noteService;

    @Inject
    private InvitationService invitationService;

//    public String getEventById(Long eventId) {
//        Event event = eventService.getEvent(eventId);
//        if (null != event) {
//            return "";
//        }
//        throw new BadRequestException(getNoEntityMessage());
//    }
    //user method
    public EventDTO updateEvent(EventBean updateEvent, Long eventId) {
        Event oldEvent = loadEntity(eventId);
        if (null != oldEvent) {
            Event currentEvent = new Event(updateEvent);
            oldEvent = modifiedCheckerEvent(oldEvent, currentEvent);
            setEntity(oldEvent);
            saveEntity();
            return new EventDTO(oldEvent);
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //user method
    public AddressDTO updateEventAddress(AddressBean updateAddress, Long eventId) {
        Event currentEvent = loadEntity(eventId);
        if (null != currentEvent) {
            Address currentAddress = addressService.getAddressByAllParameters(updateAddress.getCity(), updateAddress.getCountry(), updateAddress.getZipCode(), updateAddress.getStreet(), updateAddress.getHouseNumber());
            Address oldAddress = currentEvent.getAddress();
            if (oldAddress.equals(currentAddress)) {
                return new AddressDTO(oldAddress);
            }
            if (null == currentAddress) {
                if (oldAddress.getEvents().size() == 1 && oldAddress.getCompanies().isEmpty()) {
                    Address newAddress = new Address(updateAddress);
                    newAddress.setId(oldAddress.getId());
                    addressService.editAddress(newAddress);
                    return new AddressDTO(newAddress);
                } else {
                    Address newAddress = new Address(updateAddress);
                    addressService.createAddress(newAddress);
                    currentEvent.setAddress(newAddress);
                    setEntity(currentEvent);
                    saveEntity();
                    return new AddressDTO(newAddress);
                }
            } else {
                currentEvent.setAddress(currentAddress);
                setEntity(currentEvent);
                saveEntity();
                return new AddressDTO(currentAddress);
            }
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    public List<EventDTO> deleteEventById(Long eventId) {
        Event deleteEvent = loadEntity(eventId);
        if (null != deleteEvent) {
            eventService.deleteEvent(deleteEvent);
            if (!isAnyCompanyAndEventUseThisAddress(deleteEvent.getAddress())) {
                addressService.deleteAddress(deleteEvent.getAddress());
            }
            List<EventDTO> eventDTOs = new ArrayList<>();
            for (Event e : getEntities()) {
                EventDTO eventDTO = new EventDTO(e);
                eventDTOs.add(eventDTO);
            }
            return eventDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    public List<NoteDTO> createNote(NoteBean regNote, Long eventId, Long userId) {
        Event currentEvent = loadEntity(eventId);
        User currentUser = userService.getUser(userId);
        if (null != currentEvent) {
            if (null != currentUser) {
                Note newNote = new Note(regNote);
                newNote.setEvent(currentEvent);
                newNote.setUser(currentUser);
                noteService.createNote(newNote);
                List<NoteDTO> noteDTOs = new ArrayList<>();
                for (Note n : eventService.getNotesListByEventId(currentUser.getId())) {
                    NoteDTO noteDTO = new NoteDTO(n);
                    noteDTOs.add(noteDTO);
                }
                return noteDTOs;
            }
            throw new BadRequestException("No user found in database!");
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    //eventbe megy sender kerdeses hogy adjuk at
    public String createInvitations(InvitationBean createInvitation, Long eventId, Long senderId, List<Long> receiverList) {
        Event event = loadEntity(eventId);
        User sender = userService.getUser(senderId);
        if (null != event) {
            if (null != sender) {
                Invitation invitation = new Invitation(createInvitation);
                for (Long receiverId : receiverList) {
                    User receiver = userService.getUser(receiverId);
                    invitation.setEvent(event);
                    invitation.setSender(sender);
                    invitation.setReceiver(receiver);
                    invitation.setFeedback(FeedbackType.UNANSWERED);
                    invitationService.createInvitation(invitation);
                }
                return getListPage();
            }
            throw new BadRequestException("No sender found in database!");
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    public List<NoteDTO> getNotesListByEventId(Long eventId) {
        Event currentEvent = loadEntity(eventId);
        if (null != currentEvent) {
            List<NoteDTO> noteDTOs = new ArrayList<>();
            for (Note n : eventService.getNotesListByEventId(getEntityId())) {
                NoteDTO noteDTO = new NoteDTO(n);
                noteDTOs.add(noteDTO);
            }
            return noteDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }

    public List<UserDTO> getUsersListByEventIdAndFeedbackIsAccepted(FeedbackType feedback, Long eventId) {
        Event currentEvent = loadEntity(eventId);
        if (null != currentEvent) {
            List<UserDTO> userDTOs = new ArrayList<>();
            for (User u : eventService.getUsersListByEventIdAndFeedbackIsAccepted(getEntityId(), feedback, getLimit(), getOffset())) {
                UserDTO userDTO = new UserDTO(u);
                userDTOs.add(userDTO);
            }
            return userDTOs;
        }
        throw new BadRequestException(getNoEntityMessage());
    }
    
    public List<EventDTO> getEventsListByStringFilter(String name) {
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event e : eventService.getEventsListByStringFilter(name, getLimit(), getOffset())) {
            EventDTO eventDTO = new EventDTO(e);
            eventDTOs.add(eventDTO);
        }
        return eventDTOs;
    }

    public List<EventDTO> getEventsListByTypeFilter(String type) {
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event e : eventService.getEventsListByTypeFilter(type, getLimit(), getOffset())) {
            EventDTO eventDTO = new EventDTO(e);
            eventDTOs.add(eventDTO);
        }
        return eventDTOs;
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
        return null;
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
        if (!currentEvent.getTitle().equals("") || !currentEvent.getTitle().equals(oldEvent.getTitle())) {
            oldEvent.setTitle(currentEvent.getTitle());
        }
        if (!currentEvent.getDescription().equals("") || !currentEvent.getDescription().equals(oldEvent.getDescription())) {
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

    private boolean isAnyCompanyAndEventUseThisAddress(Address address) {
        for (Event e : getEntities()) {
            if (address.equals(e.getAddress())) {
                return true;
            }
        }
        for (Company c : companyService.getCompaniesListWithoutLimit()) {
            if (address.equals(c.getAddress())) {
                return true;
            }
        }
        return false;
    }

}
