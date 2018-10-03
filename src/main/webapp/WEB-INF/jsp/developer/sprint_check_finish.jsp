<%--
  User: Shengzhao Li
  Date: 13-8-28
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div>
    <input type="hidden" value="${checkSprintFinishDto.allowFinish}" id="checkSprintAllowFinish"/>
    <c:choose>
        <c:when test="${not checkSprintFinishDto.allowFinish}">
            <div class="alert alert-block">
                <h4><spring:message code="sprint.check.finish.page.oops" text="Oops!"/> </h4>

                <p>
                    <spring:message code="sprint.check.finish.page.not.finish.tasks" text="The Sprint({0}) is not finished all tasks yet until now." arguments="${checkSprintFinishDto.name}"/>
                    <br/>
                    <spring:message code="sprint.check.finish.page.not.finish.tasks.info"
                                    text="The estimate total time is <strong>{0}</strong> hours, but already finished time is <strong>{1}</strong> hours."
                                    arguments="${checkSprintFinishDto.estimateTimesAsHour},${checkSprintFinishDto.usedTimesAsHour}"/>
                </p>
            </div>
        </c:when>
        <c:when test="${not checkSprintFinishDto.todayIsDeadline}">
            <div class="alert alert-block">
                <p>
                    <spring:message code="sprint.check.finish.page.not.deadline.today"
                                    text="The Sprint({0}) deadline is <strong>{1}</strong>, it is not today."
                                    arguments="${checkSprintFinishDto.name},${checkSprintFinishDto.deadline}"/>
                    <br/>
                    <spring:message code="sprint.check.finish.page.not.deadline.sure" text="Are you sure finish it now ?"/>
                </p>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info alert-block">
                <p>
                    <spring:message code="sprint.check.finish.page.sure.finish" text="Are you sure finish the Sprint({0}) now ?" arguments="${checkSprintFinishDto.name}"/>
                </p>
            </div>
        </c:otherwise>
    </c:choose>
</div>