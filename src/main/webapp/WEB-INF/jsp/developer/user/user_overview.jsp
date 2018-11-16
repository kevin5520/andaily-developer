<%--
  User: Shengzhao Li
  Date: 13-11-25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="user.overview.page.title" text="User"/></title>
</head>
<body>
<div class="row">
    <hr/>
    <div class="span8">
        <form action="" id="userForm" class="form-inline">
            <spring:message code="user.overview.page.form.email.address" text="Email address" var="emailAddressMessage"/>
            <input type="text" name="email" value="${userOverviewDto.email}" title="${emailAddressMessage}"
                   placeholder="${emailAddressMessage}" class="input-medium"/>
            <select name="term" class="input-medium">
                <option value=""><spring:message code="user.overview.page.form.all.terms" text="All Terms"/></option>
                <c:forEach items="${userOverviewDto.terms}" var="t">
                    <option value="${t.value}" ${t eq userOverviewDto.term?'selected':''}>${t.label}</option>
                </c:forEach>
            </select>
            <select name="teamGuid" class="input-medium">
                <option value=""><spring:message code="user.overview.page.form.all.teams" text="All Teams"/></option>
                <c:forEach items="${userOverviewDto.teams}" var="t">
                    <option value="${t.guid}" ${t.guid eq userOverviewDto.teamGuid?'selected':''}>${t.name}</option>
                </c:forEach>
            </select>
            &nbsp;
            <label class="checkbox">
                <input type="checkbox" name="archived" ${userOverviewDto.archived?'checked':''}/> <spring:message code="user.overview.page.form.archived" text="archived"/>
            </label>
            &nbsp;
            <button class="btn" type="submit"><i class="icon-search"></i> <spring:message code="user.overview.page.form.search" text="Search"/></button>
        </form>
    </div>
    <div class="span2">
        <span class="muted">
            <spring:message code="user.overview.page.size.users" text="Size of users"/>: <strong>${userOverviewDto.totalSize}</strong>
        </span>
    </div>
    <div class="span2">
        <a class="btn btn-success btn-small" href="user_form/create"><i
                class="icon-plus icon-white"></i> <spring:message code="user.overview.page.add.user" text="Add user"/></a>
                
        <a class="btn btn-success btn-small" href="${contextPath}/developer/testController/001"><i
                class="icon-plus icon-white"></i> <spring:message  text="test button"/></a>
    </div>

</div>
<div class="row">
    <div class="span12">
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-ok-sign icon-white"></i>
                <span id="saveUserSuccess" class="hide"><spring:message code="user.overview.page.alert.add.edit.success" text="Add/Edit the user successful!"/></span>
                <span id="archiveSuccess" class="hide"><spring:message code="user.overview.page.alert.archive.success" text="Archive the user successful!"/></span>
                <span id="activeSuccess" class="hide"><spring:message code="user.overview.page.alert.active.success" text="Active the user successful!"/></span>
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
<div class="row">
    <div class="span9">
        <spring:message code="user.overview.page.table.email" text="Email address" var="emailMessage"/>
        <spring:message code="user.overview.page.table.nick.name" text="Nick name" var="nickNameMessage"/>
        <spring:message code="user.overview.page.table.cellphone" text="Cellphone" var="cellphoneMessage"/>
        <spring:message code="user.overview.page.table.term" text="Term" var="termMessage"/>
        <spring:message code="user.overview.page.table.team" text="Team" var="teamMessage"/>

        <displaytag:table list="${userOverviewDto}" class="table table-hover table-striped" id="user"
                          form="userForm">
            <displaytag:column title="${emailMessage}">
                <div class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            ${user.email}
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                        <custom:user_overview_menu_actions user="${user}"/>
                    </ul>
                </div>
            </displaytag:column>
            <displaytag:column title="${nickNameMessage}" property="nickName"/>
            <displaytag:column title="${cellphoneMessage}" property="cellPhone"/>
            <displaytag:column title="${termMessage}" property="scrumTerm.label"/>
            <displaytag:column title="${teamMessage}" property="teamDto.name"/>
        </displaytag:table>
    </div>
    <div class="span3">
        <div class="well">
            <div>
                <h5><a href="javascript:void(0);"><spring:message code="user.overview.page.statistics" text="Statistics"/></a></h5>
            </div>

            <div>
                <table class="table">
                    <tr class="info">
                        <td>${userOverviewDto.termDataList[0].term.label}</td>
                        <td><strong>${userOverviewDto.termDataList[0].count}</strong></td>
                    </tr>
                    <tr class="success">
                        <td>${userOverviewDto.termDataList[1].term.label}</td>
                        <td><strong>${userOverviewDto.termDataList[1].count}</strong></td>
                    </tr>
                    <tr style="background-color: white;">
                        <td>${userOverviewDto.termDataList[2].term.label}</td>
                        <td><strong>${userOverviewDto.termDataList[2].count}</strong></td>
                    </tr>
                    <tr class="warning">
                        <td>${userOverviewDto.termDataList[3].term.label}</td>
                        <td><strong>${userOverviewDto.termDataList[3].count}</strong></td>
                    </tr>
                </table>
            </div>

        </div>
    </div>
</div>

<%--JS i18n messages--%>
<div class="hidden">
    <span id="resetPassTitleMessage"><spring:message code="user.overview.page.js.reset.password" text="Reset password"/></span>
</div>

<script type="text/javascript">
    $(function () {
        new UserOverview('${param.alert}');
    });
</script>
</body>
</html>