<%--
  User: Shengzhao Li
  Date: 13-8-6
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title><spring:message code="developer.home.page.title" text="Task"/></title>
    <link href="${contextPath}/js/web/bootstrap/datepicker/datepicker.css" rel="stylesheet"/>
    <script src="${contextPath}/js/web/bootstrap/datepicker/bootstrap-datepicker.js"></script>

    <script src="${contextPath}/js/web/ckeditor/ckeditor.js"></script>
    <script src="${contextPath}/js/jquery/Chart/Chart.min.js"></script>
</head>
<body>
<hr/>
<c:set var="currSprint" value="${developerOverviewDto.currentSprint}"/>
<div class="row">
    <div class="span3">
        <form action="" id="developerForm">
            <select name="currentSprint.guid" id="sprintSelect">
                <c:if test="${empty developerOverviewDto.sprintDtos}">
                    <option value=""><spring:message code="developer.home.page.form.no.sprints" text="--- No Sprints ---"/></option>
                </c:if>
                <c:forEach items="${developerOverviewDto.sprintDtosGroupResults}" var="group" varStatus="status">
                    <optgroup label="${group.key.label}">
                        <c:forEach items="${group.results}" var="sprint">
                            <option value="${sprint.guid}" ${sprint.guid eq currSprint.guid?'selected':''}>${sprint.name}</option>
                        </c:forEach>
                    </optgroup>
                </c:forEach>
            </select>
            <a href="#currentSprintModal" id="sprintInfo" data-toggle="modal"><i class="icon-info-sign"></i></a>
            <input type="hidden" name="status" value="${developerOverviewDto.status}" id="taskStatus"/>
            <input type="hidden" name="onlyShowMyTasks" value="${developerOverviewDto.onlyShowMyTasks}"/>
        </form>
    </div>
    <div class="span3">
        <spring:message code="developer.home.page.form.curr.sprint.finished" text="finished" var="finishedMessage"/>
        <c:set var="progressVal" value="${empty currSprint.usedTimeAsPercent?'0%':currSprint.usedTimeAsPercent}"/>
        <div class="progress progress-striped" title="${progressVal} ${finishedMessage}"
             style="position: relative;top:5px;">
            <div class="bar" style="width: ${progressVal};"></div>
        </div>
    </div>
    <div class="span1">
        <spring:message code="developer.home.page.form.curr.sprint.status" text="Current sprint status" var="currentSprintStatusMessage"/>
        <span class="label" title="${currentSprintStatusMessage}" style="margin-top: 5px;">${currSprint.status.label}</span>
    </div>
    <div class="span2">
        <form action="" id="developerSearchForm" class="form-inline">
            <spring:message code="developer.home.page.form.search.by.number" text="Search task by number" var="searchByNumberMessage"/>
            <input type="text" name="number" class="input-medium"
                   placeholder="${searchByNumberMessage}" id="searchInput" value="${developerOverviewDto.number}"/>
            <input type="hidden" name="status" value="${developerOverviewDto.status}"/>
            <input id="searchSprintGuid" type="hidden" name="currentSprint.guid" value="${currSprint.guid}"/>
            <input type="hidden" name="onlyShowMyTasks" value="${developerOverviewDto.onlyShowMyTasks}"/>
        </form>
    </div>
    <div class="span3">
        <c:if test="${not empty currSprint and not empty currSprint.guid}">
            <c:set value="${currSprint.status.finished}" var="finished"/>
            <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_MEMBER">
                <c:if test="${not finished}">
                    <a class="btn btn-success btn-small"
                       href="${contextPath}/developer/task_form/create?sprintGuid=${currSprint.guid}"><i
                            class="icon-plus icon-white"></i>
                        <spring:message code="developer.home.page.create.task" text="Create task"/></a>
                </c:if>
            </sec:authorize>

            <div class="btn-group">
                <a class="btn btn-small dropdown-toggle" data-toggle="dropdown" href="#">
                    <spring:message code="developer.home.page.action" text="Action"/> <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <custom:sprint_menu_actions currentSprint="${currSprint}"/>
                </ul>
            </div>
        </c:if>
    </div>
