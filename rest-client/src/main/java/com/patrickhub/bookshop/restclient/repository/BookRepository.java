/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restclient.repository;

import com.patrickhub.bookshop.restclient.beans.Author;
import com.patrickhub.bookshop.restclient.beans.Book;
import com.patrickhub.bookshop.restclient.beans.LinkResource;
import java.util.List;
import javax.json.JsonArray;

/**
 *
 * @author PatrickHub
 */
public interface BookRepository {
    
    /**
     * save new book to db.
     * 
     * @param book book to save
     * @return persisted entity
     */
    Book saveBook(Book book);
    
    /**
     * update a book 
     * @param book book to update
     * @return persisted book
     */
    Book updateBook(Book book);
    
    /**
     * delete an "id" book from db.
     * 
     * @param id id of the book
     */
    void deleteBook(final int id);
    
    /**
     * get all book from db.
     * 
     * @return list of Book
     */
    List<Book> getAll();
    
    /**
     * get an "id" book.
     * 
     * @param id id of the book
     * @return Optional<Book>
     */
    Book getByid(int id);
    
    /**
     * extract authors from the jsonArray.
     * 
     * @param jsonArray the jsonArray
     * @return the list of link
     */
    List<Author> extractAuthors(JsonArray jsonArray);
    
    /**
     * extract linkResource from jsonArray.
     * 
     * @param jsonArray the jsonArray
     * @return list of author
     */
    List<LinkResource> extractLinks(JsonArray jsonArray);
}
