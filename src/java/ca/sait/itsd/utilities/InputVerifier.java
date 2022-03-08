/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd.utilities;

/**
 *
 * @author 857421
 */
public class InputVerifier {        
    
    private static final String[] badCharacters = {"\"", "\'", "<", ">", "\\", "/", ";", "(", ")"};
    
    private InputVerifier() {}        
    
    public static boolean checkBadString(String s) {
        
        boolean badString = false;
        
        for (String badCharacter : badCharacters) {
            if (s.contains(badCharacter)) {
                badString = true;
            }
        }
        
        return badString;
    }
    
    public static boolean checkEmail(String s) {
        
        boolean badString = false;
        
        if(s.contains("@") && s.contains(".")) badString = true;
        
        return badString;
    }
    
    public static boolean checkPhoneNo(String s) {
        
        boolean badString = false;
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!Character.isDigit(c)) {
                badString = true;
            }
        }
        
        return badString;
    }

}
