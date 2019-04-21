/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.repository;

import com.patrickhub.bookshop.restserver.beans.Author;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
            statement.setDate(3, new java.sql.Date(author.getBirthdate().getTime()));
            statement.setString(4, author.getBlogURL());
            
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
        List<Author> authors = new ArrayList<>();
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
                author.setBirthdate(new Date(set.getDate("authorBirthdate").getTime()));
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
                author.setBirthdate(new Date(set.getDate("authorBirthdate").getTime()));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AuthorRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.ofNullable(author);
    }

    @Override
    public Author updateAuthor(Author author) {
        try {
             // get connection to db
            Connection connection = dbConnection.getConnection();
            // write sql update
            String sql = "UPDATE authors SET authorFirstName = ?, authorLastName = ?, "
                            + "authorBirthdate = ?, authorBlogURL = ?"
                            + "WHERE authorID = ?;";
       
            // get prepared statement
            PreparedStatement statement = connection.prepareStatement(sql);
            // set parameters
            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());
            statement.setDate(3, new java.sql.Date(author.getBirthdate().getTime()));
            statement.setString(4, author.getBlogURL());
            statement.setInt(5, author.getId());
            
            // execute update query
            int row = statement.executeUpdate();
            // check if author entity is updated
            if (row == 0) {
                throw new SQLException("Failled to update author, no rows affected.");
	    }
        } catch (SQLException ex) {
            Logger.getLogger(BookRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return author;
    }
}
