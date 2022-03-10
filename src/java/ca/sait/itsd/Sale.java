/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

import java.util.Date;

/**
 *
 * @author 857421
 */
public class Sale {
    
    public int saleID;
    public float transactionAmount;
    public Date timestamp;
    
    public Sale(int saleID, float transactionAmount, Date timestamp) {
        this.saleID = saleID;
        this.transactionAmount = transactionAmount;
        this.timestamp = timestamp;
    }
    
    public int getSaleID() {
        return this.saleID;
    }
    
    public float getTransactionAmount() {
        return this.transactionAmount;
    }
    
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }
    
    public void getTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    
    public void getTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
