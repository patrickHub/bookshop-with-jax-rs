/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restclient.beans;

import com.patrickhub.bookshop.restclient.repository.BookRepository;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author PatrickHub
 */
@Named("bookManager")
@ViewScoped
public class BookManager implements Serializable{
    
    @Inject
    private BookRepository bookRepository;
    
    private Book book;
    private int id;
    
    public void onLoad(){
        book = bookRepository.getByid(id);
    }

    /**
     * @return the bookRepository
     */
    public BookRepository getBookRepository() {
        return bookRepository;
    }

    /**
     * @param bookRepository the bookRepository to set
     */
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
}
