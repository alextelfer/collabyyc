/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd.servlets;

import ca.sait.itsd.DBOperations;
import ca.sait.itsd.Item;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        
        DBOperations dbOps = new DBOperations();
        
        ArrayList<Item> items = new ArrayList<Item>();
        
        //Building dummy items for testing
        items.add(new Item(0, 0, "item1", 1.50, 1, "cat1"));
        items.add(new Item(2, 0, "item1", 1.50, 1, "cat1"));
        items.add(new Item(3, 1, "item1", 1.50, 1, "cat1"));
        items.add(new Item(4, 2, "item1", 1.50, 1, "cat2"));
        items.add(new Item(5, 3, "item1", 1.50, 1, "cat3"));
        items.add(new Item(6, 4, "item1", 1.50, 1, "cat4"));
        items.add(new Item(7, 4, "item1", 1.50, 1, "cat5"));
        items.add(new Item(8, 4, "item1", 1.50, 1, "cat6"));
        items.add(new Item(9, 5, "item1", 1.50, 1, "cat3"));
        items.add(new Item(10, 5, "item1", 1.50, 1, "cat4"));
        
        request.getSession().setAttribute("itemlist", items);
        
        //jsp sends "action" param with the form that tells this servlet what servlet to send the request to
        String action = request.getParameter("action");
        if(action == null) action = "";
        
        switch(action) {
            
            case "additem":            
                //Getting values from jsp to build item
                int itemID = 0; //temp value, real value must be assigned in db
                int vendorID = Integer.parseInt(request.getParameter("vendor"));
                String name = request.getParameter("name");
                double price = Double.parseDouble(request.getParameter("price"));
                int quantity = 1;
                String category = request.getParameter("category");
                
                Item newItem = new Item(itemID, vendorID, name, price, quantity, category);
                
                request.getSession().setAttribute("newitem", newItem);                
                response.sendRedirect("AddItem");
                break;
                
            default:
                request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
                break;
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