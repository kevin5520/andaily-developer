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
        <c:if test="${task.executorOfMine}">
            <li>
                <spring:message code="developer.home.page.pending.action.finish.task" text="Finish task \#{0}" arguments="${task.number}" var="pendingFinishTaskMessage"/>
                <a tabindex="-1" href="${contextPath}/developer/task_action/finish/${task.guid}" class="finishTask"
                   load-url="${contextPath}/developer/task_action/finishform/${task.guid}"
                   modal-title="${pendingFinishTaskMessage}"><i
                    class="icon-thumbs-up"></i> <spring:message code="developer.home.page.pending.action.finish.it" text="Finish it"/></a></li>
        </c:if>
        <li><a tabindex="-1" href="${contextPath}/developer/task_action/revert/${task.guid}" class="confirm"
               confirm-content="<spring:message code="developer.home.page.pending.action.revert.content" text="Are you sure revert the task(\#{0}) to Created ?"  arguments="${task.number}"/>"
               confirm-title="<spring:message code="developer.home.page.pending.action.revert.confirm" text="Confirm revert task \#{0}"  arguments="${task.number}"/>"><i
                class="icon-share-alt"></i> <spring:message code="developer.home.page.pending.action.revert" text="Revert"/></a>
        </li>
        <li><a tabindex="-1" href="${contextPath}/developer/task_action/cancel/${task.guid}" class="confirm"
               confirm-content="<spring:message code="developer.home.page.pending.action.cancel.content" text="Are you sure cancel the task(\#{0}) ?"  arguments="${task.number}"/>"
               confirm-title="<spring:message code="developer.home.page.pending.action.cancel.confirm" text="Confirm cancel task(\#{0}) ?"  arguments="${task.number}"/>"><i
                class="icon-remove-sign"></i> <spring:message code="developer.home.page.pending.action.cancel" text="Cancel"/></a></li>
    </sec:authorize>
</c:if>