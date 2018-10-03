<%--
  User: Shengzhao Li
  Date: 13-9-8
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="backlog.form.page.title" text="Backlog"/></title>
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
                <span id="successAddNext" class="hide"><spring:message code="backlog.form.page.alert.save.finished" text="Save the backlog finished, please add new one."/></span>
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
<form:form commandName="backlogFormDto" id="backlogForm" enctype="multipart/form-data">
    <form:hidden path="guid"/>
    <div class="row backlog-form">
        <div class="span8 well">
            <fieldset>
                <legend><spring:message code="backlog.form.page.title" text="Backlog"/></legend>
                <table class="width100">
                    <tr>
                        <td>
                            <label><spring:message code="backlog.form.page.form.type" text="Type"/></label>
                            <c:forEach items="${backlogFormDto.availableTypes}" var="p">
                                <label class="radio inline">
                                    <form:radiobutton path="type" value="${p.value}"/> ${p.label}
                                </label>
                            </c:forEach>
                            <form:errors path="type" cssClass="label label-warning"/>
                            <span class="help-block">&nbsp;</span>
                        </td>
                        <td>
                            <label><spring:message code="backlog.form.page.form.priority" text="Priority"/></label>
                            <c:forEach items="${backlogFormDto.availablePriorities}" var="p">
                                <label class="radio inline">
                                    <form:radiobutton path="priority" value="${p.value}"/> ${p.label}
                                </label>
                            </c:forEach>
                            <form:errors path="priority" cssClass="label label-warning"/>
                            <span class="help-block">&nbsp;</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="estimateTime"><spring:message code="backlog.form.page.form.estimate" text="Estimate time(hour)"/></label>
                            <input type="number" name="estimateTime" id="estimateTime"
                                   placeholder="Estimate of the backlog"
                                   required="true" value="${backlogFormDto.estimateTime}"/>
                            <form:errors path="estimateTime" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="backlog.form.page.form.estimate.help" text="Suggest the estimate do not more than 16 hours"/></span>
                        </td>
                        <td>
                            <label for="projectGuid"><spring:message code="backlog.form.page.form.project" text="Project"/></label>

                            <spring:message code="backlog.form.page.form.project.choose" text="Choose a project" var="chooseProjectMessage"/>
                            <c:if test="${backlogFormDto.newly}" var="newly">
                                <form:select path="projectGuid" id="projectGuid">
                                    <%--disabled="${ empty backlogFormDto.projectGuid?'false':'true'}"--%>
                                    <form:option value="" label="${chooseProjectMessage}"/>
                                    <form:options items="${backlogFormDto.availableProjects}" itemLabel="name"
                                                  itemValue="guid"/>
                                </form:select>
                            </c:if>
                            <c:if test="${not newly}">
                                <form:select path="projectGuid" id="projectGuid">
                                    <form:options items="${backlogFormDto.availableProjects}" itemLabel="name"
                                                  itemValue="guid"/>
                                </form:select>
                            </c:if>
                            <form:errors path="projectGuid" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="backlog.form.page.form.project.help" text="Specify a project"/></span>

                                <%--For bind list data if validation failed--%>
                            <c:forEach items="${backlogFormDto.availableProjects}" var="p" varStatus="s">
                                <input type="hidden" name="availableProjects[${s.index}].guid" value="${p.guid}"/>
                                <input type="hidden" name="availableProjects[${s.index}].name" value="${p.name}"/>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label for="content"><spring:message code="backlog.form.page.form.content" text="Content"/> <form:errors path="content"
                                                                      cssClass="label label-warning"/></label>
                            <form:textarea path="content" cssClass="ckeditor" id="content"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
        <div class="span3 well">
            <fieldset>
                <legend><spring:message code="backlog.form.page.form.documents" text="Documents"/></legend>
            </fieldset>
            <p>
                <input type="file" name="file1"/>
            </p>

            <p>
                <input type="file" name="file2"/>
            </p>

            <p>
                <input type="file" name="file3"/>
            </p>

            <p>
                <span class="help-block"><spring:message code="backlog.form.page.form.documents.help" text="Documents of the backlog(optional), max upload  3 files."/></span>
            </p>

            <div>
                <ul class="unstyled">
                    <spring:message code="backlog.form.page.form.document.remove.confirm" text="Confirm remove" var="removeConfirmMessage"/>

                    <c:forEach items="${backlogFormDto.documentDtos}" var="d">
                        <li>
                            <a href="../file/download/${d.fileDto.guid}">${d.fileDto.name}</a>
                            <span class="muted">(${d.fileDto.sizeAsText})</span>
                            <spring:message code="backlog.form.page.form.document.remove.content" text="Are you sure remove the document({0}) ?"
                                            arguments="${d.fileDto.name}" var="removeContentMessage"/>
                            <a href="../backlog_action/remove_document/${d.guid}?backlogGuid=${backlogFormDto.guid}"
                               confirm-title="${removeConfirmMessage}"
                               confirm-content="${removeContentMessage}"
                               class="confirm">
                                <i class="icon-remove"></i></a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="span5 offset2">
            <form:hidden path="addNext" id="addNext"/>
            <button type="submit" class="btn btn-success btn-large" addNext="false"><i class="icon-plus icon-white"></i>
                <spring:message code="backlog.form.page.form.save" text="Save"/>
            </button>
            <button type="submit" class="btn btn-info btn-large" addNext="true"><i class="icon-plus icon-white"></i>
                <spring:message code="backlog.form.page.form.save.next" text="Save & Add next"/>
            </button>
            <a href="../backlog_overview?projectGuid=${backlogFormDto.projectGuid}" class="btn btn-link"><spring:message
                    code="backlog.form.page.form.cancel" text="Cancel"/></a>
        </div>
    </div>
</form:form>
<script type="text/javascript">
    $(function () {
        new BacklogForm('${param.alert}');
    });
</script>
</body>
</html>