<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./login.css">
    <title>Collab YYC Login</title>
</head>

<body>
    <table>
        <tr>
            <td>
                <img src="collabyyc.png">
            </td>

        </tr>
    </table>
        <form action="LoginServlet" method="POST">
            <table>
            <tr>
                <td>
                    <input name="username" type="text" placeholder="Username">
                </td>
            </tr>
            <tr>
                <td>
                    <input name="password" type="password" placeholder="Password">
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Login">
                </td>
                <input type="hidden" name="action" value="login">
            </tr>
            </table>
        </form>
        ${invalidLogin}
</body>

</html>