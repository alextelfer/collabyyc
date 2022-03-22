/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

/**
 *
 * @author 857421
 */
public class VendorShare {
    
    private final double amount;
    private final int vendorID;
    
    public VendorShare(double amount, int vendorID) {
        this.amount = amount;
        this.vendorID = vendorID;
    }
    
    public double getAmount() {
        return this.amount;
    }
    
    public int getVendorID() {
        return this.vendorID;
    }
}
