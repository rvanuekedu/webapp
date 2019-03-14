<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
    <head>
        <link href="../css/TestPageStyle.css" rel="stylesheet">
        <link href="../css/pencil.css" rel="stylesheet">
        <link href="../css/timer.css" rel="stylesheet">
        <script src='../js/pencil.js'></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>

    <body>
        <c:choose>
            <c:when test="${sessionScope.role == 'STUDENT'}">
            <script src='../js/timer.js'></script>
                <div class="timer" id="timer">
                        <div id="minute">${testTime}</div>&nbsp;:
                        <div id="second">00</div>
                </div>

                <div class="container">
                    <form action="/ResultTest?testId=${testId}" id="testPass" name="TestPage" method="POST">
                            <c:set var="number" value="1" scope="page"/>
                        <c:forEach items="${questions}" var="question">
                            <c:set var="questionId" value="${question.questionId}" scope="page"/>
                            <div class="question">
                                <p> ${number}. ${question.questionText}</p>
                            </div>
                            <div class="answers">
                                <c:forEach items="${answers}" var="answer">
                                    <c:if test="${answer.questionId == questionId}">
                                        <label>
                                            <input class="cb pristine" type="checkbox" name="${answer.questionId}" value="${answer.answerText}">
                                            <span>${answer.answerText}</span>
                                        </label>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <c:set var="number" value="${number + 1}" scope="page"/>
                        </c:forEach>
                        <button class="btn" type="submit" >PASS</button>
                    </form>
                </div>
            </c:when>

            <c:when test="${sessionScope.role == 'ADMIN'}">
                <div class="timer" id="timer">
                        <div id="minute">${testTime}</div>&nbsp;:
                        <div id="second">00</div>
                </div>

                <div class="container">
                    <div class="addQuestion">
                        <form action="AddQuestion?testId=${testId}" name="AddQuestion" method="POST">
                            <input type="text" name="newQuestion">
                            <button class="addQuestionBtn" type="submit">Add question</button>
                        </form>
                    </div>
                            <c:set var="number" value="1" scope="page"/>
                        <c:forEach items="${questions}" var="question">
                            <c:set var="questionId" value="${question.questionId}" scope="page"/>
                            <div class="question">
                                <div class="delete-edit">
                                    <a class="delete" href="DeleteQuestion?questionId=${questionId}&testId=${testId}" title="Delete" ><img src="../images/delete.png" alt=""></a>
                                </div>
                                <p> ${number}. ${question.questionText}</p>
                            </div>
                                <div class="addAnswer">
                                    <form id="addAnswerForm" action="AddAnswer?questionId=${questionId}&testId=${testId}" name="AddAnswer" method="POST">
                                        <input type="text" name="newAnswer">
                                            <select name="isCorrect">
                                                <option value="true">true</option>
                                                <option value="false" selected>false</option>
                                            </select>
                                        <button type="submit" from="addAnswerForm">Add answer</button>
                                    </form>
                                </div>
                            <div class="answers">
                                <c:forEach items="${answers}" var="answer">
                                    <c:if test="${answer.questionId == questionId}">
                                        <label>
                                            <div class="delete-edit">
                                                <a class="delete" href="DeleteAnswer?answerId=${answer.answerId}&testId=${testId}" title="Delete" ><img src="../images/delete.png" alt=""></a>
                                            </div>
                                            <c:choose>
                                                <c:when test="${answer.isCorrect == 'true'}">
                                                    <input form="Result" class="cb pristine" type="checkbox" name="${answer.questionId}" value="${answer.answerText}" checked>
                                                </c:when>
                                                <c:otherwise>
                                                    <input form="Result" class="cb pristine" type="checkbox" name="${answer.questionId}" value="${answer.answerText}">
                                                </c:otherwise>
                                            </c:choose>
                                            <span>${answer.answerText}</span>
                                        </label>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <c:set var="number" value="${number + 1}" scope="page"/>
                        </c:forEach>
                        <div class="back">
                            <a class="back" href="Enter" title="Back" ><img src="../images/back.png" alt=""></a>
                        </div>
                </div>
            </c:when>
        </c:choose>

    </body>
</html>