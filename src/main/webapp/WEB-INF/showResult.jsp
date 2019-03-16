<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>

    <head>
        <link href="../css/showResult.css" rel="stylesheet">
    </head>
    <title>Result</title>
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

                <p>
                    <a href="Enter" title="Back to main page"><img src="../images/success.png" alt=""></a>
                    <h1><fmt:message key="your result" bundle="${BundleContent}"/></h1>
                    <h1>${result}</h1>
                </p>

    </body>
</html>