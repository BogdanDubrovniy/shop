<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
You make order successful!
<br>
<div class="h2">
    Your contacts:
    Address : <c:out value="${requestScope.address}"/>
    <br>
    Your pay method: <c:out value="${requestScope.deliveryMethod}"/>
</div>
<br>
<div class="h2">
    There are goods in the bucket:
</div>
<table class="table-bordered h3">
    <tr>
        <th>Number</th>
        <th>Name</th>
        <th>Category</th>
        <th>Firm</th>
        <th>Price</th>
        <th>Amount</th>
    </tr>
    <c:forEach var="rol_row" items="${requestScope.bucketList}">
        <tr>
            <td>${rol_row.id}</td>
            <td>${rol_row.name}</td>
            <td>${rol_row.category}</td>
            <td>${rol_row.firm}</td>
            <td>${rol_row.price} $</td>
            <td>${rol_row.amount}</td>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<div class="h2">
    Your last orders:
</div>
<table class="table-bordered h3">
    <tr>
        <th>ID</th>
        <th>Status</th>
        <th>Details</th>
        <th>User Info</th>
        <th>Date</th>
    </tr>
    <c:forEach var="rol_row" items="${requestScope.userOrderList}">
        <tr>
            <td>${rol_row.idOrder}</td>
            <td>${rol_row.status}</td>
            <td>${rol_row.details}</td>
            <td>${rol_row.userInfo}</td>
            <td>${rol_row.date}</td>
        </tr>
    </c:forEach>
</table>

<br>
<a href="<c:url value="/login.jsp"/>">Return to main page.</a>
</body>
</html>
