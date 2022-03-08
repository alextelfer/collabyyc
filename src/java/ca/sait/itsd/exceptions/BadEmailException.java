/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd.exceptions;

/**
 *
 * @author 857421
 */
public class BadEmailException extends Exception{
    
    private String message;
    
    public BadEmailException() {
        this.message = "Please enter a correctly formatted email address.";
    }
    
    public String getMessage() {
        return this.message;
    }
}
