<%-- 
    Document   : reports
    Created on : Feb. 16, 2022, 8:06:00 p.m.
    Author     : corbi
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./index.css">
        <title>Sales Reports</title>
    </head>
    <body>
        <div class="header">
            <img src=".\Resources\photos\collabyyc.png">

            <%@ include file="jspf/segment.jspf" %>
        </div>

        <div class="inventory">
            <h1>
                Sales Reports
            </h1>
            <div class="form">
                <table>
                    <tr>
                        <th>Transaction ID</th>
                        <th>Payment Date</th>
                        <th>Sale Amount</th>
                        <th>Vendor Payment</th>
                        <th>Items Sold</th>
                    </tr>
                    
                        <c:forEach items="${saleslist}" var="sale">
                            <tr>
                                <td>${sale.transactionID}</td>
                                <td>${sale.paymentDate}</td>
                                <td>${sale.saleAmount}</td>
                                <td>${sale.payVendorAmount}</td>
                                <td>${sale.soldItems}</td>
                            </tr>
                            
                        </c:forEach>
                    
                </table>
            </div>
        </div>
    </body>
</html>
