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

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int vendorID = rs.getInt("vendorID");
                    String name = rs.getString("vendorName");
                    String email = rs.getString("vendorEmail");
                    String phone = rs.getString("vendorPhone");
                    Vendor vendor = new Vendor(vendorID, name, email, phone);
                    vendors.add(vendor);
                }
                rs.close();
                ps.close();
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

            String sql = "select sku, nameproducts, price, quantity, category, vendorName from collabyyc.items";
            PreparedStatement ps = conn.prepareStatement(sql);

            try (ResultSet rs = ps.executeQuery()) {
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
                rs.close();
                ps.close();
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

            if (rs.next()) {
                item = new Item(
                        rs.getInt("itemID"),
                        rs.getInt("sku"),
                        rs.getInt("vendorID"),
                        rs.getString("nameProducts"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("category"));                
            }
            rs.close();
            ps.close();
            connectionPool.freeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    public ArrayList<EmployeeAccount> getEmployeeAccounts() {
        ArrayList<EmployeeAccount> employees = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {

            Connection conn = connectionPool.getConnection();

            String sql = "SELECT * FROM collabyyc.EmployeeAccounts";
            PreparedStatement ps = conn.prepareStatement(sql);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int employeeID = Integer.parseInt(rs.getString("employeeID"));
                    String employeePassword = rs.getString("employeePassword");
                    String employeeName = rs.getString("employeeName");
                    String employeeEmail = rs.getString("employeeEmail");
                    long employeePhone = Long.parseLong(rs.getString("employeePhone"));
                    EmployeeAccount employee = new EmployeeAccount(employeeID, employeePassword, employeeName,
                            employeeEmail, employeePhone);
                    employees.add(employee);
                }
                rs.close();
                ps.close();
                connectionPool.freeConnection(conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;

    }

    public EmployeeAccount getEmployeeAccount(String employeeID, String password) throws Exception {
        EmployeeAccount employee = null;

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {
            Connection conn = connectionPool.getConnection();

            String sql = "SELECT * FROM collabyyc.EmployeeAccounts where employeeID=? AND employeePassword=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(employeeID));
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String employeeFound = rs.getString("employeeID");
                int employeeIDReturned = Integer.parseInt(rs.getString("employeeID"));
                String passwordReturned = rs.getString("employeePassword");
                String employeeName = rs.getString("employeeName");
                String employeeEmail = rs.getString("employeeEmail");
                long employeePhone = Long.parseLong(rs.getString("employeePhone"));

                employee = new EmployeeAccount(employeeIDReturned, passwordReturned, employeeName, employeeEmail,
                        employeePhone);
            } else {
                Exception noEmployeeFound = new Exception();
                throw noEmployeeFound;
            }
        } catch (Exception noUserFound) {
            throw noUserFound;
        }

        return employee;
    }

    public boolean addEmployeeAccount(EmployeeAccount employee) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();

        try {

            String sql = "insert into collabyyc.EmployeeAccounts set employeeID=?, employeePassword=?, employeeName=?, employeeEmail=?, employeePhone=?";

            Connection conn = pool.getConnection();

            PreparedStatement st = conn.prepareStatement(sql);

            // st.setString(1, Integer.toString(employee.getEmployeeID()));
            st.setInt(1, employee.getEmployeeID());
            st.setString(2, employee.getEmployeePassword());
            st.setString(3, employee.getEmployeeName());
            st.setString(4, employee.employeeEmail());
            st.setLong(5, employee.getEmployeePhone());
            // st.setString(5, Integer.toString(employee.getEmployeePhone()));

            int rowAffected = st.executeUpdate();
            result = (rowAffected > 0);

            st.close();
            pool.freeConnection(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public ArrayList<User> getUsers() {

        ArrayList<User> users = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {

            Connection conn = connectionPool.getConnection();

            String sql = "SELECT * FROM collabyyc.users";
            PreparedStatement ps = conn.prepareStatement(sql);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String userName = rs.getString("userName");
                    String password = rs.getString("password");
                    User user = new User(userName, password);
                    users.add(user);
                }
                rs.close();
                ps.close();
                connectionPool.freeConnection(conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;

    }

    public User getUserAccount(String username, String password) throws Exception {
        User user = null;

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {
            Connection conn = connectionPool.getConnection();

            String sql = "SELECT * FROM collabyyc.Users where userName=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String usernameReturned = rs.getString("userName");
                String passwordReturned = rs.getString("password");

                user = new User(usernameReturned, passwordReturned);

            } else {
                Exception noUserFound = new Exception();
                throw noUserFound;
            }
            rs.close();
            ps.close();
            connectionPool.freeConnection(conn);
        } catch (Exception noUserFound) {
            throw noUserFound;
        }

        return user;
    }

    public boolean addUser(User user) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();

        try {

            String sql = "insert into collabyyc.users set userName=?, password=?";

            Connection conn = pool.getConnection();

            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());

            int rowAffected = st.executeUpdate();
            result = (rowAffected > 0);

            st.close();
            pool.freeConnection(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
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
            String sql = "insert into collabyyc.items set sku=?, VendorID=?, vendorName=?, NameProducts=?, Price=?, Quantity=?, Category=?";

            Connection conn = pool.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            String skuStr = Integer.toString(item.sku);
            System.out.println(item.vendorName);
            int vendorID1 = returnVendorID(item.vendorName);
            // String VendorIDStr = Integer.toString(item.vendorID);
            String PriceStr = Double.toString(item.price);
            String QuantityStr = Integer.toString(item.quantity);

            st.setString(1, skuStr);
            st.setInt(2, vendorID1);
            st.setString(3, item.vendorName);
            st.setString(4, item.name);
            st.setString(5, PriceStr);
            st.setString(6, QuantityStr);
            st.setString(7, item.category);

            int rowAffected = st.executeUpdate();

            result = (rowAffected > 0);

            st.close();
            pool.freeConnection(conn);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;

    }

    public boolean addVendor(String vendorName, String vendorEmail, String vendorPhone) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();

        try {
            String sql = "insert into collabyyc.vendors set vendorName=?, vendorEmail=?, vendorPhone=?";

            Connection conn = pool.getConnection();

            PreparedStatement st = conn.prepareStatement(sql);            
            
            st.setString(1, vendorName);
            st.setString(2, vendorEmail);
            st.setString(3, vendorPhone);

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
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int itemID = rs.getInt("itemID");
                    String vendorName = rs.getString("vendorID");
                    String name = rs.getString("nameProducts");
                    Double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String category = rs.getString("category");
                    Item item = new Item(itemID, vendorName, name, price, quantity, category);
                    singleItem.add(item);
                }
                rs.close();
                ps.close();
                connectionPool.freeConnection(conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return singleItem;
    }

    public ArrayList<Vendor> retrieveVendor(int modifyVendor) {
        ArrayList<Vendor> singleVendor = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {

            Connection conn = connectionPool.getConnection();

            String sql = "SELECT * FROM collabyyc.vendors WHERE VendorID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, modifyVendor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int vendorID = rs.getInt("vendorID");
                    String vendorName = rs.getString("vendorName");
                    String vendorEmail = rs.getString("vendorEmail");
                    String vendorPhone = rs.getString("vendorPhone");
                    Vendor vendor = new Vendor(vendorID, vendorName, vendorEmail, vendorPhone);
                    singleVendor.add(vendor);

                }
                rs.close();
                ps.close();
                connectionPool.freeConnection(conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return singleVendor;
    }

    public boolean modifyItem(String sku, String itemName, String price, String quantity, String category,
            String oldSKU) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();

        try {
            String sql = "update collabyyc.items set sku=?, nameProducts=?, price=?, quantity=?, category=? where sku=?";
            Connection conn = pool.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            // String itemID = request.getParameter("itemid");
            // String vendorID = Integer.toString(item.getVendorID());
            // String itemName = item.getName();
            // String price = Double.toString(item.getPrice());
            // String quantity = Integer.toString(item.getQuantity());
            // String category = item.getCategory();
            // String oldIDStr = Integer.toString(oldID);
            st.setString(1, sku);
            // st.setString(2, vendorID);
            st.setString(2, itemName);
            st.setString(3, price);
            st.setString(4, quantity);
            st.setString(5, category);
            st.setString(6, oldSKU);

            int rowsAffected = st.executeUpdate();
            result = (rowsAffected > 0);
            st.close();
            pool.freeConnection(conn);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public boolean updateVendor(String vendorName, String vendorEmail, String vendorPhone, String oldVendorID) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();

        try {
            String sql = "update collabyyc.vendors set vendorName=?, vendorEmail=?, vendorPhone=? where vendorID=?";
            Connection conn = pool.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            // String vendorIDStr = Integer.toString(vendor.getVendorID());
            // String vendorName = vendor.getName();
            // String vendorEmail = vendor.getVendorEmail();
            // String vendorPhoneNumber = vendor.getVendorPhoneNumber();
            // st.setString(1, vendorID);
            st.setString(1, vendorName);
            st.setString(2, vendorEmail);
            st.setString(3, vendorPhone);
            st.setString(4, oldVendorID);

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

            try (ResultSet rs = ps.executeQuery()) {
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

                    Sale sale = new Sale(transactionID, customerID, paymentDate, saleAmount, payVendorAmount, soldItems,
                            sentShippingDate, shippingAddress, pickupDate);
                    sales.add(sale);
                }
                rs.close();
                ps.close();
                connectionPool.freeConnection(conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sales;
    }

    public boolean addSale(Sale sale) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();

        try {
            Connection conn = pool.getConnection();

            String sql = "insert into collabyyc.sale set transactionID = ?, customerID = ?, paymentDate = ?, "
                    + "saleAmount = ?, payVendorAmount = ?, soldItems = ?, shippingSentDate = ?, "
                    + "shippingAddress = ?, pickupDate = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, sale.getTransactionID());
            ps.setInt(2, sale.getCustomerID());
            ps.setDate(3, new Date(sale.getPaymentDate().getTime()));
            ps.setDouble(4, sale.saleAmount);
            ps.setDouble(5, sale.getPayVendorAmount());
            ps.setString(6, sale.getSoldItems());
            ps.setDate(7, new Date(sale.getSentShippingDate().getTime()));
            ps.setString(8, sale.getShippingAddress());
            ps.setDate(9, new Date(sale.getPickupDate().getTime()));

            int rowAffected = ps.executeUpdate();
            result = (rowAffected > 0);
            ps.close();

            pool.freeConnection(conn);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;

    }

    public ArrayList<Item> searchBySKU(int searchedSku) {
        ArrayList<Item> result = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {
            Connection conn = connectionPool.getConnection();

            String sql = "SELECT * FROM collabyyc.items where sku=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, searchedSku);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int sku = rs.getInt("sku");
                    String name = rs.getString("nameProducts");
                    Double price = rs.getDouble("price");
                    String category = rs.getString("category");
                    String vendor = rs.getString("vendorName");
                    Item item = new Item(sku, name, price, category, vendor);
                    result.add(item);
                }
                rs.close();
                ps.close();
                connectionPool.freeConnection(conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int returnVendorID(String vendorName) {
        int result = 0;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            Connection conn = connectionPool.getConnection();
            String sql = "SELECT vendorID FROM collabyyc.vendors where vendorName=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, vendorName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("vendorID");
            }
            rs.close();
            ps.close();
            connectionPool.freeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public ArrayList<Sale> searchByDate(String date) {
        ArrayList<Sale> searched = new ArrayList<Sale>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            Connection conn = connectionPool.getConnection();
            String sql = "select * from collabyyc.sale where DATE(paymentDate)=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
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

                Sale sale = new Sale(transactionID, customerID, paymentDate, saleAmount, payVendorAmount, soldItems,
                        sentShippingDate, shippingAddress, pickupDate);
                searched.add(sale);
            }
            rs.close();
            ps.close();
            connectionPool.freeConnection(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searched;
    }

    public Sale getSale(int transactionID) {
        Sale sale = null;
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        try {
            Connection c = cp.getConnection();
            
            String sql = "SELECT * FROM collabyyc.sale WHERE transactionID = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            
            ps.setInt(1, transactionID);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                sale = new Sale(
                        rs.getInt("transactionID"),
                        rs.getDate("paymentDate"),
                        rs.getDouble("saleAmount"),
                        rs.getDouble("payVendorAmount"),
                        rs.getString("soldItems")
                );
            }
            rs.close();
            ps.close();
            cp.freeConnection(c);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return sale;
    }

    public ArrayList<Item> getSoldItems(int transactionID) {
        ArrayList<Item> items = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {

            Connection conn = connectionPool.getConnection();

            String sql = "SELECT * FROM collabyyc.solditems WHERE transactionID = ?";            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, transactionID);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Item item = getItem(String.valueOf(rs.getInt("itemID")));
                    items.add(item);
                }
                rs.close();
                ps.close();
                connectionPool.freeConnection(conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public void addSoldItems(ArrayList<Item> soldItems, int transactionID) {
        
        ConnectionPool cp = ConnectionPool.getInstance();
        
        try {
            Connection c = cp.getConnection();
            
            String sql = "INSERT INTO collabyyc.solditems (transactionID, itemID) VALUES (?, ?)";
            PreparedStatement ps = c.prepareStatement(sql);
            
            for(Item item : soldItems) {
                ps.setInt(1, transactionID);                
                ps.setInt(2, item.getSku());
                ps.addBatch();
            }
            
            ps.executeBatch();
            
            ps.close();
            cp.freeConnection(c);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
