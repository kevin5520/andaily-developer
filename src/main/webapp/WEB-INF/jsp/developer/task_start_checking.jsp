<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
    <c:if test="${result.allow}" var="allow">
    <%//lihao-20181120-andaily_taskpage_improvement-modified-start%>
        <p class="text-center">
            <spring:message  text="The task name is <strong>{0}</strong> and estimate time is <strong>{1}</strong> hour(s)."
                            arguments="${sprintTaskDto.name},${sprintTaskDto.estimateTime}"/>
        <br><spring:message text="The priority is <strong>{0}</strong> and the urgent status is <strong>{1}</strong>."
        					arguments="${sprintTaskDto.priority},${sprintTaskDto.urgent}"/></br>
		<br><spring:message text="<strong>Are you confirm do the task now ?</strong>"/></br>
        </p>
    <%//lihao-20181120-andaily_taskpage_improvement-modified-end%>
    </c:if>
    <c:if test="${not allow}">
        <div class="alert">
            <strong><spring:message code="task.start.checking.page.warning" text="Warning!"/></strong>
            <spring:message code="task.start.checking.page.warning.content"
                            text="The task already assigned to [{0}], please confirm you will do it now(If click 'Confirm', the task will assign to you)?" arguments="${result.oldExecutor}"/>
        </div>
    </c:if>

</div>

