<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,800,700,600,300'
          rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/templatemo_style.css"/>">

    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="<c:url value="/js/myScript.js"/>"></script>
</head>
<body>
<div class="middle-content">
    <div class="container">
        <div class="row">
            <c:choose>
                <c:when test="${requestScope.bucketList == null ||
                requestScope.bucketList.size() == 0}">
                    <div class="h2">
                        Bucket is empty!
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="h2">
                        There are goods in the bucket:
                    </div>
                    <br>

                    <form action="<c:url value="/cleanBucket"/>" method="post">
                        <input type="submit" value="clean bucket">
                    </form>

                    <table class="table-bordered h3">
                        <tr>
                            <th>Number</th>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Firm</th>
                            <th>Price</th>
                            <th>Amount</th>
                            <th>Clear</th>
                        </tr>
                        <c:forEach var="rol_row" items="${requestScope.bucketList}">
                            <tr>
                                <td>${rol_row.id}</td>
                                <td>${rol_row.name}</td>
                                <td>${rol_row.category}</td>
                                <td>${rol_row.firm}</td>
                                <td>${rol_row.price} $</td>
                                <td>${rol_row.amount}</td>
                                <td>
                                    <c:set var="currentGoodID"
                                           scope="page" value="item${rol_row.id}"/>
                                    <button id="${currentGoodID}" value="${rol_row.id}"
                                            onclick="deleteGoodByID(${rol_row.id})"> -
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
            <br>
            <c:choose>
                <c:when test="${sessionScope.userLogin == '' || sessionScope.userLogin == null}">
                    You can not do order. Please, log in
                    <a href="<c:url value="/login"/>">here.</a>
                </c:when>
                <c:when test="${requestScope.bucketList.size() == 0}">
                    Nothing to buy!
                </c:when>
                <c:otherwise>
                    You can make your order.
                    <form action="<c:url value="/confirm"/>" method="post">
                        <br>
                        <label>
                            <h2>Order status:</h2>
                            <select name="orderStatus">
                                <option value="sent"> - sent</option>
                                <option value="prepared"> - prepared</option>
                                <option value="canceled"> - canceled</option>
                            </select>
                            <br>
                            <h2>Order description:</h2>
                            <input type="text" name="orderDescription">
                        </label>
                        <input type="submit" value=" Make order ">
                    </form>

                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<br>
<br>
<br>
<%@include file="/WEB-INF/tags/returnBack.tag" %>
</body>
</html>
