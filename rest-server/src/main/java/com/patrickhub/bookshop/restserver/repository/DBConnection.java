/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;

/**
 *
 * @author PatrickHub
 */
@Startup
@Singleton
public class DBConnection {
    private Connection connection;
    
    @PostConstruct
    private void init(){
        try {
            // load Mysql Driver
            Class.forName("com.mysql.jdbc.Driver");
            Logger.getAnonymousLogger().info("Mysql JDBC Driver Registered");
            // get hold of the DriverManager
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshopWithJAXRSdb?autoReconnect=true&useSSL=false", "root", "P@trick29");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(connection != null){
            Logger.getAnonymousLogger().info("Connection made to DB");
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
}
