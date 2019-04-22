/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.rest;

import com.patrickhub.bookshop.restserver.beans.Book;
import com.patrickhub.bookshop.restserver.beans.LinkResource;
import com.patrickhub.bookshop.restserver.exceptions.IdNotFoundException;
import com.patrickhub.bookshop.restserver.repository.BookRepository;
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
@Path("/books")
public class BookResource {
    // this will allow us to get access to informations about the uri
    // without having to refer to it explicitely
    @Context
    private UriInfo uriInfo;
    @EJB
    private BookRepository bookRepository;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks(){
        List<Book> books = bookRepository.getAll();
        books.forEach((book) -> {
            LinkResource selfLink =  LinkResource.createLinkRResource(uriInfo, getClass(), "getBookById", book.getId(), "self", "GET");
            LinkResource deleteLink = LinkResource.createLinkRResource(uriInfo, getClass(),"deleteBook", book.getId(), "delete", "DELETE");
            LinkResource updateLink = LinkResource.createLinkRResource(uriInfo, getClass(), "updateBook", book.getId(), "udpate", "PUT");

              
            book.addLink(selfLink);
            book.addLink(deleteLink);
            book.addLink(updateLink);
        });
        GenericEntity<List<Book>> bookWrapper = new GenericEntity<List<Book>>(books){};
        return Response.ok(bookWrapper).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveBook(@Valid Book book){
       Book persistedBook = bookRepository.saveBook(book);
       LinkResource selfLink =  LinkResource.createLinkRResource(uriInfo, getClass(), "getBookById", persistedBook.getId(), "self", "GET");
        LinkResource deleteLink = LinkResource.createLinkRResource(uriInfo, getClass(),"deleteBook", persistedBook.getId(), "delete", "DELETE");
        LinkResource updateLink = LinkResource.createLinkRResource(uriInfo, getClass(), "updateBook", persistedBook.getId(), "udpate", "PUT");
        
        persistedBook.addLink(selfLink);
        persistedBook.addLink(deleteLink);
        persistedBook.addLink(updateLink);
        return Response.status(Response.Status.CREATED).entity(persistedBook).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id: \\d+}")
    public Response getBookById(final @PathParam("id") int id) throws IdNotFoundException{
        Optional<Book> book = bookRepository.getByid(id);
        if(book.isPresent()){
            Book persistedBook = book.get();
            
              
            LinkResource selfLink =  LinkResource.createLinkRResource(uriInfo, getClass(), "getBookById", id, "self", "GET");
            LinkResource deleteLink = LinkResource.createLinkRResource(uriInfo, getClass(),"deleteBook", id, "delete", "DELETE");
            LinkResource updateLink = LinkResource.createLinkRResource(uriInfo, getClass(), "updateBook", id, "udpate", "PUT");

            persistedBook.addLink(selfLink);
            persistedBook.addLink(deleteLink);
            persistedBook.addLink(updateLink);
            return Response.ok(persistedBook).build();
        }
        throw new IdNotFoundException();
    }
    
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id: \\d+}")
    public Response deleteBook(final @PathParam("id") int id) throws IdNotFoundException{
        Optional<Book> book = bookRepository.deleteBook(id);
        if(book.isPresent()){
            return Response.ok(book.get()).build();
        }
        throw new IdNotFoundException();
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id: \\d+}") 
    public Response updateBook(@Valid Book book, final @PathParam("id") int id){
        book.setId(id);
        Book persistedBook = bookRepository.updateBook(book);
        LinkResource selfLink =  LinkResource.createLinkRResource(uriInfo, getClass(), "getBookById", id, "self", "GET");
        LinkResource deleteLink = LinkResource.createLinkRResource(uriInfo, getClass(),"deleteBook", id, "delete", "DELETE");
        LinkResource updateLink = LinkResource.createLinkRResource(uriInfo, getClass(), "updateBook", id, "udpate", "PUT");

        persistedBook.addLink(selfLink);
        persistedBook.addLink(deleteLink);
        persistedBook.addLink(updateLink);
        return Response.ok(persistedBook).build();
    }
}
