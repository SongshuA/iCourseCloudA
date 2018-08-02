<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="config.GlobalConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页 - <%=GlobalConfig.siteName%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
</head>
<style>body{background-color: #f0f0f0; }</style>
<body>
<jsp:include page="components/navbar.jsp" />

<div class="page-container container">

    <div class="card">
        <form name="course" action="${pageContext.request.contextPath}/course" method="POST" enctype="multipart/form-data">
            <div class="card-content">
                <c:choose>
                    <c:when test="${course eq null}">
                        <h4 class="center-align">创建新课程</h4>
                    </c:when>

                    <c:otherwise>
                        <h4 class="center-align">修改课程 - ${course.name}</h4>
                        <input type="hidden" name="courseId" value="${course.id}" />
                    </c:otherwise>
                </c:choose>


                <div class="file-field input-field">

                    <div class="btn">
                        <span>上传封面图</span>
                        <input type="file" name="cover" accept="image/*">
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" placeholder="无">
                    </div>

                </div>

                <div class="input-field">
                    <input id="name" name="name" type="text" class="validate" value="${course.name}">
                    <label for="name">课程名</label>
                </div>

                <div class="input-field">
                    <textarea id="description" name="description" class="materialize-textarea">${course.description}</textarea>
                    <label for="description">课程简介</label>
                </div>

            </div>
            <div class="card-action">
                <a href="javascript:document.forms['course'].submit();">提交</a>
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
