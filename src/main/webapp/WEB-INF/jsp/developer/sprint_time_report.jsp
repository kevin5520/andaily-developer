<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><spring:message code="sprint.time.report.page.title" text="Time Report"/></title>
</head>
<body>
<div class="row">
    <hr/>
    <div class="span6">
        <form class="form-inline">
            <select id="sprintSelect" name="guid">
                <c:forEach items="${timeReportDto.sprintDtosGroupResults}" var="group" varStatus="status">
                    <optgroup label="${group.key.label}">
                        <c:forEach items="${group.results}" var="sprint">
                            <option value="${sprint.guid}" ${sprint.guid eq timeReportDto.guid?'selected':''}>${sprint.name}</option>
                        </c:forEach>
                    </optgroup>
                </c:forEach>
            </select>
            <label class="radio inline">
                <input type="radio" name="reportByDay" value="true" checked="checked" disabled="disabled"/>
                <spring:message code="sprint.time.report.page.report.by.day" text="Report by day"/>
            </label>
            <label class="radio inline">
                <input type="radio" name="reportByDay" value="false" disabled="disabled"/>
                <spring:message code="sprint.time.report.page.report.by.week" text="Report by week"/>
            </label>
        </form>
    </div>
    <div class="span4"></div>
    <div class="span2">
        <a href="../?currentSprint.guid=${timeReportDto.guid}" class="btn pull-right"><spring:message code="sprint.time.report.page.back" text="Back"/> <i
                class="icon-chevron-right"></i></a>
    </div>
</div>
<div class="row">
    <div class="span12">
        <br/>
        <table class="table table-bordered table-hover table-striped">
            <thead>
            <tr>
                <th>&nbsp;</th>
                <c:forEach items="${timeReportDto.dates}" var="d">
                    <th><span class="${d.today?'text-warning':''}">${d.showDate}</span></th>
                </c:forEach>
                <th class="text-error"><spring:message code="sprint.time.report.page.total" text="Total"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${timeReportDto.reportDeveloperDtos}" var="d">
                <tr>
                    <td class="${d.currentDeveloper?'text-info':''}">${d.developerDto.showName}</td>
                    <c:forEach items="${d.shellTimes}" var="t">
                        <td>
                            <span class="${t.showTimeText eq '0.0'?'muted':''}">${t.showTimeText}</span>
                        </td>
                    </c:forEach>
                    <td class="text-error"><strong>${d.totalEstimateTimes}</strong></td>
                </tr>
            </c:forEach>
            <tr class="text-success info">
                <td><strong><spring:message code="sprint.time.report.page.total" text="Total"/></strong></td>
                <c:forEach items="${timeReportDto.totals}" var="t">
                    <td class="${t.times eq '0.0'?'muted':''}">${t.times}</td>
                </c:forEach>
                <td><strong>${timeReportDto.allTotalTimes}</strong></td>
            </tr>
            </tbody>
        </table>
        <ul class="pager">
            <c:if test="${timeReportDto.hasPrevious}">
                <li class="previous">
                    <a href="?previousEndDate=${timeReportDto.previousEndDate}">&larr; <spring:message code="sprint.time.report.page.previous" text="Previous"/></a>
                </li>
            </c:if>
            <li>
                <div class="muted">
                    <spring:message code="sprint.time.report.page.estimate.time" text="Estimate time"/>: <span class="text-info">${timeReportDto.sprint.estimateTimesAsHour}</span>,
                    <spring:message code="sprint.time.report.page.actual.used" text="Actual used time"/>: <span class="text-info">${timeReportDto.sprint.usedTimesAsHour}</span> |
                    <span class="text-info">${timeReportDto.sprint.startDate}</span>/<custom:show_sprint_deadline sprint="${timeReportDto.sprint}"/>
                </div>
            </li>
            <c:if test="${timeReportDto.hasNext}">
                <li class="next">
                    <a href="?nextStartDate=${timeReportDto.nextStartDate}"><spring:message code="sprint.time.report.page.next" text="Next"/> &rarr;</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<script>
    $(function () {
        new SprintTimeReport();
    });
</script>
</body>
</html>