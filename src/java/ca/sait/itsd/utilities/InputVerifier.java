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
    
    private static String[] badCharacters = {"\"", "\'", "<", ">", "\\", "/", ";", "(", ")"};
    
    private InputVerifier() {}        
    
    public static boolean checkString(String s) {
        
        boolean badString = false;
        
        for (String badCharacter : badCharacters) {
            if (s.contains(badCharacter)) {
                badString = true;
            }
        }
        
        return badString;
    }

}
