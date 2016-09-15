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
    private ConnectionChannelDaoImpl channelDao;
    
    public void createConnectionChannel(ConnectionChannel connectionChannel) {
        channelDao.create(connectionChannel);
    }
    
    public ConnectionChannel getConnectionChannel(Long connectionChannelId){
        return channelDao.read(connectionChannelId);
    }
    
    public ConnectionChannel editConnectionChannel(ConnectionChannel connectionChannel){
        return channelDao.update(connectionChannel);
    }
    
    public ConnectionChannel deleteConnectionChannel(ConnectionChannel connectionChannel) {
        return channelDao.delete(connectionChannel);
    }
    
    public List<ConnectionChannel> getConnectionChannelListByOwnerId(Long ownerId) {
        return channelDao.getConnectionChannelListByOwnerId(ownerId);
    }
    
}
