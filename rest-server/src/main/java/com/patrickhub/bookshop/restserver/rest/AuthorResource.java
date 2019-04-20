/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.rest;

import com.patrickhub.bookshop.restserver.beans.Author;
import com.patrickhub.bookshop.restserver.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author PatrickHub
 */
@Stateless
@Path("/authors")
public class AuthorResource {
    @EJB
    private AuthorRepository authorRepository;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAuthor(){
        List<Author> authors = authorRepository.getAll();
        GenericEntity<List<Author>> authorsWrapper = new GenericEntity<List<Author>>(authors){};
        return Response.ok(authorsWrapper).build();
    }
    
    @GET 
    @Path("{id: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthorById(final @PathParam("id") int id){
        Optional<Author> author = authorRepository.getByid(id);
        if(author.isPresent()){
            return Response.ok(author.get()).build();
        }
        
        return Response.noContent().build();
    }
    
    @DELETE
    @Path("{id: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAuthor(final @PathParam("id") int id){
        Optional<Author> author = authorRepository.deleteAuthor(id);
        if(author.isPresent()){
            return Response.ok(author.get()).build();
        }
        
        return Response.noContent().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id: \\d+}") 
    public Response updateAuthor(Author author, final @PathParam("id") int id){
        Author persistedAuthor = authorRepository.updateAuthor(author);
        return Response.ok(persistedAuthor).build();
    }
    
}
