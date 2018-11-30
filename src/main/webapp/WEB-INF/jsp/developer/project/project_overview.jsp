<%--
  User: Shengzhao Li
  Date: 13-10-20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="project.overview.page.title" text="Project"/></title>
</head>
<body>
<div class="row">
    <hr/>
    <div class="span8">
        <form action="" id="projectForm" class="form-inline">
            <spring:message code="project.overview.page.form.project.name" text="Project name" var="projectNameMessage"/>
            <input type="text" name="name" value="${projectOverviewDto.name}" placeholder="${projectNameMessage}" style="width:120px"/>
            <spring:message  text="Project code" var="projectNameMessage"/>
            <input type="text" name="projectCode" value="${projectOverviewDto.projectCode}" placeholder="${projectNameMessage}"style="width:120px"/>
            <c:if test="${projectOverviewDto.showTeamCondition}">
                <select name="teamGuid" class="input-medium">
                    <option value=""><spring:message code="project.overview.page.form.all.teams" text="All Teams"/></option>
                    <c:forEach items="${projectOverviewDto.teamDtos}" var="t">
                        <option value="${t.guid}" ${t.guid eq projectOverviewDto.teamGuid?'selected':''}>${t.name}</option>
                    </c:forEach>
                </select>
            </c:if>
            <c:if test="${projectOverviewDto.showProductOwnerCondition}">
                <select name="productOwnerGuid" class="input-medium">
                    <option value=""><spring:message code="project.overview.page.form.all.product.owners" text="All Product Owners"/></option>
                    <c:forEach items="${projectOverviewDto.productOwners}" var="t">
                        <option value="${t.guid}" ${t.guid eq projectOverviewDto.productOwnerGuid?'selected':''}>${t.showName}</option>
                    </c:forEach>
                </select>
            </c:if>

            <button class="btn btn-default" type="submit"><i class="icon-search"></i> <spring:message code="project.overview.page.form.search" text="Search"/></button>
        </form>
    </div>
    <div class="span2">
        <span class="muted">
            <spring:message code="project.overview.page.size.projects" text="Size of projects"/>: <strong>${projectOverviewDto.totalSize}</strong>
        </span>
    </div>
    <div class="span2">
        <sec:authorize ifAnyGranted="ROLE_SUPER_MAN,ROLE_PRODUCT_OWNER">
            <a class="btn btn-success btn-small" href="../project_form/create"><i
                    class="icon-plus icon-white"></i> <spring:message code="project.overview.page.create.project" text="Create project"/></a>
        </sec:authorize>
    </div>

</div>
<div class="row">
    <div class="span12">
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-ok-sign icon-white"></i>
                <span id="projectFormSuccess" class="hide"><spring:message code="project.overview.page.alert.add.edit.success" text="Add/Edit the project successful!"/></span>
            </div>
        </div>
        <div class="alert alert-info hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-info-sign icon-white"></i>
                <span id="archiveProjectInfo" class="hide"><spring:message code="project.overview.page.alert.archive.finished" text="Archive the project already finished!"/></span>
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
    <div class="span12">
        <spring:message code="project.overview.page.table.name" text="Name" var="nameMessage"/>
        <spring:message code="project.overview.page.table.code" text="Code" var="codeMessage"/>
        
    
        <spring:message text="Start date" var="startDateMessage"/>
        
        <spring:message code="project.overview.page.table.finish.date" text="Finish date" var="finishDateMessage"/>
        
        <spring:message text="Status" var="statusMessage"/>
        
        
        <spring:message code="project.overview.page.table.product.owner" text="Product owner" var="productOwnerMessage"/>
        <spring:message code="project.overview.page.table.team" text="Team" var="teamMessage"/>
       
        
        <displaytag:table list="${projectOverviewDto}" class="table table-hover table-striped" id="project"
                          form="projectForm">
            
            <displaytag:column title="${nameMessage}">
                <div class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            ${project.name}
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                        <custom:project_overview_menus project="${project}"/>
                    </ul>
                </div>
            </displaytag:column>
            <displaytag:column title="${codeMessage}" property="code" style="vertical-align: middle;"/>
            
			<displaytag:column title="${startDateMessage}" property="startDateAsText" style="vertical-align: middle;" class="text-success"/>
            
            <displaytag:column title="${finishDateMessage}" property="finishDateAsText" style="vertical-align: middle;" class="text-success"/>
            
            <displaytag:column title="${statusMessage}" property="status" style="vertical-align: middle;"/>
            
            <displaytag:column title="${productOwnerMessage}" property="productOwnersAsText" style="vertical-align: middle;"/>
            <displaytag:column title="${teamMessage}" property="teamsAsText" style="vertical-align: middle;"/>
           
            <displaytag:column title="Create time" property="createTime" style="vertical-align: middle;"/>

            <displaytag:column title="Description" property="description" style="vertical-align: middle;">
            
            </displaytag:column>
        </displaytag:table>
    </div>
</div>

<%--JS i18n messages--%>
<div class="hidden">
    <span id="projectDescMessage"><spring:message code="project.overview.page.js.project.description"
                                                  text="Project description"/></span>
</div>
<script type="text/javascript">
    $(function () {
        new ProjectOverview('${param.alert}');
    });
</script>
</body>
</html>