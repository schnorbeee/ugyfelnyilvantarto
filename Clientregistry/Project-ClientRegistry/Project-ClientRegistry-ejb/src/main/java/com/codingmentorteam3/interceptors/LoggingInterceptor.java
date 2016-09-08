package com.codingmentorteam3.interceptors;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Zsolt
 */
@Interceptor
public class LoggingInterceptor {

    private static final Logger LOGGER = Logger.getLogger(LoggingInterceptor.class.getName());
    
    @AroundInvoke
    public Object loggingMethod(InvocationContext ctx) throws Exception {
        LOGGER.log(Level.INFO, createLogMessage(ctx));
        ctx.getClass().getName();
        
        return ctx.proceed();
    }
    
    private String createLogMessage(InvocationContext ctx) {
        StringBuilder message = new StringBuilder();
        message.append("Called Class name: ")
                .append(ctx.getMethod().getDeclaringClass().getName())
                .append(" Method name: ")
                .append(ctx.getMethod().getName())
                .append(" Parameters:");
        for(Object parameter : ctx.getParameters()) {
            message.append(" ")
                    .append(parameter.toString());
        }
        
        return message.toString();
    }

}
