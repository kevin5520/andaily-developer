<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="well well-small">
    <div style="margin-left: 20px;">
        <p class="text-info">
            <spring:message code="assign.task.form.page.first" text="Assign the task(\#{0}) to" arguments="${assignTaskDto.taskNumber}"/>
        </p>
        <select id="executorFormSelect">
            <option value=""><spring:message code="assign.task.form.page.select.developer" text="--Select a developer--" /></option>
            <c:forEach items="${assignTaskDto.developers}" var="d">
                <option value="${d.guid}" ${(d.guid eq assignTaskDto.executorGuid)?'selected':''}>${d.showName}</option>
            </c:forEach>
        </select>
        <span class="label label-warning hidden" id="assignTaskErrorInfo"><spring:message code="assign.task.form.page.select.developer.help" text="Please select a developer firstly." /></span>
    </div>
</div>

