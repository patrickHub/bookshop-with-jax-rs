/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.rest;

import com.patrickhub.bookshop.restserver.beans.Book;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author PatrickHub
 */
@Stateless
@Path("/books")
public class BookResource {
    @EJB
    private BookRepository bookRepository;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks(){
          List<Book> books = bookRepository.getAll();
          
          GenericEntity<List<Book>> bookWrapper = new GenericEntity<List<Book>>(books){};
          
          return Response.ok(bookWrapper).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveBook(@Valid Book book){
       Book persistedBook = bookRepository.saveBook(book);
       return Response.status(Response.Status.CREATED).entity(persistedBook).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id: \\d+}")
    public Response getBookById(final @PathParam("id") int id){
        Optional<Book> book = bookRepository.getByid(id);
        if(book.isPresent()){
            return Response.ok(book.get()).build();
        }
        return Response.noContent().build();
    }
    
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id: \\d+}")
    public Response deleteBook(final @PathParam("id") int id){
        Optional<Book> book = bookRepository.deleteBook(id);
        if(book.isPresent()){
            return Response.ok(book.get()).build();
        }
        return Response.noContent().build();
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id: \\d+}") 
    public Response updateBook(Book book, final @PathParam("id") int id){
        Book persistedBook = bookRepository.updateBook(book);
        return Response.ok(persistedBook).build();
    }
}
