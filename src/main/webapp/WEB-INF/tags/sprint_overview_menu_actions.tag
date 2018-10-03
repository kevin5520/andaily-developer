<%--
  User: Shengzhao Li
--%>
<%@ attribute name="currentSprint" required="true"
              type="com.andaily.domain.dto.developer.SprintDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@tag pageEncoding="UTF-8" %>

<li><a href="${contextPath}/developer?currentSprint.guid=${currentSprint.guid}"><i class="icon-th-list"></i>
    <spring:message code="sprint.overview.page.action.tasks" text="Tasks"/> (${currentSprint.amountOfTask})</a></li>
<li><a href="${contextPath}/developer/sprint_meeting/overview?sprintGuid=${currentSprint.guid}"><i
        class="icon-time"></i> <spring:message code="sprint.overview.page.action.meetings" text="Meetings"/> (${currentSprint.amountOfMeeting})</a></li>
<%--set default--%>
<sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_PRODUCT_OWNER">
    <c:if test="${not currentSprint.defaultSprint}">
        <li class="divider"></li>
        <li><a href="${contextPath}/developer/sprint_action/set_default/${currentSprint.guid}"><i
                class="icon-certificate"></i> <spring:message code="sprint.overview.page.action.set.default" text="Set default"/></a></li>
    </c:if>
    <c:if test="${currentSprint.defaultSprint}">
        <li class="divider"></li>
        <li><a href="${contextPath}/developer/sprint_action/cancel_default/${currentSprint.guid}"><i
                class="icon-fast-backward"></i> <spring:message code="sprint.overview.page.action.cancel.default" text="Cancel default"/></a></li>
    </c:if>
</sec:authorize>

<c:choose>
    <c:when test="${currentSprint.status eq 'CREATED'}">
        <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_PRODUCT_OWNER">
            <li><a href="${contextPath}/developer/sprint_action/start/${currentSprint.guid}" class="startSprint"
                   load-url="${contextPath}/developer/sprint_action/check_start/${currentSprint.guid}"><i
                    class="icon-play"></i>
                <spring:message code="sprint.overview.page.action.start" text="Start"/></a></li>
        </sec:authorize>
        <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_PRODUCT_OWNER">
            <li class="divider"></li>
            <li><a href="${contextPath}/developer/sprint_form/${currentSprint.guid}" class="editSprintClass"><i
                    class="icon-edit"></i> <spring:message code="sprint.overview.page.action.edit" text="Edit"/></a>
            </li>
            <li>
                <a href="${contextPath}/developer/sprint_action/archive/${currentSprint.guid}?to=sprint" class="confirm"
                   confirm-content="<spring:message code="sprint.overview.page.action.archive.confirm.content" text="Are you sure archive the Sprint?"/>"><i
                    class="icon-remove"></i> <spring:message code="sprint.overview.page.action.archive" text="Archive"/></a>
            </li>
        </sec:authorize>
    </c:when>
    <c:when test="${currentSprint.status eq 'PENDING'}">
        <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_PRODUCT_OWNER">
            <li>
                <a href="${contextPath}/developer/sprint_action/manage_backlogs/${currentSprint.guid}"><i
                        class="icon-tags"></i> <spring:message code="sprint.overview.page.action.manage.backlogs" text="Manage backlogs"/></a>
            </li>
            <li><a href="${contextPath}/developer/sprint_action/finish/${currentSprint.guid}?to=sprint"
                   class="finishSprint"
                   load-url="${contextPath}/developer/sprint_action/check_finish/${currentSprint.guid}"><i
                    class="icon-ok"></i>
                <spring:message code="sprint.overview.page.action.finish" text="Finish"/></a></li>
        </sec:authorize>
        <li>
            <a href="${contextPath}/developer/time_report/${currentSprint.guid}"><i
                    class="icon-calendar"></i> <spring:message code="sprint.overview.page.action.time.report" text="Time Report"/></a>
        </li>
    </c:when>
    <c:when test="${currentSprint.status eq 'FINISHED'}">
        <li>
            <a href="${contextPath}/developer/time_report/${currentSprint.guid}"><i
                    class="icon-calendar"></i> <spring:message code="sprint.overview.page.action.time.report" text="Time Report"/></a>
        </li>
        <%--${contextPath}/developer/sprint_action/report/${currentSprint.guid}--%>
        <%--<li class="disabled"><a href="javascript:void(0);"><i--%>
        <%--class="icon-th"></i>--%>
        <%--Report</a></li>--%>
    </c:when>
</c:choose>

