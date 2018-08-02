<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="config.GlobalConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑回答 - <%=GlobalConfig.siteName%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
<jsp:include page="components/navbar.jsp" />

<div class="page-container container">

    <div class="card">
        <form name="answer" action="${pageContext.request.contextPath}/createAnswer" method="POST">
            <div class="card-content">
                <c:choose>
                    <c:when test="${answer eq null}">
                        <h4 class="center-align">创建回答</h4>
                        <input type="hidden" name="homeworkId" value="${homeworkId}" />
                    </c:when>

                    <c:otherwise>
                        <h4 class="center-align">修改回答</h4>
                        <input type="hidden" name="answerId" value="${answer.id}" />
                    </c:otherwise>
                </c:choose>


                <div class="input-field">
                    <textarea id="context" name="context" class="materialize-textarea">${answer.context}</textarea>
                    <label for="context">回答内容</label>
                </div>

            </div>
            <div class="card-action">
                <a href="javascript:document.forms['answer'].submit();">提交</a>
            </div>
        </form>

    </div>

</div>

<jsp:include page="components/footer.jsp" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/materialize.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
