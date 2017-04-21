<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,800,700,600,300'
          rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/font-awesome.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/animate.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/templatemo_misc.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/templatemo_style.css"/>">
</head>
<body>
<div class="container">
    <c:choose>
        <c:when test="${sessionScope.userLogin == '' || sessionScope.userLogin == null}">
            <div id="content">
                <form id="jform" action="<c:url value="/registrationServlet"/>" method="post"
                      enctype="multipart/form-data">
                    <fieldset>
                        <legend>Enter information for registration:</legend>
                        <p>
                            <label for="userName" class="block">Enter your name
                                <small>(more then 5 symbols)</small>
                                :</label>
                            <input type="text" name="userName" id="userName"
                                   value="${requestScope.userName}"/>
                        </p>
                        <p>
                            <label for="userSurname" class="block">Enter your surname
                                <small>(more then 5 symbols)</small>
                                :</label>
                            <input type="text" name="userSurname" id="userSurname"
                                   value="${requestScope.userSurname}"/>
                        </p>
                        <p>
                            <label for="userLogin" class="block">Enter your login
                                <small>(4 - 15 symbols)</small>
                                :</label>
                            <input type="text" name="userLogin" id="userLogin"
                                   value="${requestScope.userSurname}"/>
                        </p>
                        <legend>Email</legend>
                        <p>
                            <label for="email" class="block">Email
                                <small>(mickey@mou.se)</small>
                                :</label>
                            <input type="text" name="email" id="email"
                                   value="${requestScope.email}"/>
                        </p>
                        <p>
                            <label for="userPassword" class="block">Enter your password
                                <small>(4 - 16 symbols)</small>
                                :</label>
                            <input type="password" name="userPassword" id="userPassword"/>
                        </p>
                        <p>
                            <label for="userPassword" class="block">Confirm your password
                                :</label>
                            <input type="password" name="userPasswordConfirmed"
                                   id="userPasswordConfirmed" title="userPasswordConfirmed"/>
                        </p>
                    </fieldset>
                    <br>
                    <fieldset>
                        <%@include file="/WEB-INF/tags/captcha_Tag.tag" %>
                    </fieldset>
                    <br>
                    <input type="file" name="inputFile" size="50"/>
                    <br/>
                    <br>
                    <p>
                        <button type="submit" id="send">add new user</button>
                    </p>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            You can not registered, you have already logged! Please, log out.
        </c:otherwise>
    </c:choose>
</div>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"
        charset="utf-8"></script>
<script type="text/javascript" src="<c:url value="/js/validationScript.js"/>"></script>

</body>
</html>
