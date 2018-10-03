<%--
  User: Shengzhao Li
  Date: 13-9-15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="well">
    <div>#${backlogDto.number} ${backlogDto.name} (<strong>${backlogDto.estimateTime}</strong>)
        <spring:message code="sprint.backlogs.page.referenced.tasks" text="Amount of referenced tasks" var="referencedTasksTitle"/>
        <c:if test="${backlogDto.referencedTasks > 0}">[<strong class="text-warning" title="${referencedTasksTitle}">${backlogDto.referencedTasks}</strong>]</c:if>
    </div>
    <div>
        <ul class="unstyled">
            <c:forEach items="${backlogDto.documentDtos}" var="d">
                <li>
                    <a href="${contextPath}/developer/file/download/${d.fileDto.guid}">${d.fileDto.name}</a>
                    <span class="muted">(${d.fileDto.sizeAsText})</span>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>