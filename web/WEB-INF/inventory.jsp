<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script>
    function setDecimalNumber(event) {
        this.value = parseFloat(this.value).toFixed(2);
    }
</script>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./index.css">
<title>CollabYYC Home</title>
</head>

<body>
	<div class="header">
		<img src=".\Resources\photos\collabyyc.png">
	</div>
	<h1>Inventory</h1>
	<%@ include file="jspf/segment.jspf" %>
	<form action="FrontController" method="GET">
		Search by Vendor Name:<select name="vendorName">
			<option disabled selected value>Select a Vendor</option>
			<c:forEach items="${vendorlist}" var="vendor">
				<option>${vendor.name}</option>
			</c:forEach>
		</select> <input type='submit' value='Search'>
	</form>
	<div class="inventory" id="itemlist">
		<form action="FrontController" method="GET">
			<table>
				<tr>
					<th>SKU</th>
					<th>Vendor Name</th>
					<th>Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Category</th>
				</tr>

				<c:forEach items="${itemlist}" var="item">
					<tr>
						<td>${item.sku}</td>
						<td>${item.vendorName}</td>
						<td>${item.name}</td>
						<td>\$<fmt:formatNumber minFractionDigits='2'
								value='${item.price}' /></td>
						<td>${item.quantity}</td>
						<td>${item.category}</td>
						<td><input type="hidden" name="modifyItem" value=${item.sku}>
							<input type="submit" value="Edit"></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>
	

	<div class="form" id="sidebar">
		<h3>Navigation</h3>
		<table>
				<tr>
					<td><a href="">Link text</a></td>
				</tr>
				<tr>
					<td><a href="">Link text</a></td>
				</tr>
				<tr>
					<td><a href="">Link text</a></td>
				</tr>
				<tr>
					<td><a href="">Link text</a></td>
				</tr>
				<tr>
					<td><a href="">Link text</a></td>
				</tr>
		</table>
		
		<h3>Add Item</h3>
		<form action="FrontController" method="GET">
			<table>
				<tr>
					<td>SKU:<input type="number" name="sku" /></td>
				</tr>
				<tr>
					<td>Vendor Name:<select name="vendorName">
							<c:forEach items="${vendorlist}" var="vendor">
								<option>${vendor.name}</option>
							</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td>Name:<input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>Price:<input type="number" name="price" min="0" /></td>
				</tr>
				<tr>
			<td>Quantity:<input type="number" name="quantity" min="0" /></td>
				</tr>
				<tr>
					<td>Category:<select name="category">
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
					</select></td>
				</tr>
				<td><input type="hidden" name="action" value="additem" /> <input
					type="submit" value="Add Item" /></td>
				</tr></table>
		</form>

		<h3>Delete Item</h3>
		<table>
			<form action="FrontController" method="GET">
				<tr>
					<td>Item ID: <select name="deleteID">
							<option disabled selected value>Select an Item</option>
							<c:forEach items="${itemlist}" var="item">
								<option value="${item.sku}">${item.name}</option>
							</c:forEach>
					</select>
				</tr>
				<tr>
					</td>
					<td><input type="hidden" name="action" value="deleteitem" />
						<input type="submit" value="Delete Item"
						onclick="return confirm('Delete item?')" /></td>
				</tr>
			</form>
		</table>
	</div>
</body>
</html>