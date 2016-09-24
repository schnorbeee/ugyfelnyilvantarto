package com.codingmentorteam3.services;

import com.codingmentorteam3.daos.ConnectionChannelDaoImpl;
import com.codingmentorteam3.entities.ConnectionChannel;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class ConnectionChannelService {

    @Inject
    private ConnectionChannelDaoImpl connectionChannelDao;

    public void createConnectionChannel(ConnectionChannel connectionChannel) {
        connectionChannelDao.create(connectionChannel);
    }

    public ConnectionChannel getConnectionChannel(Long connectionChannelId) {
        return connectionChannelDao.read(connectionChannelId);
    }

    public ConnectionChannel editConnectionChannel(ConnectionChannel connectionChannel) {
        return connectionChannelDao.update(connectionChannel);
    }

    public ConnectionChannel deleteConnectionChannel(ConnectionChannel connectionChannel) {
        return connectionChannelDao.delete(connectionChannel);
    }
    
    public  List<ConnectionChannel> getConnectionChannelList(Integer limit, Integer offset) {
        return connectionChannelDao.getConnectionChannelList(limit, offset);
    }
    //personba eleg
//    public List<ConnectionChannel> getConnectionChannelListByOwnerId(Long ownerId) {
//        return channelDao.getConnectionChannelListByOwnerId(ownerId);
//    }

}
