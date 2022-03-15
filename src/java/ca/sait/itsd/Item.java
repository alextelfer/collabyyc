package ca.sait.itsd;

import java.io.Serializable;

public class Item implements Serializable {

    int itemID;
    int sku;
    int vendorID;
    String name;
    double price;
    int quantity;
    String category;
    
    // public Item(int itemID, int vendorID, String name, double price, int quantity, String category) {
    //     this.itemID = itemID;
    //     this.vendorID = vendorID;
    //     this.name = name;
    //     this.price = price;
    //     this.quantity = quantity;
    //     this.category = category;
    // }

    public Item(int sku, int vendorID, String name, double price, int quantity, String category) {
     //   this.itemID = itemID;
        this.sku = sku;
        this.vendorID = vendorID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }
    
    public Item() {}
        
    public Vendor getVendorFinance() {
        //dummy vendor
        Vendor vendorInfo = new Vendor(0, "", "", "");
        return vendorInfo;
    } 

    public int getItemID() {
        return this.itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getVendorID() {
        return this.vendorID;
    }

    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    

}