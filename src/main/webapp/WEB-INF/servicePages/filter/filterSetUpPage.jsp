<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Set Up Filter</title>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,800,700,600,300'
          rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/templatemo_style.css"/>">
</head>
<body>
<div class="middle-content">
    <div class="container">
        <div class="row">
            <fieldset>
                <%@include file="/WEB-INF/tags/filterTag.tag" %>
            </fieldset>
        </div>
    </div>
</div>
</body>
</html>
