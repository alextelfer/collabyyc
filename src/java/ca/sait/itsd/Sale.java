package ca.sait.itsd;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author 840303
 */
public class Sale implements Serializable {
    
    int transactionID;
    int customerID;
    Date paymentDate;
    double saleAmount;
    double payVendorAmount;
    String soldItems;
    Date sentShippingDate;
    String shippingAddress;
    Date pickupDate;
    ArrayList<Item> items;
    ArrayList<VendorShare> shares;
    
    public Sale () {
        items = new ArrayList<>();
        shares = new ArrayList<>();
    }
        
    // in store sale
    public Sale(int transactionID, Date paymentDate, double saleAmount, double payVendorAmount, String soldItems) {
        this.transactionID = transactionID;
        this.paymentDate = paymentDate;
        this.saleAmount = saleAmount;
        this.payVendorAmount = payVendorAmount;
        this.soldItems = soldItems;
    }
    // online order with shipping
    public Sale(int transactionID, Date paymentDate, double saleAmount, double payVendorAmount, String soldItems, Date sentShippingDate, String shippingAddress) {
        this.transactionID = transactionID;
        this.paymentDate = paymentDate;
        this.saleAmount = saleAmount;
        this.payVendorAmount = payVendorAmount;
        this.soldItems = soldItems;
        this.sentShippingDate = sentShippingDate;
        this.shippingAddress = shippingAddress;
    }
    // online order with pickup 
    public Sale(int transactionID, Date paymentDate, double saleAmount, double payVendorAmount, String soldItems, Date pickupDate) {
        this.transactionID = transactionID;
        this.paymentDate = paymentDate;
        this.saleAmount = saleAmount;
        this.payVendorAmount = payVendorAmount;
        this.soldItems = soldItems;
        this.pickupDate = pickupDate;
    }
    // default sale
    public Sale(int transactionID, int customerID, Date paymentDate, double saleAmount, double payVendorAmount, String soldItems, Date sentShippingDate, String shippingAddress, Date pickupDate) {
        this.transactionID = transactionID;
        this.customerID = customerID;
        this.paymentDate = paymentDate;
        this.saleAmount = saleAmount;
        this.payVendorAmount = payVendorAmount;
        this.soldItems = soldItems;
        this.sentShippingDate = sentShippingDate;
        this.shippingAddress = shippingAddress;
        this.pickupDate = pickupDate;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public double getPayVendorAmount() {
        return payVendorAmount;
    }

    public void setPayVendorAmount(double payVendorAmount) {
        this.payVendorAmount = payVendorAmount;
    }

    public String getSoldItems() {
        return soldItems;
    }

    public void setSoldItems(String soldItems) {
        this.soldItems = soldItems;
    }

    public Date getSentShippingDate() {
        return sentShippingDate;
    }

    public void setSentShippingDate(Date sentShippingDate) {
        this.sentShippingDate = sentShippingDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }
    
    public ArrayList<Item> getItems() {
        return this.items;
    }
    
    public void setItems(ArrayList<Item> items) {    
        for(Item item : items) {
            this.saleAmount += item.getPrice();
            VendorShare vendorShare = new VendorShare((item.getPrice() * 0.55), item.getVendorID());
            shares.add(vendorShare);
        }        
        this.items = items;
    }
    
}
