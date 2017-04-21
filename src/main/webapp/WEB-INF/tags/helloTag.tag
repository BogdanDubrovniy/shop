<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<c:choose>
    <c:when test="${sessionScope.userLogin == '' || sessionScope.userLogin == null}">
        You are not logged, please, login:
        <a href="<c:url value="/login"/>">here.</a>
    </c:when>
    <c:otherwise>
        Hello, <c:out value="${sessionScope.userLogin}"/>
        You can <a href="<c:url value="/personalPage"/>">
        go to personal page</a> or
        <form action="<c:url value="/logout"/>">
            <input type="submit" value=" log out">
        </form>
    </c:otherwise>
</c:choose>