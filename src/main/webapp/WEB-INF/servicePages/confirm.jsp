<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm</title>
</head>
<body>
<h2>Enter your information and confirm your order:</h2>
<br>
<form action="<c:url value="/makeOrder"/>" method="post">
    <fieldset>
        <legend>Enter information for order:</legend>
        <br>
        Delivery method:
        <select name="deliveryMethod" title="deliveryMethod">
            <option value="courier"> - courier</option>
            <option value="NewPost"> - New Post</option>
        </select>
        <br>
        Your Address:
        <br>
        <input type="text" name="address">
        <br>
        <label for="cardId" class="block">Your card:
            <small>(XXXX-XXXX-XXXX-XXXX)</small>
            :</label>
        <input type="text" name="cardId" id="cardId"
               value="${requestScope.userSurname}"/>
        <br>
        <input type="submit" value="confirm">
    </fieldset>
    <br>
    Total good amount: <c:out value="${requestScope.bucketGoodAmount}"/>
    <br>
    Total price: <c:out value="${requestScope.bucketTotalPrice}"/>
    <br>
</form>
</body>
</html>
