/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.repository;

import com.patrickhub.bookshop.restserver.beans.Author;
import com.patrickhub.bookshop.restserver.beans.Book;
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
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author PatrickHub
 */
@Stateless
public class BookRepositoryImpl implements BookRepository{
    
    @Inject
    private DBConnection dbConnection;
    
    @EJB
    private AuthorRepository authorRepository;
    
    @EJB
    private BookAuthorRepository bookAuthorRepository;
    
    private static final String API_URL = "http://localhost:8080/rest-server";
    private static final String IMAGE_LOCATION = "/images/covers/";

    @Override
    public Book saveBook(Book book) {
        try {
             // get connection to db
            Connection connection = dbConnection.getConnection();
            // write sql insert
            String sql = "INSERT INTO books(bookTitle, bookDescription, bookPrice, bookImgPath, bookPublishedDate,  bookLink)"
                            + "VALUES(?,?,?,?,?,?)";
       
            // get prepared statement
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // set parameters
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getDescription());
            statement.setFloat(3, book.getPrice());
            statement.setString(4, book.getImgPath());
            statement.setDate(5, new Date(book.getPublished().getTime()));
            statement.setString(6, book.getLink());
            
            // execute insert query
            int row = statement.executeUpdate();
            // check if book entity is created
            if (row == 0) {
                throw new SQLException("Failled to create book, no rows affected.");
	    }
			
            // check weather bookID has been generated
	    ResultSet generatedIds = statement.getGeneratedKeys();
            if (generatedIds.next()) {
                book.setId(generatedIds.getInt(1));
                book.setImgPath(API_URL + IMAGE_LOCATION + book.getImgPath());
                // save authors of the current book to db
                for(Author author: book.getAuthors()){
                   authorRepository.saveAuthor(author);
                   bookAuthorRepository.saveBookAuthor(book.getId(), author.getId());
                }
            }
            else {
                throw new SQLException("Failled to create books, no bookID obtained.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return book;
    }

    @Override
    public Optional<Book> deleteBook(int id) {
        Optional<Book> optional =  getByid(id);
        Connection connection = dbConnection.getConnection();
        
        try {
            // write sql delete
            String sql = "DELETE FROM books WHERE bookID = ?;";
            // get prepared statement
            PreparedStatement statement =  connection.prepareStatement(sql);
            // set sql delete parameters
            statement.setInt(1, id);
            // execute sql delete
            int row = statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BookRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return optional;
    }

    @Override
    public List<Book> getAll() {
        Connection connection = dbConnection.getConnection();
        List<Book> books = null;
        Book book = null;
        
        try {
            // write sql query
            String sql = "SELECT * FROM books;";
            // get prepared statement
            PreparedStatement statement =  connection.prepareStatement(sql);
           
            // execute sql query
            ResultSet set = statement.executeQuery();
            while(set.next()){
                book = new Book();
                book.setId(set.getInt("bookID"));
                book.setTitle(set.getString("bookTitle"));
                book.setDescription(set.getString("bookDescription"));
                book.setPrice(set.getFloat("bookPrice"));
                book.setImgPath(API_URL + IMAGE_LOCATION + set.getString("bookImgPath"));
                book.setPublished(set.getDate("bookPublishedDate"));
                book.setLink(set.getString(sql));
                books.add(book);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }

    @Override
    public Optional<Book> getByid(int id) {
        Connection connection = dbConnection.getConnection();
        Book book = null;
        
        try {
            // write sql query
            String sql = "SELECT * FROM books WHERE bookID = ?;";
            // get prepared statement
            PreparedStatement statement =  connection.prepareStatement(sql);
            statement.setInt(1, id);
           
            // execute sql query
            ResultSet set = statement.executeQuery();
            while(set.next()){
                book = new Book();
                book.setId(set.getInt("bookID"));
                book.setTitle(set.getString("bookTitle"));
                book.setDescription(set.getString("bookDescription"));
                book.setPrice(set.getFloat("bookPrice"));
                book.setImgPath(API_URL + IMAGE_LOCATION + set.getString("bookImgPath"));
                book.setPublished(set.getDate("bookPublishedDate"));
                book.setLink(set.getString(sql));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.ofNullable(book);
    }
    
    
}
