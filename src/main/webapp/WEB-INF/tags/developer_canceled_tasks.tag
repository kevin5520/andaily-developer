<%--
  User: Shengzhao Li
--%>
<%@ attribute name="developerOverviewDto" required="true"
              type="com.andaily.domain.dto.developer.DeveloperOverviewDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@tag pageEncoding="UTF-8" %>

<spring:message code="developer.home.page.pending.table.task" text="Task" var="cancelTaskMessage"/>
<spring:message code="developer.home.page.pending.table.estimate" text="Estimate" var="cancelEstimateMessage"/>
<spring:message code="developer.home.page.pending.table.priority" text="Priority" var="cancelPriorityMessage"/>
<spring:message code="developer.home.page.canceled.table.cancel.time" text="Cancel Time" var="canceledTimeMessage"/>

<displaytag:table list="${developerOverviewDto}" class="table table-striped table-hover" id="task" form="developerForm">
    <displaytag:column property="number" title="#" headerClass="width5" class="${task.urgent?'urgent':''}"/>
    <displaytag:column title="${cancelTaskMessage}" headerClass="width50">
        <div class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    ${task.name}
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                <custom:canceled_task_actions currentSprint="${developerOverviewDto.currentSprint}" task="${task}"/>
            </ul>
        </div>
    </displaytag:column>
    <displaytag:column property="estimateTime" title="${cancelEstimateMessage}"/>
    <displaytag:column property="priority.label" title="${cancelPriorityMessage}" class="text-warning"/>
    <displaytag:column property="cancelTime" title="${canceledTimeMessage}"/>
    <displaytag:column title="&nbsp;">
        <span data-content="${task.tooltip}" class="taskDetails" style="cursor: pointer;"><i
                class="icon-question-sign"></i></span>
        <c:if test="${task.referBacklog}">
            <a href="javascript:void(0)" title="<spring:message code="developer.home.page.pending.table.show.backlog" text="Show backlog"/>"
               load-url="${contextPath}/developer/task_action/load_backlog/${task.guid}"
               class="taskBacklogDetail"><i class="icon-tag"></i></a>
        </c:if>
        <c:if test="${task.moved}">
            <a href="javascript:void(0)" title="<spring:message code="developer.home.page.created.table.move.task" text="This is a moved task"/>"><i class="icon-move"></i></a>
        </c:if>
    </displaytag:column>
</displaytag:table>
<div class="badge badge-info developer-time-budget pull-right">
    <spring:message code="developer.home.page.pending.total" text="Total"/>: <strong>${developerOverviewDto.totalEstimatedHours}</strong>
    <spring:message code="developer.home.page.pending.hours" text="hour(s)"/>
</div>