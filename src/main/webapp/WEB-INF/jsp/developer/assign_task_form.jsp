<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>








<style>
.redbutton {
	color: #923c47;
	border: 1px solid #d96d7c;
	background-image: -moz-linear-gradient(#f997b0, #f6677b);
	background-image: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#f6677b),
		to(#f997b0));
	background-image: -webkit-linear-gradient(#f997b0, #f6677b);
	background-image: -o-linear-gradient(#f997b0, #f6677b);
	text-shadow: 1px 1px 1px #fdbcc7;
	background-color: #f6677b;
}

.redbutton:hover {
	border: 1px solid #c75964;
	background-image: -moz-linear-gradient(#f6677b, #f997b0);
	background-image: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#f997b0),
		to(#f6677b));
	background-image: -webkit-linear-gradient(#f6677b, #f997b0);
	background-image: -o-linear-gradient(#f6677b, #f997b0);
	background-color: #f997b0;
}

.redbutton:active {
	border: 1px solid #ab3e4b;
}
</style>









<!-- <style>
#test {
	visibility: hidden;
	float: left;
	border: 1px solid #333;
	background: #FFF;
	width: 530px;
	height: 80px;
	margin-left: -25px;
	margin-top: 40px;
	overflow: yes;
	overflow: auto;
	overflow-y: yes;
	overflow-y: auto
}
</style> -->

<div class="well well-small">
	<div style="margin-left: 20px;">
		<p class="text-info">
			<spring:message code="assign.task.form.page.first"
				text="Assign the task(\#{0}) to"
				arguments="${assignTaskDto.taskNumber}" />
		</p>
		<select id="executorFormSelect">
			<option value=""><spring:message
					code="assign.task.form.page.select.developer"
					text="--Select a developer--" /></option>
			<c:forEach items="${assignTaskDto.developers}" var="d">
				<option value="${d.guid}"
					${(d.guid eq assignTaskDto.executorGuid)?'selected':''}>${d.showName}</option>
			</c:forEach>
		</select>



		<!-- <div id=test></div> -->


<a href="${contextPath}/developer/sortController/001">
    <input type="button" class="redbutton"
			style="margin-left: 50px; margin-top: -15px" name="bestchoice"
			value="Checking personal task count" /> 
</a>
		
			
			
			<span
			class="label label-warning hidden" id="assignTaskErrorInfo"><spring:message
				code="assign.task.form.page.select.developer.help"
				text="Please select a developer firstly." /></span>
	</div>
</div>

