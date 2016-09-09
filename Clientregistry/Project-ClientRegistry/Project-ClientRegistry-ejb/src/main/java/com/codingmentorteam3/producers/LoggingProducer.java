package com.codingmentorteam3.producers;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author Zsolt
 */
public class LoggingProducer {

    @Produces
    public Logger createLogger(InjectionPoint point) {
        return Logger.getLogger(point.getMember().getDeclaringClass().getName());
    }
}
