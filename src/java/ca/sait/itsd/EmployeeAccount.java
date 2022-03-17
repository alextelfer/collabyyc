/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

/**
 *
 * @author manve
 */
public class EmployeeAccount {
    
    private int employeeID;
    private String employeePassword;
    private String employeeName;
    private String employeeEmail;
    private int employeePhone;
    
    public EmployeeAccount() {
    }

    public EmployeeAccount(int employeeID, String employeePassword, String employeeName, String employeeEmail, int employeePhone) {
        this.employeeID = employeeID;
        this.employeePassword = employeePassword;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeePhone = employeePhone;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String employeeEmail() {
        return employeeEmail;
    }

    public void employeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public int getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(int employeePhone) {
        this.employeePhone = employeePhone;
    }
  
}
