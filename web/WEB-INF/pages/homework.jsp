<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="config.GlobalConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业 - <%=GlobalConfig.siteName%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
<jsp:include page="components/navbar.jsp" />

<c:if test="${isCreator}">
    <script>
        function setExamineFormAnswerId(answerId) {
            $('form[name="examine-answer"] input[name="answerId"]').val(answerId);
        }
    </script>
    <div id="examine-answer-modal" class="modal">

        <div class="modal-content">
            <div class="row">
                <h4 class="col s12">评分</h4>

                <form name="examine-answer" class="col s12" action="${pageContext.request.contextPath}/examineAnswer" method="POST">

                    <input type="hidden" name="answerId" value="" />

                    <div class="row">
                        <div class="input-field col s12">
                            <input name="score" id="answer-score" type="number" class="validate" />
                            <label for="answer-score">分数</label>
                        </div>
                    </div>

                    <div class="row">
                        <p class="error-display center-align"></p>
                    </div>
                </form>
            </div>

        </div>

        <div class="modal-footer">
            <a href="#" class="modal-close waves-effect waves-green btn-flat">取消</a>
            <a href="javascript:util.submit('examine-answer')" class="waves-effect waves-green btn-flat">确认</a>
        </div>
    </div>
</c:if>


<div class="page-container container">

    <div class="row">
        <div class="card col s12">
            <div class="card-content">
                <c:choose>
                    <c:when test="${accessible}">
                        <h4>${homework.name}</h4>
                        <p>${homework.context}</p>
                    </c:when>

                    <c:otherwise>
                        <p>您无权访问此资源</p>
                    </c:otherwise>

                </c:choose>

            </div>


            <c:if test="${accessible}">
                <div class="card-action">
                    <c:choose>
                        <c:when test="${isCreator}">
                            <a href="${pageContext.request.contextPath}/createHomework?homeworkId=${homework.id}">修改作业</a>
                        </c:when>

                        <c:when test="${isSelector}">
                            <c:choose>
                                <c:when test="${answer eq null}">
                                    <a href="${pageContext.request.contextPath}/createAnswer?homeworkId=${homework.id}">创建回答</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/createAnswer?answerId=${answer.id}">修改回答</a>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>

                </div>
            </c:if>

        </div>

        <c:if test="${isSelector && answer ne null}">
            <div class="card col s12">
                <div class="card-content">
                    <h5>我的回答</h5>
                    <p>${answer.context}</p>
                </div>
                <div class="card-content">
                    得分：${answer.score}
                </div>
            </div>
        </c:if>

        <c:if test="${isCreator}">
            <c:forEach items="${answers}" var="answerItem">
                <div class="card col s12">
                    <div class="card-content">
                        <h5>${answerItem.user.username} 的回答</h5>
                        <p>${answerItem.context}</p>
                    </div>
                    <div class="card-content">
                        得分：${answerItem.score}
                    </div>
                    <div class="card-action">
                        <a href="#examine-answer-modal" onclick="setExamineFormAnswerId(${answerItem.id})" class="modal-trigger">评分</a>
                    </div>
                </div>
            </c:forEach>
        </c:if>

    </div>

</div>

<jsp:include page="components/footer.jsp" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/materialize.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
