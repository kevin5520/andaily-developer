<%--
  User: Shengzhao Li
  Date: 13-11-30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="user.form.page.title" text="User"/></title>
</head>
<body>
<div class="row">
    <hr/>
</div>
<div class="row">
    <div class="span12">
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-ok-sign icon-white"></i>
                <span id="saveUserSuccess" class="hide"><spring:message code="user.form.page.alert.add.edit.success" text="Add/Edit the user successful!"/></span>
            </div>
        </div>
        <div class="alert alert-info hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-info-sign icon-white"></i>
                <%--<span id="finishSprintInfo" class="hide">The sprint has been finished.!</span>--%>
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
<form:form commandName="userFormDto" id="userForm">
    <form:hidden path="guid"/>
    <div class="row sprint-form">
        <div class="span8 well">
            <fieldset>
                <legend><spring:message code="user.form.page.title" text="User"/></legend>
                <table class="width100">
                    <tr>
                        <td>
                            <label for="email"><spring:message code="user.form.page.form.email.address" text="Email address"/></label>
                            <spring:message code="user.form.page.form.email.address.holder" text="Email address" var="emailAddressHolderMessage"/>
                            <form:input path="email" id="email" placeholder="${emailAddressHolderMessage}" required="true"
                                        maxlength="255"/>
                            <form:errors path="email" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="user.form.page.form.email.address.help" text="Email is login name, unique."/></span>
                            <form:hidden path="existEmail"/>
                        </td>
                        <td>
                            <label for="nickName"><spring:message code="user.form.page.form.nickname" text="Nickname"/></label>
                            <spring:message code="user.form.page.form.nickname.holder" text="Nickname" var="nicknameHolderMessage"/>
                            <form:input path="nickName" id="nickName" placeholder="${nicknameHolderMessage}"
                                        maxlength="255"/>
                            <form:errors path="nickName" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="user.form.page.form.nickname.help" text="Your nickname."/></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="cellPhone"><spring:message code="user.form.page.form.cellphone" text="Cellphone"/></label>
                            <spring:message code="user.form.page.form.cellphone.holder" text="Cellphone" var="cellphoneHolderMessage"/>
                            <form:input path="cellPhone" id="cellPhone" placeholder="${cellphoneHolderMessage}"
                                        maxlength="255"/>
                            <form:errors path="cellPhone" cssClass="label label-warning"/>
                            <form:hidden path="existCellPhone"/>
                            <span class="help-block">&nbsp;</span>
                        </td>
                        <td>
                            <label for="teamGuid"><spring:message code="user.form.page.form.team" text="Team"/></label>
                            <spring:message code="user.form.page.form.team.empty" text="Select a team" var="emptyTeamMessage"/>
                            <form:select path="teamGuid" id="teamGuid">
                                <form:option value="" label="${emptyTeamMessage}"/>
                                <form:options items="${userFormDto.teams}" itemLabel="name" itemValue="guid"/>
                            </form:select>
                            <form:errors path="teamGuid" cssClass="label label-warning"/>
                            <span class="help-block"><spring:message code="user.form.page.form.team.help" text="The team of the user, optional."/></span>

                                <%--For bind if validation failed--%>
                            <c:forEach items="${userFormDto.teams}" var="t" varStatus="s">
                                <input type="hidden" name="teams[${s.index}].guid" value="${t.guid}"/>
                                <input type="hidden" name="teams[${s.index}].name" value="${t.name}"/>
                            </c:forEach>
                        </td>
                    </tr>
                    <c:if test="${userFormDto.newly}">
                        <tr>
                            <td>
                                <label for="password"><spring:message code="user.form.page.form.password" text="Password"/></label>
                                <spring:message code="user.form.page.form.password.holder" text="Password, length >= 7" var="passwordHolderMessage"/>
                                <form:password path="password" id="password" placeholder="${passwordHolderMessage}"
                                               required="true" maxlength="255"/>
                                <form:errors path="password" cssClass="label label-warning"/>
                                <span class="help-block"><spring:message code="user.form.page.form.password.help" text="User password, length >= 7."/></span>
                            </td>
                            <td>
                                <label for="rePassword"><spring:message code="user.form.page.form.repassword" text="Re-password"/></label>
                                <spring:message code="user.form.page.form.repassword.holder" text="Re-password" var="rePassHolderMessage"/>
                                <form:password path="rePassword" id="rePassword" placeholder="${rePassHolderMessage}"
                                               required="true" maxlength="255"/>
                                <form:errors path="rePassword" cssClass="label label-warning"/>
                                <span class="help-block"><spring:message code="user.form.page.form.repassword.help" text="Input user password again."/></span>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td colspan="2">
                             <label for="description"><spring:message code="user.form.page.form.description" text="Re-password"/></label>
                            <spring:message code="user.form.page.form.description.holder" text="More information about the user" var="descriptionHolderMessage"/>
                            <form:textarea path="description" id="description" rows="3" cssStyle="width:77%;" placeholder="${descriptionHolderMessage}"/>
                            <form:errors path="description" cssClass="label label-warning"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
        <div class="span3 well">
            <fieldset>
                <legend><spring:message code="user.form.page.user.role" text="User Role"/></legend>
            </fieldset>
            <ul class="unstyled" id="backlogUl">
                <c:forEach items="${userFormDto.terms}" var="t">
                    <li>
                        <label class="radio">
                            <input type="radio" name="scrumTerm"
                                   value="${t.value}" ${userFormDto.scrumTerm eq t?'checked':''}/> ${t.label}
                        </label>
                    </li>
                </c:forEach>
                <li>
                    <form:errors path="scrumTerm" cssClass="label label-warning"/>
                </li>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="span5 offset2">
            <form:hidden path="addNext" id="addNext"/>
            <button type="submit" class="btn btn-success btn-large" addNext="false"><i class="icon-plus icon-white"></i>
                <spring:message code="user.form.page.save" text="Save"/>
            </button>
            <button type="submit" class="btn btn-info btn-large" addNext="true"><i class="icon-plus icon-white"></i>
                <spring:message code="user.form.page.save.next" text="Save & Add next"/>
            </button>
            <a href="../overview?teamGuid=${userFormDto.teamGuid}" class="btn btn-link"><spring:message code="user.form.page.cancel" text="Cancel"/></a>
        </div>
    </div>
</form:form>

<script type="text/javascript">
    $(function () {
        var userForm = new UserForm('${param.alert}');
        userForm.setPasswordValues('${userFormDto.password}', '${userFormDto.rePassword}');
    });
</script>
</body>
</html>