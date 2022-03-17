package ca.sait.itsd;

import java.sql.Connection;
import java.sql.Date;
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

        ArrayList<Vendor> vendors = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {

            Connection conn = connectionPool.getConnection();

            String sql = "SELECT * FROM collabyyc.vendors";
            PreparedStatement ps = conn.prepareStatement(sql);

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int vendorID = rs.getInt("vendorID");
                    String name = rs.getString("vendorName");
                    String email = rs.getString("vendorEmail");
                    String phone = rs.getString("vendorPhone");
                    Vendor vendor = new Vendor(vendorID, name, email, phone);
                    vendors.add(vendor);
                }

                connectionPool.freeConnection(conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendors;
    }

    public ArrayList<Item> getItems() {

        ArrayList<Item> items = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {

            Connection conn = connectionPool.getConnection();

            String sql = "select sku, nameproducts, price, quantity, category, vendorName from collabyyc.items inner join vendors on collabyyc.items.vendorID=collabyyc.vendors.vendorID";
            PreparedStatement ps = conn.prepareStatement(sql);

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int sku = rs.getInt("sku");                    
                    String name = rs.getString("nameProducts");
                    Double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String category = rs.getString("category");
                    String vendorName = rs.getString("vendorName");
                    Item item = new Item(sku, vendorName, name, price, quantity, category);
                    items.add(item);
                }

                connectionPool.freeConnection(conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }
    
    public Item getItem(String sku) {
        
        Item item = null;
        
        ConnectionPool connectionPool = ConnectionPool.getInstance();                        
        
        try {
            
            Connection conn = connectionPool.getConnection();
            
            String sql = "SELECT * FROM collabyyc.items WHERE sku = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sku);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                item = new Item(
                rs.getInt("sku"),
                rs.getString("vendorID"),
                rs.getString("nameProducts"),
                rs.getDouble("price"),
                rs.getInt("quantity"),
                rs.getString("category")
                );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return item;
    }
    
    public ArrayList<User> getUsers(){
        
        ArrayList<User> users = new ArrayList<>();        
        
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        
        try {
            
            Connection conn = connectionPool.getConnection();
            
            String sql = "SELECT * FROM collabyyc.users";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    String userName = rs.getString("userName");
                    String password = rs.getString("password");
                    int userType = rs.getInt("userType");
                    User user = new User(userName, password, userType);
                    users.add(user);                    
                }
                
                connectionPool.freeConnection(conn);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
               
        return users;
        
    }
    
     public String getInventory() {
        String result = "";
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            String sql = "select * from collabyyc.items;";
            Connection conn = pool.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result = result + rs.getInt(1) + "," + rs.getInt(2) + "," + rs.getString(3)
                        + "," + rs.getDouble(4) + "," + rs.getInt(5) + "," + rs.getString(6) + ";";
            }
            rs.close();
            st.close();
            pool.freeConnection(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public boolean deleteItem(String itemID) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            String sql = "delete from collabyyc.items where sku=?";
            Connection conn = pool.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, itemID);
            int rowAffected = st.executeUpdate();
            result = (rowAffected > 0);

            st.close();
            pool.freeConnection(conn);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public boolean deleteVendor(String vendorID) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            String sql = "delete from collabyyc.vendors where vendorID=?";
            Connection conn = pool.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, vendorID);
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
            String sql = "insert into collabyyc.items set sku=?, VendorID=?, NameProducts=?, Price=?, Quantity=?, Category=?";

            Connection conn = pool.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            String skuStr = Integer.toString(item.sku);
            String VendorIDStr = Integer.toString(item.vendorID);
            String PriceStr = Double.toString(item.price);
            String QuantityStr = Integer.toString(item.quantity);

            st.setString(1, skuStr);
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

    public ArrayList<Item> retrieveItem(int modifyItem) {
        ArrayList<Item> singleItem = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {

            Connection conn = connectionPool.getConnection();

            String sql = "SELECT * FROM collabyyc.items WHERE itemID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, modifyItem);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int itemID = rs.getInt("itemID");
                    String vendorName = rs.getString("vendorName");
                    String name = rs.getString("nameProducts");
                    Double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String category = rs.getString("category");
                    Item item = new Item(itemID, vendorName, name, price, quantity, category);
                    singleItem.add(item);

                }

                connectionPool.freeConnection(conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return singleItem;
    }

    public boolean modifyItem(String sku, String vendorID, String itemName, String price, String quantity, String category, String oldSKU) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();

        try {
            String sql = "update collabyyc.items set sku=?, vendorID=?, nameProducts=?, price=?, quantity=?, category=? where sku=?";
            Connection conn = pool.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

//            String itemID = request.getParameter("itemid");
//            String vendorID = Integer.toString(item.getVendorID());
//            String itemName = item.getName();
//            String price = Double.toString(item.getPrice());
//            String quantity = Integer.toString(item.getQuantity());
//            String category = item.getCategory();
//            String oldIDStr = Integer.toString(oldID);

            st.setString(1, sku);
            st.setString(2, vendorID);
            st.setString(3, itemName);
            st.setString(4, price);
            st.setString(5, quantity);
            st.setString(6, category);
            st.setString(7, oldSKU);

            int rowsAffected = st.executeUpdate();
            result = (rowsAffected > 0);
            st.close();
            pool.freeConnection(conn);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public boolean updateVendor(Vendor vendor) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();

        try {
            String sql = "update collabyyc.vendors set vendorName=?, vendorEmail=?, vendorPhone=? where vendorID=?";
            Connection conn = pool.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            String vendorID = Integer.toString(vendor.getVendorID());
            String vendorName = vendor.getName();
            String vendorEmail = vendor.getVendorEmail();
            String vendorPhoneNumber = vendor.getVendorPhoneNumber();

            st.setString(1, vendorName);
            st.setString(2, vendorEmail);
            st.setString(3, vendorPhoneNumber);
            st.setString(4, vendorID);

            int rowAffected = st.executeUpdate();
            result = (rowAffected > 0);

            st.close();
            pool.freeConnection(conn);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public String showVendor() {
        String result = "";
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            String sql = "select * from collabyyc.vendors;";
            Connection conn = pool.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result = result + rs.getInt(1) + "," + rs.getString(2) + "," + rs.getString(3)
                        + "," + rs.getString(4) + ";";
            }
            rs.close();
            st.close();
            pool.freeConnection(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<Sale> getSales() {

        ArrayList<Sale> sales = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {

            Connection conn = connectionPool.getConnection();

            String sql = "SELECT * FROM collabyyc.sale";
            PreparedStatement ps = conn.prepareStatement(sql);

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int transactionID = rs.getInt("transactionID");
                    int customerID = rs.getInt("customerID");
                    Date paymentDate = rs.getDate("paymentDate");
                    double saleAmount = rs.getDouble("saleAmount");
                    double payVendorAmount = rs.getDouble("payVendorAmount");
                    String soldItems = rs.getString("soldItems");
                    Date sentShippingDate = rs.getDate("shippingSentDate");
                    String shippingAddress = rs.getString("shippingAddress");
                    Date pickupDate = rs.getDate("pickupDate");
                    
                    Sale sale = new Sale(transactionID, customerID, paymentDate, saleAmount, payVendorAmount, soldItems, sentShippingDate, shippingAddress, pickupDate);
                    sales.add(sale);
                }

                connectionPool.freeConnection(conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sales;
    }
    
}
