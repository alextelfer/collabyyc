/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd.servlets;

import ca.sait.itsd.DBOperations;
import ca.sait.itsd.Item;
import ca.sait.itsd.Sale;
import ca.sait.itsd.Vendor;
import ca.sait.itsd.exceptions.BadStringException;
import ca.sait.itsd.exceptions.MissingSaleException;
import ca.sait.itsd.utilities.InputVerifier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 857421
 */
public class FrontController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        DBOperations dbOps = new DBOperations();

        ArrayList<Vendor> vendors = dbOps.getVendors();
        request.getSession().setAttribute("vendorlist", vendors);

        ArrayList<Item> items = dbOps.getItems();
        request.getSession().setAttribute("itemlist", items);
        
        ArrayList<Sale> sales = dbOps.getSales();
        request.getSession().setAttribute("saleslist", sales);
        
        
        
        //jsp sends "action" param with the form that tells this servlet what servlet to send the request to
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        String modifyItem = request.getParameter("modifyItem");

        if (modifyItem != null && !modifyItem.equals("")) {
            System.out.println(modifyItem);
            dbOps.retrieveItem(Integer.parseInt(modifyItem));
            ArrayList<Item> singleItem = dbOps.retrieveItem(Integer.parseInt(modifyItem));
            request.getSession().setAttribute("singleItem", singleItem);
            System.out.println(singleItem);
            request.getSession().setAttribute("modifyItem", modifyItem);
            request.getRequestDispatcher("WEB-INF/ModifyPage.jsp").forward(request, response);

        } else {

         
            switch (action) {

                case "additem":
                    //Getting values from jsp to build item
                    
                    int sku = Integer.parseInt(request.getParameter("sku")); //temp value, real value must be assigned in db
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
                        request.getSession().setAttribute("newitem", newItem);
                        response.sendRedirect("AddItem");

                    } catch (BadStringException bse) {
                        request.getSession().setAttribute("exceptionmessage", bse.getMessage());
                        response.sendRedirect("FrontController");
                    }

                    break;

                case "addvendor":
                    int vendorID1 = Integer.parseInt(request.getParameter("vendorid"));
                    vendorName = request.getParameter("vendorName");
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
                        request.getSession().setAttribute("newvendor", newVendor);
                        response.sendRedirect("AddVendor");

                    } catch (BadStringException bse) {
                        request.getSession().setAttribute("exceptionmessage", bse.getMessage());
                        response.sendRedirect("FrontController");
                    }

                    break;

                case "updateItem":
                    String updatedItemID = request.getParameter("updatedSKU");
                    String updatedItemName = request.getParameter("updatedItemName");
                    String updatedVendorID = request.getParameter("updatedVendorID");
                    String updatedPrice = request.getParameter("updatedPrice");
                    String updatedCategory = request.getParameter("updatedCategory");
                    String updatedQuantity = request.getParameter("updatedQuantity");
                    String oldSKU = request.getParameter("oldSKU");
                    
                    dbOps.modifyItem(updatedItemID, updatedVendorID, updatedItemName, updatedPrice, updatedQuantity, updatedCategory, oldSKU);
                    System.out.println(updatedPrice);
                    response.sendRedirect("FrontController");
                    break;

                case "deleteitem":
                    String itemID1 = request.getParameter("deleteID");
                    dbOps.deleteItem(itemID1);
                    response.sendRedirect("FrontController");
                    break;

                case "deletevendor":
                    String vendorID2 = request.getParameter("deleteID");
                    dbOps.deleteVendor(vendorID2);
                    response.sendRedirect("FrontController");
                    break;
<<<<<<< Updated upstream
                    
                case "addtosale":
                    ArrayList<Item> saleItems = (ArrayList<Item>) session.getAttribute("saleitems");
                    if(saleItems == null) {
                        saleItems = new ArrayList<>();
                    }                    
                    String itemID2 = request.getParameter("itemsku");
                    Item item = dbOps.getItem(itemID2);                    
                    saleItems.add(item);
                    session.setAttribute("saleitems", saleItems);
                    request.getRequestDispatcher("WEB-INF/sales.jsp").forward(request, response);
                    break;
                    
                case "createsale":                                                            
                    try {
                        ArrayList<Item> sessionItems = (ArrayList<Item>) session.getAttribute("saleitems");
                        if(sessionItems == null) {                            
                            throw new MissingSaleException();
                        } else {
                            Sale newSale = new Sale();
                            newSale.setItems(sessionItems);
                            newSale.setTransactionID(Integer.parseInt(request.getParameter("saleID")));
                            newSale.setCustomerID(Integer.parseInt(request.getParameter("customerID")));
                            newSale.setPaymentDate(new Date());                            
                            newSale.setSoldItems("");
                            newSale.setSentShippingDate(new Date());
                            newSale.setShippingAddress("");
                            newSale.setPickupDate(new Date());
                            dbOps.addSale(newSale);
                            session.setAttribute("saleslist", dbOps.getSales());
                        }
                    } catch(MissingSaleException mse) {
                        System.out.println(mse.getMessage());
                    }   
                    request.getRequestDispatcher("WEB-INF/sales.jsp").forward(request, response);
                    break;

=======
                case "searchbysku":
                    int searchBySku = Integer.parseInt(request.getParameter("sku"));
                    ArrayList<Item> searchedItems = dbOps.searchBySKU(searchBySku);
                    request.getSession().setAttribute("salelist", searchedItems);
                    response.sendRedirect("FrontController");
>>>>>>> Stashed changes
                default:
                    request.getRequestDispatcher("WEB-INF/inventory.jsp").forward(request, response);
                    break;
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
