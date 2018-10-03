<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><spring:message code="sprint.activity.page.title" text="Activity"/></title>
</head>
<body>
<hr/>
<div class="row">
    <div class="span12">
        <div class="pull-right">
            <a href="../../?currentSprint.guid=${activityOverviewDto.sprintDto.guid}" class="btn"><spring:message code="sprint.activity.page.back" text="Back"/> &raquo;</a>
        </div>
        <h2 class="text-success">${activityOverviewDto.sprintDto.name}
            <small> <spring:message code="sprint.activity.page.title" text="Activity"/></small>
        </h2>
    </div>
</div>
<div class="row">
    <div class="span12">
        <br/>
        <c:forEach items="${activityOverviewDto.list}" var="log" varStatus="s">
            <blockquote>
                <p class="text-info">${log.displayContent}</p>
                <small>${log.createTime}</small>
            </blockquote>
        </c:forEach>
        <displaytag:table list="${activityOverviewDto}" class="table hidden" id="log" requestURI="">
            <displaytag:column/>
        </displaytag:table>
    </div>
</div>
</body>
</html>