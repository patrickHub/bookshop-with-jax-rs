/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restclient.repository;

import com.patrickhub.bookshop.restclient.beans.Author;
import com.patrickhub.bookshop.restclient.beans.Book;
import com.patrickhub.bookshop.restclient.beans.LinkResource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
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
    
    // construct the client and to ensure that it's construct before any call of this class
    @PostConstruct
    public void init(){
        client = ClientBuilder.newClient();
    }
    
    // clean the client api before destroy
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
        processHATEOAS(id, "delete");
    }

    @Override
    public Book getByid(int id) {
        getAll();
       return processHATEOAS(id, "self");
    }

    @Override
    public Book updateBook(Book book) {
        return book;

    }

    @Override
    public List<Book> getAll() {
        // set the web target uri
        WebTarget target = client.target(BOOK_ENDPOINT);
        
        // set the mediatype and call the get http method
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        
        // clear the cachedBooks in order to be sure that the list of book is fresch
        cachedbooks.clear();
        
        // read the json response and deserialize it into a jsonArray
        JsonArray jsonArray = response.readEntity(JsonArray.class);
        
        // construct book object from jsonObject in jsonArray
        for(int i=0; i<jsonArray.size(); i++){
            JsonObject bookJson = jsonArray.getJsonObject(i);
            List<LinkResource> hypermedia = extractLinks(bookJson.getJsonArray("links"));
            List<Author> authors = new ArrayList<>();
            
            Book book = new Book();
            book.setId(bookJson.getInt("id"));
            book.setTitle(bookJson.getString("title"));
            book.setDescription(bookJson.getString("description"));
            book.setPrice((float)bookJson.getJsonNumber("price").doubleValue());
            book.setImgPath(bookJson.getString("imgPath"));
            book.setAuthors(authors);
            book.setPublished(bookJson.getString("published"));
            book.setLink(bookJson.getString("link"));
            book.setLinks(hypermedia);
            
            cachedbooks.add(book);
        }
        
        return Collections.unmodifiableList(cachedbooks);
    }

    @Override
    public List<Author> extractAuthors(JsonArray jsonArray) {
       List<Author> authors = new ArrayList<>();
       for(int i=0; i<jsonArray.size(); i++){
           JsonObject jsonObject = jsonArray.getJsonObject(i);
           Author author = new Author();
           author.setId(jsonObject.getInt("id"));
           author.setFirstName(jsonObject.getString("firtName", ""));
           author.setLastName(jsonObject.getString("lastName", ""));
           author.setBirthdate(jsonObject.getString("birthdate", ""));
           author.setBlogURL(jsonObject.getString("blogURL", ""));
           authors.add(author);
       }
       return Collections.unmodifiableList(authors);
    }

    @Override
    public List<LinkResource> extractLinks(JsonArray jsonArray) {
        
        List<LinkResource> links = new ArrayList<>();
        for(int i=0; i<jsonArray.size(); i++){
            JsonObject jsonLink = jsonArray.getJsonObject(i);
            LinkResource link = new LinkResource();
            link.setRel(jsonLink.getString("rel", ""));
            link.setType(jsonLink.getString("type", ""));
            link.setUri(jsonLink.getString("uri", ""));
            links.add(link);
        }
        return Collections.unmodifiableList(links);
    }
    private Book processHATEOAS(int id, String rel){
        Book result = null;
        for(Book book: cachedbooks){
            if(book.getId() == id){
                for(LinkResource linkResource: book.getLinks()){
                    if(linkResource.getRel().equals(rel)){
                     result = (Book)client.target(linkResource.getUri())
                                            .request(MediaType.APPLICATION_JSON)
                                            .method(linkResource.getType())
                                            .readEntity(Book.class);
                     break;
                    }
                }
            }
        }
        return result;
    }
}
