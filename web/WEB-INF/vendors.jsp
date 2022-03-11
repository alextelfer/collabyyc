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
    <link rel="stylesheet" href="vendors.css">
    <title>Vendors Page</title>
</head>

<body>
    <div class="titleLogo">
        <img src=".\Resources\photos\collabyyc.png">
        <h1>Vendors</h1>
    </div>

    <div>
        <table class="vendorTable">
            <c:forEach vendors="${vendorList}" var="vendor">
                <tr>
                    <td>Vendor name: ${vendor.name}</td>
                    <td id="vendorIDSpace">Vendor ID: ${vendor.vendorID}</td>
                </tr>
                <tr>
                    <td>Vendor email: ${vendor.vendorEmail}</td>
                    <td id="vendorPhoneSpace">Vendor phone number: ${vendor.vendorPhoneNumber}</td>
                </tr>

                <tr>
                    <td>
                        <form action="FrontController" method="GET">
                            <input type="hidden" name="deleteVendor" value=${vendor.vendorID}>
                            <input id="deleteButton" type="submit" value="X">
                        </form>
                    </td>
                </tr>

                <tr id="separator">
                    <!--this is where the line that seperates the vendors is-->
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
