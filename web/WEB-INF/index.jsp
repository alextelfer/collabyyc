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
        <title>CollabYYC Home</title>
    </head>
    
    <body>
        
        <div class="form">
            <form action="FrontController" method="GET">
                 
                <table>
                    <tr>
                        <td>Item ID:</td>
                        <td><input type="number" name="itemid" /></td>
                    </tr>
                    <tr>
                        <td>Name:</td>
                        <td><input type="text" name="name" /></td>
                    </tr>
                    <tr>
                        <td>Vendor ID:</td>
                        <td><input type="number" name="vendor" /></td>
                    </tr>
                    <tr>
                        <td>Category:</td>
                        <td><input type="text" name="category" /></td>
                    </tr>
                    <tr>
                        <td>Price:</td>
                        <td><input type="number" name ="price" /></td>
                    </tr>
                </table>
                <input type="hidden" name="action" value="additem" />
                <input type="submit" value="Add Item" />
                
            </form>
        </div>
        
        <div class="inventory">
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
            <form action="FrontController" method="GET">
                <table>
                    <tr>
                        <td>Vendor ID:</td>
                        <td><input type="number" name="vendorid" /></td>
                    </tr>
                    <tr>
                        <td>Name:</td>
                        <td><input type="text" name="vendorname" /></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input type="text" name="email" /></td>
                    </tr>
                    <tr>
                        <td>Phone No.:</td>
                        <td><input type="number" name="phoneno" /></td>
                    </tr>
                </table>
                
                <input type="submit" value="Add Vendor" />
                <input type="hidden" name="action" value="addvendor" />
            </form>
        </div>
        
        <div class="vendors">
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
    </body>
</html>
