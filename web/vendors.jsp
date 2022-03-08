<%-- 
    Document   : vendors
    Created on : Feb. 16, 2022, 8:06:20 p.m.
    Author     : corbi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./vendors.css">
        <title>JSP Page</title>
    </head>
    <body>

        <div class="vendors">
            <h1>Vendors</h1>

            <table id="contents">
                <tr><td id="cellContents"><a href="index.jsp">Inventory</a></td></tr>
            </table>

            <table>
                <tr>
                    <th>Vendor ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone No.</th>                                        
                </tr>

                <c:forEach items="${vendorlist}" var="vendor">
                    <tr>
                        <td>${vendor.vendorID}</td>                        
                        <td>${vendor.name}</td>
                        <td>${vendor.vendorEmail}</td>
                        <td>${vendor.vendorPhoneNumber}</td>                        
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="form">
            <h3>Add Vendor</h3>
            <form action="FrontController" method="GET">
                <table>
                    <tr>
                        <td>Vendor ID:</td>
                        <td><input type="number" name="vendorid" /></td>

                        <td>Name:</td>
                        <td><input type="text" name="vendorname" /></td>

                        <td>Email:</td>
                        <td><input type="text" name="email" /></td>

                        <td>Phone No.:</td>
                        <td><input type="number" name="phoneno" /></td>
                    </tr>
                </table>

                <input type="submit" value="Add Vendor" />
                <input type="hidden" name="action" value="addvendor" />
            </form>
        </div>

        <form action="FrontController" method="GET">
            Vendor ID: <input type="number" name="deleteID" />
            <input type="hidden" name="action" value="deletevendor" />
            <input type="submit" value="Delete Vendor" />
        </form>

    </body>
</html>
