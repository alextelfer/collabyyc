/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd.servlets;

import ca.sait.itsd.DBOperations;
import ca.sait.itsd.Item;
import ca.sait.itsd.Sale;
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
public class Director extends HttpServlet {

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
        
        String direction = request.getParameter("direction");
        
        switch(direction) {
            
            case "vendors":
                request.getRequestDispatcher("WEB-INF/vendors.jsp").forward(request, response);
                break;
                
            case "inventory":
                request.getRequestDispatcher("WEB-INF/inventory.jsp").forward(request, response);
                break;
                
            case "sales":
                sendToSales(request, response);
                break;
                
            case "reports":
                request.getRequestDispatcher("WEB-INF/reports.jsp").forward(request, response);
                break;
                
            case "saledetails":                
                DBOperations dbOps = new DBOperations();
                Sale sale = dbOps.getSale(Integer.parseInt(request.getParameter("transactionID")));
                request.setAttribute("sale", sale);     
                ArrayList<Item> soldItems = dbOps.getSoldItems(2);
                System.out.println(soldItems.toString());
                request.setAttribute("solditems", soldItems);
                request.getRequestDispatcher("WEB-INF/saledetails.jsp").forward(request, response);
                break;
                
            default:
                request.getRequestDispatcher("WEB-INF/inventory.jsp").forward(request, response);
                break;
        }
    }
    
    private void sendToSales(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        ArrayList<Sale> sales = new ArrayList<>();
        
        //sales.add(new Sale(1, 10.00f, new Date()));
        
        request.getSession().setAttribute("salelist", sales);
        
        request.getRequestDispatcher("WEB-INF/sales.jsp").forward(request, response);
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
