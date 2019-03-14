<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<html>
    <head>
        <link href="../css/editTestStyle.css" rel="stylesheet">
    </head>

    <body>
        <div class="back">
            <a class="back" href="Enter" title="Back" ><img src="../images/back.png" alt=""></a>
        </div>
        <div class="container">
                    <form action="EditTest?testId=${testId}" name="EditTest" method="POST">
                        <div class="inputCenter">
                            <h1>Test name</h1>
                            <input type="text" name="testName" value="${testInfo.testName}">
                            <h1>Choose complexity</h1>
                                <select name="complexity">
                                    <c:choose>
                                        <c:when test="${testInfo.complexity == 'easy'}">
                                            <option value="easy" selected>easy</option>
                                            <option value="medium">medium</option>
                                            <option value="hard">hard</option>
                                        </c:when>
                                        <c:when test="${testInfo.complexity == 'medium'}">
                                            <option value="easy">easy</option>
                                            <option value="medium" selected>medium</option>
                                            <option value="hard">hard</option>
                                        </c:when>
                                        <c:when test="${testInfo.complexity == 'hard'}">
                                            <option value="easy">easy</option>
                                            <option value="medium">medium</option>
                                            <option value="hard" selected>hard</option>
                                        </c:when>
                                    </c:choose>
                                </select>
                            <h1>Time for Test</h1>
                            <input type="number" name="testTime" value="${testInfo.time}">
                            <br>
                            <input class="btn" type="submit" name="submit" value="Edit">
                        </div>
                    </form>
                </div>

    </body>
</html>