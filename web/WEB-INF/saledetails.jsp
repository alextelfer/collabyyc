<%-- 
    Document   : saledetails
    Created on : Mar 29, 2022, 9:56:49 AM
    Author     : 857421
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="./index.css">
    </head>
    <body>
        <h1>Sale</h1>
        <c:if test="${sale}">
            <table>
                <tr>
                    <th>Sale ID</th>
                    <th>Date</th>
                    <th>Amount</th>
                    <th>Vendor Share</th>
                    <th>Items</th>
                    <th>SSD</th>
                    <th>SA</th>
                    <th>PD</th>
                </tr>
                
                <tr>
                    <td>${sale.transactionID}</td>
                    <td>${sale.paymentDate}</td>
                    <td>${sale.saleAmount}</td>
                    <td>${sale.payVendorAmount}</td>
                    <td>${sale.soldItems}</td>                                                
                    <td>${sale.sentShippingDate}</td>
                    <td>${sale.shippingAddress}</td>
                    <td>${sale.pickupDate}</td>
                </tr>
            </table>
        </c:if>
    </body>
</html>