</div>
<div class="row">
    <div class="span12">
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-ok-sign icon-white"></i>
                <span id="archiveSprintSuccess" class="hide"><spring:message code="developer.home.page.alert.archive.sprint.success" text="Archive the sprint successful!"/></span>
                <span id="startSprintSuccess" class="hide"><spring:message code="developer.home.page.alert.start.sprint.success" text="Start the sprint successful!"/></span>
                <span id="startTaskSuccess" class="hide"><spring:message code="developer.home.page.alert.start.task.success" text="Start the task successful!"/></span>
                <span id="archiveTaskSuccess" class="hide"><spring:message code="developer.home.page.alert.archive.task.success" text="Archive the task successful!"/></span>
                <span id="cancelTaskSuccess" class="hide"><spring:message code="developer.home.page.alert.cancel.task.success" text="Cancel the task successful!"/></span>
                <span id="restoreTaskSuccess" class="hide"><spring:message code="developer.home.page.alert.restore.task.success" text="Restore the task to Created successful!"/></span>
                <span id="revertTaskSuccess" class="hide"><spring:message code="developer.home.page.alert.revert.task.success" text="Revert the task successful!"/></span>
                <span id="finishTaskSuccess" class="hide"><spring:message code="developer.home.page.alert.finish.task.success" text="Finish the task successful!"/></span>
                <span id="addMeetingSuccess" class="hide"><spring:message code="developer.home.page.alert.add.meeting.success" text="Add sprint meeting successful!"/></span>
                <span id="assignTaskSuccess" class="hide"><spring:message code="developer.home.page.alert.assign.task.success" text="Assign the task successful!"/></span>

            </div>
        </div>
        <div class="alert alert-info hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-info-sign icon-white"></i>
                <span id="taskFormSuccess" class="hide"><spring:message code="developer.home.page.alert.save.task.success" text="Save the task successful!"/></span>
                <span id="finishSprintInfo" class="hide"><spring:message code="developer.home.page.alert.sprint.finished" text="The sprint has been finished."/></span>
                <span id="moveTaskInfo" class="hide"><spring:message code="developer.home.page.alert.move.task.finished" text="Move task has been finished."/></span>
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
        <div class="tabbable">
            <ul class="nav nav-tabs">
                <li class="${developerOverviewDto.status.pending?'active':''}">
                    <a href="#pending" class="taskTab" task-status="PENDING">${developerOverviewDto.statuses[1].label}
                        (${developerOverviewDto.totalPendingTasks})</a>
                </li>
                <li class="${developerOverviewDto.status.created?'active':''}">
                    <a href="#created" class="taskTab" task-status="CREATED">${developerOverviewDto.statuses[0].label}
                        (${developerOverviewDto.totalCreatedTasks})</a>
                </li>
                <li class="${developerOverviewDto.status.finished?'active':''}">
                    <a href="#finished" class="taskTab" task-status="FINISHED">${developerOverviewDto.statuses[3].label}
                        (${developerOverviewDto.totalFinishedTasks})</a>
                </li>
                <li class="${developerOverviewDto.status.canceled?'active':''}">
                    <a href="#canceled" class="taskTab" task-status="CANCELED">${developerOverviewDto.statuses[2].label}
                        (${developerOverviewDto.totalCanceledTasks})</a>
                </li>
                <li class="${empty developerOverviewDto.status?'active':''}">
                    <a href="#alltasks" class="taskTab" task-status=""><spring:message code="developer.home.page.all.tasks" text="All Tasks"/>
                        (${developerOverviewDto.totalTasks})</a>
                </li>
            </ul>
            <div class="tab-content">
                <c:choose>
                    <c:when test="${developerOverviewDto.status.pending}">
                        <div class="tab-pane active" id="pending">
                            <custom:developer_pending_tasks developerOverviewDto="${developerOverviewDto}"/>
                        </div>
                    </c:when>
                    <c:when test="${developerOverviewDto.status.created}">
                        <div class="tab-pane active" id="created">
                            <custom:developer_created_tasks developerOverviewDto="${developerOverviewDto}"/>
                        </div>
                    </c:when>
                    <c:when test="${developerOverviewDto.status.finished}">
                        <div class="tab-pane active" id="finished">
                            <custom:developer_finished_tasks developerOverviewDto="${developerOverviewDto}"/>
                        </div>
                    </c:when>
                    <c:when test="${developerOverviewDto.status.canceled}">
                        <div class="tab-pane active" id="canceled">
                            <custom:developer_canceled_tasks developerOverviewDto="${developerOverviewDto}"/>
                        </div>
                    </c:when>
                    <c:when test="${empty developerOverviewDto.status}">
                        <div class="tab-pane active" id="alltasks">
                            <custom:developer_all_tasks developerOverviewDto="${developerOverviewDto}"/>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="span3">
        <div class="well">
            <div>
                <spring:message code="developer.home.page.burn.down.chart.details" text="Burn down chart details" var="chartDetailsMessage"/>
                <a href="javascript:void(0);" id="burnDownDetailsId"
                   load-url="${contextPath}/developer/sprint_action/burndown/details/${currSprint.guid}"
                   class="pull-right"
                   title="${chartDetailsMessage}"><i class="icon-info-sign"></i></a>
                <h5><a href="javascript:void(0);"><spring:message code="developer.home.page.burn.down.chart" text="Burn down chart"/></a></h5>
            </div>

            <div>
                <canvas id="burnDownChart"></canvas>
            </div>

        </div>
        <%-- empty sprints forbid add meeting--%>
        <c:if test="${not empty developerOverviewDto.sprintDtos}">
            <div class="well">
                <div>
                    <%--add meeting--%>
                        <spring:message code="developer.home.page.add.meeting" text="Add meeting" var="addMeetingMessage"/>
                    <a href="javascript:void(0);" class="pull-right" title="${addMeetingMessage}" id="meetingFormId"
                       load-url="${contextPath}/developer/sprint/meeting_form/create?sprintGuid=${currSprint.guid}"><i
                            class="icon-plus-sign"></i></a>
                    <h5>
                        <a href="${contextPath}/developer/sprint_meeting/overview?sprintGuid=${currSprint.guid}"><spring:message code="developer.home.page.meetings" text="Meetings"/></a>
                    </h5>
                </div>
                <div>
                    <ul class="unstyled">
                        <c:if test="${empty developerOverviewDto.latestMeetings}" var="emptyMeetings">
                            <li><spring:message code="developer.home.page.not.added.meetings" text="Not added meetings yet."/></li>
                        </c:if>
                        <c:forEach items="${developerOverviewDto.latestMeetings}" var="meeting" varStatus="s">
                            <li>
                                <p>
                                    <i class="icon-time icon-white"></i>
                                    <a href="javascript:void(0);"
                                       load-url="${contextPath}/developer/sprint_meeting/details/${meeting.guid}"
                                       class="muted taskMeetingDetails">
                                            ${meeting.meetingDate} (${meeting.type.label})
                                    </a>
                                </p>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </c:if>
        <c:if test="${empty developerOverviewDto.status}">
            <div class="well">
                <div>
                    <h5><a href="javascript:void(0);"><spring:message code="developer.home.page.statistics" text="Statistics"/></a></h5>
                </div>

                <div>
                    <table class="table">
                        <tr class="info">
                            <td style="width: 80%;">${developerOverviewDto.statuses[1].label}</td>
                            <td><strong>${developerOverviewDto.totalPendingTasks}</strong></td>
                        </tr>
                        <tr class="success">
                            <td>${developerOverviewDto.statuses[3].label}</td>
                            <td><strong>${developerOverviewDto.totalFinishedTasks}</strong></td>
                        </tr>
                        <tr class="warning">
                            <td>${developerOverviewDto.statuses[2].label}</td>
                            <td><strong>${developerOverviewDto.totalCanceledTasks}</strong></td>
                        </tr>
                        <tr style="background-color: white;">
                            <td>${developerOverviewDto.statuses[0].label}</td>
                            <td><strong>${developerOverviewDto.totalCreatedTasks}</strong></td>
                        </tr>
                    </table>
                </div>
            </div>
        </c:if>
    </div>
