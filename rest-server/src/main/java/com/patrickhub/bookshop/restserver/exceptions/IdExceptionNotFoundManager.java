/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author PatrickHub
 */
@Provider
public class IdExceptionNotFoundManager implements  ExceptionMapper<IdNotFoundException> {

    @Override
    public Response toResponse(IdNotFoundException exception) {
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
}
