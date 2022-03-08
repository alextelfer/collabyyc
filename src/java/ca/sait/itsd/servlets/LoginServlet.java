/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd.servlets;

import ca.sait.itsd.DBOperations;
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
        ArrayList<User> users = dbo.getUsers();

        String action = request.getParameter("action");

        if (action != null && action.equals("login")) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean login = false;
            int userType = 1;

            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                if (username.equals(user.getUsername())) {
                    if (password.equals(user.getPassword())) {
                        login = true;
                        userType = user.getUserType();
                    }
                }
            }

            if (login == true) {
                if (userType == 0) {
                    session.setAttribute("adminUser", username);
                    response.sendRedirect("FrontController");
                    return;
                    /*
                If the user is not admin
                     */
                } else {
                    session.setAttribute("validUser", username);
                    request.setAttribute("invalidLogin", "Not Admin!");
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                }

            } else {
                request.setAttribute("invalidLogin", "Invalid Username or Password!");
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