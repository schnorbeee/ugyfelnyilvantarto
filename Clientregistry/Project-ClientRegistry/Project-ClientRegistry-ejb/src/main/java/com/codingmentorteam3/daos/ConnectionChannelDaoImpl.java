package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.ConnectionChannel;
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
        List<ConnectionChannel> query = em.createNamedQuery("connectionChannel.list", ConnectionChannel.class).getResultList();
//        if (query.isEmpty()) {
//            throw new EmptyListException("There are no users to show.");
//        }
        return query;
    }

}
