<%--
  User: Shengzhao Li
  Date: 13-9-8
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="backlog.overview.page.title" text="Backlog"/></title>
</head>
<body>
<div class="row">
    <hr/>
    <div class="span8">
        <form action="" id="backlogForm" class="form-inline">
            <c:if test="${backlogOverviewDto.specifyProject}" var="specifyProject">
                <spring:message code="backlog.overview.page.form.project" text="Project" var="projectMessage"/>
                <span class="label" title="${projectMessage}">${backlogOverviewDto.list[0].projectDto.name}</span>
            </c:if>
            <c:if test="${not empty backlogOverviewDto.sprintDto}" var="specifySprint">
                <spring:message code="backlog.overview.page.form.sprint" text="Project" var="sprintMessage"/>
                <span class="label" title="${sprintMessage}">${backlogOverviewDto.sprintDto.name}</span>
            </c:if>
            <input type="hidden" name="projectGuid" value="${backlogOverviewDto.projectGuid}"/>
            <input type="hidden" name="sprintGuid" value="${backlogOverviewDto.sprintGuid}"/>

            <spring:message code="backlog.overview.page.number" text="Backlog number" var="backlogNumberMessage"/>
            <input type="text" name="number" value="${backlogOverviewDto.number}" placeholder="${backlogNumberMessage}"
                   style="width: 120px;"/>
            <select name="type" id="backlogTypeSelect" style="width: 120px;">
                <option value=""><spring:message code="backlog.overview.page.all.types" text="All types"/></option>
                <c:forEach items="${backlogOverviewDto.availableTypes}" var="s">
                    <option value="${s}" ${s eq backlogOverviewDto.type?'selected':''}>${s.label}</option>
                </c:forEach>
            </select>
            &nbsp;
            <select name="priority" id="backlogPrioritySelect" style="width: 150px;">
                <option value=""><spring:message code="backlog.overview.page.all.priorities" text="All priorities"/></option>
                <c:forEach items="${backlogOverviewDto.availablePriorities}" var="s">
                    <option value="${s}" ${s eq backlogOverviewDto.priority?'selected':''}>${s.label}</option>
                </c:forEach>
            </select>
            <button class="btn" type="submit"><i class="icon-search"></i> <spring:message code="backlog.overview.page.form.search" text="Search"/></button>
        </form>
    </div>
    <div class="span2">
        <span class="muted">
            <spring:message code="backlog.overview.page.size.backlogs" text="Size of backlogs"/>: <strong>${backlogOverviewDto.totalSize}</strong>
        </span>
    </div>
    <div class="span2">
        <sec:authorize ifAnyGranted="ROLE_MASTER,ROLE_PRODUCT_OWNER">
            <a class="btn btn-success btn-small"
               href="backlog_form/create?pGuid=${(not empty backlogOverviewDto.projectGuid)?backlogOverviewDto.projectGuid:backlogOverviewDto.sprintDto.projectDto.guid}"><i
                    class="icon-plus icon-white"></i> <spring:message code="backlog.overview.page.create.backlogs" text="Create backlog"/></a>
        </sec:authorize>
    </div>

</div>
<div class="row">
    <div class="span12">
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-ok-sign icon-white"></i>
                <span id="successAddBacklog" class="hide"><spring:message code="backlog.overview.page.alert.add.edit.success" text="Add/Edit the backlog successful!"/></span>
            </div>
        </div>
        <div class="alert alert-info hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-info-sign icon-white"></i>
                <span id="archiveBacklogInfo" class="hide"><spring:message code="backlog.overview.page.alert.archive.finish" text="Archive the backlog already finished."/></span>
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
        <spring:message code="backlog.overview.page.table.content" text="Content" var="contentMessage"/>
        <spring:message code="backlog.overview.page.table.estimate" text="Estimate" var="estimateMessage"/>
        <spring:message code="backlog.overview.page.table.project" text="Project" var="projectMessage"/>
        <spring:message code="backlog.overview.page.table.type" text="Type" var="typeMessage"/>
        <spring:message code="backlog.overview.page.table.priority" text="Priority" var="priorityMessage"/>
        <spring:message code="backlog.overview.page.table.sprint" text="Sprint" var="sprintMessage"/>

        <displaytag:table list="${backlogOverviewDto}" class="table table-hover table-striped" id="backlog"
                          form="backlogForm">
            <displaytag:column title="#" property="number"/>
            <displaytag:column title="${contentMessage}" headerClass="width50">
                <div class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            ${fun:substring(backlog.content, 0, 100)}
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                        <custom:backlog_overview_menu_actions backlog="${backlog}"/>
                    </ul>
                </div>
            </displaytag:column>

            <c:if test="${specifyProject}">
                <displaytag:column title="${estimateMessage}" property="estimateTime" style="vertical-align: middle;"/>
            </c:if>
            <c:if test="${not specifyProject}">
                <displaytag:column title="${projectMessage}">
                    <a href="${contextPath}/developer/backlog_overview?projectGuid=${backlog.projectDto.guid}">${backlog.projectDto.name}</a>
                </displaytag:column>
            </c:if>

            <displaytag:column title="${typeMessage}" property="type.label" style="vertical-align: middle;"/>
            <displaytag:column title="${priorityMessage}" property="priority.label" style="vertical-align: middle;"
                               class="${backlog.priority} text-warning"/>
            <c:if test="${not specifySprint}">
                <displaytag:column title="${sprintMessage}" style="vertical-align: middle;">
                    <c:if test="${backlog.referSprint}" var="hasSprint">
                        <a href="${contextPath}/developer/backlog_overview?sprintGuid=${backlog.sprint.guid}">${backlog.sprint.name}</a>
                    </c:if>
                    <c:if test="${not hasSprint}">
                        ...
                    </c:if>
                </displaytag:column>
            </c:if>
            <displaytag:column title="&nbsp;" style="vertical-align: middle;">
                <a href="#${backlog.guid}Modal" class="backlogDetails" data-toggle="modal"><i
                        class="icon-info-sign"></i></a>
                <%-- the sprint modal--%>
                <div id="${backlog.guid}Modal" class="modal hide fade" tabindex="-1" role="dialog"
                     aria-labelledby="${backlog.guid}ModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="${backlog.guid}ModalLabel"><spring:message code="backlog.overview.page.table.backlog.details" text="Backlog details"/></h3>
                    </div>
                    <div class="modal-body">
                            ${backlog.htmlTip}
                        <div>
                            <br/>
                            <ul class="unstyled">
                                <c:forEach items="${backlog.documentDtos}" var="d">
                                    <li>
                                        <a href="file/download/${d.fileDto.guid}">${d.fileDto.name}</a>
                                        <span class="muted">(${d.fileDto.sizeAsText})</span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="backlog.overview.page.table.close" text="Close"/></button>
                    </div>
                </div>
                <c:if test="${backlog.documentsSize > 0}">
                    <spring:message code="backlog.overview.page.table.download.documents" text="Download documents" var="downloadDocumentsMessage"/>
                    <a href="backlog_action/download_documents/${backlog.guid}" title="${downloadDocumentsMessage}"><i
                            class="icon-download"></i></a>
                </c:if>
            </displaytag:column>
        </displaytag:table>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        new BacklogOverview("${param.alert}");
    });
</script>
</body>
</html>