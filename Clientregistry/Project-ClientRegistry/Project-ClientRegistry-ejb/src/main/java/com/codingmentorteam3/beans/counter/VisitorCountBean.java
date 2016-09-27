package com.codingmentorteam3.beans.counter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author istvan.mosonyi
 */

@SessionScoped
@ManagedBean(name = "visitorCountBean")
public class VisitorCountBean {

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public int getSessionCount() {
        return SessionCounter.getCount();
    }

}