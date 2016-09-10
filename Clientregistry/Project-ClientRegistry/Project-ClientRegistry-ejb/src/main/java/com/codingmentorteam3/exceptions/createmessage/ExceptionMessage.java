package com.codingmentorteam3.exceptions.createmessage;

/**
 *
 * @author Zsolt
 */
public class ExceptionMessage {

    private String text;

    public ExceptionMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}
