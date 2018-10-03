<%--
  User: Shengzhao Li
  Date: 13-9-29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
    <div>
        <canvas id="burnDownDetailsChart"></canvas>
    </div>
    <div class="alert alert-info text-center">
        <spring:message code="burndown.details.page.estimate" text="Estimate"/>: <strong>${burnDownDetailsDto.burnDownWrapper.burnDown.estimateTimesAsHours}</strong>
        &nbsp;&nbsp;&nbsp;
        <spring:message code="burndown.details.page.used" text="Used"/>: <strong>${burnDownDetailsDto.burnDownWrapper.burnDown.usedTimesAsHours}</strong>
    </div>
    <script type="text/javascript">
        var labels = [];
        var expectDatas = [];
        var actualDatas = [];

        <c:forEach items="${burnDownDetailsDto.burnDownWrapper.burnDown.labels}" var="label">
        labels.push('${label.dateAsDay}');
        </c:forEach>
        <c:forEach items="${burnDownDetailsDto.burnDownWrapper.burnDown.expectPoints}" var="exp">
        expectDatas.push('${exp.expectRemainTime}');
        </c:forEach>
        <c:forEach items="${burnDownDetailsDto.burnDownWrapper.burnDown.actualPoints}" var="actual">
        actualDatas.push('${actual.actualRemainTime}');
        </c:forEach>

        var data = {
            labels:labels,
            datasets:[
                {
                    fillColor:"rgba(255,255,255,0.5)",
                    strokeColor:"rgba(0,0,255,0.8)",
                    pointColor:"rgba(0,0,255,0.8)",
                    pointStrokeColor:"#fff",
                    data:expectDatas
                },
                {
                    fillColor:"rgba(151,187,205,0.4)",
                    strokeColor:"rgba(255,0,0,0.8)",
                    pointColor:"rgba(255,0,0,0.8)",
                    pointStrokeColor:"#fff",
                    data:actualDatas
                }
            ]
        };

        $("#burnDownDetailsChart").attr("height", 200).attr("width", 520);
        var context = $("#burnDownDetailsChart").get(0).getContext("2d");
        new Chart(context).Line(data, {
            scaleOverride:true,
            scaleStepWidth:${burnDownDetailsDto.burnDownWrapper.chartStepWidth},
            scaleStartValue:0,
            scaleSteps:${burnDownDetailsDto.burnDownWrapper.chartSteps}
        });
    </script>
</div>