<%-- 
    Document   : sales
    Created on : Feb. 16, 2022, 8:05:01 p.m.
    Author     : corbi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            
            <%@ include file="jspf/segment.jspf" %>
        </div>
        
        <div class="sales">
            <h1>Sales</h1>
            <table>
                <tr>
                    <th>Sale ID</th>
                    <th>Transaction Amount</th>
                    <th>Timestamp</th>
                </tr>
                
                <c:forEach items="${salelist}" var="sale">
                    <tr>
                        <td>${sale.saleID}</td>
                        <td>${sale.transactionAmount}</td>
                        <td>${sale.timestamp}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>               
    </body>
</html>
