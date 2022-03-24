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
}
