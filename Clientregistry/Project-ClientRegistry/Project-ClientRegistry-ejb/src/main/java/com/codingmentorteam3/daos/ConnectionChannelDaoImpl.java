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
    
    public List<ConnectionChannel> getConnectionChannelListByOwnerId(Long ownerId) {
        return em.createNamedQuery("connection.channel.list.by.owner.id", ConnectionChannel.class).setParameter("id", ownerId).getResultList();
    }

}
