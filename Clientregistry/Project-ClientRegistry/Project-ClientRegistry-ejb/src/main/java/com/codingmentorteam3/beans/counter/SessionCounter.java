package com.codingmentorteam3.beans.counter;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author schno sch.norbeee@gmail.com
 */
@WebListener
public class SessionCounter implements HttpSessionListener {

    private static int count;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        count++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        //Nothing to do
    }

    public static int getCount() {
        return count;
    }

}