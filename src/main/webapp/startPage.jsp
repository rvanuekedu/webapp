<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<head>
		<link href="../css/startPageStyle.css" rel="stylesheet">
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

		<div class="mainblock">
			<form action="Enter" name="login" method="POST">
				<div class="imgLogo">
					<img src="../images/logo.svg" alt="">
				</div>
				<div class="inputCenter">
					<input type="text" name="email" placeholder="<fmt:message key="login" bundle="${BundleContent}"/>" required autofocus>
					<input type="password" name="password" placeholder="<fmt:message key="password" bundle="${BundleContent}"/>" required>
					<input class="btn" type="submit" name="submit" value="<fmt:message key="login" bundle="${BundleContent}"/>">
				</div>
			</form>
			<div class="createAcc">
					Not registered? <a href="createAccount.jsp"><fmt:message key="create an account" bundle="${BundleContent}"/></a>
			</div>
		</div>
	</body>
	
</html>