<%--
  User: Shengzhao Li
  Date: 13-10-9
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="alert alert-block alert-info">
    <form id="moveTaskForm" action="${contextPath}/developer/task_action/move_task" class="form-inline">
        <input name="guid" type="hidden" value="${moveTaskDto.guid}"/>

        <p>
            <spring:message code="task.move.page.info" text="Just move the task <strong>{0}</strong>(\#{1}) to another sprint."
                            arguments="${moveTaskDto.taskName},${moveTaskDto.taskNumber}"/>
        </p>

        <p>
            <spring:message code="task.move.page.choose.target.sprint" text="Please choose the target sprint"/>
            <select id="targetSprintSelect" name="targetSprintGuid">
                <option value=""><spring:message code="task.move.page.select.a.sprint" text="--- select a sprint ---"/></option>
                <c:forEach items="${moveTaskDto.targetSprints}" var="s">
                    <option value="${s.guid}">${s.name}</option>
                </c:forEach>
            </select>
            <span class="label label-warning hide" id="targetSprintSelectError"><spring:message code="task.move.page.target.sprint.required"
                                                                                                text="Target sprint is required."/></span>
        </p>
    </form>
</div>