</div>
<%--more row add in here--%>


<%-- statics modals--%>
<div id="currentSprintModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="sprintModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="sprintModalLabel">${currSprint.name}</h3>
    </div>
    <div class="modal-body">
        ${currSprint.htmlTip}
    </div>
    <div class="modal-footer">
        <button class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="developer.home.page.close" text="Close"/></button>
    </div>
</div>

<%--JS i18n messages--%>
<div class="hidden">
   <span id="meetingDetailsMessage"><spring:message code="developer.home.page.js.meeting.details" text="Sprint meeting details"/></span>
   <span id="moveTaskMessage"><spring:message code="developer.home.page.js.move.task" text="Move task"/></span>
   <span id="burnDownDetailsMessage"><spring:message code="developer.home.page.js.burn.down.details" text="Burn down details"/></span>
   <span id="sprintMeetingMessage"><spring:message code="developer.home.page.js.sprint.meeting" text="Sprint meeting"/></span>
   <span id="backlogMessage"><spring:message code="developer.home.page.js.backlog" text="Backlog"/></span>

   <span id="startSprintMessage"><spring:message code="developer.home.page.js.start.sprint" text="Start sprint"/></span>
   <span id="finishSprintMessage"><spring:message code="developer.home.page.js.finish.sprint" text="Finish sprint"/></span>
   <span id="taskDetailsMessage"><spring:message code="developer.home.page.js.task.details" text="Task details"/></span>
</div>
<script type="text/javascript">
    $(function () {
        var labels = [];
        var expectDatas = [];
        var actualDatas = [];

        <c:if test="${not empty developerOverviewDto.burnDownWrapper}" var="hasBurndown">
            <c:set var="step_" value="${developerOverviewDto.burnDownWrapper.itemStep}"/>
            <c:forEach items="${developerOverviewDto.burnDownWrapper.burnDown.labels}" var="label" step="${step_}">
            labels.push('${label.dateAsDay}');
            </c:forEach>
            <c:forEach items="${developerOverviewDto.burnDownWrapper.burnDown.expectPoints}" var="exp" step="${step_}">
            expectDatas.push('${exp.expectRemainTime}');
            </c:forEach>
            <c:forEach items="${developerOverviewDto.burnDownWrapper.burnDown.actualPoints}" var="actual" step="${step_}">
            actualDatas.push('${actual.actualRemainTime}');
            </c:forEach>
        </c:if>

        var developerHome = new DeveloperHome("${param.alert}");
        <c:if test="${hasBurndown}">
            developerHome.initialChart(labels, expectDatas, actualDatas,
                    ${developerOverviewDto.burnDownWrapper.chartSteps},
                    ${developerOverviewDto.burnDownWrapper.chartStepWidth});
        </c:if>
    });
</script>
</body>
</html>