package com.codingmentorteam3.beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author schno sch.norbeee@gmail.com
 */
@ApplicationScoped
@ManagedBean(name = "saveBean")
public class SaveBean {

    private AddressBean addressBean;
    
    private CompanyBean companyBean;
    
    private String companySearchString;
    
    private ConnectionChannelBean connectionChannelBean;
    
    private ContactPersonBean contactPersonBean;
    
    private EventBean eventBean;
    
    private String eventSearchString;
    
    private InvitationBean invitationBean;
    
    private NoteBean noteBean;
    
    private ProjectBean projectBean;
    
    private String projectSearchString;
    
    private RoleBean roleBean;
    
    private UserBean userBean;
    
    private String userSearchString;

    public AddressBean getAddressBean() {
        return addressBean;
    }

    public AddressBean setAddressBean(AddressBean addressBean) {
        this.addressBean = addressBean;
        return addressBean;
    }

    public CompanyBean getCompanyBean() {
        return companyBean;
    }

    public CompanyBean setCompanyBean(CompanyBean companyBean) {
        this.companyBean = companyBean;
        return companyBean;
    }

    public String getCompanySearchString() {
        return companySearchString;
    }

    public void setCompanySearchString(String companySearchString) {
        this.companySearchString = companySearchString;
    }

    public ConnectionChannelBean getConnectionChannelBean() {
        return connectionChannelBean;
    }

    public void setConnectionChannelBean(ConnectionChannelBean connectionChannelBean) {
        this.connectionChannelBean = connectionChannelBean;
    }

    public ContactPersonBean getContactPersonBean() {
        return contactPersonBean;
    }

    public void setContactPersonBean(ContactPersonBean contactPersonBean) {
        this.contactPersonBean = contactPersonBean;
    }

    public EventBean getEventBean() {
        return eventBean;
    }

    public void setEventBean(EventBean eventBean) {
        this.eventBean = eventBean;
    }

    public String getEventSearchString() {
        return eventSearchString;
    }

    public void setEventSearchString(String eventSearchString) {
        this.eventSearchString = eventSearchString;
    }

    public InvitationBean getInvitationBean() {
        return invitationBean;
    }

    public void setInvitationBean(InvitationBean invitationBean) {
        this.invitationBean = invitationBean;
    }

    public NoteBean getNoteBean() {
        return noteBean;
    }

    public void setNoteBean(NoteBean noteBean) {
        this.noteBean = noteBean;
    }

    public ProjectBean getProjectBean() {
        return projectBean;
    }

    public void setProjectBean(ProjectBean projectBean) {
        this.projectBean = projectBean;
    }

    public String getProjectSearchString() {
        return projectSearchString;
    }

    public void setProjectSearchString(String projectSearchString) {
        this.projectSearchString = projectSearchString;
    }

    public RoleBean getRoleBean() {
        return roleBean;
    }

    public void setRoleBean(RoleBean roleBean) {
        this.roleBean = roleBean;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String getUserSearchString() {
        return userSearchString;
    }

    public void setUserSearchString(String userSearchString) {
        this.userSearchString = userSearchString;
    }

    
}
