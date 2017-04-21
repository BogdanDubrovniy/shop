<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal Page</title>
</head>
<body>
Hello,
<c:out value="${sessionScope.userLogin}"/>. You are logged.
<br>
Your photo is next:
<br>
<fieldset>
    <%@include file="/WEB-INF/tags/userPhotoTag.tag" %>
</fieldset>

<br>
<br>
You can <a href="<c:url value="/bucket"/>">go to bucket</a>
<br>
<br>
You can return to previous page:
<br>
<%@include file="/WEB-INF/tags/returnBack.tag" %>
</body>
</html>
