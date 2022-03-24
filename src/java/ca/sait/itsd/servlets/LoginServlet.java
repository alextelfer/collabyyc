/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd.servlets;

import ca.sait.itsd.DBOperations;
import ca.sait.itsd.EmployeeAccount;
import ca.sait.itsd.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author manve
 */
public class LoginServlet extends HttpServlet {

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

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String logout = (String) request.getAttribute("logout");

        if (logout != null && !logout.equals("")) {
            session.invalidate();
            request.setAttribute("loggedOut", "loggedOut");
        }
        
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        return;

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

        HttpSession session = request.getSession();

        DBOperations dbo = new DBOperations();

        String action = request.getParameter("action");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            EmployeeAccount employee = dbo.getEmployeeAccount(username, password);
            session.setAttribute("employee", username);
            response.sendRedirect("FrontController");
            return;
        } catch (Exception e1) {
            try {
                User user = dbo.getUserAccount(username, password);
                session.setAttribute("user", username);
                response.sendRedirect("FrontController");
                return;
            } catch (Exception e2) {
                request.setAttribute("invalidLogin", "Invalid Login!");
                e2.printStackTrace();
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
        }

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
