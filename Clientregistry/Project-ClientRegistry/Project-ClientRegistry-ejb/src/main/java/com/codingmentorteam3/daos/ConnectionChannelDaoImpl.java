package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.ConnectionChannel;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class ConnectionChannelDaoImpl extends AbstractDao<ConnectionChannel> {

    public ConnectionChannelDaoImpl() {
        super(ConnectionChannel.class);
    }

}
