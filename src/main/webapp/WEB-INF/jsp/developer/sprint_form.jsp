<%--
  User: Shengzhao Li
  Date: 13-8-6
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title><spring:message code="sprint.form.page.title" text="Sprint"/></title>

    <link href="${contextPath}/js/web/bootstrap/datepicker/datepicker.css" rel="stylesheet"/>
    <script src="${contextPath}/js/web/bootstrap/datepicker/bootstrap-datepicker.js"></script>
    <script src="${contextPath}/js/web/bootstrap/datepicker/bootstrap-datepicker.zh-CN.js"></script>

    <script src="${contextPath}/js/web/ckeditor/ckeditor.js"></script>
</head>
<body>
<div class="row">
    <hr/>
</div>
<form:form commandName="sprintFormDto" id="sprintForm">
    <form:hidden path="guid"/>
    <div class="row sprint-form">
        <div class="span8 well">
            <fieldset>
                <legend><spring:message code="sprint.form.page.title" text="Sprint"/></legend>
                <table class="width100">
                    <tr>
                        <td>
                            <label for="name"><spring:message code="sprint.form.page.form.sprint.name" text="Sprint name"/></label>
                            <spring:message code="sprint.form.page.form.sprint.name.placeholder" text="Sprint name" var="sprintNameHolderMessage"/>
                            <form:input path="name" id="name" placeholder="${sprintNameHolderMessage}" required="true" cssClass="span3"
                                        maxlength="255"/>
                            <form:errors path="name" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="sprint.form.page.form.sprint.name.help" text="Sprint name is unique, max length: 255."/></span>
                            <form:hidden path="existName"/>
                        </td>
                        <td>
                            <label for="projectGuid"><spring:message code="sprint.form.page.form.project" text="Project"/></label>
                            <c:if test="${sprintFormDto.newly}" var="newly">
                                <form:select path="projectGuid" id="projectGuid">
                                    <%--disabled="${ empty backlogFormDto.projectGuid?'false':'true'}"--%>
                                    <%--<form:option value="" label="Choose project"/>--%>
                                    <form:options items="${sprintFormDto.availableProjects}" itemLabel="name"
                                                  itemValue="guid"/>
                                </form:select>
                            </c:if>
                            <c:if test="${not newly}">
                                <form:select path="projectGuid" id="projectGuid">
                                    <form:options items="${sprintFormDto.availableProjects}" itemLabel="name"
                                                  itemValue="guid"/>
                                </form:select>
                            </c:if>
                            <form:errors path="projectGuid" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="sprint.form.page.form.project.help" text="Specify a project, required."/></span>

                                <%--For bind list data if validation failed--%>
                            <c:forEach items="${sprintFormDto.availableProjects}" var="p" varStatus="s">
                                <input type="hidden" name="availableProjects[${s.index}].guid" value="${p.guid}"/>
                                <input type="hidden" name="availableProjects[${s.index}].name" value="${p.name}"/>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="startDate"><spring:message code="sprint.form.page.form.start.date" text="Start date"/></label>

                            <div class="input-append date" id="startDate">
                                <spring:message code="sprint.form.page.form.start.date.placeholder" text="Start date" var="startDateHolderMessage"/>
                                <form:input path="startDate" placeholder="${startDateHolderMessage}" cssClass="span2" readonly="true"
                                            required="true"/>
                                <span class="add-on"><i class="icon-th"></i></span>
                            </div>
                            <form:errors path="startDate" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="sprint.form.page.form.start.date.help" text="Start date must be after(or equal) today."/></span>
                        </td>
                        <td>
                            <label for="deadline"><spring:message code="sprint.form.page.form.deadline" text="Deadline"/></label>

                            <div class="input-append date" id="deadline">
                                <spring:message code="sprint.form.page.form.deadline.placeholder" text="Deadline" var="deadlineHolderMessage"/>
                                <form:input path="deadline" placeholder="${deadlineHolderMessage}" cssClass="span2" readonly="true"
                                            required="true"/>
                                <span class="add-on"><i class="icon-th"></i></span>
                            </div>
                            <form:errors path="deadline" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="sprint.form.page.form.deadline.help" text="Deadline must be after the start date, required."/></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="teamGuid"><spring:message code="sprint.form.page.form.team" text="Team"/></label>
                            <spring:message code="sprint.form.page.form.team.empty.option" text="Choose a team" var="chooseTeamMessage"/>
                            <form:select path="teamGuid" id="teamGuid">
                                <form:option value="" label="${chooseTeamMessage}"/>
                                <form:options items="${sprintFormDto.availableTeams}" itemLabel="name"
                                              itemValue="guid"/>
                            </form:select>
                            <form:errors path="teamGuid" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="sprint.form.page.form.team.help" text="Which team will do the sprint, required."/></span>

                                <%--For bind list data if validation failed--%>
                            <c:forEach items="${sprintFormDto.availableTeams}" var="p" varStatus="s">
                                <input type="hidden" name="availableTeams[${s.index}].guid" value="${p.guid}"/>
                                <input type="hidden" name="availableTeams[${s.index}].name" value="${p.name}"/>
                            </c:forEach>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label for="description"><spring:message code="sprint.form.page.form.description" text="Description"/></label>
                            <form:textarea path="description" cssClass="ckeditor" id="description"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
        <div class="span3 well">
            <fieldset>
                <legend><spring:message code="sprint.form.page.select.backlogs" text="Select backlogs"/></legend>
            </fieldset>
            <ul class="unstyled" id="backlogUl">
                <c:if test="${empty sprintFormDto.backlogs}" var="emptyBacklogs">
                    <li>
                        <label><spring:message code="sprint.form.page.no.backlogs" text="No available backlogs"/></label>
                    </li>
                </c:if>
                <c:if test="${not emptyBacklogs}">
                    <c:forEach items="${sprintFormDto.backlogs}" var="b" varStatus="s">
                        <li>
                            <label class="checkbox">
                                <input type="checkbox" name="backlogs[${s.index}].guid"
                                       value="${b.guid}" ${b.selected?'checked':''} ${b.disabled?'disabled':''} />
                                <span>${fun:substring(b.name,0,150)}</span>
                                (<strong
                                    class="estimateTime ${b.selected?'backlogEstTime':''}">${b.estimateTime}</strong>)
                                <c:if test="${b.disabled}">
                                    <input type="hidden" name="backlogs[${s.index}].guid" value="${b.guid}"/>
                                </c:if>
                            </label>
                                <%--Use the hiddens for bind values after validation failed--%>
                            <form:hidden path="backlogs[${s.index}].name"/>
                            <form:hidden path="backlogs[${s.index}].estimateTime"/>
                            <form:hidden path="backlogs[${s.index}].selected"/>
                            <form:hidden path="backlogs[${s.index}].disabled"/>
                        </li>
                    </c:forEach>
                    <li>
                        <div class="badge badge-info sprint-backlog-budget" id="budgetTimes"><spring:message code="sprint.form.page.budget" text="Budget"/> :
                            <strong>${sprintFormDto.budgetBacklogsTime}</strong> <spring:message code="sprint.form.page.hours" text="hour(s)"/>
                        </div>
                        <form:hidden path="budgetBacklogsTime"/>
                    </li>
                </c:if>
            </ul>

        </div>
    </div>
    <div class="row">
        <div class="span5 offset2">
            <form:hidden path="addTask" id="addTask"/>
            <button type="submit" class="btn btn-success btn-large" addTask="false"><i class="icon-plus icon-white"></i>
                <spring:message code="sprint.form.page.save" text="Save"/>
            </button>
            <sec:authorize ifAnyGranted="ROLE_MASTER">
                <button type="submit" class="btn btn-info btn-large" addTask="true"><i class="icon-plus icon-white"></i>
                    <spring:message code="sprint.form.page.save.and.task" text="Save & Create task"/>
                </button>
            </sec:authorize>
            <a href="../sprint_overview?projectGuid=${sprintFormDto.projectGuid}" class="btn btn-link"><spring:message code="sprint.form.page.cancel" text="Cancel"/></a>
        </div>
    </div>
</form:form>
<script type="text/javascript">
    $(function () {
        var sprintForm = new SprintForm();
        sprintForm.initialDatePicker('${sprintFormDto.today}', '${SPRING_SECURITY_CONTEXT.authentication.principal.language.chinese?'zh-CN':'en'}');
    });
</script>
</body>
</html>