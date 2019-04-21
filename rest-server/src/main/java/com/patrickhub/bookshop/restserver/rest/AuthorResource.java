/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.rest;

import com.patrickhub.bookshop.restserver.beans.Author;
import com.patrickhub.bookshop.restserver.beans.LinkResource;
import com.patrickhub.bookshop.restserver.exceptions.IdNotFoundException;
import com.patrickhub.bookshop.restserver.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author PatrickHub
 */
@Stateless
@Path("/authors")
public class AuthorResource {
    @Context
    private UriInfo uriInfo;
    @EJB
    private AuthorRepository authorRepository;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAuthor(){
        List<Author> authors = authorRepository.getAll();
        authors.forEach((author) -> {
            Link self = Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(getClass())
                    .path(getClass(), "getAuthorById")
                    .build(author.getId()))
                    .rel("self")
                    .type("GET")
                    .build();
            Link delete = Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(getClass())
                    .path(getClass(), "deleteAuthor")
                    .build(author.getId()))
                    .rel("delete")
                    .type("DELETE")
                    .build();
            Link update = Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(getClass())
                    .path(getClass(), "updateAuthor")
                    .build(author.getId()))
                    .rel("update")
                    .type("PUT")
                    .build();
            
            LinkResource selfLink = new  LinkResource(self);
            LinkResource deleteLink = new  LinkResource(delete);
            LinkResource updateLink = new  LinkResource(update);
            
            author.addLink(selfLink);
            author.addLink(deleteLink);
            author.addLink(updateLink);
        });
        GenericEntity<List<Author>> authorsWrapper = new GenericEntity<List<Author>>(authors){};
        return Response.ok(authorsWrapper).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveAuthor(@Valid Author author){
        Author persistedAuthor = authorRepository.saveAuthor(author);
        Link self = Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(getClass())
                    .path(getClass(), "getAuthorById")
                    .build(persistedAuthor.getId()))
                    .rel("self")
                    .type("GET")
                    .build();
            Link delete = Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(getClass())
                    .path(getClass(), "deleteAuthor")
                    .build(persistedAuthor.getId()))
                    .rel("delete")
                    .type("DELETE")
                    .build();
            Link update = Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(getClass())
                    .path(getClass(), "updateAuthor")
                    .build(persistedAuthor.getId()))
                    .rel("update")
                    .type("PUT")
                    .build();
            
            LinkResource selfLink = new  LinkResource(self);
            LinkResource deleteLink = new  LinkResource(delete);
            LinkResource updateLink = new  LinkResource(update);
            
            persistedAuthor.addLink(selfLink);
            persistedAuthor.addLink(deleteLink);
            persistedAuthor.addLink(updateLink);
            return Response.status(Response.Status.CREATED).entity(persistedAuthor).build();
    }
    
    
    @GET 
    @Path("{id: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthorById(final @PathParam("id") int id) throws IdNotFoundException{
        Optional<Author> author = authorRepository.getByid(id);
        if(author.isPresent()){
            Author persistedAuthor = author.get();
            Link self = Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(getClass())
                    .path(getClass(), "getAuthorById")
                    .build(persistedAuthor.getId()))
                    .rel("self")
                    .type("GET")
                    .build();
            Link delete = Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(getClass())
                    .path(getClass(), "deleteAuthor")
                    .build(persistedAuthor.getId()))
                    .rel("delete")
                    .type("DELETE")
                    .build();
            Link update = Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(getClass())
                    .path(getClass(), "updateAuthor")
                    .build(persistedAuthor.getId()))
                    .rel("update")
                    .type("PUT")
                    .build();
            
            LinkResource selfLink = new  LinkResource(self);
            LinkResource deleteLink = new  LinkResource(delete);
            LinkResource updateLink = new  LinkResource(update);
            
            persistedAuthor.addLink(selfLink);
            persistedAuthor.addLink(deleteLink);
            persistedAuthor.addLink(updateLink);
            return Response.ok(author.get()).build();
        }
        
        throw new IdNotFoundException();
    }
    
    @DELETE
    @Path("{id: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAuthor(final @PathParam("id") int id) throws IdNotFoundException{
        Optional<Author> author = authorRepository.deleteAuthor(id);
        if(author.isPresent()){
            return Response.ok(author.get()).build();
        }
        
        throw new IdNotFoundException();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id: \\d+}") 
    public Response updateAuthor(Author author, final @PathParam("id") int id){
        author.setId(id);
        Author persistedAuthor = authorRepository.updateAuthor(author);
        Link self = Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(getClass())
                    .path(getClass(), "getAuthorById")
                    .build(persistedAuthor.getId()))
                    .rel("self")
                    .type("GET")
                    .build();
            Link delete = Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(getClass())
                    .path(getClass(), "deleteAuthor")
                    .build(persistedAuthor.getId()))
                    .rel("delete")
                    .type("DELETE")
                    .build();
            Link update = Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(getClass())
                    .path(getClass(), "updateAuthor")
                    .build(persistedAuthor.getId()))
                    .rel("update")
                    .type("PUT")
                    .build();
            
            LinkResource selfLink = new  LinkResource(self);
            LinkResource deleteLink = new  LinkResource(delete);
            LinkResource updateLink = new  LinkResource(update);
            
            persistedAuthor.addLink(selfLink);
            persistedAuthor.addLink(deleteLink);
            persistedAuthor.addLink(updateLink);
        return Response.ok(persistedAuthor).build();
    }
    
}
