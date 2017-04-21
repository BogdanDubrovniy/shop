<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enter to site</title>
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
            <div class="h2">
                Please, enter you login and password:
            </div>
            <form action="<c:url value="/loginServlet"/>" method="post">
                <table border="1">
                    <tr>
                        <th>Enter login:</th>
                        <td><label>
                            <input type="text" name="inputLogin">
                        </label></td>
                    </tr>
                    <tr>
                        <th>Enter password:</th>
                        <td><label>
                            <input type="password" name="inputPassword">
                        </label></td>
                    </tr>

                    <tr>
                        <th><input type="submit" value="log in"></th>
                    </tr>
                </table>

                <br>
                <br>

            </form>
        </div>
    </div>
</div>

</body>
</html>
