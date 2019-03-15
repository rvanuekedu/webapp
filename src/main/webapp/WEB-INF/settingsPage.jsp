<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
    <head>
        <link href="../css/header.css" rel="stylesheet">
        <link href="../css/settingsPageStyle.css" rel="stylesheet">
    </head>
    <title>Settings</title>

    <body>
    <div class="fixed">
        <%@ include file="jspf/header.jspf" %>

        <div class="container-change-email">
            <form action="ChangeEmail" name="changeEmail" method="POST">
                <div class="inputCenter">
                    <h1 class="title-center">Change email</h1>
                    <h1>Current Email</h1>
                    <input type="email" name="currentEmail" id="email" required>
                    <h1>New Email</h1>
                    <input type="email" name="newEmail" id="email" required>
                    <h1>Password</h1>
                    <input type="password" name="password" id="password1" required>
                    <br>
                    <input class="btn" type="submit" name="submit" value="Change" onclick="return validateForm()">
                </div>
            </form>
        </div>

        <hr>

        <div class="container-change-password">
            <form action="ChangePassword" name="changePassword" method="POST">
                <div class="inputCenter">
                    <h1 class="title-center">Change password</h1>
                    <h1>Current password</h1>
                    <input type="password" name="currentPassword" id="currentPassword" required>
                    <h1>New password</h1>
                    <input type="password" name="password1" id="password1" pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}" title="Must contain at least one number, one upcase letter, one special symbol(!@#$%^&*) and at least 6 or more characters" required>
                    <h1>Verify password</h1>
                    <input type="password" name="password2" id="password2" pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}" title="Must contain at least one number, one upcase letter, one special symbol(!@#$%^&*) and at least 6 or more characters" required>
                    <br>
                    <input class="btn" type="submit" name="submit" value="Change" onclick="return validateForm()">
                </div>
            </form>
        </div>

    </div>
    </body>
</html>