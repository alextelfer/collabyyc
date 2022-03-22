<%-- 
    Document   : vendors.jsp
    Created on : Mar 8, 2022, 10:39:35 AM
    Author     : 857421
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./index.css">
        <title>Vendors Page</title>
    </head>

    <body>

        <div class="header">
            <img src=".\Resources\photos\collabyyc.png">

            <h1>Vendors</h1>
        </div>

        <div>
            <%@ include file="jspf/segment.jspf" %>
            <table class="vendorTable">
                
                <tr>
                    <th>Vendor name</th>
                    <th>Vendor ID</th>
                    <th>Vendor email</th>
                    <th>Vendor phone number</th>
                </tr>

                <c:forEach items="${vendorlist}" var="vendor">
                    <tr>
                        <td>${vendor.name}</td>
                        <td> ${vendor.vendorID}</td>
                        <td> ${vendor.vendorEmail}</td>
                        <td> ${vendor.vendorPhoneNumber}</td>

                        <td>
                            <form action="FrontController" method="GET">
                                <input type="hidden" name="deleteVendor" value=${vendor.vendorID}>
                                <input id="deleteButton" type="submit" value="X">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="form">
            <h3>Add vendor</h3>
            <form action="FrontController" method="GET">
                <table>
                    <tr>
                        <td>Vendor Name:<input type="text" name="vendorName"></td>
                        <td>Vendor ID:<input type="number" name="vendorid"></td>
                        <td>Vendor Email:<input type="email" name="email"></td>
                        <td>Vendor Phone number:<input type="text" name="phoneno"></td>

                        <td>
                            <input type="submit" value="Add Vendor">
                            <input type="hidden" name="action" value="addvendor">
                        </td>
                    </tr>
                </table>
            </form>
        </div>

    </body>

</html>
