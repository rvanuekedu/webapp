<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<html>
    <head>
        <link href="../css/addTestStyle.css" rel="stylesheet">
    </head>
    <title>Add Test</title>

    <body>
        <div class="back">
            <a class="back" href="Enter" title="Back" ><img src="../images/back.png" alt=""></a>
        </div>
        <div class="container">
                    <form action="AddTest" name="AddTest" method="POST">
                        <div class="inputCenter">
                            <h1>Test name</h1>
                            <input type="text" name="testName" required autofocus>
                            <h1>Choose subject</h1>
                                <select name="subjects">
                                    <c:forEach items="${subjects}" var="subject">
                                        <option value="${subject.id}"> ${subject.name}</option>
                                    </c:forEach>
                                </select>
                            <h1>Or add new Subject</h1>
                            <input type="text" name="newSubject">
                            <h1>Choose complexity</h1>
                                <select name="complexity">
                                        <option value="easy">easy</option>
                                        <option value="medium">medium</option>
                                        <option value="hard">hard</option>
                                </select>
                            <h1>Time for Test</h1>
                            <input type="number" name="testTime"  required>
                            <br>
                            <input class="btn" type="submit" name="submit" value="Add">
                        </div>
                    </form>
                </div>

    </body>
</html>