package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.ConnectionChannel;
import java.util.ArrayList;
import java.util.List;
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
    
    public List<ConnectionChannel> getConnectionChannelsList() {
        // DELETE AT MERGE
        return new ArrayList<>();
    }

}
