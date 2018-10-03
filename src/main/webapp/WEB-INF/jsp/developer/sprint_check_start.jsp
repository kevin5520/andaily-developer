<%--
  User: Shengzhao Li
  Date: 13-8-21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div>
    <input type="hidden" value="${checkSprintStartDto.pass}" id="checkSprintStartPass"/>
    <c:if test="${checkSprintStartDto.pass}">
        <div class="alert alert-info alert-block">
            <p>
                <spring:message code="sprint.check.start.page.sure.start"
                                text="Are you sure start to do the Sprint({0}) now ?"
                                arguments="${checkSprintStartDto.name}"/>
            </p>

            <p>
                <spring:message code="sprint.check.start.page.sprint.period"
                                text="The sprint period is from <strong>{0}</strong> to <strong>{1}</strong>."
                                arguments="${checkSprintStartDto.startDate},${checkSprintStartDto.deadline}"/>
            </p>
        </div>
    </c:if>
    <c:if test="${not checkSprintStartDto.pass}">
        <div class="alert alert-block">
            <h4>Oops! </h4>

            <p>
                <spring:message code="sprint.check.start.page.sprint.incorrect.date"
                                text="The sprint({0}) start date is <strong>{1}</strong>, but today is <strong>{2}</strong>."
                                arguments="${checkSprintStartDto.name},${checkSprintStartDto.startDate},${checkSprintStartDto.today}"/>
            </p>

            <p>
                <spring:message code="sprint.check.start.page.incorrect.date.content"
                                text="Please edit the sprint for change the start date to today firstly, click 'Confirm' button go to the sprint form."/>
            </p>
        </div>
    </c:if>
</div>