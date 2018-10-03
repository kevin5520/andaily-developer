<%--
  User: Shengzhao Li
--%>
<%@ attribute name="backlog" required="true"
              type="com.andaily.domain.dto.developer.BacklogDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@tag pageEncoding="UTF-8" %>

<sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_PRODUCT_OWNER">
    <li><a href="${contextPath}/developer/backlog_form/${backlog.guid}"><i
            class="icon-edit"></i> <spring:message code="backlog.overview.page.action.edit" text="Edit"/></a>
    </li>
    <c:if test="${not backlog.referSprint}">
        <li>
            <spring:message code="backlog.overview.page.action.archive.content" text="Are you sure archive the Backlog?" var="archiveBacklogMessage"/>
            <a href="${contextPath}/developer/backlog_action/archive/${backlog.guid}" class="confirm"
               confirm-content="${archiveBacklogMessage}"><i
                class="icon-remove"></i> <spring:message code="backlog.overview.page.action.archive" text="Archive"/></a>
        </li>
    </c:if>
</sec:authorize>
