<%-- 
    Document   : vendors.jsp
    Created on : Mar 8, 2022, 10:39:35 AM
    Author     : 857421
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="index.css">
    <title>Vendors Page</title>
</head>

<body>

    <div class="header">
        <img src=".\Resources\photos\collabyyc.png">
        <%@ include file="jspf/segment.jspf" %>
    </div>

    <div>
        <table class="vendorTable">
            
        <tr>
            <th>Vendor name</th>
            <th>Vendor ID</th>
            <th>Vendor email</th>
            <th>Vendor phone number</th>
        </tr>

            <c:forEach vendors="${vendorList}" var="vendor">
                <tr>
                    <td>${vendor.name}</td>
                    <td> ${vendor.vendorID}</td>
                    <td> ${vendor.vendorEmail}</td>
                    <td> ${vendor.vendorPhoneNumber}</td>
                
                    <td>
                        <form action="FrontController" method="GET">
                            <input type="hidden" name="deleteVendor" value=${vendor.vendorID}>
                            <input id="deleteButton" type="submit" value="X">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <!--
            <form action="FrontController" method="GET">
                <table class="searchTable">
                    <td>Search Vendor</td>
                    <tr>
                        <td>Vendor ID:<input type="text" name="searchID"></td>
                        <td>Name:<input type="text" name="searchName"></td>
                        <td>Email:<input type="text" name="searchEmail"></td>
                        <td><input type="submit" id="searchButton" name="search" value="Search"></td>
                    </tr>
                </table>
            </form>
-->
    <div class="navigation">
        <h3><b>Navigation Dashboard</b></h2>
            <a href="sales.jsp">Sales Page</a> <br><br><br>
            <a href="inventory.jsp">Inventory</a> <br><br><br>
            <a id="salesPageLink" href="reports.jsp">Sale Reports</a> <br><br><br>
    </div>

    <div class="twoButtons">
        <form action="FrontController" method="GET">
            <input id="logoutButton" type="button" value="Logout">
        </form>
        <form action="FrontController" method="GET">
            <input id="backButton" type="button" value="Back">
        </form>

    </div>
</body>

</html>
