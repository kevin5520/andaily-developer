<%--
  User: Shengzhao Li
  Date: 13-9-12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="well">
    <table class="table table-hover table-striped">
        <tbody>
        <c:forEach items="${sprintBacklogsDto.backlogs}" var="backlog" varStatus="s">
            <tr>
                <td>#${backlog.number}</td>
                <td>${backlog.content} (<strong>${backlog.estimateTime}</strong>)
                    <spring:message code="sprint.backlogs.page.referenced.tasks" text="Amount of referenced tasks" var="referencedTasksTitle"/>
                    <c:if test="${backlog.referencedTasks > 0}">[<strong class="text-warning" title="${referencedTasksTitle}">${backlog.referencedTasks}</strong>]</c:if>
                    <ul class="unstyled">
                        <c:forEach items="${backlog.documentDtos}" var="d">
                            <li>
                                <a href="${contextPath}/developer/file/download/${d.fileDto.guid}">${d.fileDto.name}</a>
                                <span class="muted">(${d.fileDto.sizeAsText})</span>
                            </li>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="badge badge-info sprint-backlog-budget"><spring:message code="sprint.backlogs.page.total" text="Total"/> :
        <strong>${sprintBacklogsDto.budgetBacklogsTime}</strong> <spring:message code="sprint.backlogs.page.hours" text="hour(s)"/>
    </div>
    &nbsp;<a href="${contextPath}/developer/backlog_overview?sprintGuid=${sprintBacklogsDto.sprintGuid}"
             class="btn btn-link"><spring:message code="sprint.backlogs.page.full.backlogs" text="Full backlogs"/></a>
</div>