/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.repository;

import com.patrickhub.bookshop.restserver.beans.Book;
import java.util.List;
import java.util.Optional;

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
     * @return Optional<Book>
     */
    Optional<Book> deleteBook(final int id);
    
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
    Optional<Book> getByid(int id);
}
