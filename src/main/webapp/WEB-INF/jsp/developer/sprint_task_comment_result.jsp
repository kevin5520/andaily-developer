<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<li>
    <spring:message code="sprint.task.comment.result.page.delete" text="Delete" var="deleteMessage"/>
    <blockquote>
        <p class="text-info">
            ${commentDto.content}
            <c:if test="${commentDto.myCreated}">
                &nbsp;<a href="javascript:void(0)" title="${deleteMessage}" class="deleteMyComment" guid="${commentDto.guid}"><i class="icon-remove"></i></a>
            </c:if>
        </p>
        <small>${commentDto.who} (${commentDto.createDate})</small>
    </blockquote>
</li>