<%-- 
    Document   : ModifyPage
    Created on : Mar 1, 2022, 9:26:24 AM
    Author     : 706081
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./index.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit Item</h1>

        <form action="FrontController" method="GET">

            <c:forEach items="${singleItem}" var="item">
                <table>
                    <tr>
                        <td>SKU:
                            <input type="number" name="updatedSKU" value="${item.sku}" /></td>

                        <td>Name:
                            <input type="text" name="updatedItemName" value="${item.name}" /></td>

<!--                        <td>Vendor ID:
                        <input type="number" name="updatedVendorID" value="${item.vendorID}" /></td>-->


                        <td>Category:<select name="updatedCategory" >
                                <option value="${item.category}">${item.category}</option>
                                <option>Accessories</option>
                                <option>Apparel</option>
                                <option>Art & Prints</option>
                                <option>Bath & Body</option>
                                <option>Ceramics</option>
                                <option>Collab Exclusives</option>
                                <option>DIY Kits</option>
                                <option>Food and Drink</option>
                                <option>For the Home</option>
                                <option>Fundraiser</option>
                                <option>Jewelry</option>
                                <option>Kiddos & Baby</option>
                                <option>Other</option>
                                <option>Pets</option>
                                <option>Seasonal</option>
                                <option>Stationary</option>
                            </select> 
                        </td>

                        <td>Quantity:
                            <input type="number" name ="updatedQuantity" value="${item.quantity}" /></td>

                        <td>Price:
                            <input type="number" name ="updatedPrice" value="${item.price}" /></td>


                    </tr>  
                </table>

                <input type="hidden" name="oldSKU" value="${item.sku}" />
            </c:forEach>
            <input type="hidden" name="action" value="updateItem" />
            <input type="submit" value="Save"/>


        </form>

    </body>
</html>
