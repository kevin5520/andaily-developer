<%--
  User: Shengzhao Li
  Date: 13-10-1
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div>
    <form:form commandName="sprintMeetingFormDto" id="sprintMeetingForm">
        <form:hidden path="guid"/>
        <form:hidden path="sprintDto.guid"/>
        <form:hidden path="backToOverview" id="meetingBackToOverview"/>
        <table class="width100">
            <tr>
                <td>
                    <label for="meetingDate"><spring:message code="sprint.meeting.form.page.date" text="Date"/></label>

                    <div class="input-append date" id="meetingDate">
                        <spring:message code="sprint.meeting.form.page.date.holder" text="Meeting date" var="dateHolderMessage"/>
                        <form:input path="meetingDate" placeholder="${dateHolderMessage}" cssClass="span2" readonly="true"
                                    required="true"/>
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                    <form:errors path="meetingDate" cssClass="label label-warning"/>
                </td>
                <td>
                    <label for="meetingType"><spring:message code="sprint.meeting.form.page.type" text="Type"/></label>
                    <form:select path="type" id="meetingType" items="${sprintMeetingFormDto.types}" itemLabel="label"
                                 itemValue="value"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <span class="pull-right badge badge-warning hide" id="meetingContentWarn"><spring:message code="sprint.meeting.form.page.content.warn"
                                                                                                              text="Content should be not empty."/></span>
                    <label for="content"><spring:message code="sprint.meeting.form.page.content" text="Content"/></label>
                    <form:textarea path="content" id="content"/>
                    <span class="label"><spring:message code="sprint.meeting.form.page.content.help" text="Standing meeting content should be include current status, challenge and import updates."/></span>
                </td>
            </tr>
        </table>
    </form:form>
    <script type="text/javascript">
        $(function () {
            $('#meetingDate').datepicker({
                format:'yyyy-mm-dd',
                autoclose:true,
                weekStart:1
            });

            CKEDITOR.replace('content');
        })
    </script>
</div>