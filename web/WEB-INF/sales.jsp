<%-- 
    Document   : sales
    Created on : Feb. 16, 2022, 8:05:01 p.m.
    Author     : corbi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        </div>        
        <%@ include file="jspf/segment.jspf" %>
        <h1>Create Sales</h1>  
        <c:if test="${saleitems != null}">
            <table>
                <tr>
                    <th>Sale ID</th>                    
                </tr>
                <form action="FrontController" method="GET">
                    <tr>
                        <td><input type="number" name="saleID"/> </td>                        
                        <td><input type="submit" value="Create Sale"/></td>
                    </tr>
                    <input type="hidden" name="action" value="createsale" />
                </form>
            </table>

            <table>

                <tr>
                    <th>Item ID</th>
                    <th>Vendor ID</th>
                    <th>Name</th>
                    <th>Price</th>                    
                    <th>Category</th>
                </tr>

                <c:forEach items="${saleitems}" var="item">
                    <tr>
                        <td>${item.sku}</td>
                        <td>${item.vendorName}</td>
                        <td>${item.name}</td>
                        <td>${item.price}</td>                        
                        <td>${item.category}</td>                        
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <form action="FrontController" method="GET">
            Search by SKU:<input type="number" name="sku">
            <input type="hidden" name="action" value="searchbysku">
            <input type="submit" value="Search">
        </form>
        
        <table>
            <tr>
                <th>SKU</th>
                <th>Name</th>
                <th>Price</th>
                <th>Category</th>
                <th>Vendor<th>
            </tr>                
            <c:forEach items="${searchedlist}" var="searched">
                <tr>
                <form action="FrontController" method="GET">
                    <td>${searched.sku}</td>
                    <td>${searched.name}</td>
                    <td>\$<fmt:formatNumber minFractionDigits="2" value='${searched.price}'/></td>
                    <td>${searched.category}</td>
                    <td>${searched.vendorName}</td>
                    <td><input type=submit value="Add to Cart"></td>
                    <input type="hidden" name="action" value="addtosale" />
                    <input type="hidden" name="itemsku" value="${searched.sku}" />
                </form>                            
                </tr>
            </c:forEach>              
        </table> 


        <h2>Sale History</h2>
        <div class="sales">                                              
            <table>
                <tr>
                    <th>Sale ID</th>
                    <th>Date</th>
                    <th>Total</th>
                    <th>V. Share</th>
                    <th>Items</th>
                </tr>

                <c:forEach items="${saleslist}" var="sale">
                    <tr>
                        <td>${sale.transactionID}</td>
                        <td>${sale.paymentDate}</td>
                        <td>${sale.saleAmount}</td>
                        <td>${sale.payVendorAmount}</td>
                        <td><a href="Director?direction=saledetails&transactionid=${sale.transactionID}">...</a></td>                                                                        
                    </tr>
                </c:forEach>
            </table>
        </div>        
    </body>
</html>
