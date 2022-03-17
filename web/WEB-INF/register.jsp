<%-- 
    Document   : register
    Created on : Mar 15, 2022, 10:05:09 AM
    Author     : manve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register User</title>
        <link rel="stylesheet" type="text/css" href="./login.css">
    </head>
    
    <h1>Register User</h1>
    
    <table>
        <tr>
            <td>
                <img src=".\Resources\photos\collabyyc.png">
            </td>

        </tr>
    </table>
    
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
    <form action="LoginServlet" method="GET">
        <input type="submit" value="Login">
    </form>
    <form action="RegisterServlet" method="GET">
        <input type="submit" value="Register Employee">
        <input type="hidden" name="register" value="user">
    </form>
    <h4>${registerSuccess}</h4>
    <h4>${registerError}</h4>
    <h4>${unauthorizedReister}</h4>
    <h4>${employeeLoggindIn}</h4>
</html>
