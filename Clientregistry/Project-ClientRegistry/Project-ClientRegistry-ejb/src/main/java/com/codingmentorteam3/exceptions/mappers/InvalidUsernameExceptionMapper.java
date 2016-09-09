package com.codingmentorteam3.exceptions.mappers;

import com.codingmentorteam3.exceptions.createmessage.ExceptionMessage;
import com.codingmentorteam3.exceptions.validator.InvalidUsernameException;
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
public class InvalidUsernameExceptionMapper implements ExceptionMapper<InvalidUsernameException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(InvalidUsernameException exception) {
        logger.log(Level.SEVERE,
                "InvalidUsernameException was thrown: " + exception.getMessage(),
                exception);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ExceptionMessage(exception.getMessage() + ": " + exception.getCause().toString()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
