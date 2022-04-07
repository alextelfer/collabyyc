<%-- 
    Document   : sales
    Created on : Feb. 16, 2022, 8:05:01 p.m.
    Author     : Stephen / corbi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./index.css">
        <title>Sales Page</title>
    </head>
    <body>

        <div class="header">
            <img src=".\Resources\photos\collabyyc.png">


        </div>

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

        <div class="sales">
            <h1>Sale Page</h1>    
            <%@ include file="jspf/segment.jspf" %>
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
                    <th>LINK</th>
                </tr>

                <!-- comment 1 -->

                <!-- <c:set var="total" value="${0}"/>
                <c:forEach items="${itemlist}" var="item">
                    <c:set var="total" value="${total + item.price}"/>
                    <tr>
                        <td>${item.itemID}</td>
                        <td>${item.name}</td>
                        <td><input type="number" id="priceSalesPage" max="999.99"
                                   step="0.01" min="1" value="${item.price}" onchange="updateTotals()"/></td>
                        <td><input type="number" id="quantitySalesPage" max="${item.quantity}"
                                   min="1" value="${item.quantity}" onchange="updateTotals()"/></td>
                        <td>${item.category}</td>
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
                </c:forEach> -->

                <c:forEach items="${saleslist}" var="sale">
                    <tr>
                        <td>${sale.transactionID}</td>                        
                        <td>${sale.paymentDate}</td>
                        <td>${sale.saleAmount}</td>
                        <td>${sale.payVendorAmount}</td>
                        <td>${sale.soldItems}</td>                                                
                        <td>${sale.sentShippingDate}</td>
                        <td>${sale.shippingAddress}</td>
                        <td>${sale.pickupDate}</td>
                        <td><a href="Director?direction=saledetails&transactionID=${sale.transactionID}">link</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="inventory">
            <h1>Inventory</h1>

            <table>
                <tr>
                    <th>SKU</th>
                    <th>Vendor ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Category</th>
                </tr>

                <c:forEach items="${itemlist}" var="item">
                    <tr>
                        <td>${item.sku}</td>
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

        <!-- comment 2 -->

        <!-- <div class="totals">
            <table>
                <tr>
                    <th>Subtotal</th>
                    <th>Tax</th>
                    <th>Total</th>
                </tr>
                <tr>
                    <td id="totalBeforeTax">$${total}</td>
                    <td id="totalTax">$${total * 0.05}</td>
                    <td id="totalAfterTax">$${total * 0.05 + total}</td>
                </tr>
            </table></br>
            <input type="hidden" name="action" value="purchase" />
            <input type="submit" value="Proceed to Payment" />
        </div>        

        <script>
//            const input = document.querySelector('input');
//            const newPrice = document.getElementById("priceSalesPage").value;
//
//            input.addEventListener('input', updateValue);
//
//            function updateTotals(x) {
//                newPrice.textContent = x.target.value;
//            }

            function updateTotals()
            {
                var newPrice = document.getElementById("priceSalesPage").value;
                var newQuantity = document.getElementById("quantitySalesPage").value;

                var newBeforeTax = document.getElementById("totalBeforeTax").value = total + (newPrice * newQuantity);
                var newTax = document.getElementById("totalTax").value = (total * 0.05) + (newPrice * newQuantity);
                var newAfterTax = document.getElementById("totalAfterTax").value = (total * 0.05) + (newPrice * newQuantity) + total;

                document.getElementById("demo").innerHTML = "Changes are: " + newAfterTax;
                
            }
        </script> -->
    </body>
</html>
