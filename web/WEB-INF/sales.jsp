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

            
        </div>
<<<<<<< Updated upstream
        
            <c:if test="${saleitems != null}">
                <table>
                    <tr>
                        <th>Sale ID</th>
                        <th>Customer ID</th>
                    </tr>
                    <form action="FrontController" method="GET">
                        <tr>
                            <td><input type="number" name="saleID"/> </td>
                            <td><input type="number" name="customerID"/></td>
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
                        <th>Quantity</th>
                        <th>Category</th>
                    </tr>

                    <c:forEach items="${saleitems}" var="item">
                        <tr>
                            <td>${item.itemID}</td>
                            <td>${item.vendorID}</td>
                            <td>${item.name}</td>
                            <td>${item.price}</td>
                            <td>${item.quantity}</td>
                            <td>${item.category}</td>                        
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        
=======

>>>>>>> Stashed changes
        <div class="sales">
            <h1>Sales</h1>
            <%@ include file="jspf/segment.jspf" %>
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
                <c:forEach items="${salelist}" var="sale">
                    <tr>
                        <td>${sale.sku}</td>
                        <td>${sale.name}</td>
                        <td>${sale.price}</td>
                        <td>${sale.category}</td>
                        <td>${sale.vendor}</td>
                    </tr>
                </c:forEach>

            </table>
            <table>
                <tr>
                    <th>Sale ID</th>
                    <th>Customer ID</th>
                    <th>Payment Date</th>
                    <th>Sale Amount</th>
                    <th>Vendor Share</th>
                    <th>Sold Items</th>
                    <th>Sent Shipping Date</th>
                    <th>Shipping Address</th>
                    <th>Pickup Date</th>
                </tr>
<<<<<<< Updated upstream
                
                <c:forEach items="${saleslist}" var="sale">
                    <tr>
                        <td>${sale.transactionID}</td>
                        <td>${sale.customerID}</td>
                        <td>${sale.paymentDate}</td>
                        <td>${sale.saleAmount}</td>
                        <td>${sale.payVendorAmount}</td>
                        <td>${sale.soldItems}</td>                                                
                        <td>${sale.sentShippingDate}</td>
                        <td>${sale.shippingAddress}</td>
                        <td>${sale.pickupDate}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        
        <div class="inventory">
            <h1>Inventory</h1>
           
            <table>
                <tr>
                    <th>Item ID</th>
                    <th>Vendor ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Category</th>
                </tr>

                <c:forEach items="${itemlist}" var="item">
=======

                <c:forEach items="${salelist}" var="sale">
>>>>>>> Stashed changes
                    <tr>
                        <td>${item.itemID}</td>
                        <td>${item.vendorID}</td>
                        <td>${item.name}</td>
                        <td>${item.price}</td>
                        <td>${item.quantity}</td>
                        <td>${item.category}</td>
                        
                        <form action="FrontController" method="GET">
                            <td><input type="hidden" name="modifyItem" value=${item.itemID}>
                                <input type="submit" value="Edit"></td>
                        </form>
                    
                        <form action="FrontController" method="GET">
                            <td><input type="hidden" name="action" value="addtosale" />
                                <input type="hidden" name="itemsku" value="${item.sku}" />
                                <input type="submit" value="Add" /></td>
                        </form>
                                        
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
