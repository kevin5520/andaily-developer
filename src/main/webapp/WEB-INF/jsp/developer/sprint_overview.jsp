<%--
  User: Shengzhao Li
  Date: 13-8-6
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="sprint.overview.page.title" text="Sprint"/></title>
</head>
<body>
<div class="row">
    <hr/>
    <div class="span8">
        <form action="" id="sprintForm" class="form-inline">
            <c:if test="${sprintOverviewDto.specifyProject}" var="specifyProject">
                <spring:message code="sprint.overview.page.form.project.name" text="Project name"
                                var="projectNameMessage"/>
                <span class="label" title="${projectNameMessage}">${sprintOverviewDto.list[0].projectDto.name}</span>
            </c:if>
            <input type="hidden" name="projectGuid" value="${sprintOverviewDto.projectGuid}"/>
            <select name="status" id="sprintStatusSelect">
                <option value=""><spring:message code="sprint.overview.page.form.all.status"
                                                 text="All status"/></option>
                <c:forEach items="${sprintOverviewDto.availableStatuses}" var="s">
                    <option value="${s}" ${s eq sprintOverviewDto.status?'selected':''}>${s.label}</option>
                </c:forEach>
            </select>
            <%--<button class="btn btn-small" type="submit"><i class="icon-search"></i> Search</button>--%>
        </form>
    </div>
    <div class="span2">
        <span class="muted">
            <spring:message code="sprint.overview.page.size.sprints" text="Size of sprints"/>: <strong>${sprintOverviewDto.totalSize}</strong>
        </span>
    </div>
    <div class="span2">
        <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_PRODUCT_OWNER">
            <spring:message code="sprint.overview.page.choose.a.project" text="Choose a project" var="chooseProjectMessage"/>
            <a class="btn btn-success btn-small" id="createSprint"
               load-url="sprint_action/load_sprint_projects"
               modal-title="${chooseProjectMessage}" projectGuid="${sprintOverviewDto.projectGuid}"
               href="sprint_form/create"><i
                    class="icon-plus icon-white"></i> <spring:message code="sprint.overview.page.create.sprint" text="Create sprint"/></a>
        </sec:authorize>
    </div>

