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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author manve
 */
public class RegisterServlet extends HttpServlet {

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
        String employee = (String) session.getAttribute("employee");
        String register = request.getParameter("register");

        if (employee != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/registerEmployee.jsp").forward(request, response);
            return;

        } else {
            request.setAttribute("notEmployee", "Not Employee");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }
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
        String registerReq = request.getParameter("register_req");
        String employeeSession = (String) session.getAttribute("employee");

        if (registerReq != null && registerReq.equals("employee")) {
            if (employeeSession != null) {
                int employeeID = Integer.parseInt(request.getParameter("employeeid"));
                String password = request.getParameter("password");
                String name = request.getParameter("name");
                String email = request.getParameter("email");
//                long phoneNumber = request.getParameter("phone");
                long phone = Long.parseLong(request.getParameter("phone"));
                System.out.println(employeeID + password + name + email + phone);

                try {
                    EmployeeAccount employee = new EmployeeAccount(employeeID, password, name, email, phone);
                    boolean result = dbo.addEmployeeAccount(employee);
                    if (result == false) {
                        Exception notRegistered = new Exception();
                        throw notRegistered;
                    }
                    request.setAttribute("registerSuccess", "Employee successfully registered!");
                    getServletContext().getRequestDispatcher("/WEB-INF/registerEmployee.jsp").forward(request, response);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("registerError", "There was an error.");
                    getServletContext().getRequestDispatcher("/WEB-INF/registerEmployee.jsp").forward(request, response);
                    return;
                }
            } else {
                request.setAttribute("unauthorizedRegister", "You cannot register en employee.");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
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
