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

<div>
    <label class="checkbox text-warning">
        <input type="checkbox" class="onlyShowMyTasks" name="onlyShowMyTasks"
               value="${developerOverviewDto.onlyShowMyTasks}" ${developerOverviewDto.onlyShowMyTasks?'checked':''}/>
        <spring:message code="developer.home.page.only.show.my.tasks" text="Only show my task(s)"/>
    </label>
</div>
<spring:message code="developer.home.page.pending.table.task" text="Task" var="allTaskMessage"/>
<spring:message code="developer.home.page.finished.table.estimate.used" text="Estimate/Used" var="allEstimateUsedMessage"/>
<spring:message code="developer.home.page.pending.table.executor" text="Executor" var="allExecutorMessage"/>
<spring:message code="developer.home.page.pending.table.priority" text="Priority" var="allPriorityMessage"/>
<spring:message code="developer.home.page.all.table.status" text="Status" var="allStatusMessage"/>

<displaytag:table list="${developerOverviewDto}" class="table table-hover" id="task" form="developerForm">
    <displaytag:column property="number" title="#" headerClass="width5" class="${task.urgent?'urgent':''}"/>
    <displaytag:column title="${allTaskMessage}" headerClass="width50">
        <div class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    ${task.name}
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                <c:choose>
                    <c:when test="${task.status eq 'CREATED'}">
                        <custom:created_task_actions currentSprint="${developerOverviewDto.currentSprint}"
                                                     task="${task}"/>
                    </c:when>
                    <c:when test="${task.status eq 'PENDING'}">
                        <custom:pending_task_actions currentSprint="${developerOverviewDto.currentSprint}"
                                                     task="${task}"/>
                    </c:when>
                    <c:when test="${task.status eq 'CANCELED'}">
                        <custom:canceled_task_actions currentSprint="${developerOverviewDto.currentSprint}"
                                                      task="${task}"/>
                    </c:when>
                    <c:when test="${task.status eq 'FINISHED'}">
                        <custom:finished_task_actions currentSprint="${developerOverviewDto.currentSprint}"
                                                      task="${task}"/>
                    </c:when>
                </c:choose>
            </ul>
        </div>
    </displaytag:column>
    <displaytag:column title="${allEstimateUsedMessage}">
        ${task.estimateTime} / <span class="text-info">${task.actualUsedTime}${task.timeDifferenceAsHtml}</span>
    </displaytag:column>
    <displaytag:column property="executor.showName" title="${allExecutorMessage}"/>
    <displaytag:column property="priority.label" title="${allPriorityMessage}" class="text-warning"/>
    <displaytag:column property="status.label" title="${allStatusMessage}"/>
    <displaytag:column title="&nbsp;" class="${task.status}">
        <span data-content="${task.tooltip}" class="taskDetails" style="cursor: pointer;"><i
                class="icon-question-sign"></i></span>
        <c:if test="${task.referBacklog}">
            <a href="javascript:void(0)" title="<spring:message code="developer.home.page.pending.table.show.backlog" text="Show backlog"/>"
               load-url="${contextPath}/developer/task_action/load_backlog/${task.guid}"
               class="taskBacklogDetail"><i class="icon-tag"></i></a>
        </c:if>
    </displaytag:column>
</displaytag:table>
<div class="badge badge-info developer-time-budget pull-right">
    <spring:message code="developer.home.page.pending.total" text="Total"/>: <strong>${developerOverviewDto.totalEstimatedHours}</strong>
    <spring:message code="developer.home.page.pending.hours" text="hour(s)"/>
</div>