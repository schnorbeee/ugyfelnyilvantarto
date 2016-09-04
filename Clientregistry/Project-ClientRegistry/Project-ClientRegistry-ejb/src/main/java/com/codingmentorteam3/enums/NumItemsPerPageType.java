package com.codingmentorteam3.enums;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public enum NumItemsPerPageType {

    TEN(10),FIFTY(50),HUNDRED(100),TWO_HUNDRED(200);
    
    private final Integer numItemsPerPage;
    
    private NumItemsPerPageType(Integer numItemsPerPage) {
        this.numItemsPerPage = numItemsPerPage;
    }
    
    public Integer getNumItemsPerPage() {
        return numItemsPerPage;
    }
}
