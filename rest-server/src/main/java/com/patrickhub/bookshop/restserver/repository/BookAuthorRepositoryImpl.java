/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Patrickhub
 */
@Stateless
public class BookAuthorRepositoryImpl implements BookAuthorRepository{

     @Inject
    private DBConnection dbConnection;
    
    @Override
    public void saveBookAuthor(int bookID, int authorID) {
        try {
             // get connection to db
            Connection connection = dbConnection.getConnection();
            // write sql insert
            String sql = "INSERT INTO bookAuthor(bookID, authorID)"
                            + "VALUES(?,?)";
       
            // get prepared statement
            PreparedStatement statement = connection.prepareStatement(sql);
            // set parameters
            statement.setInt(1, bookID);
            statement.setInt(2, authorID);
            
            // execute insert query
            int row = statement.executeUpdate();
            // check if bookAuthor entity is created
            if (row == 0) {
                throw new SQLException("Failled to create bookAuthor, no rows affected.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookAuthorRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
}
