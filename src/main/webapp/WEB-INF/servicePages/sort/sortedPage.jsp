<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sorted Goods</title>

    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,800,700,600,300' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/templatemo_style.css"/>">
</head>
<body>
<div class="middle-content">
    <div class="container">
        <div class="row">

            <c:choose>
                <c:when test="${requestScope.sortedGoodList == null}">
                    <div class="h2">
                        There is no goods!
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="h2">
                        There is the sorted goods list from this site:
                    </div>
                    <table class="table-bordered h3">
                        <tr>
                            <th>Number</th>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Firm</th>
                            <th>Price</th>
                            <th>Photo</th>
                        </tr>
                        <c:forEach var="rol_row" items="${requestScope.sortedGoodList}">

                            <tr>
                                <td>${rol_row.id}</td>
                                <td>${rol_row.name}</td>
                                <td>${rol_row.category}</td>
                                <td>${rol_row.firm}</td>
                                <td>${rol_row.price} $</td>
                                <c:choose>
                                    <c:when test="${rol_row.photoPath == ''}">
                                        <td>no photo!</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><img src="<c:url
                                        value="/images/repository/goodsPic/${rol_row.photoPath}"/>"></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>

                    </table>
                </c:otherwise>
            </c:choose>
            <br>
            <br>
            You can return to previous page:
            <br>
            <%@include file="/WEB-INF/tags/returnBack.tag" %>
        </div>
    </div>
</div>
</body>
</html>
