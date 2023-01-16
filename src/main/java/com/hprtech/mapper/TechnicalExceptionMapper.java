package com.hprtech.mapper;

import com.hprtech.dto.ErrorMessage;
import com.hprtech.exception.BusinessException;
import com.hprtech.exception.TechnicalException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TechnicalExceptionMapper implements ExceptionMapper<TechnicalException> {
    @Override
    public Response toResponse(TechnicalException exception) {
        ErrorMessage errorMessage = new ErrorMessage();

        errorMessage.setStatus(exception.getStatus());
        errorMessage.setMessage(exception.getMessage());

        return Response.
                status(Response.Status.INTERNAL_SERVER_ERROR).
                entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
