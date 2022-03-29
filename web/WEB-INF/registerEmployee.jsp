<%-- 
    Document   : registerEmployee
    Created on : Mar 17, 2022, 9:04:38 AM
    Author     : manve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./index.css">
        <title>Register Employee</title>
    </head>
    <h1>Register Employee</h1>

    <div class="header">
        <img src=".\Resources\photos\collabyyc.png">

        <%@ include file="jspf/segment.jspf" %>
    </div>

    <form action="RegisterServlet" method="POST">
        <table>
            <tr>
                <td>
                    <input name="employeeid" type="text" placeholder="Employee ID">
                </td>
            </tr>
            <tr>
                <td>
                    <input name="password" type="password" placeholder="Employee Password">
            </tr>
            <tr>
                <td>
                    <input name="name" type="text" placeholder="Employee Name">
            </tr>
            <tr>
                <td>
                    <input name="email" type="email" placeholder="Employee Email">
            </tr>
            <tr>
                <td>
                    <input name="phone" type="number" placeholder="Employee Phone">
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Register Employee">
                    <input type="hidden" name="register_req" value="employee">
                </td>
            </tr>
        </table>
    </form>
    <form action="RegisterServlet" method="POST">
        <table>
            <tr>
                <td>
                    <input name="username" type="text" placeholder="Username">
                </td>
            </tr>
            <tr>
                <td>
                    <input name="password" type="password" placeholder="Password">
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Register User">
                    <input type="hidden" name="register_req" value="user">
                </td>
            </tr>
        </table>
    </form>
    <h4>${registerSuccess}</h4>
    <h4>${registerError}</h4>
</html>
