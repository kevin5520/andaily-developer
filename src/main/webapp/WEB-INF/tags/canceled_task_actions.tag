<%--
  User: Shengzhao Li
--%>
<%@ attribute name="currentSprint" required="true"
              type="com.andaily.domain.dto.developer.SprintDto" %>
<%@ attribute name="task" required="true"
              type="com.andaily.domain.dto.developer.SprintTaskDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@tag pageEncoding="UTF-8" %>

<c:if test="${not currentSprint.finished}">
    <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_MEMBER">
        <li><a tabindex="-1" href="${contextPath}/developer/task_action/restore/${task.guid}"
               class="confirm"
               confirm-content="<spring:message code="developer.home.page.canceled.action.restore.content" text="Are you sure restore the task to Created?"/>"
               confirm-title="<spring:message code="developer.home.page.canceled.action.restore.confirm" text="Confirm restore"/>"><i
                class="icon-fast-backward"></i> <spring:message code="developer.home.page.canceled.action.restore" text="Restore"/></a></li>
        <li class="divider"></li>
        <li><a tabindex="-1" href="${contextPath}/developer/task_action/archive/${task.guid}?status=CANCELED"
               class="confirm" confirm-content="<spring:message code="developer.home.page.canceled.action.archive.content"
               text="Are you sure archive the task?"/>"><i class="icon-remove"></i>
            <spring:message code="developer.home.page.canceled.action.archive" text="Archive"/></a></li>
    </sec:authorize>
</c:if>
<c:if test="${currentSprint.finished}">
    <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_PRODUCT_OWNER">
        <li><a tabindex="-1" href="javascript:void(0);" class="moveTask"
               load-url="${contextPath}/developer/task_action/load_move/${task.guid}"><i class="icon-move"></i>
            <spring:message code="developer.home.page.canceled.action.move" text="Move..."/></a></li>
    </sec:authorize>
</c:if>