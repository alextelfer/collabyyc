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
                    <input name="employeeid" type="number" placeholder="Employee ID" maxlength="5">
                </td>
            </tr>
            <tr>
                <td>
                    <input name="employee_password" type="password" placeholder="Employee Password" maxlength="50">
            </tr>
            <tr>
                <td>
                    <input name="employee_name" type="text" placeholder="Employee Name" maxlength="50">
            </tr>
            <tr>
                <td>
                    <input name="employee_email" type="name" placeholder="Employee Email" maxlength="50">
            </tr>
            <tr>
                <td>
                    <input name="employee_phone" type="number" placeholder="Employee Phone" maxlength="10">
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
