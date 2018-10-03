
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
<head>
    <title><spring:message code="my.team.page.title" text="My Team"/></title>
</head>
<body>
<div class="row">
    <hr/>
    <div class="span3">
        <ul class="nav nav-tabs nav-stacked">
            <li>
                <a href="../my_profile">
                    <i class="icon-chevron-right pull-right"></i> <spring:message code="my.profile.page.menu.my.info" text="My-Info"/>
                </a>
            </li>
            <li>
                <a href="change_password">
                    <i class="icon-chevron-right pull-right"></i> <spring:message code="my.profile.page.menu.change.password" text="Change Password"/>
                </a>
            </li>
            <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_MEMBER">
                <li>
                    <a href="javascript:void(0)">
                        <strong>
                            <i class="icon-chevron-right pull-right"></i> <spring:message code="my.profile.page.menu.my.team" text="My Team"/>
                        </strong>
                    </a>
                </li>
            </sec:authorize>
        </ul>
    </div>
    <div class="span9">
        <c:if test="${not myTeamDto.referTeam}">
            <p class="text-error">
                <spring:message code="my.team.page.not.added" text="Not added any team yet"/>.
            </p>
        </c:if>
        <c:if test="${myTeamDto.referTeam}">
            <h2 class="text-error">${myTeamDto.teamName}</h2>
            <small class="muted">${myTeamDto.teamDescription}</small>
            <dl>
                <dt class="text-info"><i class="icon-user"></i> <spring:message code="my.team.page.members" text="Members"/></dt>
                <dd>
                    <ul>
                        <c:forEach items="${myTeamDto.members}" var="m">
                            <li>
                                <strong style="width: 150px;display: inline-block;">${m.showName}
                                    <span class="text-success">${m.scrumTerm.master?'(Master)':''}</span>
                                </strong>
                                <span style="width: 100px;display: inline-block;"
                                      title="Cell phone">${m.cellPhone}</span>
                                <span>${m.email}</span>
                            </li>
                        </c:forEach>
                    </ul>
                </dd>
            </dl>
            <dl>
                <dt class="text-info"><i class="icon-tasks"></i> <spring:message code="my.team.page.projects" text="Projects"/></dt>
                <dd>
                    <ul>
                        <c:forEach items="${myTeamDto.projects}" var="p">
                            <li><a href="../project_/overview?name=${p.name}">${p.name} (${p.code})</a>

                                <div class="muted" title="Project description">${p.description}</div>
                            </li>
                        </c:forEach>
                    </ul>
                </dd>
            </dl>
        </c:if>
    </div>
</div>

</body>
</html>