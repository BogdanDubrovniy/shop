<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Goods list</title>

    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,800,700,600,300'
          rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/templatemo_style.css"/>">

</head>
<body>

<div class="site-header">
    <div class="container">
        <div class="main-header">
            <div class="row">
                <div class="col-md-8 col-sm-6 col-xs-2">
                    <div class="main-menu">
                        <ul class="visible-lg visible-md">
                            <li><a href="<c:url value="/login.jsp"/>">Back</a></li>
                            <li class="active"><a href="<c:url value="/login.jsp"/>">Home</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 visible-sm visible-xs">
                <div class="menu-responsive">
                    <ul>
                        <li><a href="<c:url value="/login.jsp"/>">Back</a></li>
                        <li class="active"><a href="<c:url value="/login.jsp"/>">Home</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<br>
<br>
<br>
<div class="middle-content">
    <div class="container">
        <div class="row">
            <fieldset>
                <div class="h3">
                    <a href="<c:url value="/setUpFilter"/>">set up the filter</a>
                </div>
            </fieldset>
            <br>
            <c:choose>
                <c:when test="${requestScope.goodList == null || requestScope.goodList.size() == 0}">
                    <div class="h2">
                        There is no goods!
                    </div>
                </c:when>
                <c:otherwise>
                    <fieldset>
                        <%@include file="/WEB-INF/tags/sortGoodsTag.tag" %>
                    </fieldset>
                    <br>
                    <fieldset>
                        <h4>
                            Set the max values per page:
                        </h4>
                        <form action="<c:url value="/setMaxNumberPerPage"/>">
                            <input type="number" name="recordsPerPage" title="recordsPerPage">
                            <button type="submit" class="btn-success">
                                set up
                            </button>
                        </form>
                    </fieldset>
                    <br>
                    <fieldset>
                        <div class="h2">
                            There is the goods list from this site:
                        </div>
                        <table class="table-bordered h3">
                            <tr>
                                <th>Number</th>
                                <th>Name</th>
                                <th>Category</th>
                                <th>Firm</th>
                                <th>Price</th>
                                <th>Photo</th>
                                <th>Amount</th>
                                <th>Add to Bucket</th>
                            </tr>

                            <c:forEach var="rol_row" items="${requestScope.goodList}">
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

                                    <form action="<c:url value="/addGoodToBucket"/>" method="post">
                                        <input type="hidden" name="goodNumber" value="${rol_row.id}">
                                        <td>
                                            <input type="number" name="goodAmount" title="goodAmount">
                                        </td>
                                        <td>
                                            <button type="submit"
                                                    class="btn-success"> add to bucket
                                            </button>
                                        </td>
                                    </form>
                                </tr>
                            </c:forEach>
                        </table>
                        <br>
                        <c:choose>
                            <c:when test="${requestScope.numberOfPages > 1}">
                                <div class="h2 center-block">
                                    <table border="1" cellpadding="5" cellspacing="5">
                                        <tr>
                                            <c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
                                                <td><a href="<c:url value="/goodList?page=${i}"/>">${i}</a></td>
                                            </c:forEach>
                                        </tr>
                                    </table>
                                </div>
                            </c:when>
                        </c:choose>
                    </fieldset>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

</body>
</html>
