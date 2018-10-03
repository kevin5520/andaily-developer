<%--
  User: Shengzhao Li
--%>
<%@ attribute name="sprint" required="true"
              type="com.andaily.domain.dto.developer.SprintDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag pageEncoding="UTF-8" %>

<c:if test="${sprint.showCurrDeadline}">
    <span class="text-warning" title="${sprint.deadline}">${sprint.currentDeadline}</span></c:if>
<c:if test="${not sprint.showCurrDeadline}">${sprint.deadline}</c:if>
