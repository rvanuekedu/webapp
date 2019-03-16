<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
    <head>
        <link href="../css/createAccauntPageStyle.css" rel="stylesheet">
        <script src='https://www.google.com/recaptcha/api.js'></script>
        <script src="../js/validateRegFormScript.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty sessionScope.language}">
                <fmt:setLocale value="en"/>
            </c:when>
            <c:otherwise>
                <fmt:setLocale value="${sessionScope.language}"/>
            </c:otherwise>
        </c:choose>
        <fmt:setBundle var="BundleContent" basename="Content"/>

        <div class="container">
            <form action="Registration" name="createAccauntPage" method="POST">
                <div class="inputCenter">
                    <h1><fmt:message key="first name" bundle="${BundleContent}"/></h1>
                    <input type="text" name="firstName" required autofocus>
                    <h1><fmt:message key="second name" bundle="${BundleContent}"/></h1>
                    <input type="text" name="secondName" required>
                    <h1><fmt:message key="Email" bundle="${BundleContent}"/></h1>
                    <input type="email" name="email" id="email" required>
                    <h1><fmt:message key="Password" bundle="${BundleContent}"/></h1>
                    <input type="password" name="password1" id="password1" pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}" title="<fmt:message key="must contain" bundle="${BundleContent}"/>" required>
                    <h1><fmt:message key="verify password" bundle="${BundleContent}"/></h1>
                    <input type="password" name="password2" id="password2" pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}" title="<fmt:message key="must contain" bundle="${BundleContent}"/>" required>
                    <div class="g-recaptcha" data-sitekey="6LeKI5UUAAAAAD2FhNsawiu_CpHpb2eHOYHBtkN0"></div>
                    <br>
                    <input class="btn" type="submit" name="submit" value="<fmt:message key="register" bundle="${BundleContent}"/>" onclick="return validateForm()">
                </div>
            </form>
        </div>
    </body>
</html>