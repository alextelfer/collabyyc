<%-- 
    Document   : index
    Created on : Feb 10, 2022, 1:25:02 AM
    Author     : 857421
--%>

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
                        <td>Name:</td>
                        <td><input type="text" name="name" /></td>
                    </tr>
                    <tr>
                        <td>Vendor:</td>
                        <td><input type="text" name="vendor" /></td>
                    </tr>
                    <tr>
                        <td>Price:</td>
                        <td><input type="number" name ="price" /></td>
                    </tr>
                </table>
                <input type="submit" value="Add Item" />
                
            </form>
        </div>
        
        <div class="inventory">
            <table>
                <tr>
                    <th>Item ID</th>
                    <th>Name</th>
                    <th>Vendor</th>
                    <th>Price</th>
                </tr>
            </table>
        </div>
        
    </body>
</html>
