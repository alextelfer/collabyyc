<%-- 
    Document   : index
    Created on : Feb 10, 2022, 1:25:02 AM
    Author     : 857421
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./index.css">
        <title>CollabYYC Home</title>
    </head>

    <body>


        <div class="inventory">
            <h1>Inventory</h1>

            <table id="contents">
                <tr><td id="cellContents"><a href="./vendors.jsp">Vendors</a></td></tr>
                <tr><td id="cellContents"><a href="./sales.jsp">Sales</a></td></tr>
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

                <c:forEach items="${itemlist}" var="item">
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
        </div>





        <div class="form">
            <h3>Add Item</h3>
            <form action="FrontController" method="GET">

                <table>
                    <tr>
                        <td>Item ID:</td>
                        <td><input type="number" name="itemid" /></td>

                        <td>Name:</td>
                        <td><input type="text" name="name" /></td>

                        <td>Vendor ID:</td>
                        <td><input type="number" name="vendor" /></td>

                        <td>Category:</td>
                        <td><input type="text" name="category" /></td>

                        <td>Price:</td>
                        <td><input type="number" name ="price" /></td>
                    </tr>  
                </table>

                <input type="hidden" name="action" value="additem" />
                <input type="submit" value="Add Item" />


            </form>
        </div>

        <form action="FrontController" method="GET">
            Item ID: <input type="number" name="deleteID" />
            <input type="hidden" name="action" value="deleteitem" />
            <input type="submit" value="Delete Item" />
        </form>

    </body>
</html>
