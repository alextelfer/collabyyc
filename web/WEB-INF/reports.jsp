<%-- 
    Document   : reports
    Created on : Feb. 16, 2022, 8:06:00 p.m.
    Author     : corbi
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

            
        </div>

        <div class="inventory">
            <h1>
                Sales Reports
            </h1>
            <%@ include file="jspf/segment.jspf" %>
            <form action="FrontController" method="GET">
                Search by Date:<input type="date" name="date">
                <input type="hidden" name="action" value="searchbydate">
                <input type="submit" value="Search"><br/>
            </form>
            <div class="form">
                <table>
                    <tr>
                        <th>Transaction ID</th>
                        <th>Payment Date</th>
                        <th>Sale Amount</th>
                        <th>Vendor Payment</th>
                        <th>Items Sold</th>
                    </tr>
                    
                        <c:forEach items="${searchedreports}" var="searched">
                            
                            <tr>
                                <td>${searched.transactionID}</td>
                                <td>${searched.paymentDate}</td>
                                <td>\$<fmt:formatNumber minFractionDigits='2' value='${searched.saleAmount}'/></td>
                                <td>\$<fmt:formatNumber minFractionDigits='2' value='${searched.payVendorAmount}'/></td>
                                <td>${searched.soldItems}</td>
                            </tr>
                            
                        </c:forEach>
                
                </table>
            </div>
        </div>
    </body>
</html>
