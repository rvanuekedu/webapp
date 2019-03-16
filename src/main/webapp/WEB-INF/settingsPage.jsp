<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
    <head>
        <link href="../css/header.css" rel="stylesheet">
        <link href="../css/settingsPageStyle.css" rel="stylesheet">
        <script src="../js/validatePassword.js"></script>
        <script src="../js/validateEmail.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha1/0.6.0/sha1.js"></script>
    </head>
    <title>Settings</title>

        <%@ include file="jspf/header.jspf" %>
        <br><br><br>
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


    <div id="userPassword" style="display: none;">${sessionScope.user.password}</div>
    <div id="email" style="display: none;">${sessionScope.user.email}</div>


    <div class="studentInfo">
        <h1><fmt:message key="student first name" bundle="${BundleContent}"/></h1><h2>${student.firstName}</h2>
        <br>
        <h1><fmt:message key="student second name" bundle="${BundleContent}"/></h1><h2>${student.secondName}</h2>
        <br>
        <h1><fmt:message key="student email" bundle="${BundleContent}"/></h1><h2>${student.userEmail}</h2>
    </div>

    <div class="fixed">

        <div class="container-change-email">
            <form action="ChangeEmail" name="changeEmail" method="POST">
                <div class="inputCenter">
                    <h1 class="title-center"><fmt:message key="change email" bundle="${BundleContent}"/></h1>
                    <h1><fmt:message key="current email" bundle="${BundleContent}"/></h1>
                    <input type="email" name="currentEmail" id="currentEmail" required>
                    <h1><fmt:message key="new email" bundle="${BundleContent}"/></h1>
                    <input type="email" name="newEmail" id="newEmail" required>
                    <h1><fmt:message key="Password" bundle="${BundleContent}"/></h1>
                    <input type="password" name="password" id="password" required>
                    <br>
                    <input class="btn"  type="submit" name="submit" value="<fmt:message key="change" bundle="${BundleContent}"/>" onclick="return validateEmail()">
                </div>
            </form>
        </div>

        <hr>

        <div class="container-change-password">
            <form action="ChangePassword" name="changePassword" method="POST">
                <div class="inputCenter">
                    <h1 class="title-center"><fmt:message key="change password" bundle="${BundleContent}"/></h1>
                    <h1><fmt:message key="current password" bundle="${BundleContent}"/></h1>
                    <input type="password" name="currentPassword" id="currentPassword" required>
                    <h1><fmt:message key="new password" bundle="${BundleContent}"/></h1>
                    <input type="password" name="password1" id="password1" pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}" title="<fmt:message key="must contain" bundle="${BundleContent}"/>" required>
                    <h1><fmt:message key="verify password" bundle="${BundleContent}"/></h1>
                    <input type="password" name="password2" id="password2" pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}" title="<fmt:message key="must contain" bundle="${BundleContent}"/>" required>
                    <br>
                    <input class="btn" type="submit" name="submit" value="<fmt:message key="change" bundle="${BundleContent}"/>" onclick="return validatePassword()">
                </div>
            </form>
        </div>

    </div>
    </body>
</html>