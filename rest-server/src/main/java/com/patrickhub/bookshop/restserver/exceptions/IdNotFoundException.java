/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patrickhub.bookshop.restserver.exceptions;

/**
 *
 * @author PatrickHub
 */
public class IdNotFoundException extends Exception {
    
    private final String message;

    public IdNotFoundException() {
        message = "Id not found";
    }

    public IdNotFoundException(String message) {
        this.message = message;
    }
    
}
