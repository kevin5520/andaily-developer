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
    <title><spring:message code="my.profile.page.title" text="My-Info"/></title>
</head>
<body>
<div class="row">
    <hr/>
    <div class="span3">
        <ul class="nav nav-tabs nav-stacked">
            <li>
                <a href="javascript:void(0)">
                    <strong>
                        <i class="icon-chevron-right pull-right"></i> <spring:message code="my.profile.page.menu.my.info" text="My-Info"/>
                    </strong>
                </a>
            </li>
            <li>
                <a href="my_profile/change_password">
                    <i class="icon-chevron-right pull-right"></i> <spring:message code="my.profile.page.menu.change.password" text="Change Password"/>
                </a>
            </li>
            <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_MEMBER">
                <li>
                    <a href="my_profile/team">
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
                <span id="updateProfileSuccess" class="hide"><spring:message code="my.profile.page.alert.update.my.info.success" text="Update my-info successful!"/></span>
            </div>
        </div>
        <div>
            <form:form commandName="profileDto" action="my_profile" cssClass="form-horizontal">
                <div class="control-group">
                    <label class="control-label"><spring:message code="my.profile.page.form.role" text="Role"/></label>

                    <div class="controls">
                        <input type="text" disabled="disabled" value="${profileDto.term.label}" class="input-xlarge"/>
                        <input type="hidden" name="term" value="${profileDto.term}"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="email"><spring:message code="my.profile.page.form.email" text="Email"/></label>

                    <div class="controls">
                        <spring:message code="my.profile.page.form.email.placeholder" text="Email address" var="emailAddressMessage"/>
                        <form:input path="email" id="email" placeholder="${emailAddressMessage}" required="true"
                                    maxlength="255" cssClass="input-xlarge"/>
                        <form:errors path="email" cssClass="label label-warning"/>
                        <span class="help-block"><spring:message code="my.profile.page.form.email.help" text="Email is login name, unique"/>.</span>
                        <form:hidden path="existEmail"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="nickName"><spring:message code="my.profile.page.form.nickname" text="Nickname"/></label>

                    <div class="controls">
                        <spring:message code="my.profile.page.form.nickname.placeholder" text="Nick name" var="nickNameMessage"/>
                        <form:input path="nickName" id="nickName" placeholder="${nickNameMessage}"
                                    maxlength="255" cssClass="input-xlarge"/>
                        <form:errors path="nickName" cssClass="label label-warning"/>
                        <span class="help-block"><spring:message code="my.profile.page.form.nickname.help" text="Your nick name"/>.</span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="cellPhone"><spring:message code="my.profile.page.form.cellphone" text="Cellphone"/></label>

                    <div class="controls">
                        <spring:message code="my.profile.page.form.cellphone.placeholder" text="Cell phone" var="cellPhoneMessage"/>
                        <form:input path="cellPhone" id="cellPhone" placeholder="${cellPhoneMessage}"
                                    maxlength="255" cssClass="input-xlarge"/>
                        <form:errors path="cellPhone" cssClass="label label-warning"/>
                        <span class="help-block"><spring:message code="my.profile.page.form.cellphone.help" text="Your cell phone number"/>.</span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label"><spring:message code="my.profile.page.form.language" text="Language"/></label>

                    <div class="controls">
                        <form:radiobutton path="language" value="ENGLISH"/> English&nbsp;
                        <form:radiobutton path="language" value="CHINESE"/> 中文
                        <form:errors path="language" cssClass="label label-warning"/>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <button type="submit" class="btn btn-success"><i
                                class="icon-ok icon-white"></i> <spring:message code="my.profile.page.form.save" text="Save"/>
                        </button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<script>
    $(function () {
        new MyProfile('${param.alert}');
    });
</script>
</body>
</html>