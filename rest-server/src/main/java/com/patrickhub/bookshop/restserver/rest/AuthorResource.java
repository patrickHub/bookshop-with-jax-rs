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
            LinkResource selfLink =  LinkResource.createLinkRResource(uriInfo, getClass(), "getAuthorById", author.getId(), "self", "GET");
            LinkResource deleteLink = LinkResource.createLinkRResource(uriInfo, getClass(),"deleteAuthor", author.getId(), "delete", "DELETE");
            LinkResource updateLink = LinkResource.createLinkRResource(uriInfo, getClass(), "updateAuthor", author.getId(), "udpate", "PUT");
            
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
        LinkResource selfLink =  LinkResource.createLinkRResource(uriInfo, getClass(), "getAuthorById", persistedAuthor.getId(), "self", "GET");
        LinkResource deleteLink = LinkResource.createLinkRResource(uriInfo, getClass(),"deleteAuthor", persistedAuthor.getId(), "delete", "DELETE");
        LinkResource updateLink = LinkResource.createLinkRResource(uriInfo, getClass(), "updateAuthor", persistedAuthor.getId(), "udpate", "PUT");

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
            LinkResource selfLink =  LinkResource.createLinkRResource(uriInfo, getClass(), "getAuthorById", id, "self", "GET");
            LinkResource deleteLink = LinkResource.createLinkRResource(uriInfo, getClass(),"deleteAuthor", id, "delete", "DELETE");
            LinkResource updateLink = LinkResource.createLinkRResource(uriInfo, getClass(), "updateAuthor", id, "udpate", "PUT");
            
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
        LinkResource selfLink =  LinkResource.createLinkRResource(uriInfo, getClass(), "getAuthorById", id, "self", "GET");
        LinkResource deleteLink = LinkResource.createLinkRResource(uriInfo, getClass(),"deleteAuthor", id, "delete", "DELETE");
        LinkResource updateLink = LinkResource.createLinkRResource(uriInfo, getClass(), "updateAuthor", id, "udpate", "PUT");

        persistedAuthor.addLink(selfLink);
        persistedAuthor.addLink(deleteLink);
        persistedAuthor.addLink(updateLink);
        return Response.ok(persistedAuthor).build();
    }
    
}
