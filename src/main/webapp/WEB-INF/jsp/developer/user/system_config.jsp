<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><spring:message code="system.config.page.title" text="System"/></title>
</head>
<body>
<div class="row">
    <hr/>
</div>
<div class="row">
    <div class="span12">
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-ok-sign icon-white"></i>
                <span id="saveSuccess" class="hide"><spring:message code="system.config.page.alert.save.success"
                                                                    text="Save system configuration successful!"/></span>
            </div>
        </div>
    </div>
</div>

<div class="row sprint-form">
    <div class="span12">
        <form:form commandName="formDto" cssClass="form-horizontal">
            <div class="control-group">
                <label class="control-label"><spring:message code="system.config.page.perpagesize.label"
                                                             text="Amount of per page data"/></label>

                <div class="controls">
                    <c:forEach items="${formDto.perPageSizes}" var="p">
                        <label class="radio inline">
                            <form:radiobutton path="perPageSize" value="${p.value}"/> ${p.size}
                        </label>
                    </c:forEach>
                    <form:errors path="perPageSize" cssClass="label label-warning"/>
                    <span class="help-block"><spring:message code="system.config.page.perpagesize.help"
                                                             text="Specify per page data size in pagination page, default is 20"/></span>
                </div>
            </div>

            <div class="control-group" style="margin-left: 15%;">
                <button type="submit" class="btn btn-success"><i class="icon-ok icon-white"></i> <spring:message
                        code="system.config.page.save" text="Save"/></button>
            </div>
        </form:form>
    </div>
</div>


<script type="text/javascript">
    $(function () {
        new SystemConfig('${param.alert}');
    });
</script>
</body>
</html>