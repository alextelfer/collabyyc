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
            String sql = "select * from collabyyc.items;";
            Connection conn = pool.getConnection();            
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result = result + rs.getInt(1) + "," + rs.getInt(2) + "," + rs.getString(3) +
                    "," + rs.getDouble(4) + "," + rs.getInt(5) + "," + rs.getString(6) + ";";
            }
            rs.close();
            st.close();
            pool.freeConnection(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
     
    public boolean deleteItem(String itemName) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();   
        try {
            String sql = "delete from collabyyc.items where name=?";
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
            String sql = "insert into collabyyc.items set itemID=?, vendorID=?, name=?, price=?, quantity=?, category=?";
            Connection conn = pool.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
                       
            String itemID = Integer.toString(item.getItemID());
            String vendorID = Integer.toString(item.getVendorID());
            String itemName = item.getName();
            String price = Double.toString(item.getPrice());
            String quantity = Integer.toString(item.getQuantity());
            String category = item.getCategory();

            st.setString(1, itemID);
            st.setString(2, vendorID);
            st.setString(3, itemName);
            st.setString(4, price);
            st.setString(5, quantity);
            st.setString(6, category);
            
            int rowAffected = st.executeUpdate();
            result = (rowAffected > 0);

            st.close();
            pool.freeConnection(conn);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;

    }
    
    public boolean modifyItem(Item item) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try {
            String sql = "update collabyyc.items set vendorID=?, name=?, price=?, quantity=?, cateogory=? where itemID=?";
            Connection conn = pool.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            
            String itemID = Integer.toString(item.getItemID());
            String vendorID = Integer.toString(item.getVendorID());
            String itemName = item.getName();
            String price = Double.toString(item.getPrice());
            String quantity = Integer.toString(item.getQuantity());
            String category = item.getCategory();
            
            st.setString(1, vendorID);
            st.setString(2, itemName);
            st.setString(3, price);
            st.setString(4, quantity);
            st.setString(5, category);
            st.setString(6, itemID);
            
            int rowsAffected = st.executeUpdate();
            result = (rowsAffected > 0);
            st.close();
            pool.freeConnection(conn);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
