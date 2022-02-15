package ca.sait.itsd;

import java.io.Serializable;

public class Vendor implements Serializable {
    
    int vendorID;
    String name;
    String vendorEmail;
    String vendorPhoneNumber;
    
    public Vendor(int vendorID, String name, String vendorEmail, String vendorPhoneNumber) {
        this.vendorID = vendorID;
        this.name = name;
        this.vendorEmail = vendorEmail;
        this.vendorPhoneNumber = vendorPhoneNumber;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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

    public String getVendorPhoneNumber() {
        return this.vendorPhoneNumber;
    }

    public void setVendorPhoneNumber(String vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

/* 
    public void createNewPayment() {
        
    }

    public double sendPayment() {
        return 0.0;
    } */


}
