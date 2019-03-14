<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <link href="../css/createAccauntPageStyle.css" rel="stylesheet">
        <script src='https://www.google.com/recaptcha/api.js'></script>
        <script src="../js/validateRegFormScript.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>
    <body>
        <div class="container">
            <form action="Registration" name="createAccauntPage" method="POST">
                <div class="inputCenter">
                    <h1>First name</h1>
                    <input type="text" name="firstName" required autofocus>
                    <h1>Second name</h1>
                    <input type="text" name="secondName" required>
                    <h1>Email</h1>
                    <input type="email" name="email" id="email" required>
                    <h1>Password</h1>
                    <input type="password" name="password1" id="password1" pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}" title="Must contain at least one number, one upcase letter, one special symbol(!@#$%^&*) and at least 6 or more characters" required>
                    <h1>Verify password</h1>
                    <input type="password" name="password2" id="password2" pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}" title="Must contain at least one number, one upcase letter, one special symbol(!@#$%^&*) and at least 6 or more characters" required>
                    <div class="g-recaptcha" data-sitekey="6LeKI5UUAAAAAD2FhNsawiu_CpHpb2eHOYHBtkN0"></div>
                    <br>
                    <input class="btn" type="submit" name="submit" value="Register" onclick="return validateForm()">
                </div>
            </form>
        </div>
    </body>
</html>