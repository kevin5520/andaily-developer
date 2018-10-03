<%--
  User: Shengzhao Li
--%>
<%@ attribute name="project" required="true"
              type="com.andaily.domain.dto.developer.project.ProjectDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@tag pageEncoding="UTF-8" %>

<sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_PRODUCT_OWNER,ROLE_MEMBER">
    <li><a tabindex="-1"
           href="${contextPath}/developer/sprint_overview?projectGuid=${project.guid}"><i
            class="icon-road"></i> <spring:message code="project.overview.action.sprints" text="Sprints"/> (${project.amountOfSprints})</a>
    </li>
    <li><a tabindex="-1"
           href="${contextPath}/developer/backlog_overview?projectGuid=${project.guid}"><i
            class="icon-tags"></i> <spring:message code="project.overview.action.backlogs" text="Backlogs"/> (${project.amountOfBacklogs})</a>
    </li>
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_PRODUCT_OWNER">
    <li class="divider"></li>
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_SUPER_MAN,ROLE_PRODUCT_OWNER">
    <li><a tabindex="-1"
           href="${contextPath}/developer/project_form/${project.guid}"><i
            class="icon-edit"></i> <spring:message code="project.overview.action.edit" text="Edit"/></a>
    </li>
    <li>
        <spring:message code="project.overview.action.archive.content" text="Are you sure archive the project? It is not reversible." var="archiveContentMessage"/>
        <a tabindex="-1" class="confirm" confirm-content="${archiveContentMessage}"
           href="${contextPath}/developer/project_/archive/${project.guid}"><i
            class="icon-remove"></i> <spring:message code="project.overview.action.archive" text="Archive"/></a>
    </li>
</sec:authorize>