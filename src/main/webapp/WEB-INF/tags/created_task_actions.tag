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
    <c:set var="sprintPending" value="${currentSprint.status eq 'PENDING'}"/>
    <sec:authorize ifAnyGranted="ROLE_MASTER">
        <li>
            <a tabindex="-1" href="${contextPath}/developer/task_action/assign_task_submit/${task.guid}"
               modal-title="<spring:message code="developer.home.page.created.action.assign.title" text="Assign task \#{0}" arguments="${task.number}"/>"
               class="assignTask"
               load-url="${contextPath}/developer/task_action/assign_task_form/${task.guid}"><i
                    class="icon-hand-right"></i> <spring:message code="developer.home.page.created.action.assign" text="Assign..."/></a>
        </li>
    </sec:authorize>
    <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_MEMBER">
        <li ${sprintPending?'':'class="disabled"'}>
            <c:if test="${sprintPending and task.canDoTask}" var="allowDoTask">
                <a tabindex="-1" href="${contextPath}/developer/task_action/start/${task.guid}" class="doTask"
                   taskGuid="${task.guid}" load-url="${contextPath}/developer/task_action/start_checking/${task.guid}"
                   modal-title="<spring:message code="developer.home.page.created.action.do.it.title" text="Confirm"/>"><i
                        class="icon-play"></i> <spring:message code="developer.home.page.created.action.do.it" text="Do it"/></a>
            </c:if>
            <c:if test="${not allowDoTask}">
                <a tabindex="-1" href="javascript:void(0);" style="color: #d3d3d3;"><i class="icon-play icon-white"></i> <spring:message code="developer.home.page.created.action.do.it" text="Do it"/></a>
            </c:if>
        </li>
    </sec:authorize>
    <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_MEMBER">
        <li class="divider"></li>
        <li><a tabindex="-1"
               href="${contextPath}/developer/task_form/${task.guid}?sprintGuid=${currentSprint.guid}"><i
                class="icon-edit"></i> <spring:message code="developer.home.page.created.action.edit" text="Edit"/></a>
        </li>
        <li><a tabindex="-1" href="${contextPath}/developer/task_action/archive/${task.guid}" class="confirm"
               confirm-content="<spring:message code="developer.home.page.created.action.archive.content" text="Are you sure archive the task?"/>"
                ><i class="icon-remove"></i> <spring:message code="developer.home.page.created.action.archive" text="Archive"/></a></li>
    </sec:authorize>
</c:if>