<%-- 
    Document   : addvendor
    Created on : Mar 10, 2022, 10:24:42 AM
    Author     : manve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add Vendor</h1>
        <form method="GET" action="FrontController">
            <table>
                <tr><td><label for="vendorid"></label></td>
                    <td><input type="text" id="vendorid"></td></tr>
                <tr><td><label for="vendorname"></label></td>
                    <td><input type="text" id="vendorname"></td></tr>
                <tr><td><label for="email"></label></td>
                    <td><input type="text" id="email"></td></tr>
                <tr><td><label for="phoneno"></label></td>
                    <td><input type="number" id="phoneno"></td></tr>
                <input type="hidden" name="action" value="addvendor" />
                <tr><td><input type="submit" value="Save"></tr>
            </table>
        </form>
    </body>
</html>
