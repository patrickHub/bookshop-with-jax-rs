/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.repository;

import com.patrickhub.bookshop.restserver.beans.Author;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author PatrickHub
 */
public interface AuthorRepository {
    
    /**
     * save new author to db.
     * 
     * @param author author to save
     * @return persisted author
     */
    Author saveAuthor(Author author);
    
    /**
     * delete an "id" author from db.
     * 
     * @param id id of the author
     * @return Optional<Auhtor>
     */
    Optional<Author> deleteAuthor(final int id);
    
    
    /**
     * get all authors form db.
     * 
     * @return List of author
     */
    List<Author> getAll();
    
    /**
     * get an "id" author.
     * 
     * @param id id of the author
     * @return Optional<Author>
     */
    Optional<Author> getByid(int id);
}
