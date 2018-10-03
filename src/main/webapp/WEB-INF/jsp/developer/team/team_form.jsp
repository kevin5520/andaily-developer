<%--
  User: Shengzhao Li
  Date: 14-1-6
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="team.form.page.title" text="Team"/></title>
    <script src="${contextPath}/js/web/ckeditor/ckeditor.js"></script>
</head>
<body>
<div class="row">
    <hr/>
</div>

<form:form commandName="teamFormDto" id="teamForm">
    <form:hidden path="guid"/>
    <div class="row sprint-form">
        <div class="span8 well">
            <fieldset>
                <legend><spring:message code="team.form.page.title" text="Team"/></legend>
                <table class="width100">
                    <tr>
                        <td colspan="2">
                            <label for="name"><spring:message code="team.form.page.name" text="Team name"/></label>
                            <spring:message code="team.form.page.name.holder" text="Team name" var="teamNameHolderMessage"/>
                            <form:input path="name" id="name" placeholder="${teamNameHolderMessage}" required="true"
                                        maxlength="255" class="input-xxlarge"/>
                            <form:errors path="name" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="team.form.page.name.help" text="Please input the team name, required."/></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <c:forEach items="${teamFormDto.developerDtoGrouperList}" var="gd" varStatus="vs">
                                <label>${gd.key.label}</label>
                                <c:forEach items="${gd.results}" var="dev">
                                    <c:if test="${gd.key.master}">
                                        <form:checkbox path="masters" value="${dev.guid}"/> ${dev.showName}&nbsp;&nbsp;
                                    </c:if>
                                    <c:if test="${gd.key.member}">
                                        <form:checkbox path="members" value="${dev.guid}"/> ${dev.showName}&nbsp;&nbsp;
                                    </c:if>
                                </c:forEach>
                                ${vs.last?'':' <br/><br/>'}
                            </c:forEach>
                            <form:errors path="masters" cssClass="label label-warning"/>
                            <form:errors path="members" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="team.form.page.members.help" text="Please choose the team persons, required."/></span>

                                <%--For bind if validation failed--%>
                            <c:forEach items="${teamFormDto.developerDtoList}" var="d" varStatus="s">
                                <form:hidden path="developerDtoList[${s.index}].guid"/>
                                <form:hidden path="developerDtoList[${s.index}].nickName"/>
                                <form:hidden path="developerDtoList[${s.index}].email"/>
                                <form:hidden path="developerDtoList[${s.index}].scrumTerm"/>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label for="description"><spring:message code="team.form.page.description" text="Description"/></label>
                            <form:textarea path="description" cssClass="ckeditor" id="description"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
        <div class="span3 well">
            <fieldset>
                <legend><spring:message code="team.form.page.team.projects" text="Team Projects"/></legend>
                <ul class="unstyled">
                    <c:forEach items="${teamFormDto.projects}" var="p">
                        <li>
                            <label class="checkbox">
                                <form:checkbox path="projectGuids" value="${p.guid}"/> ${p.name}(${p.code})
                            </label>
                        </li>
                    </c:forEach>

                        <%--For bind if validation failed--%>
                    <c:forEach items="${teamFormDto.projects}" var="d" varStatus="s">
                        <form:hidden path="projects[${s.index}].guid"/>
                        <form:hidden path="projects[${s.index}].name"/>
                        <form:hidden path="projects[${s.index}].code"/>
                    </c:forEach>
                    <li>
                        <form:errors path="projectGuids" cssClass="label label-warning"/>
                    </li>
                </ul>
                <span class="help-block"><spring:message code="team.form.page.team.projects.help" text="Please choose the team projects, optional."/></span>
            </fieldset>
        </div>
    </div>
    <div class="row">
        <div class="span5 offset3">
            <button type="submit" class="btn btn-success btn-large"><i class="icon-plus icon-white"></i>
                <spring:message code="team.form.page.save" text="Save"/>
            </button>
            <a href="../overview" class="btn btn-link"><spring:message code="team.form.page.cancel" text="Cancel"/></a>
        </div>
    </div>
</form:form>
</body>
</html>