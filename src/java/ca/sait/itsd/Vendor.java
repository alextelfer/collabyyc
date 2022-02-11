package ca.sait.itsd;

import java.io.Serializable;

public class Vendor implements Serializable {
    
    int vendorID;
    String vendorEmail;
    int vendorPhoneNumber;
    
    public Vendor(int vendorID, String vendorEmail, int vendorPhoneNumber) {
        this.vendorID = vendorID;
        this.vendorEmail = vendorEmail;
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

    public int getVendorID() {
        return this.vendorID;
    }

    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }

    public String getVendorEmail() {
        return this.vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public int getVendorPhoneNumber() {
        return this.vendorPhoneNumber;
    }

    public void setVendorPhoneNumber(int vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

/* 
    public void createNewPayment() {
        
    }

    public double sendPayment() {
        return 0.0;
    } */


}