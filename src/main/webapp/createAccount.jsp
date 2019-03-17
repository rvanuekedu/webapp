<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
    <head>
        <link href="../css/createAccauntPageStyle.css" rel="stylesheet">
        <link href="../css/change-language.css" rel="stylesheet">
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

<header class="header-user-dropdown" style="padding-top: 0px;">

            	<div class="header-limiter">

                    <div class="header-user-menu" >
            			    <img src="../images/language.jpg" alt="" style="top: 6px;"/>
            			<ul>
            				    <li><a href="ChangeLanguage?language=ru"><fmt:message key="russian" bundle="${BundleContent}"/></a></li>
            				    <li><a href="ChangeLanguage?language=en"><fmt:message key="english" bundle="${BundleContent}"/></a></li>
            			</ul>
            		</div>


            	</div>

</header>

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