/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.repository;

import com.patrickhub.bookshop.restserver.beans.Author;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author PatrickHub
 */

@Stateless
public class AuthorRepositoryImpl implements AuthorRepository{

     @Inject
    private DBConnection dbConnection;
    
    @Override
    public Author saveAuthor(Author author) {
        try {
             // get connection to db
            Connection connection = dbConnection.getConnection();
            // write sql insert
            String sql = "INSERT INTO authors(authorFirstName, authorLastName, authorBirthdate, authorBlogURL)"
                            + "VALUES(?,?,?,?)";
       
            // get prepared statement
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // set parameters
            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());
            statement.setString(3, author.getBlogURL());
            statement.setDate(4, new Date(author.getBirthdate().getTime()));
            
            // execute insert query
            int row = statement.executeUpdate();
            // check if author entity is created
            if (row == 0) {
                throw new SQLException("Failled to create author, no rows affected.");
	    }
			
            // check weather authorID has been generated
	    ResultSet generatedIds = statement.getGeneratedKeys();
            if (generatedIds.next()) {
                author.setId(generatedIds.getInt(1));
            }
            else {
                throw new SQLException("Failled to create author, no authorID obtained.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return author;
    }

    @Override
    public Optional<Author> deleteAuthor(int id) {
        Optional<Author> optional =  getByid(id);
        Connection connection = dbConnection.getConnection();
        
        try {
            // write sql delete
            String sql = "DELETE FROM authors WHERE authorID = ?;";
            // get prepared statement
            PreparedStatement statement =  connection.prepareStatement(sql);
            // set sql delete parameters
            statement.setInt(1, id);
            // execute sql delete
            int row = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AuthorRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return optional;
    }
    
    @Override
    public List<Author> getAll() {
        Connection connection = dbConnection.getConnection();
        List<Author> authors = null;
        Author author = null;
        
        try {
            // write sql query
            String sql = "SELECT * FROM authors;";
            // get prepared statement
            PreparedStatement statement =  connection.prepareStatement(sql);
           
            // execute sql query
            ResultSet set = statement.executeQuery();
            while(set.next()){
                author = new Author();
                author.setId(set.getInt("authorID"));
                author.setFirstName(set.getString("authorFirstName"));
                author.setLastName(set.getString("authorLastName"));
                author.setBlogURL(set.getString("authorBlogURL"));
                author.setBirthdate(set.getDate("authorBirthdate"));
                authors.add(author);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AuthorRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return authors;
    }

    @Override
    public Optional<Author> getByid(int id) {
        Connection connection = dbConnection.getConnection();
        Author author = null;
        
        try {
            // write sql query
            String sql = "SELECT * FROM authors WHERE authorID = ?;";
            // get prepared statement
            PreparedStatement statement =  connection.prepareStatement(sql);
            statement.setInt(1, id);
           
            // execute sql query
            ResultSet set = statement.executeQuery();
            while(set.next()){
                author = new Author();
                author.setId(set.getInt("authorID"));
                author.setFirstName(set.getString("authorFirstName"));
                author.setLastName(set.getString("authorLastName"));
                author.setBlogURL(set.getString("authorBlogURL"));
                author.setBirthdate(set.getDate("authorBirthdate"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AuthorRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.ofNullable(author);
    }
}
