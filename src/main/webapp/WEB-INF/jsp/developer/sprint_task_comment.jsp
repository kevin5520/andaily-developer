<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="taskFinishedCommentDiv">
    <ul id="commentOl" class="unstyled">
        <c:if test="${empty commentsDto.comments}">
            <li id="emptyCommentLi">
                <p class="muted">
                    <spring:message code="sprint.task.comment.page.not.added" text="Not added any comments yet."/>
                </p>
            </li>
        </c:if>
        <c:forEach items="${commentsDto.comments}" var="c">
            <li>
                <blockquote>
                    <p class="text-info">
                            ${c.content}
                        <c:if test="${c.myCreated}">
                            &nbsp;<a href="javascript:void(0)" title="<spring:message code="sprint.task.comment.page.delete" text="Delete"/>"
                                                    class="deleteMyComment" guid="${c.guid}"><i class="icon-remove"></i></a>
                        </c:if>
                    </p>
                    <small>${c.who} (${c.createDate})</small>
                </blockquote>
            </li>
        </c:forEach>
    </ul>
    <div>
        <br/>

        <form action="${contextPath}/developer/task_action/comment_submit/${commentsDto.guid}" id="taskCommentForm"
              class="form-inline">
            <spring:message code="sprint.task.comment.page.comment.holder" text="Input comment" var="commentHolderMessage"/>
            <input type="text" placeholder="${commentHolderMessage}" maxlength="255"
                   title="${commentHolderMessage}" class="span4"
                   required name="content" id="taskCommentContent"/>
            <button type="submit" class="btn btn-success"><em class="icon-plus-sign icon-white"></em> <spring:message code="sprint.task.comment.page.add" text="Add"/></button>
        </form>
    </div>
    <script type="text/javascript">
        $(function () {
            $("form#taskCommentForm").submit(function () {
                var $content = $("input#taskCommentContent");
                if ('' === $content.val()) {
                    $content.focus();
                    return false;
                }
                $.post($(this).attr("action"), {content:$content.val()}, function (data) {
                    $("ul#commentOl").append(data);
                    $("li#emptyCommentLi").hide();
                    $content.val("");
                });
                return false;
            });

            $("#taskFinishedCommentDiv").on("click", "a.deleteMyComment", function (event) {
                event.preventDefault();
                if (confirm("<spring:message code="sprint.task.comment.page.js.sure.delete" text="Are you sure delete the comment now?"/>")) {
                    var $this = $(this);
                    var guid = $this.attr("guid");
                    $.post("${contextPath}/developer/task_action/comment_delete/" + guid, function (data) {
                        if (data.result) {
                            $this.parent().parent().parent().hide();
                        } else {
                            alert("<spring:message code="sprint.task.comment.page.js.delete.failed" text="Delete the comment failed, please confirm you created it"/>");
                        }
                    });
                }
            });
        });
    </script>
</div>