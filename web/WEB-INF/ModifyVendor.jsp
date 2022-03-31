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
        <h1>Edit Vendor</h1>

        <form action="FrontController" method="GET">

            <c:forEach items="${singleVendor}" var="vendor">
                <table>
                    <tr>
                        <!--                        <td>Vendor ID:
                                                    <input type="text" name="updatedVendorID" value="${vendor.vendorID}"/></td>-->

                        <td>Vendor Name:
                            <input type="text" name="updatedVendorName" value="${vendor.name}" /></td>

                        <td>Vendor Email:
                            <input type="text" name="updatedVendorEmail" value="${vendor.vendorEmail}" /></td>

                        <td>Vendor Phone:
                            <input type="text" name="updatedVendorPhone" value="${vendor.vendorPhoneNumber}" /></td>



                    </tr>  
                </table>
                <input type="hidden" name="updatedVendorID" value="${vendor.vendorID}"/>
            </c:forEach>

            <input type="hidden" name="action" value="updateVendor" />
            <input type="submit" value="Save"/>


        </form>

    </body>
</html>
