<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/BeanTag.tld" prefix="tag" %>

<html>
    <head>
        <link href="../css/mainPageStyle.css" rel="stylesheet">
        <link href="../css/dropDownMenu.css" rel="stylesheet">
        <link href="../css/SearchBarStyle.css" rel="stylesheet">
        <link href="../css/header.css" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    </head>
    <title>Test yourself</title>

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

        <div class="filter">

                <div class="adminPanel">
                    <c:choose>
                        <c:when test="${sessionScope.role == 'ADMIN'}">
                                <c:if test="${not empty unblockedStudents}">
                                    <div class="block-student block-unblock">
                                        <form action="BlockStudent" method="POST">
                                         <label class="label"><fmt:message key="select student" bundle="${BundleContent}"/></label>
                                         <br>
                                            <select name="blockStudents">
                                                <c:forEach items="${unblockedStudents}" var="student">
                                                    <option value="${student.id}"> ${student.firstName} ${student.secondName}</option>
                                                </c:forEach>
                                            </select>
                                            <br>
                                            <button class="blockStudent" type="submit" ><fmt:message key="block student" bundle="${BundleContent}"/></button>
                                        </form>
                                    </div>
                                </c:if>

                                <c:if test="${not empty blockedStudents}">
                                    <div class="unblock-student block-unblock" >
                                        <form action="UnblockStudent" method="POST">
                                        <label class="label"><fmt:message key="select student" bundle="${BundleContent}"/></label>
                                        <br>
                                            <select name="unblockStudents">
                                                <c:forEach items="${blockedStudents}" var="student">
                                                    <option value="${student.id}"> ${student.firstName} ${student.secondName}</option>
                                                </c:forEach>
                                            </select>
                                            <br>
                                            <button class="blockStudent" type="submit" ><fmt:message key="unblock student" bundle="${BundleContent}"/></button>
                                        </form>
                                    </div>
                                </c:if>

                                <div class="addTest">
                                    <a href="AddTest"><fmt:message key="add test" bundle="${BundleContent}"/></a>
                                </div>

                        </c:when>
                    </c:choose>
                </div>

                <div class="searchBySubject block-unblock">
                    <form action="SearchBySubject" method="GET">
                        <label class="label"><fmt:message key="select subject" bundle="${BundleContent}"/></label>
                        <br>
                        <select name="subject">
                            <c:forEach items="${subjects}" var="subject">
                                <option value="${subject.name}"> ${subject.name}</option>
                            </c:forEach>
                        </select>
                        <br>
                        <button class="searchBtn blockStudent" type="submit" ><fmt:message key="search" bundle="${BundleContent}"/></button>
                    </form>
                </div>

                <div class="searchTest">
                <form action="SearchTest" method="POST">
                  <input name="testName" placeholder="<fmt:message key="input test name" bundle="${BundleContent}"/>" type="text">
                  <button type="submit"></button>
                </form>
                </div>

            <div class="dropLink">
                <a class="filter-btn" onclick="down()"><fmt:message key="Filter" bundle="${BundleContent}"/></a>
                    <ul id="dropdown" class="menu" style="display:none">
                        <li><a><fmt:message key="sort by name" bundle="${BundleContent}"/></a>
                            <ul>
                                 <li><a href="/SortTests?sort=byNameA-Z"><fmt:message key="A-Z" bundle="${BundleContent}"/></a></li>
                                 <li><a href="/SortTests?sort=byNameZ-A"><fmt:message key="Z-A" bundle="${BundleContent}"/></a></li>
                            </ul>
                        </li>
                        <li><a><fmt:message key="sort by subject" bundle="${BundleContent}"/></a>
                            <ul>
                                 <li><a href="/SortTests?sort=bySubjectA-Z"><fmt:message key="A-Z" bundle="${BundleContent}"/></a></li>
                                 <li><a href="/SortTests?sort=bySubjectZ-A"><fmt:message key="Z-A" bundle="${BundleContent}"/></a></li>
                            </ul>
                        </li>
                        <li><a><fmt:message key="sort by complexity" bundle="${BundleContent}"/></a>
                            <ul>
                                <li><a href="/SortTests?sort=byComplexityIncrease"><fmt:message key="increase" bundle="${BundleContent}"/></a></li>
                                <li><a href="/SortTests?sort=byComplexityDecrease"><fmt:message key="decrease" bundle="${BundleContent}"/></a></li>
                            </ul>
                        </li>
                        <li><a><fmt:message key="sort by number" bundle="${BundleContent}"/></a>
                            <ul>
                                 <li><a href="/SortTests?sort=byNumberOfQuestions0-9">0-9</a></li>
                                 <li><a href="/SortTests?sort=byNumberOfQuestions9-0">9-0</a></li>
                            </ul>
                        </li>
                    </ul>
            </div>

        </div>

        <hr>

        <c:choose>
            <c:when test="${sessionScope.role == 'STUDENT'}">
                <div class="test-cards">
                    <c:choose>
                        <c:when test="${not empty tests}">
                            <c:forEach items="${tests}" var="test">
                                <div class="card" style="height: 340px;">
                                    <c:choose>
                                        <c:when test="${test.complexityName == 'easy'}">
                                            <div class="complexity" style="background-color:green; height: 10%;"></div>
                                        </c:when>
                                        <c:when test="${test.complexityName == 'medium'}">
                                             <div class="complexity" style="background-color:orange; height: 10%;"></div>
                                        </c:when>
                                        <c:when test="${test.complexityName == 'hard'}">
                                            <div class="complexity" style="background-color:red; height: 10%;"></div>
                                        </c:when>
                                     </c:choose>
                                    <div class="test-name"><strong>${test.testName}</strong></div>
                                                                    <div class="test-subject asd"><fmt:message key="subject" bundle="${BundleContent}"/> <strong>${test.subjectName}</strong></div>
                                                                    <div class="number-of-questions asd"><fmt:message key="number of questions" bundle="${BundleContent}"/> <strong>${test.numberOfQuestions}</strong></div>
                                                                    <div class="complexity asd"><fmt:message key="complexity" bundle="${BundleContent}"/> <strong>${test.complexityName}</strong></div>
                                                                    <div class="time-for-test asd"><fmt:message key="time for test" bundle="${BundleContent}"/> <strong>${test.time} <fmt:message key="minutes" bundle="${BundleContent}"/></strong></div>
                                                                    <div class="btn-pass"><a href="/EnterToTest?testId=${test.id}"><fmt:message key="pass" bundle="${BundleContent}"/></a></div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="tests-not-found"><p><fmt:message key="test not found" bundle="${BundleContent}"/></p></div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:when>

            <c:when test="${sessionScope.role == 'ADMIN'}">
                <div class="test-cards">
                <c:choose>
                    <c:when test="${not empty tests}">
                        <c:forEach items="${tests}" var="test">
                            <div class="card">
                                <c:choose>
                                    <c:when test="${test.complexityName == 'easy'}">
                                        <div class="complexity" style="background-color:green; height: 10%; margin-bottom: 3%;"></div>
                                    </c:when>
                                    <c:when test="${test.complexityName == 'medium'}">
                                        <div class="complexity" style="background-color:orange; height: 10%; margin-bottom: 3%;"></div>
                                    </c:when>
                                    <c:when test="${test.complexityName == 'hard'}">
                                        <div class="complexity" style="background-color:red; height: 10%; margin-bottom: 3%;"></div>
                                    </c:when>
                                </c:choose>
                                <div class="delete-edit">
                                    <a class="edit" href="EditTest?testId=${test.id}" title="Edit" ><img src="../images/edit.png" alt=""></a>
                                    <a class="delete" href="DeleteTest?testId=${test.id}" title="Delete" ><img src="../images/delete.png" alt=""></a>
                                </div>
                                <div class="test-name"><strong>${test.testName}</strong></div>
                                <div class="test-subject asd"><fmt:message key="subject" bundle="${BundleContent}"/> <strong>${test.subjectName}</strong></div>
                                <div class="number-of-questions asd"><fmt:message key="number of questions" bundle="${BundleContent}"/> <strong>${test.numberOfQuestions}</strong></div>
                                <div class="complexity asd"><fmt:message key="complexity" bundle="${BundleContent}"/> <strong>${test.complexityName}</strong></div>
                                <div class="time-for-test asd"><fmt:message key="time for test" bundle="${BundleContent}"/> <strong>${test.time} <fmt:message key="minutes" bundle="${BundleContent}"/></strong></div>
                                <div class="btn-pass"><a href="/EnterToTest?testId=${test.id}"><fmt:message key="view" bundle="${BundleContent}"/></a></div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="tests-not-found"><p><fmt:message key="test not found" bundle="${BundleContent}"/></p></div>
                    </c:otherwise>
                </c:choose>
                </div>
            </c:when>
        </c:choose>

        <script src="../js/dropDownMenu.js"></script>
    </body>
</html>