<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><spring:message code="change.password.page.title" text="Change Password"/></title>
</head>
<body>
<div class="row">
    <hr/>
    <div class="span3">
        <ul class="nav nav-tabs nav-stacked">
            <li>
                <a href="../my_profile">
                    <i class="icon-chevron-right pull-right"></i> <spring:message code="my.profile.page.menu.my.info" text="My-Info"/>
                </a>
            </li>
            <li>
                <a href="javascript:void(0)">
                    <strong>
                        <i class="icon-chevron-right pull-right"></i> <spring:message code="my.profile.page.menu.change.password" text="Change Password"/>
                    </strong>
                </a>
            </li>
            <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_MEMBER">
                <li>
                    <a href="../my_profile/team">
                        <i class="icon-chevron-right pull-right"></i> <spring:message code="my.profile.page.menu.my.team" text="My Team"/>
                    </a>
                </li>
            </sec:authorize>
        </ul>
    </div>
    <div class="span9">
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-ok-sign icon-white"></i>
                <span id="changePasswordSuccess" class="hide"><spring:message code="change.password.page.alert.change.success" text="Change password successful!"/></span>
            </div>
        </div>
        <div class="alert">
            <div>
                <span><strong><spring:message code="change.password.page.alert.note" text="Note!"/></strong>
                    <spring:message code="change.password.page.alert.note.info" text="Login again after change password successful"/>.</span>
            </div>
        </div>
        <div>
            <form:form commandName="changePasswordDto" action="change_password" cssClass="form-horizontal">
                <div class="control-group">
                    <label class="control-label"><spring:message code="change.password.page.form.user" text="User"/></label>

                    <div class="controls">
                        <input type="text" disabled="disabled"
                               value="${SPRING_SECURITY_CONTEXT.authentication.principal.nickName}"
                               class="input-xlarge"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="oldPassword"><spring:message code="change.password.page.form.old.password" text="Old Password"/></label>

                    <div class="controls">
                        <spring:message code="change.password.page.form.old.password.placeholder" text="Input old password" var="inputOldPassMessage"/>
                        <form:password path="oldPassword" id="oldPassword" placeholder="${inputOldPassMessage}"
                                       required="true"
                                       maxlength="255" cssClass="input-xlarge"/>
                        <form:errors path="oldPassword" cssClass="label label-warning"/>
                        <span class="help-block"><spring:message code="change.password.page.form.old.password.help" text="Please input old password"/>.</span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="newPassword"><spring:message code="change.password.page.form.new.password" text="New password"/></label>

                    <div class="controls">
                        <spring:message code="change.password.page.form.new.password.placeholder" text="Input new password, length >= 7" var="newPassHolderMessage"/>
                        <form:password path="newPassword" id="newPassword" required="true"
                                       placeholder="${newPassHolderMessage}"
                                       maxlength="255" cssClass="input-xlarge"/>
                        <form:errors path="newPassword" cssClass="label label-warning"/>
                        <span class="help-block"><spring:message code="change.password.page.form.new.password.help" text="Please input new password, length >= 7"/>.</span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="rePassword"><spring:message code="change.password.page.form.renew.password" text="Re new password"/></label>

                    <div class="controls">
                        <spring:message code="change.password.page.form.renew.password.placeholder" text="Input new password again" var="reNewPasswordMessage"/>
                        <form:password path="rePassword" id="rePassword" required="true"
                                       placeholder="${reNewPasswordMessage}"
                                       maxlength="255" cssClass="input-xlarge"/>
                        <form:errors path="rePassword" cssClass="label label-warning"/>
                        <span class="help-block"><spring:message code="change.password.page.form.renew.password.help" text="Please input new password again"/>.</span>
                    </div>
                </div>

                <div class="control-group">
                    <div class="controls">
                        <button type="submit" class="btn btn-success"><i
                                class="icon-ok icon-white"></i> <spring:message code="change.password.page.form.change" text="Change"/>
                        </button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<script>
    $(function () {
        $("#oldPassword").val("${changePasswordDto.oldPassword}");
        $("#newPassword").val("${changePasswordDto.newPassword}");
        $("#rePassword").val("${changePasswordDto.rePassword}");
    });
</script>
</body>
</html>