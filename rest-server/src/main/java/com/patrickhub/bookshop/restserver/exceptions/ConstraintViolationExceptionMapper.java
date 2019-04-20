/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.exceptions;

import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author PatrickHub
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        
        final String message = exception.getConstraintViolations().stream()
                .map(cv -> extractPropertyName(cv.getPropertyPath().toString())
                + " : " + cv.getMessage())
                .collect(Collectors.joining(", "));
        
               // return Response.status(Response.Status.BAD_REQUEST).header("X-Validation-Failure", message).build();
               return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
                        
        
    }
    
    private String extractPropertyName(String path){
        return path.substring(path.lastIndexOf(".") + 1);
    }
    
    
}
