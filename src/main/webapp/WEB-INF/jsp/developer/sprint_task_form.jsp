<%--
  User: Shengzhao Li
  Date: 13-8-18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="sprint.task.form.page.title" text="Task"/></title>

    <link href="${contextPath}/js/web/bootstrap/datepicker/datepicker.css" rel="stylesheet"/>
    <script src="${contextPath}/js/web/bootstrap/datepicker/bootstrap-datepicker.js"></script>

    <script src="${contextPath}/js/web/ckeditor/ckeditor.js"></script>
</head>
<body>
<div class="row">
    <hr/>
    <div class="span12">
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-ok-sign icon-white"></i>
                <%--<span id="successful" class="hide">Add/Edit the sprint successful!</span>--%>
            </div>
        </div>
        <div class="alert alert-info hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-info-sign icon-white"></i>
                <span id="sprintSuccessAddTask" class="hide"><spring:message code="sprint.task.form.page.alert.sprint.task.success" text="Save the sprint finished, please add task now"/></span>
                <span id="successAndAddNext" class="hide"><spring:message code="sprint.task.form.page.alert.save.task.success" text="Save the task finished, please add next task"/></span>
            </div>
        </div>
        <div class="alert alert-error hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-warning-sign icon-white"></i>
                <%--<span id="alertErrorInfo"></span>--%>
            </div>
        </div>
    </div>
</div>
<form:form commandName="sprintTaskFormDto" id="sprintTaskForm">
    <form:hidden path="guid"/>
    <div class="row sprint-task-form">
        <div class="span8 well">
            <fieldset>
                <legend>
                    <spring:message code="sprint.task.form.page.title" text="Task"/> (<a class="text-info" href="javascript:void(0);">${sprintTaskFormDto.sprintName}</a>)
                </legend>
                <table class="width100">
                    <tr>
                        <td>
                            <label for="name"><spring:message code="sprint.task.form.page.task.name" text="Task name"/></label>
                            <spring:message code="sprint.task.form.page.task.name.holder" text="Task name" var="taskNameHolderMessage"/>
                            <form:input path="name" id="name" placeholder="${taskNameHolderMessage}" cssClass="span4" required="true"
                                        maxlength="255"/>
                            <form:errors path="name" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="sprint.task.form.page.task.name.help" text="Task name is required, max length: 255."/></span>
                        </td>
                        <td>
                            <label for="estimateTime"><spring:message code="sprint.task.form.page.estimate.time" text="Estimate time(hour)"/></label>
                            <form:select path="estimateTime" id="estimateTime">
                                <form:options items="${sprintTaskFormDto.availableTimes}"/>
                            </form:select>
                            <form:errors path="estimateTime" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="sprint.task.form.page.estimate.time.help" text="The max time of the task is 4 hour"/></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label><spring:message code="sprint.task.form.page.priority" text="Priority"/></label>
                            <c:forEach items="${sprintTaskFormDto.priorities}" var="p">
                                <label class="radio inline">
                                    <form:radiobutton path="priority" value="${p.value}"/> ${p.label}
                                </label>
                            </c:forEach>
                            <form:errors path="priority" cssClass="label label-warning"/>
                            <span class="help-block">&nbsp;</span>
                        </td>
                        <td>
                            <label class="checkbox text-error">
                                <form:checkbox path="urgent" id="urgent"/> <spring:message code="sprint.task.form.page.urgent" text="Urgent"/>
                            </label>
                            <span class="help-block"><spring:message code="sprint.task.form.page.urgent.help" text="Please check if it is an urgent task"/></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label for="description"><spring:message code="sprint.task.form.page.description" text="Description"/></label>
                            <form:textarea path="description" cssClass="ckeditor" id="description"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
        <div class="span3 well">
            <fieldset>
                <legend><spring:message code="sprint.task.form.page.refer.a.backlog" text="Refer a backlog"/></legend>
            </fieldset>
            <ul class="unstyled">
                <c:if test="${empty sprintTaskFormDto.backlogs}">
                    <li>
                        <label><spring:message code="sprint.task.form.page.no.available.backlogs" text="The sprint no available backlogs"/></label>
                    </li>
                </c:if>
                <c:forEach items="${sprintTaskFormDto.backlogs}" var="b" varStatus="s">
                    <li>
                        <label class="radio">
                            <input type="radio" name="backlogGuid"
                                   value="${b.guid}" ${b.selected?'checked':''}/>#${b.number} ${b.name}
                            (<strong>${b.estimateTime}</strong>)
                            <spring:message code="sprint.task.form.page.referenced.tasks" text="Amount of referenced tasks" var="referencedTasksTitle"/>
                            <c:if test="${b.showReferencedTasks}">[<strong class="text-warning" title="${referencedTasksTitle}">${b.referencedTasks}</strong>]</c:if>
                        </label>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="span5 offset2">
            <form:hidden path="addNext" id="addNext"/>
            <button type="submit" class="btn btn-success btn-large" addNext="false"><i class="icon-plus icon-white"></i>
                <spring:message code="sprint.task.form.page.save" text="Save"/>
            </button>
            <button type="submit" class="btn btn-info btn-large" addNext="true"><i class="icon-plus icon-white"></i>
                <spring:message code="sprint.task.form.page.save.and.next" text="Save & Create next"/>
            </button>
            <a href="../../developer?currentSprint.guid=${sprintTaskFormDto.sprintGuid}&status=${sprintTaskFormDto.status}"
               class="btn btn-link"><spring:message code="sprint.task.form.page.cancel" text="Cancel"/></a>
        </div>
    </div>
</form:form>
<script type="text/javascript">
    $(function () {
        new SprintTaskForm("${param.alert}");
    });
</script>
</body>
</html>