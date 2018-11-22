<%--
  User: Shengzhao Li
  Date: 13-11-30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>1hello hello hello</title>
</head>
<body>


<form action="${contextPath}/developer/testController/002" method="post">
<table>
<tr><td>name: </td><td><input type="text" name="userName"></td></tr>
<tr><td>age: </td><td><input type="text" name="userAge" /></td></tr>
<tr><td>gender: </td><td><input type="text" name="userGender" /></td></tr>
<tr><td><input type="submit" value="submit"/></td></tr>
</table>

<table>
<tr><td>name: </td><td>${userName}</td></tr>
<tr><td>age: </td><td>${userAge}</td></tr>
<tr><td>gender: </td><td>${userGender}</td></tr>
</table>
</form>



<form action="${contextPath}/developer/testController/003" method="post">

<table>
<tr><td>name : </td> <td><input type="text" name="userName3"/></td> </tr>
<tr><td><input type="submit" value="submit" /></td></tr>
</table>

<hr/>

<table>
<tr><td>name : </td> <td>${userName3}</td> </tr>
<tr><td>age : </td> <td>${userAge3}</td> </tr>
<tr><td>gender : </td> <td>${userGender3}</td> </tr>
</table>

</form>





<table>
<tr><td>name2 : </td> <td><input type="text" id="userName2"/></td> </tr>
<tr><td>age2 : </td> <td><input type="text" id="userAge2"/></td> </tr>
<tr><td>gender2 : </td> <td><input type="text" id="userGender2"/></td> </tr>
<tr><td><input type="button" value="ajax submit" id="myButton1"/></td></tr>
</table>








<script type="text/javascript">
$(function () {
    
    $("#myButton1").click(function(){
    	
    	$.ajax({  
            type : "POST", 
            url : "${contextPath}/developer/testController/test_ajax_sumit",
            data : {  
                "userName2" : $("#userName2").val(),
                "userAge2" : $("#userAge2").val(),
                "userGender2" : $("#userGender2").val()
            },  
            success: function(data) {
            	alert("success");
            },
            error: function() {
              alert("error");
            }
        });
    	
    });
});
</script>
</body>
</html>