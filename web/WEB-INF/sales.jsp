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
            <%@ include file="jspf/segment.jspf" %>
            
        </div>
            
        <div class="sales">
            <h1>Sale Page</h1>    

            <table>
                <tr>
                    <th>Item ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Category</th>
                </tr>

                <c:set var="total" value="${0}"/>
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
                    </tr>

                </c:forEach>
            </table>
        </div>
        <div class="totals">
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
        </script>
    </body>
</html>
