<%--
  User: Shengzhao Li
  Date: 13-10-2
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div>
    <table class="width100">
        <tr>
            <td>
                <spring:message code="sprint.meeting.details.page.date" text="Date"/>:
                <span class="text-info">
                    ${sprintMeetingDto.meetingDate}
                </span>
            </td>
            <td>
                <spring:message code="sprint.meeting.details.page.type" text="Type"/>:
                <span class="text-info">
                    ${sprintMeetingDto.type.label}
                </span>
            </td>
        </tr>
        <tr>
            <td>
                <spring:message code="sprint.meeting.details.page.create.time" text="Create time"/>:
                <span class="text-info">
                    ${sprintMeetingDto.createTime}
                </span>
            </td>
            <td>
                <spring:message code="sprint.meeting.details.page.sprint" text="Sprint"/>:
                <span class="text-info">
                    ${sprintMeetingDto.sprintDto.name}
                </span>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <spring:message code="sprint.meeting.details.page.content" text="Content"/>:
                <div class="text-info">
                    ${sprintMeetingDto.content}
                </div>
            </td>
        </tr>
    </table>
</div>