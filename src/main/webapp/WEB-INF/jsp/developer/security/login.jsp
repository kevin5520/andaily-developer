<%--
  User: Shengzhao Li
  Date: 13-11-21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="andaily,and daily,daily management">
    <meta name="author" content="andaily.com">
    <meta name="Keywords" content="andaily development,andaily developer,andaily scrum"/>

    <title><spring:message code="login.page.title" text="Sign in"/> | Andaily-Developer</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
    <link rel="shortcut icon" type="image/x-icon" href="${contextPath}/images/logo/favicon.ico"/>

    <link href="${contextPath}/js/web/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        body {
            padding-top: 8%;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            max-width: 300px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }

        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }

    </style>
    <link href="${contextPath}/js/web/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">

    <script src="${contextPath}/js/jquery/jquery-1.8.0.min.js"></script>
    <script src="${contextPath}/js/web/bootstrap/js/bootstrap.min.js"></script>

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://cdnjs.bootcss.com/ajax/libs/html5shiv/3.6.2/html5shiv.js"></script>
    <![endif]-->

</head>
<body>

<spring:message code="login.page.form.email" text="Email" var="emailMessage"/>
<spring:message code="login.page.form.password" text="Password" var="passwordMessage"/>

<div>
    <div class="container">

        <form class="form-signin" action="${contextPath}/signin" method="post">
            <a href="http://a.andaily.com" target="_blank"><img src="${contextPath}/images/logo/andaily.png"
                                                                style="max-width: 140px;"/></a>
            <span class="badge badge-inverse" style="position: relative;top: 20px;">developer</span>

            <div style="margin-top: 10px;">
                <input type="text" name="j_username" class="input-block-level" title="${emailMessage}"
                       placeholder="${emailMessage}"
                       required value="${username}">
                <input type="password" name="j_password" class="input-block-level" title="${passwordMessage}"
                       placeholder="${passwordMessage}" required value="${password}">
                <label class="checkbox">
                    <input type="checkbox" name="_spring_security_remember_me"> <spring:message
                        code="login.page.form.remember.me" text="Remember me"/>
                </label>
                <button class="btn btn-large btn-primary" type="submit"><spring:message
                        code="login.page.form.sign.in" text="Sign in"/></button>

                <div style="float: right;">
                    <c:if test="${param.error == 1}">
                        <span class="label label-warning"><spring:message code="login.page.error.failed"
                                                                          text="Sign in failed, please check"/>.</span>
                    </c:if>
                    <c:if test="${param.error == 2}">
                        <span class="label label-warning"><spring:message code="login.page.error.access.denied"
                                                                          text="Access denied, pleas sign in"/>.</span>
                    </c:if>
                </div>

            </div>
        </form>
        <p class="text-center muted">&copy; Andaily 2013-2016 Ver.${adVersion}</p>
    </div>
    <!-- /container -->
</div>
</body>
</html>