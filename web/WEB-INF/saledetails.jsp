<%-- 
    Document   : saledetails
    Created on : Apr 10, 2022, 11:17:44 PM
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
        <div class="header">
            <img src=".\Resources\photos\collabyyc.png">
        </div>
        <%@ include file="jspf/segment.jspf" %>
        <h1>Sale Details</h1>
        <table>
            <tr>
                <th>Sale ID</th>
                <th>Date</th>
                <th>Total</th>
                <th>V. Share</th>
                <th>Items</th>
                <th>SSD</th>
                <th>SA</th>
                <th>PD</th>
            </tr>
            <tr>
                <td>${detailsale.transactionID}</td>
                <td>${detailsale.paymentDate}</td>
                <td>${detailsale.saleAmount}</td>
                <td>${detailsale.payVendorAmount}</td>
                <td>${detailsale.soldItems}</td>                                                
                <td>${detailsale.sentShippingDate}</td>
                <td>${detailsale.shippingAddress}</td>
                <td>${detailsale.pickupDate}</td>
            </tr>
        </table>
            
        <table>
            <tr>
                <th>SKU</th>
                <th>VID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Category</th>
            </tr>
            <c:forEach items="${solditems}" var="item">
                <tr>
                    <td>${item.sku}</td>
                    <td>${item.vendorID}</td>
                    <td>${item.name}</td>
                    <td>${item.price}</td>
                    <td>${item.quantity}</td>
                    <td>${item.category}</td>                        
                </tr>
            </c:forEach>
        </table>                
    </body>
</html>
