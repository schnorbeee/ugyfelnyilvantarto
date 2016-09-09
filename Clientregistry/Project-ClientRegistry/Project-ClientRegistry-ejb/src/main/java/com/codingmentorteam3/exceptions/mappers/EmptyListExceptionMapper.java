package com.codingmentorteam3.exceptions.mappers;

import com.codingmentorteam3.exceptions.createmessage.ExceptionMessage;
import com.codingmentorteam3.exceptions.query.EmptyListException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author Zsolt
 */
public class EmptyListExceptionMapper implements ExceptionMapper<EmptyListException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(EmptyListException exception) {
        logger.log(Level.SEVERE,
                "EmptyListException was thrown: " + exception.getMessage(),
                exception);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ExceptionMessage(exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
