<%--
  User: Shengzhao Li
  Date: 13-10-2
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="sprint.meeting.overview.page.title" text="Meeting"/></title>
    <link href="${contextPath}/js/web/bootstrap/datepicker/datepicker.css" rel="stylesheet"/>
    <script src="${contextPath}/js/web/bootstrap/datepicker/bootstrap-datepicker.js"></script>

    <script src="${contextPath}/js/web/ckeditor/ckeditor.js"></script>
</head>
<body>
<div class="row">
    <hr/>
    <div class="span6">
        <form:form id="sprintMeetingSearchForm" method="get" cssClass="form-inline" commandName="overviewDto">
            <select name="sprintGuid" id="sprintSelect">
                <c:forEach items="${overviewDto.sprintDtosGroupResults}" var="group" varStatus="status">
                    <optgroup label="${group.key.label}">
                        <c:forEach items="${group.results}" var="sprint">
                            <option value="${sprint.guid}" ${sprint.guid eq overviewDto.sprintGuid?'selected':''}>${sprint.name}</option>
                        </c:forEach>
                    </optgroup>
                </c:forEach>
            </select>
            <form:select path="type" cssStyle="width: 150px;">
                <option value=""><spring:message code="sprint.meeting.overview.page.form.all.types" text="All types"/></option>
                <form:options items="${overviewDto.types}" itemLabel="label" itemValue="value"/>
            </form:select>
            <input type="submit" value="<spring:message code="sprint.meeting.overview.page.form.search" text="Search"/>" class="btn"/>
        </form:form>
    </div>
    <div class="span3">
        <span class="muted">
            <spring:message code="sprint.meeting.overview.page.size.meetings" text="Size of meetings"/>: <strong>${overviewDto.totalSize}</strong>
        </span>
    </div>
    <div class="span3">
        <a href="javascript:void(0);" id="sprintMeetingFormId"
           load-url="${contextPath}/developer/sprint/meeting_form/create?sprintGuid=${overviewDto.sprintGuid}"
           class="btn btn-success btn-small"><i
                class="icon-plus icon-white"></i> <spring:message code="sprint.meeting.overview.page.add.meeting" text="Add meeting"/></a>

        <div class="btn-group">
            <a class="btn btn-small dropdown-toggle" data-toggle="dropdown" href="#">
                <spring:message code="sprint.meeting.overview.page.back.to" text="Back to"/> <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${contextPath}/developer?currentSprint.guid=${overviewDto.sprintGuid}"><i
                        class="icon-th-list"></i> <spring:message code="sprint.meeting.overview.page.tasks" text="Tasks"/></a></li>
                <li><a href="${contextPath}/developer/sprint_overview"><i class="icon-road"></i> <spring:message code="sprint.meeting.overview.page.sprints" text="Sprints"/></a></li>
            </ul>
        </div>
    </div>
</div>
<div class="row">
    <div class="span12">
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-ok-sign icon-white"></i>
                <span id="addMeetingSuccess" class="hide"><spring:message code="sprint.meeting.overview.page.alert.save.success" text="Save sprint meeting successful!"/></span>

            </div>
        </div>
        <div class="alert alert-info hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-info-sign icon-white"></i>
                <%--<span id="taskFormSuccess" class="hide">Create/Edit the task successful!</span>--%>
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
        <spring:message code="sprint.meeting.overview.page.table.date" text="Date" var="dateMessage"/>
        <spring:message code="sprint.meeting.overview.page.table.type" text="Type" var="typeMessage"/>
        <spring:message code="sprint.meeting.overview.page.table.create.time" text="Create time" var="createTimeMessage"/>
        <spring:message code="sprint.meeting.overview.page.table.edit.meeting" text="Edit meeting" var="editMeetingMessage"/>
        <spring:message code="sprint.meeting.overview.page.table.meeting.details" text="Meeting details" var="meetingDetailsMessage"/>

        <displaytag:table list="${overviewDto}" class="table table-hover" id="meeting"
                          form="sprintMeetingSearchForm">
            <displaytag:column title="${dateMessage}" property="meetingDate"/>
            <displaytag:column title="${typeMessage}" property="type.label" class="${meeting.type}"/>
            <displaytag:column title="${createTimeMessage}" property="createTime"/>
            <displaytag:column title="&nbsp;">
                <a href="javascript:void(0);" class="meetingEdit" title="${editMeetingMessage}"
                   load-url="${contextPath}/developer/sprint/meeting_form/${meeting.guid}?sprintGuid=${meeting.sprintDto.guid}"><i
                        class="icon-edit"></i></a>
                <a href="javascript:void(0);" class="meetingDetails" title="${meetingDetailsMessage}"
                   load-url="${contextPath}/developer/sprint_meeting/details/${meeting.guid}"><i
                        class="icon-info-sign"></i></a>
            </displaytag:column>
        </displaytag:table>
    </div>
    <div class="span3">
        <div class="well">
            <div>
                <h5><a href="javascript:void(0);"><spring:message code="sprint.meeting.overview.page.statistics" text="Statistics"/></a></h5>
            </div>

            <div>
                <table class="table">
                    <tr class="info">
                        <%--Daily standing--%>
                        <td>${overviewDto.types[0].label}</td>
                        <td><strong>${overviewDto.dailyStandingMeetings}</strong></td>
                    </tr>
                    <tr class="success">
                        <%--Sprint review--%>
                        <td>${overviewDto.types[2].label}</td>
                        <td><strong>${overviewDto.reviewMeetings}</strong></td>
                    </tr>
                    <tr style="background-color: white;">
                        <%--Retrospective--%>
                        <td>${overviewDto.types[3].label}</td>
                        <td><strong>${overviewDto.retrospectiveMeetings}</strong></td>
                    </tr>
                    <tr class="warning">
                        <%--Sprint planning--%>
                        <td>${overviewDto.types[1].label}</td>
                        <td><strong>${overviewDto.planningMeetings}</strong></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="hidden">
    <span id="meetingDetailsMessage"><spring:message code="sprint.meeting.overview.page.js.meeting.details" text="Sprint meeting details"/></span>
    <span id="sprintMeetingMessage"><spring:message code="sprint.meeting.overview.page.js.sprint.meeting" text="Sprint meeting"/></span>
</div>
<script type="text/javascript">
    $(function () {
        new SprintMeetingOverview("${param.alert}");
    });
</script>
</body>
</html>