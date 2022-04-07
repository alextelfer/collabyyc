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
import ca.sait.itsd.utilities.RequestOptions;
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
        
        String vendorName = request.getParameter("vendorName");
        System.out.println(vendorName);
        ArrayList<Item> items = dbOps.getItems(vendorName);
        request.getSession().setAttribute("itemlist", items);

        ArrayList<Sale> sales = dbOps.getSales();
        request.getSession().setAttribute("saleslist", sales);
                                        
        //jsp sends "action" param with the form that tells this servlet what servlet to send the request to
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        String modifyItem = request.getParameter("modifyItem");
        String modifyVendor = request.getParameter("modifyVendor");
        

        if (modifyItem != null && !modifyItem.equals("")) {
            dbOps.retrieveItem(Integer.parseInt(modifyItem));
            ArrayList<Item> singleItem = dbOps.retrieveItem(Integer.parseInt(modifyItem));
            request.getSession().setAttribute("singleItem", singleItem);
            request.getSession().setAttribute("modifyItem", modifyItem);
            request.getRequestDispatcher("WEB-INF/ModifyPage.jsp").forward(request, response);

        } else if (modifyVendor != null && !modifyVendor.equals("")) {
            dbOps.retrieveVendor(Integer.parseInt(modifyVendor));
            ArrayList<Vendor> singleVendor = dbOps.retrieveVendor(Integer.parseInt(modifyVendor));
            request.getSession().setAttribute("singleVendor", singleVendor);
            System.out.println(singleVendor);
            request.getSession().setAttribute("modifyVendor", modifyVendor);
            request.getRequestDispatcher("WEB-INF/ModifyVendor.jsp").forward(request, response);

        } else {

            switch (action) {                                      
                case "additem":
                    RequestOptions.addItem(request, response);                    
                    break;

                case "addvendor":
                    RequestOptions.addVendor(request, response);
                    break;

                case "updateItem":
                    RequestOptions.updateItem(request, response);
                    break;

                case "updateVendor":
                    RequestOptions.updateVendor(request, response);
                    break;

                case "deleteitem":
                    RequestOptions.deleteItem(request, response);
                    break;

                case "deletevendor":
                    RequestOptions.deleteVendor(request, response);
                    break;
                    
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
                            dbOps.addSoldItems(newSale.getItems(), newSale.getTransactionID());
                            session.setAttribute("saleslist", dbOps.getSales());  
                            session.setAttribute("saleitems", null);
                        }
                    } catch(MissingSaleException mse) {
                        System.out.println(mse.getMessage());
                    }   
                    //request.getRequestDispatcher("WEB-INF/sales.jsp").forward(request, response);
                    response.sendRedirect("Director?direction=sales");
                    break;


                case "searchbysku":
                    int searchBySku = Integer.parseInt(request.getParameter("sku"));
                    ArrayList<Item> searchedItems = dbOps.searchBySKU(searchBySku);
                    request.getSession().setAttribute("searchedlist", searchedItems);
                    request.getRequestDispatcher("WEB-INF/sales.jsp").forward(request, response);
                case "searchbydate":
                    String searchedDate = request.getParameter("date");
                    ArrayList<Sale> searchedByDate = dbOps.searchByDate(searchedDate);
                    request.getSession().setAttribute("searchedreports", searchedByDate);
                    request.getRequestDispatcher("WEB-INF/reports.jsp").forward(request, response);
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
