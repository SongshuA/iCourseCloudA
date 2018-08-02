<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="config.GlobalConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传资源 - <%=GlobalConfig.siteName%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
<jsp:include page="components/navbar.jsp" />

<div class="page-container container">

    <div class="card">
        <form name="resource" action="${pageContext.request.contextPath}/resource" method="POST" enctype="multipart/form-data">
            <div class="card-content">

                <h4>上传资源</h4>
                <input type="hidden" name="courseId" value="${courseId}" />


                <div class="file-field input-field">

                    <div class="btn">
                        <span>上传资源文件</span>
                        <input type="file" name="file">
                    </div>

                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" placeholder="无">
                    </div>

                    <div class="input-field col s12">
                        <input id="name" type="text" name="name" class="validate">
                        <label for="name">资源名</label>
                    </div>

                </div>

            </div>
            <div class="card-action">
                <a href="javascript:document.forms['resource'].submit();">提交</a>
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
