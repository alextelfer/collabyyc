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
public class BadStringException extends Exception {
    
    private String message;
    
    public BadStringException(String whichString) {
        this.message = whichString + "is an invalid String";
    }
    
    public String getMessage() {
        return this.message;
    }
}
