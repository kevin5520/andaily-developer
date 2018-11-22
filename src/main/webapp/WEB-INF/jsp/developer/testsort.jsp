
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>hello</title>
</head>
<body>




<form action="${contextPath}/developer/sortController/003" method="post">

<table>
<tr><td>Developername : </td> <td><input type="text" name="userName3"/></td> </tr>
<tr><td><input type="submit" value="submit" /></td></tr>
</table>

<hr/>

<table>
<tr><td>Name of developer: </td> <td>${userName3}</td> </tr>
<%-- <tr><td>id: </td> <td>${userAge3}</td> </tr> --%>
<tr><td>Tasks count in processing  : </td> <td>${userCount3}</td> </tr>
</table>

</form>








<a class="btn btn-success btn-small" href="${contextPath}/developer"><i
                class="icon-white"></i> <spring:message  text="Go Back"/></a>

</body>
</html>