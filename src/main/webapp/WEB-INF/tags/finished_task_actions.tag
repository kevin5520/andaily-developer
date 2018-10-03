<%--
  User: Shengzhao Li
--%>
<%@ attribute name="currentSprint" required="true"
              type="com.andaily.domain.dto.developer.SprintDto" %>
<%@ attribute name="task" required="true"
              type="com.andaily.domain.dto.developer.SprintTaskDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@tag pageEncoding="UTF-8" %>

<li>
    <%--${contextPath}/developer/task_action/comment_submit/${task.guid}--%>
    <a tabindex="-1" href="javascript:void(0)" class="finishedTaskComment"
       modal-title="<spring:message code="developer.home.page.finished.action.comment.title" text="Comment of task(\#{0})" arguments="${task.number}"/> "
       load-url="${contextPath}/developer/task_action/comment_form/${task.guid}">
        <i class="icon-comment"></i> <spring:message code="developer.home.page.finished.action.comment" text="Comment"/></a>
</li>