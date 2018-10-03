<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="alert alert-info">
    <spring:message code="sprint.projects.form.page.text" text="Please choose a project for the new sprint firstly."/>
    <br/>
    <select id="projectSprintSelect">
        <c:forEach items="${projects}" var="p">
            <option value="${p.guid}">${p.name}(${p.code})</option>
        </c:forEach>
    </select>
    <span class="label label-warning hide" id="projectSprintSelectError"><spring:message code="sprint.projects.form.page.label" text="Please choose a project."/></span>
    <br/>
    <spring:message code="sprint.projects.form.page.click.confirm" text="then click 'Confirm' button."/>
</div>