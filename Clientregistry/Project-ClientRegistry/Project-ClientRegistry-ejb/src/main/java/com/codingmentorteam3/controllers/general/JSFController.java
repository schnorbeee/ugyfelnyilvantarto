package com.codingmentorteam3.controllers.general;

/**
 *
 * @author istvan.mosonyi
 */
public interface JSFController {
    
    void onPreRender();

    String getDetailHeader();
    
    String getListPage();
    
    String getNewItemOutcome();
    
}
