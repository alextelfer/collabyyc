/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Alex Telfer, Brady Belsher
 */
public class DBOperations {
    
    public ArrayList<Vendor> getVendors() {
        
        ArrayList<Vendor> vendors = new ArrayList<Vendor>();
        vendors.add(new Vendor(0, "", "", ""));
        
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        
        try {
            
            Connection conn = connectionPool.getConnection();
            
            String sql = "SELECT * FROM collabyyc.vendors";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    int vendorID = rs.getInt("vendorID");
                    String name = rs.getString("vendorName");
                    String email = rs.getString("vendorEmail");
                    String phone = rs.getString("vendorPhone");
                    Vendor vendor = new Vendor(vendorID, name, email, phone);
                    vendors.add(vendor);
                    System.out.println("adding");
                }
                
                connectionPool.freeConnection(conn);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
               
        return vendors;
    }
     public String getInventory() {
        String result = "";
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            Connection conn = pool.getConnection();
            String sql = "select * from collabyyc.items;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = result + rs.getInt(1) + "," + rs.getInt(2) + "," + rs.getString(3) +
                    "," + rs.getDouble(4) + "," + rs.getInt(5) + "," + rs.getString(6) + ";";
            }
            rs.close();
            ps.close();
            pool.freeConnection(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
     
    public boolean deleteItem(String itemName) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();

        String sql = "delete from collabyyc.items where name=?";

        try {
            Connection conn = pool.getConnection();

            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, itemName);

            int rowAffected = st.executeUpdate();

            result = (rowAffected > 0);

            st.close();
            pool.freeConnection(conn);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }
    
    public boolean addItem(Item item) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();

        try {
            String sql = "insert into collabyyc.items set ItemID=?, VendorID=?, NameProducts=?, Price=?, Quantity=?, Category=?";

            Connection conn = pool.getConnection();

            PreparedStatement st = conn.prepareStatement(sql);
            
            String ItemIDStr = Integer.toString(item.itemID);
            String VendorIDStr = Integer.toString(item.vendorID);
            String PriceStr = Double.toString(item.price);
            String QuantityStr = Integer.toString(item.quantity);
           

            st.setString(1, ItemIDStr);
            st.setString(2, VendorIDStr);
            st.setString(3, item.name);
            st.setString(4, PriceStr);
            st.setString(5, QuantityStr);
            st.setString(6, item.category);
            
            int rowAffected = st.executeUpdate();

            result = (rowAffected > 0);

            st.close();
            pool.freeConnection(conn);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;

    }
    
    public boolean addVendor(Vendor vendor) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();

        try {
            String sql = "insert into collabyyc.vendors set vendorID=?, vendorName=?, vendorEmail=?, vendorPhone=?";

            Connection conn = pool.getConnection();

            PreparedStatement st = conn.prepareStatement(sql);
            
            String vendorIDStr = Integer.toString(vendor.vendorID);                    

            st.setString(1, vendorIDStr);
            st.setString(2, vendor.name);
            st.setString(3, vendor.vendorEmail);
            st.setString(4, vendor.vendorPhoneNumber);        
            
            int rowAffected = st.executeUpdate();

            result = (rowAffected > 0);

            st.close();
            pool.freeConnection(conn);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;

    }
     
}
