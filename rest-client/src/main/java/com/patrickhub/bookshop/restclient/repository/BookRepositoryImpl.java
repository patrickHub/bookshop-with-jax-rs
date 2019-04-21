/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restclient.repository;

import com.patrickhub.bookshop.restclient.beans.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author PatrickHub
 */
@Stateless
public class BookRepositoryImpl implements BookRepository{
    
    
    private static final String API_URL = "http://localhost:8080/rest_server";
    private static final String BOOK_ENDPOINT = API_URL + "/api/books/";

    private List<Book> cachedbooks = new ArrayList<>();
    
    private Client client;
    
    
    @PostConstruct
    public void init(){
        client = ClientBuilder.newClient();
    }
    
    @PreDestroy
    private void destroy(){
        client.close();
    }
    
    @Override
    public Book saveBook(Book book) {
        return book;
    }

    @Override
    public void deleteBook(int id) {
        
    }

    @Override
    public Book getByid(int id) {
       return null;
    }

    @Override
    public Book updateBook(Book book) {
        return book;

    }

    @Override
    public List<Book> getAll() {
        WebTarget target = client.target(BOOK_ENDPOINT);
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        cachedbooks = response.readEntity(new GenericType<ArrayList<Book>>(){});
        return Collections.unmodifiableList(cachedbooks);
    }

    
}
