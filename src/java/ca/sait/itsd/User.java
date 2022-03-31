/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

import java.io.Serializable;

/**
 *
 * @author manve
 */
public class User implements Serializable {

    private String username;
    private String password;
    private int userType;

    public User(){
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
//        this.userType = userType;
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
    
    

}
