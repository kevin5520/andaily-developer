<%--
  User: Shengzhao Li
  Date: 13-10-21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="project.form.page.title" text="Project"/></title>

    <link href="${contextPath}/js/web/bootstrap/datepicker/datepicker.css" rel="stylesheet"/>
    <script src="${contextPath}/js/web/bootstrap/datepicker/bootstrap-datepicker.js"></script>
    <script src="${contextPath}/js/web/bootstrap/datepicker/bootstrap-datepicker.zh-CN.js"></script>

    <script src="${contextPath}/js/web/ckeditor/ckeditor.js"></script>
</head>
<body>
<div class="row">
    <hr/>
</div>
<form:form commandName="projectFormDto" id="projectForm">
    <form:hidden path="guid"/>
    <div class="row sprint-form">
        <div class="span8 well">
            <fieldset>
                <legend><spring:message code="project.form.page.title" text="Project"/></legend>
                <table class="width100">
                    <tr>
                        <td>
                            <label for="name"><spring:message code="project.form.page.project.name" text="Project name"/></label>
                            <spring:message code="project.form.page.project.name.placeholder" text="Project name" var="projectNameHolderMessage"/>
                            <form:input path="name" id="name" placeholder="${projectNameHolderMessage}" required="true"
                                        cssClass="span4" maxlength="255"/>
                            <form:errors path="name" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="project.form.page.project.name.help" text="Project name is required, max length: 255."/></span>
                        </td>
                        <td>
                            <label for="code"><spring:message code="project.form.page.project.code" text="Project code"/></label>
                            <spring:message code="project.form.page.project.code.holder" text="Project code" var="projectCodeHolderMessage"/>
                            <form:input path="code" id="code" placeholder="${projectCodeHolderMessage}" maxlength="255"
                                        cssClass="span2"/>
                            <form:errors path="code" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="project.form.page.project.code.help" text="A code of the project (optional)."/></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="finishDate"><spring:message code="project.form.page.finish.date" text="Finish date"/></label>

                            <div class="input-append date" id="finishDate">
                                <spring:message code="project.form.page.finish.date.holder" text="Finish date" var="finishDateHolderMessage"/>
                                <form:input path="finishDate" placeholder="${finishDateHolderMessage}" cssClass="span2"/>
                                <span class="add-on"><i class="icon-th"></i></span>
                            </div>
                            <form:errors path="finishDate" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="project.form.page.finish.date.help" text="Specify the project finish date (optional)."/></span>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    
                    <tr>
                        <td>
                            <label for="startDate"><spring:message text="Start date"/></label>

                            <div class="input-append date" id="startDate">
                                <spring:message text="Start date" var="startDateHolderMessage"/>
                                <form:input path="startDate" placeholder="${startDateHolderMessage}" cssClass="span2"/>
                                <span class="add-on"><i class="icon-th"></i></span>
                            </div>
                            <form:errors path="startDate" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message text="Specify the project start date (optional)."/></span>
                        </td>
                        <td>&nbsp;</td>
                    </tr>

                    
                    <tr>
                        <td colspan="2">
                            <label for="description"><spring:message code="project.form.page.description" text="Description"/></label>
                            <form:textarea path="description" cssClass="ckeditor" id="description"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
        <div class="span3 well">
            <fieldset>
                <legend><spring:message code="project.form.page.product.owner" text="Product Owner"/></legend>
                <ul class="unstyled">
                    <c:if test="${empty projectFormDto.availableProductOwners}">
                        <li><span class="text-warning"><spring:message code="project.form.page.not.available.product.owner"
                                                                       text="Not available product owners."/></span></li>
                    </c:if>
                    <c:forEach items="${projectFormDto.availableProductOwners}" var="po" varStatus="s">
                        <li>
                            <form:checkbox path="productOwnerGuids" value="${po.guid}"/> ${po.showName}
                        </li>

                        <%--For validation failed--%>
                        <form:hidden path="availableProductOwners[${s.index}].guid"/>
                        <form:hidden path="availableProductOwners[${s.index}].nickName"/>
                        <form:hidden path="availableProductOwners[${s.index}].email"/>
                    </c:forEach>
                </ul>
                <form:errors path="productOwnerGuids" cssClass="label label-warning"/>
            </fieldset>
        </div>
    </div>
    <div class="row">
        <div class="span5 offset2">
            <button type="submit" class="btn btn-success btn-large"><i class="icon-plus icon-white"></i>
                <spring:message code="project.form.page.save" text="Save"/>
            </button>
            <a href="../project_/overview" class="btn btn-link"><spring:message code="project.form.page.cancel" text="Cancel"/></a>
        </div>
    </div>
</form:form>
<script type="text/javascript">
    $(function () {
        new ProjectForm('${SPRING_SECURITY_CONTEXT.authentication.principal.language.chinese?'zh-CN':'en'}');
    });
</script>
</body>
</html>