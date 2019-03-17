<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<html>
    <head>
        <link href="../css/addTestStyle.css" rel="stylesheet">
        <link href="../css/header.css" rel="stylesheet">
        <script src="../js/validateNumber.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>
    <title>Add Test</title>

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

        <%@ include file="jspf/header.jspf" %>

        <div style="margin-top: 50px">
        <div class="back">
            <a class="back" href="Enter" title="Back" ><img src="../images/back.png" alt=""></a>
        </div>
        <div class="container" style="height: auto;">
                    <form action="AddTest" name="AddTest" method="POST">
                        <div class="inputCenter">
                            <h1><fmt:message key="test name" bundle="${BundleContent}"/></h1>
                            <input type="text" name="testName" required autofocus>
                            <h1><fmt:message key="choose subject" bundle="${BundleContent}"/></h1>
                                <select name="subjects">
                                    <c:forEach items="${subjects}" var="subject">
                                        <option value="${subject.id}"> ${subject.name}</option>
                                    </c:forEach>
                                </select>
                            <h1><fmt:message key="or add" bundle="${BundleContent}"/></h1>
                            <input type="text" name="newSubject">
                            <h1><fmt:message key="choose complexity" bundle="${BundleContent}"/></h1>
                                <select name="complexity">
                                        <option value="easy"><fmt:message key="easy" bundle="${BundleContent}"/></option>
                                        <option value="medium"><fmt:message key="medium" bundle="${BundleContent}"/></option>
                                        <option value="hard"><fmt:message key="hard" bundle="${BundleContent}"/></option>
                                </select>
                            <h1><fmt:message key="Time for test" bundle="${BundleContent}"/></h1>
                            <input id="number" type="number" name="testTime" required>
                            <br>
                            <input class="btn" type="submit" name="submit" value="<fmt:message key="add" bundle="${BundleContent}"/>" onclick="return validateNumber()">
                        </div>
                    </form>
                </div>
        </div>
    </body>
</html>