</div>
<div class="row">
    <div class="span12">
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-ok-sign icon-white"></i>
                <span id="sprintFormSuccess" class="hide"><spring:message code="sprint.overview.page.alert.add.sprint.success" text="Add/Edit the sprint successful!"/></span>
                <span id="archiveSprintSuccess" class="hide"><spring:message code="sprint.overview.page.alert.archive.sprint.success" text="Archive the sprint successful!"/></span>
                <span id="setDefaultSprintSuccess" class="hide"><spring:message code="sprint.overview.page.alert.set.default.sprint.success" text="Set default sprint successful!"/></span>
                <span id="cancelDefaultSprintSuccess" class="hide"><spring:message code="sprint.overview.page.alert.cancel.sprint.success" text="Cancel default sprint successful!"/></span>
            </div>
        </div>
        <div class="alert alert-info hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-info-sign icon-white"></i>
                <span id="finishSprintInfo" class="hide"><spring:message code="sprint.overview.page.alert.sprint.finished" text="The sprint has been finished."/></span>
            </div>
        </div>
        <div class="alert alert-error hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-warning-sign icon-white"></i>
                <%--<span id="alertErrorInfo"></span>--%>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="span9">
        <spring:message code="sprint.overview.page.table.name" text="Name" var="nameMessage"/>
        <spring:message code="sprint.overview.page.table.progress" text="Progress" var="progressMessage"/>
        <spring:message code="sprint.overview.page.table.progress.finished" text="finished" var="progressFinishedMessage"/>
        <spring:message code="sprint.overview.page.table.estimate.used" text="Estimate / Used" var="estimateUsedMessage"/>
        <spring:message code="sprint.overview.page.table.project" text="Project" var="projectMessage"/>

        <spring:message code="sprint.overview.page.table.team" text="Team" var="teamMessage"/>
        <spring:message code="sprint.overview.page.table.start.deadline" text="Start / Deadline" var="startDeadlineMessage"/>


        <displaytag:table list="${sprintOverviewDto}" class="table table-hover" id="sprint"
                          form="sprintForm">
            <displaytag:column title="${nameMessage}" headerClass="width25" class="${sprint.status}">
                <div class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <c:if test="${sprint.defaultSprint}">
                            <strong>${sprint.name}</strong>
                        </c:if>
                        <c:if test="${not sprint.defaultSprint}">
                            ${sprint.name}
                        </c:if>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                        <custom:sprint_overview_menu_actions currentSprint="${sprint}"/>
                    </ul>
                </div>
            </displaytag:column>
            <displaytag:column title="${progressMessage}" headerClass="width25">
                <c:set var="progressVal"
                       value="${empty sprint.usedTimeAsPercent?'0%':sprint.usedTimeAsPercent}"/>
                <div class="progress progress-striped" title="${progressVal} ${progressFinishedMessage}" style="margin-bottom: 5px;">
                    <div class="bar" style="width: ${progressVal};"></div>
                </div>
            </displaytag:column>
            <c:if test="${specifyProject}">
                <displaytag:column title="${estimateUsedMessage}">
                    ${sprint.estimateTimesAsHour} / ${sprint.usedTimesAsHour}${sprint.timeDifferenceAsHtml}
                </displaytag:column>
            </c:if>
            <c:if test="${not specifyProject}">
                <displaytag:column title="${projectMessage}">
                    <a href="${contextPath}/developer/sprint_overview?projectGuid=${sprint.projectDto.guid}">${sprint.projectDto.name}</a>
                </displaytag:column>
            </c:if>
            <displaytag:column title="${teamMessage}" property="executeTeamDto.name"/>
            <displaytag:column title="${startDeadlineMessage}">
                ${sprint.startDate} / <custom:show_sprint_deadline sprint="${sprint}"/>
            </displaytag:column>
            <displaytag:column title="&nbsp;">
                <a href="#${sprint.guid}Modal" class="sprintDetails" data-toggle="modal"><i class="icon-info-sign"></i></a>
                <%-- the sprint modal--%>
                <div id="${sprint.guid}Modal" class="modal hide fade" tabindex="-1" role="dialog"
                     aria-labelledby="${sprin.guid}ModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="${sprint.guid}ModalLabel">${sprint.name}</h3>
                    </div>
                    <div class="modal-body">
                            ${sprint.htmlTip}
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="sprint.overview.page.table.close" text="Close"/></button>
                    </div>
                </div>

                <c:if test="${sprint.haveBacklogs}">
                    <spring:message code="sprint.overview.page.table.show.backlogs" text="Show backlogs" var="showBacklogsMessage"/>
                    <a href="javascript:void(0)" title="${showBacklogsMessage}"
                       load-url="${contextPath}/developer/sprint_action/load_backlogs/${sprint.guid}"
                       class="sprintBacklogDetails"><i class="icon-tags"></i></a>
                </c:if>
            </displaytag:column>
        </displaytag:table>
    </div>
    <div class="span3">
        <div class="well">
            <div>
                <h5><a href="javascript:void(0);"><spring:message code="sprint.overview.page.statistics" text="Statistics"/></a></h5>
            </div>

            <div>
                <table class="table">
                    <tr class="info">
                        <%--Pending--%>
                        <td style="width: 80%;">${sprintOverviewDto.availableStatuses[1].label}</td>
                        <td><strong>${sprintOverviewDto.pendingSprints}</strong></td>
                    </tr>
                    <tr class="success">
                        <%--Finished--%>
                        <td>${sprintOverviewDto.availableStatuses[2].label}</td>
                        <td><strong>${sprintOverviewDto.finishedSprints}</strong></td>
                    </tr>
                    <tr style="background-color: white;">
                        <%--Created--%>
                        <td>${sprintOverviewDto.availableStatuses[0].label}</td>
                        <td><strong>${sprintOverviewDto.createdSprints}</strong></td>
                    </tr>
                </table>
            </div>

        </div>
        <c:if test="${not empty sprintOverviewDto.projectDto}">
            <div class="well">
                <div>
                    <h5>
                        <a href="javascript:void(0);">${sprintOverviewDto.projectDto.name}(${sprintOverviewDto.projectDto.code})</a>
                    </h5>
                </div>
                <div>
                    <table class="table table-striped">
                        <tr>
                            <td>
                                <spring:message code="sprint.overview.page.project.finish.date" text="Finish date"/>: <span
                                    class="text-error">${sprintOverviewDto.projectDto.finishDateAsText}</span>
                            </td>
                        </tr>
                        <tr style="background-color: white;">
                            <td>
                                <spring:message code="sprint.overview.page.project.dev.teams" text="Development teams"/>: <span
                                    class="text-info">${sprintOverviewDto.projectDto.teamsAsText}</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <spring:message code="sprint.overview.page.project.product.owners" text="Product owners"/>: <span
                                    class="text-info">${sprintOverviewDto.projectDto.productOwnersAsText}</span>
                            </td>
                        </tr>
                        <tr style="background-color: white;">
                            <td>
                                <div class="text-info">
                                        ${sprintOverviewDto.projectDto.description}
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </c:if>
    </div>
</div>

<%--JS i18n messages--%>
<div class="hidden">
   <span id="sprintBacklogsMessage"><spring:message code="sprint.overview.page.js.sprint.backlogs" text="Sprint backlogs"/></span>
   <span id="startSprintMessage"><spring:message code="sprint.overview.page.js.start.sprint" text="Start sprint"/></span>
   <span id="finishSprintMessage"><spring:message code="sprint.overview.page.js.finish.sprint" text="Finish sprint"/></span>
</div>
<script type="text/javascript">
    $(function () {
        new SprintOverview("${param.alert}");
    });
</script>
</body>
</html>