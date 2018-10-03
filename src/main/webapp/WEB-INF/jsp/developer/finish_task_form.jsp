<%--
  User: Shengzhao Li
  Date: 13-8-20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div>
    <form action="" class="form-horizontal" id="finishSprintTaskForm">
        <p class="text-center">
            <spring:message code="finish.task.form.page.estimate.content" text="The task(\#{0}) estimate time is <strong>{1}</strong> hour(s)."
                            arguments="${sprintTaskDto.number},${sprintTaskDto.estimateTime}"/>
        </p>

        <div class="control-group">
            <label class="control-label" for="actualUsedTimeSelect"><spring:message code="finish.task.form.page.actual.time" text="Actual time"/></label>

            <div class="controls">
                <select id="actualUsedTimeSelect" class="input-small" name="usedTime">
                    <c:forEach items="${taskTimes}" var="t">
                        <option value="${t}" ${t eq sprintTaskDto.estimateTime?'selected':''}>${t}</option>
                    </c:forEach>
                </select>
                &nbsp;
                <label class="checkbox inline">
                    <input type="checkbox" name="larger4Hours" class="input-mini"/> <spring:message code="finish.task.form.page.larger.than" text="larger than 4 hours"/>
                </label>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="comment"><spring:message code="finish.task.form.page.comment" text="Comment"/></label>

            <div class="controls">
                <input type="text" id="comment" name="comment"
                       placeholder="<spring:message code="finish.task.form.page.comment.holder" text="Comment of the task (optional)"/>"
                       maxlength="255">
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    $(function () {
        new FinishTaskForm();
    });
</script>