<%--
  User: Shengzhao Li
  Date: 13-12-5
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="alert alert-success">
    <h4><spring:message code="reset.password.page.head" text="Reset successful!"/></h4>
    <spring:message code="reset.password.page.tip" text="The user password reset to"/> <span class="label">${newPass}</span>,
    <br/>
    <spring:message code="reset.password.page.content" text="Please change it ASAP after the use login at the next time."/>
</div>