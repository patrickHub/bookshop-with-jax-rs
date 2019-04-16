/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.repository;

/**
 *
 * @author PatrickHub
 */
public interface BookAuthorRepository {
    
    /**
     * save bookAuthor that result from (n, n) relationship
     * between book and author.
     * 
     * @param bookID
     * @param authorID 
     */
    void saveBookAuthor(int bookID, int authorID);
}
