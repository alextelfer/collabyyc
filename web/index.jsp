<%-- 
    Document   : index
    Created on : Feb 10, 2022, 1:25:02 AM
    Author     : 857421
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./index.css">
<title>CollabYYC Home</title>
</head>

<body>
	<div class="inventory">
		<img src="collabyyc.png">
		<h1>Inventory</h1>

		<table id="contents">
			<tr>
				<td><h3>Menu</h3></td>
			</tr>
			<tr>
				<td id="cellContents"><a href="">Logout</a></td>
			</tr>
			<tr>
				<td id="cellContents"><a href="./vendors.jsp">Vendors</a></td>
			</tr>
			<tr>
				<td id="cellContents"><a href="">Sales</td>
			</tr>
			<tr>
				<td id="cellContents"><a href="">Inventory</a></td>
			</tr>

			<form action="FrontController" method="GET">
				<tr>
					<td><h3>Add Item</h3></td>
				</tr>
				<tr>
					<td>Item ID: <input type="number" name="itemid" /></td>
				</tr>
				<tr>
					<td>Name: <input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>Vendor ID: <input type="number" name="vendor" /></td>
				</tr>
				<tr>
					<td>Category: <input type="text" name="category" /></td>
				</tr>
				<tr>
					<td>Price: <input type="number" name="price" /></td>
				</tr>
				<tr>
					<td><input type="hidden" name="action" value="additem" /> <input
						type="submit" value="Add Item" /></td>
				</tr>
			</form>
		</table>

		<table id="allItems" style="overflow-y: auto">
			<c:forEach items="${itemlist}" var="item">
				<table id="eachItem">
					<tr></tr>
					<tr>
						<td>ID: ${item.itemID}</td>
						<td>Name: ${item.name}</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>Vendor ID: ${item.vendorID}</td>
						<td>Category: ${item.category}</td>
						<td>Price: {item.price}</td>
						<td>Quantity: {item.quantity}</td>
						<td><a href="">Delete</a></td>
					</tr>
				</table>
			</c:forEach>
			</div>
</body>
</html>