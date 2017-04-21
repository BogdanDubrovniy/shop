<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Shop Market</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <meta name="author" content="templatemo">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,800,700,600,300'
          rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/templatemo_misc.css">
    <link rel="stylesheet" href="css/templatemo_style.css">
</head>

<body>
<div class="site-header">
    <div class="container">
        <div class="main-header">
            <div class="row">
                <div class="col-md-4 col-sm-6 col-xs-10">
                    <div class="logo">
                        <div class="blue">
                            <%@include file="WEB-INF/tags/helloTag.tag" %>
                        </div>
                    </div>
                </div>
                <div class="col-md-8 col-sm-6 col-xs-2">
                    <div class="main-menu">
                        <ul class="visible-lg visible-md">
                            <li class="active"><a href="login.jsp">Home</a></li>
                            <li><a href="login.jsp">Services</a></li>
                            <li><a href="<c:url value="/list"/>">Goods List</a></li>
                            <li><a href="login.jsp">About Us</a></li>
                            <li><a href="login.jsp">Contact</a></li>
                            <li><a href="<c:url value="/registrationPage"/>">Registration</a></li>
                        </ul>
                        <a href="#" class="toggle-menu visible-sm visible-xs">
                            <i class="fa fa-bars"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 visible-sm visible-xs">
                <div class="menu-responsive">
                    <ul>
                        <li class="active"><a href="login.jsp">Home</a></li>
                        <li><a href="login.jsp">Services</a></li>
                        <li><a href="<c:url value="/list"/>">Goods List</a></li>
                        <li><a href="login.jsp">About Us</a></li>
                        <li><a href="login.jsp">Contact</a></li>
                        <li><a href="<c:url value="/registrationPage"/>">Registration</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="overlay">
    <img src="images/templatemo_slide_3.jpg">
</div>
</body>
</html>
