<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><spring:message code="sprint.manage.backlogs.page.title" text="Manage Backlogs"/></title>

    <style>
        select option {
            padding: 3px;
        }
    </style>
</head>
<body>
<hr/>
<div class="row">
    <div class="span12">
        <h2>${manageBacklogsDto.sprintDto.name}
            <small> <spring:message code="sprint.manage.backlogs.page.title" text="Manage Backlogs"/></small>
        </h2>
        <p class="muted">
            <i class="icon-info-sign"></i>
            <spring:message code="sprint.manage.backlogs.page.tip"
                            text="Remove the sprint unused backlog(s) or add no-related backlog(s) to current sprint. If the backlog is disabled, it's mean the backlog already used, do not remove it."/>
        </p>
    </div>
</div>
<div class="row">
    <div class="span5">
        <input type="hidden" id="sprintGuid" value="${manageBacklogsDto.sprintDto.guid}"/>
        <label for="sprintSelect"><spring:message code="sprint.manage.backlogs.page.sprint.backlogs" text="The sprint backlogs"/></label>
        <select size="20" class="span5" id="sprintSelect">
            <c:forEach items="${manageBacklogsDto.sprintBacklogs}" var="b">
                <option value="${b.guid}" ${b.selected?'disabled':''} class="${b.selected?'muted':''}"
                        title="${b.content}">${b.content}</option>
            </c:forEach>
        </select>
        <span class="help-block"><spring:message code="sprint.manage.backlogs.page.total.backlogs" text="Total {0} backlog(s)"
                                                 arguments="${manageBacklogsDto.sprintBacklogsSize}"/></span>
    </div>
    <div class="span1">
        <br/>
        <br/>
        <br/>
        <button class="btn btn-block" disabled="disabled" id="moveToUnused" title="Remove the backlog from the sprint">
            <i class="icon-forward"></i></button>
        <br/>
        <br/>
        <button class="btn btn-block" disabled="disabled" id="moveToSprint" title="Move the backlog to the sprint"><i
                class="icon-backward"></i></button>
    </div>
    <div class="span6">
        <label for="unreferSelect"><spring:message code="sprint.manage.backlogs.page.no.related.backlogs" text="No-related backlogs"/></label>
        <select size="20" class="span5" id="unreferSelect">
            <c:forEach items="${manageBacklogsDto.unusedBacklogs}" var="b">
                <option value="${b.guid}" title="${b.content}">${b.content}</option>
            </c:forEach>
        </select>
        <span class="help-block"><spring:message code="sprint.manage.backlogs.page.total.no.related.backlogs" text="Total {0} backlog(s)"
                                                 arguments="${manageBacklogsDto.unusedBacklogsSize}"/></span>
    </div>
</div>
<div class="row">
    <div class="span1 offset5">
        <a href="javascript:history.back();" class="btn btn-inverse btn-block">&laquo; <spring:message code="sprint.manage.backlogs.page.back" text="Back"/></a>
    </div>
</div>

<%--JS i18n messages--%>
<div class="hidden">
    <span id="confirmRemoveBacklogMessage"><spring:message code="sprint.manage.backlogs.page.js.confirm.remove.backlog"
                                                           text="Are you sure remove the backlog from the sprint?"/></span>
    <span id="removeBacklogFailedMessage"><spring:message code="sprint.manage.backlogs.page.js.remove.backlog.failed"
                                                           text="Remove the backlog from sprint failed,make sure the backlog in the sprint firstly !!!"/></span>
    <span id="addBacklogMessage"><spring:message code="sprint.manage.backlogs.page.js.add.backlog.confirm"
                                                           text="Are you sure add the backlog to the sprint?"/></span>
    <span id="addBacklogFailedMessage"><spring:message code="sprint.manage.backlogs.page.js.add.backlog.failed"
                                                           text="Add the backlog to the sprint failed, make sure the backlog not related any sprint yet."/></span>
</div>

<script>
    $(function () {
        new SprintManageBacklogs();
    });
</script>
</body>
</html>