<%--
  User: Shengzhao Li
  Date: 13-12-17
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="team.overview.page.title" text="Team"/></title>
</head>
<body>
<div class="row">
    <hr/>
    <div class="span8">
        <form action="" id="teamForm" class="form-inline">
            <spring:message code="team.overview.page.form.team.name" text="Team name" var="teamNameMessage"/>
            <input type="text" name="name" value="${teamOverviewDto.name}" title="${teamNameMessage}"
                   placeholder="${teamNameMessage}" class="input-medium"/>
                   
                   
            <spring:message text="Create Time" var="createDateMessage"/>
            <input type="text" name="createDate"  value="${teamOverviewDto.createDate}" title="${createDateMessage}"
                   placeholder="${createDateMessage}" class="input-medium"/>
                   
            &nbsp;
            <label class="checkbox">
                <input type="checkbox" name="archived" ${teamOverviewDto.archived?'checked':''}/> <spring:message code="team.overview.page.form.archived" text="Archived"/>
            </label>
            &nbsp;
            <button class="btn" type="submit"><i class="icon-search"></i> <spring:message code="team.overview.page.form.search" text="Search"/></button>
        </form>
    </div>
    <div class="span2">
        &nbsp;
    </div>
    <div class="span2">
        <a class="btn btn-success btn-small" href="team_form/create"><i
                class="icon-plus icon-white"></i> <spring:message code="team.overview.page.add.team" text="Add team"/></a>
    </div>

</div>
<div class="row">
    <div class="span12">
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-ok-sign icon-white"></i>
                <span id="saveTeamSuccess" class="hide"><spring:message code="team.overview.page.alert.save.success" text="Save team successful!"/></span>
                <span id="archiveTeamSuccess" class="hide"><spring:message code="team.overview.page.alert.archive.success" text="Archive team successful!"/></span>
            </div>
        </div>

    </div>
</div>

<div class="row">
    <div class="span9">
        <spring:message code="team.overview.page.table.name" text="Name" var="nameMessage"/>
        <spring:message code="team.overview.page.table.create.date" text="Create date" var="createDateMessage"/>
        <spring:message code="team.overview.page.table.projects" text="Projects" var="projectsMessage"/>
        <spring:message code="team.overview.page.table.members" text="Members" var="membersMessage"/>

        <displaytag:table list="${teamOverviewDto}" class="table table-hover table-striped" id="team"
                          form="teamForm">
            <displaytag:column title="${nameMessage}">
                <c:if test="${not team.archived}">
                    <div class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                ${team.name}
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                            <li><a href="../project_/overview?teamGuid=${team.guid}"><i
                                    class="icon-align-justify"></i> <spring:message code="team.overview.page.action.projects" text="Projects"/>(${team.projectSize})</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="team_form/${team.guid}"><i
                                    class="icon-pencil"></i> <spring:message code="team.overview.page.action.edit" text="Edit"/></a>
                            </li>
                            <li>
                                <spring:message code="team.overview.page.action.archive.content" text="Are you sure archive the team? It is not reversible." var="archiveContentMessage"/>
                                <a tabindex="-1" class="confirm"
                                   confirm-content="${archiveContentMessage}"
                                   href="archive/${team.guid}"><i
                                    class="icon-remove"></i> <spring:message code="team.overview.page.action.archive" text="Archive"/></a>
                            </li>

                        </ul>
                    </div>
                </c:if>
                <c:if test="${team.archived}">${team.name}</c:if>
            </displaytag:column>
            <displaytag:column title="${createDateMessage}" property="createDate"/>
            <displaytag:column title="${projectsMessage}" property="projectsText"/>
            <displaytag:column title="${membersMessage}" property="membersText"/>
            <displaytag:column title="">
                <a href="javascript:void(0);" data-content="${team.htmlTip}" teamName="${team.name}"
                   class="teamDetails"><i
                        class="icon-question-sign"></i></a>
            </displaytag:column>
        </displaytag:table>
    </div>
    <div class="span3">
        <div class="well">
            <div>
                <h5><a href="javascript:void(0);"><spring:message code="team.overview.page.action.statistics" text="Statistics"/></a></h5>
            </div>

            <div>
                <i class="icon-certificate"></i> <spring:message code="team.overview.page.action.statistics.teams" text="Total: {0} team(s). " arguments="${teamOverviewDto.totalSize}"/>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        new TeamOverview('${param.alert}');
    });
</script>

</body>
</html>