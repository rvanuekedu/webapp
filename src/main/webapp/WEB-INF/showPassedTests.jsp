<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
    <head>
        <link href="../css/header.css" rel="stylesheet">
        <link href="../css/showPassedTests.css" rel="stylesheet">
    </head>
    <title>Results</title>
    <body>
        <%@ include file="jspf/header.jspf" %>

                <div class="test-cards">
                    <c:choose>
                        <c:when test="${not empty results}">
                            <c:forEach items="${results}" var="result">
                                <div class="card">
                                    <div class="test-name"><strong>${result.testName}</strong></div>
                                    <div class="asd">Passage date: <strong>${result.date}</strong></div>
                                    <div class="asd">Result: <span class="result"><strong>${result.valueOfResult}</strong></span></div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="tests-not-found"><p>Passed tests not found</p></div>
                        </c:otherwise>
                    </c:choose>
                </div>

    </body>
</html>