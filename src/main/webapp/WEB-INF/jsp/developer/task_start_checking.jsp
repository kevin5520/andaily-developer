<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
    <c:if test="${result.allow}" var="allow">
        <spring:message code="task.start.checking.page.confirm" text="Are you confirm do the task now ?"/>
    </c:if>
    <c:if test="${not allow}">
        <div class="alert">
            <strong><spring:message code="task.start.checking.page.warning" text="Warning!"/></strong>
            <spring:message code="task.start.checking.page.warning.content"
                            text="The task already assigned to [{0}], please confirm you will do it now(If click 'Confirm', the task will assign to you)?" arguments="${result.oldExecutor}"/>
        </div>
    </c:if>

</div>

