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
public class BadPhoneNoException extends Exception {
    
    private String message;
    
    public BadPhoneNoException() {
        this.message = "Please enter a correctly formatted phone number";
    }
    
    public String getMessage() {
        return this.message;
    }
}
