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

/**
 *
 * @author Alex Telfer, Brady Belsher
 */
public class DBOperations {
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

        String sql = "delete from CreateTablesCollab.items where name=?";

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
    
    public boolean addItem(int ItemID, int VendorID, String itemName, Double Price, int Quantity, String Category) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();

        try {
            String sql = "insert into CreateTablesCollab.items set ItemID=?, VendorID=?, Name=?, Price=?, Quantity=?, Category=?";

            Connection conn = pool.getConnection();

            PreparedStatement st = conn.prepareStatement(sql);
            
            String ItemIDStr = Integer.toString(ItemID);
            String VendorIDStr = Integer.toString(VendorID);
            String PriceStr = Double.toString(Price);
            String QuantityStr = Integer.toString(Quantity);
           

            st.setString(1, ItemIDStr);
            st.setString(2, VendorIDStr);
            st.setString(3, itemName);
            st.setString(4, PriceStr);
            st.setString(5, QuantityStr);
            st.setString(6, Category);
            
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
