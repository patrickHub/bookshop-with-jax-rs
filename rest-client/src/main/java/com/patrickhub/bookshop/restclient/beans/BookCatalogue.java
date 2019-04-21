/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restclient.beans;

import com.patrickhub.bookshop.restclient.repository.BookRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Patrickhub
 */
@Named("bookCatalogue")
@RequestScoped
public class BookCatalogue {
    
    @Inject
    private BookRepository bookRepository;
    
    private List<Book> books;
    
    @PostConstruct
    public void init(){
        books = bookRepository.getAll();
    }

    /**
     * @return the books
     */
    public List<Book> getBooks() {
        return books;
    }
}
