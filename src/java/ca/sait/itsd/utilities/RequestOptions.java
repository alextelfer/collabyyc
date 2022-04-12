/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd.utilities;

import ca.sait.itsd.DBOperations;
import ca.sait.itsd.Item;
import ca.sait.itsd.Sale;
import ca.sait.itsd.Vendor;
import ca.sait.itsd.exceptions.BadStringException;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 857421
 */
public final class RequestOptions {
    
    private RequestOptions() {}
    
    public static void updateSessionLists(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        DBOperations dbOps = new DBOperations();
        
        ArrayList<Vendor> vendors = dbOps.getVendors();
        session.setAttribute("vendorlist", vendors);
        
        ArrayList<Item> items = dbOps.getItems();
        session.setAttribute("itemlist", items);

        ArrayList<Sale> sales = dbOps.getSales();
        session.setAttribute("saleslist", sales);
    }
    
    public static void addItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DBOperations dbOps = new DBOperations();
        
        int sku = Integer.parseInt(request.getParameter("sku"));
        String vendorName = request.getParameter("vendorName");
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String category = request.getParameter("category");
        int vendorID = dbOps.returnVendorID(vendorName);
        
        try {
            if (InputVerifier.checkBadString(name)) {
                throw new BadStringException(name);
            }
            if (InputVerifier.checkBadString(category)) {
                throw new BadStringException(category);
            }

            Item newItem = new Item(sku, vendorID, vendorName, name, price, quantity, category);            
            dbOps.addItem(newItem);
            response.sendRedirect("FrontController");

        } catch (BadStringException bse) {
            request.getSession().setAttribute("exceptionmessage", bse.getMessage());
            response.sendRedirect("FrontController");
        }
    }

    public static void addVendor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int vendorID1 = Integer.parseInt(request.getParameter("vendorid"));
        String vendorName = request.getParameter("vendorName");
        String email = request.getParameter("email");
        String phoneNo = request.getParameter("phoneno");

        try {
            if (InputVerifier.checkBadString(vendorName)) {
                throw new BadStringException("vendorName");
            }
            if (InputVerifier.checkBadString(email)) {
                throw new BadStringException("email");
            }
            if (InputVerifier.checkBadString(phoneNo)) {
                throw new BadStringException("phoneNo");
            }

            Vendor newVendor = new Vendor(vendorID1, vendorName, email, phoneNo);
            DBOperations dbOps = new DBOperations();
            dbOps.addVendor(newVendor);
            updateSessionLists(request, response);
            response.sendRedirect("Director?direction=vendors");

        } catch (BadStringException bse) {
            request.getSession().setAttribute("exceptionmessage", bse.getMessage());
            response.sendRedirect("FrontController");
        }
    }

    public static void updateItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String updatedItemID = request.getParameter("updatedSKU");
                    String updatedItemName = request.getParameter("updatedItemName");
//                    String updatedVendorID = request.getParameter("updatedVendorID");
                    String updatedPrice = request.getParameter("updatedPrice");
                    String updatedCategory = request.getParameter("updatedCategory");
                    String updatedQuantity = request.getParameter("updatedQuantity");
                    String oldSKU = request.getParameter("oldSKU");
                    System.out.println(oldSKU);
                    DBOperations dbOps = new DBOperations();
                    dbOps.modifyItem(updatedItemID, updatedItemName, updatedPrice, updatedQuantity, updatedCategory, oldSKU);
                    response.sendRedirect("FrontController");
    }
    
    public static void updateVendor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String oldVendorID = request.getParameter("oldVendorID");
        HttpSession session = request.getSession();
                    String updatedVendorID = request.getParameter("updatedVendorID");
                    String updatedVendorName = request.getParameter("updatedVendorName");
                    String updatedVendorEmail = request.getParameter("updatedVendorEmail");
                    String updatedVendorPhone = request.getParameter("updatedVendorPhone");               
                    System.out.println(updatedVendorID + "*************");
                    DBOperations dbOps = new DBOperations();
                    dbOps.updateVendor(updatedVendorName, updatedVendorEmail, updatedVendorPhone, updatedVendorID);
                    ArrayList<Vendor> vendors = dbOps.getVendors();
                    session.setAttribute("vendorlist", vendors);
                    request.getRequestDispatcher("WEB-INF/vendors.jsp").forward(request, response);
    }
    
    public static void deleteItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String itemID1 = request.getParameter("deleteID");
                    DBOperations dbOps = new DBOperations();
                    dbOps.deleteItem(itemID1);
                    response.sendRedirect("FrontController");  
    }
    
    public static void deleteVendor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String vendorID2 = request.getParameter("deleteID");
        HttpSession session = request.getSession();
                    DBOperations dbOps = new DBOperations();
                    dbOps.deleteVendor(vendorID2);
                    ArrayList<Vendor> vendors = dbOps.getVendors();
                    session.setAttribute("vendorlist", vendors);
                    request.getRequestDispatcher("WEB-INF/vendors.jsp").forward(request, response);
    }
    
    public static void createSale(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
    }
    
    public static void addToSale(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
    }
    
    public static void searchBySku(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
    }
    
    public static void searchByDate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
    }
}
