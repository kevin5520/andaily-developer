<%--
  User: Shengzhao Li
--%>
<%@ attribute name="user" required="true"
              type="com.andaily.domain.dto.user.UserDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@tag pageEncoding="UTF-8" %>

<c:if test="${not user.archived}">
    <li>
        <spring:message code="user.overview.page.action.reset.password.content" text="Are you sure reset the user password?" var="resetPassActionMessage"/>
        <a href="${contextPath}/developer/user_/reset_password/${user.guid}" class="resetPassword"
           confirm-content="${resetPassActionMessage}"><i
                class="icon-repeat"></i> <spring:message code="user.overview.page.action.reset.password" text="Reset password"/></a>
    </li>
    <%--${contextPath}/developer/user_/logs/${user.guid}--%>
    <%--<li class="disabled"><a href="javascript:void(0);"><i--%>
    <%--class="icon-info-sign icon-white"></i> Logs</a>--%>
    <%--</li>--%>
    <li class="divider"></li>
    <li><a href="${contextPath}/developer/user_/user_form/${user.guid}"><i
            class="icon-edit"></i> <spring:message code="user.overview.page.action.edit" text="Edit"/></a>
    </li>
    <li>
        <spring:message code="user.overview.page.action.archive.content" text="Are you sure archive the User?" var="archiveActionMessage"/>
        <a href="${contextPath}/developer/user_/archive/${user.guid}/true" class="confirm"
           confirm-content="${archiveActionMessage}"><i
            class="icon-remove"></i> <spring:message code="user.overview.page.action.archive" text="Archive"/></a>
    </li>
</c:if>
<c:if test="${user.archived}">
    <li>
        <spring:message code="user.overview.page.action.activating.content" text="Are you sure activating the User?" var="activatingActionMessage"/>
        <a href="${contextPath}/developer/user_/archive/${user.guid}/false" class="confirm"
           confirm-content="${activatingActionMessage}"><i
            class="icon-ok-circle"></i> <spring:message code="user.overview.page.action.activating" text="Activating"/></a>
    </li>
</c:if>

