package com.codingmentorteam3.daos;

import com.codingmentorteam3.entities.ConnectionChannel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class ConnectionChannelDaoImpl extends AbstractDao<ConnectionChannel> {

    public ConnectionChannelDaoImpl() {
        super(ConnectionChannel.class);
    }

    public List<ConnectionChannel> getConnectionChannelList(int limit, int offset) {
        TypedQuery<ConnectionChannel> query = em.createNamedQuery("connection.channel.list", ConnectionChannel.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }
//personban van
//    public List<ConnectionChannel> getConnectionChannelListByOwnerId(Long ownerId) {
//        TypedQuery<ConnectionChannel> query = em.createNamedQuery("connection.channel.list.by.owner.id", ConnectionChannel.class);
//        query.setParameter("id", ownerId);
//        return query.getResultList();
//    }

}
