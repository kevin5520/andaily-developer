<%--
  User: Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="andaily,and daily,daily management">
    <meta name="author" content="andaily.com">
    <meta name="Keywords" content="andaily development,andaily developer,andaily scrum"/>

    <title><decorator:title default="Developer"/> | Andaily-Developer</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
    <link rel="shortcut icon" type="image/x-icon" href="${contextPath}/images/logo/favicon.ico"/>

    <%--<link href="http://cdnjs.bootcss.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">--%>
    <%--<link href="${contextPath}/css/developer.css" rel="stylesheet">--%>
    <%--<link href="http://cdnjs.bootcss.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap-responsive.min.css"--%>
    <%--rel="stylesheet">--%>

    <%--<script src="http://cdnjs.bootcss.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>--%>
    <%--<script src="http://cdnjs.bootcss.com/ajax/libs/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>--%>
    <%--<script src="${contextPath}/js/web/developer.js"></script>--%>


    <link href="${contextPath}/js/web/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/css/developer.css" rel="stylesheet">
    <link href="${contextPath}/js/web/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">

    <script src="${contextPath}/js/jquery/jquery-1.8.0.min.js"></script>
    <script src="${contextPath}/js/web/bootstrap/js/bootstrap.min.js"></script>
    <script src="${contextPath}/js/web/developer.js"></script>

    <decorator:head/>

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://cdnjs.bootcss.com/ajax/libs/html5shiv/3.6.2/html5shiv.js"></script>
    <![endif]-->

</head>
<body>
<div class="container">
    <div class="row">
        <div class="span2">
            <a href="http://a.andaily.com" target="_blank">
                <img src="${contextPath}/images/logo/andaily.png" class="img-rounded"/>
            </a>
        </div>
        <div class="span2">
            <span class="badge badge-inverse" style="position: relative;top: 75px;">developer</span>
            <ul class="nav nav-pills" style="position: relative;top: 70px;float: right;">
                <li class="dropdown">
                    <a class="dropdown-toggle btn-mini" data-toggle="dropdown"
                       href="#">${SPRING_SECURITY_CONTEXT.authentication.principal.nickName} <b class="caret"></b> </a>
                    <ul class="dropdown-menu">
                        <li><a href="${contextPath}/developer/my_profile"><i class="icon-user"></i> <spring:message
                                code="developer.main.page.my.profile" text="My profile"/></a></li>
                        <li><a href="${contextPath}/signout" style="color:#ee5f5b;"><i class="icon-off"></i>
                            <spring:message
                                    code="developer.main.page.sign.out" text="Sign out"/></a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="span3 offset5">
            <div style="position: relative;top: 65px;">
                <ul class="nav nav-pills" id="mainMenu">
                    <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_MEMBER,ROLE_PRODUCT_OWNER">
                        <li id="task_main_menu"><a href="${contextPath}/developer"><spring:message
                                code="developer.main.page.task" text="Task"/></a></li>
                        <li id="sprint_main_menu"><a href="${contextPath}/developer/sprint_overview"><spring:message
                                code="developer.main.page.sprint" text="Sprint"/></a></li>
                        <li id="backlog_main_menu"><a href="${contextPath}/developer/backlog_overview"><spring:message
                                code="developer.main.page.backlog" text="Backlog"/></a></li>
                        <li id="project_main_menu"><a href="${contextPath}/developer/project_/overview"><spring:message
                                code="developer.main.page.project" text="Project"/></a></li>
                    </sec:authorize>

                    <sec:authorize ifAnyGranted="ROLE_SUPER_MAN">
                        <li id="developer_main_menu"><a href="${contextPath}/developer/user_/overview"><spring:message
                                code="developer.main.page.user" text="User"/></a></li>
                        <li id="team_main_menu"><a href="${contextPath}/developer/team_/overview"><spring:message
                                code="developer.main.page.team" text="Team"/></a></li>
                        <li id="project_main_menu"><a href="${contextPath}/developer/project_/overview"><spring:message
                                code="developer.main.page.project" text="Project"/></a></li>
                        <li id="system_main_menu"><a href="${contextPath}/developer/system/config"><spring:message
                                code="developer.main.page.system" text="System"/></a></li>
                    </sec:authorize>

                </ul>
            </div>
        </div>
    </div>
    <decorator:body/>
    <div class="footer text-center">
        <hr/>
        <p>&copy; Andaily 2013-2016 Ver.${adVersion}</p>
    </div>
</div>

<!-- Modal -->
<div id="developerModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="developerModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="developerModalLabel"><spring:message code="developer.main.page.waiting" text="Waiting..."/></h3>
    </div>
    <div class="modal-body">
        <p><spring:message code="developer.main.page.waiting" text="Waiting..."/></p>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" id="confirmModalBtn"><spring:message code="developer.main.page.confirm"
                                                                             text="Confirm"/></button>
        <button class="btn" data-dismiss="modal" aria-hidden="true" id="closeModalBtn"><spring:message
                code="developer.main.page.close" text="Close"/></button>
    </div>
</div>

<%--JS i18n messages--%>
<div class="hidden">
    <span id="modalConfirmMessage"><spring:message code="developer.main.page.modal.confirm" text="Confirm"/></span>
    <span id="modalConfirmContentMessage"><spring:message code="developer.main.page.modal.confirm.content"
                                                          text="Are you sure ?"/></span>
</div>
</body>
</html>