<%--
  User: Shengzhao Li
--%>
<%@ attribute name="currentSprint" required="true"
              type="com.andaily.domain.dto.developer.SprintDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@tag pageEncoding="UTF-8" %>


<c:choose>
    <c:when test="${currentSprint.status eq 'CREATED'}">
        <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_PRODUCT_OWNER">
            <li><a href="${contextPath}/developer/sprint_action/start/${currentSprint.guid}" class="startSprint"
                   load-url="${contextPath}/developer/sprint_action/check_start/${currentSprint.guid}"><em
                    class="icon-play"></em>
                <spring:message code="developer.home.page.action.start" text="Start"/></a></li>
        </sec:authorize>
        <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_PRODUCT_OWNER">
            <li class="divider"></li>
            <li><a href="${contextPath}/developer/sprint_form/${currentSprint.guid}" class="editSprintClass"><i
                    class="icon-edit"></i> <spring:message code="developer.home.page.action.edit" text="Edit"/></a>
            </li>
            <li><a href="${contextPath}/developer/sprint_action/archive/${currentSprint.guid}" class="confirm"
                   confirm-content="<spring:message code="developer.home.page.action.archive.content" text="Are you sure archive the Sprint?"/>"><em
                    class="icon-remove"></em> <spring:message code="developer.home.page.action.archive" text="Archive"/></a>
            </li>
        </sec:authorize>
    </c:when>
    <c:when test="${currentSprint.status eq 'PENDING'}">
        <c:if test="${currentSprint.haveBacklogs}">
            <li><a href="${contextPath}/developer/backlog_overview?sprintGuid=${currentSprint.guid}"><em
                    class="icon-tags"></em> <spring:message code="developer.home.page.action.backlogs" text="Backlogs"/></a>
            </li>
        </c:if>
        <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_PRODUCT_OWNER">
            <li><a href="${contextPath}/developer/sprint_action/finish/${currentSprint.guid}" class="finishSprint"
                   load-url="${contextPath}/developer/sprint_action/check_finish/${currentSprint.guid}"><em
                    class="icon-ok"></em>
                <spring:message code="developer.home.page.action.finish" text="Finish"/></a></li>
        </sec:authorize>
        <li>
            <a href="${contextPath}/developer/time_report/${currentSprint.guid}"><i
                    class="icon-calendar"></i> <spring:message code="developer.home.page.action.time.report" text="Time Report"/></a>
        </li>
    </c:when>
    <c:when test="${currentSprint.status eq 'FINISHED'}">
        <li>
            <a href="${contextPath}/developer/time_report/${currentSprint.guid}"><em
                    class="icon-calendar"></em> <spring:message code="developer.home.page.action.time.report" text="Time Report"/></a>
        </li>
        <%--${contextPath}/developer/sprint_action/report/${currentSprint.guid}--%>
        <%--<li class="disabled"><a href="javascript:void(0);"><i--%>
        <%--class="icon-th"></i>--%>
        <%--Report</a></li>--%>
    </c:when>
</c:choose>
<li>
    <a href="${contextPath}/developer/sprint_action/activity/${currentSprint.guid}"><em
            class="icon-list-alt"></em> <spring:message code="developer.home.page.action.activity" text="Activity"/></a>
</li>
