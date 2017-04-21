<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

Are you human(Enter the captcha)?
<br>
<input type="text" name="captchaCode">
<br>
<c:set var="one" scope="page" value="${requestScope.captchaCode}"/>
<br>
<input type="hidden" name="captchaCode" value="${one}">
<img src="<c:url value="/CaptchaServlet"/>">