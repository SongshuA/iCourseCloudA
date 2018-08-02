<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="config.GlobalConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件 - <%=GlobalConfig.siteName%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
<jsp:include page="components/navbar.jsp" />

<div class="page-container container">

    <div class="card">
        <form name="point-file" action="${pageContext.request.contextPath}/pointFile" method="POST" enctype="multipart/form-data">
            <div class="card-content">

                <h4>${title}</h4>
                <input type="hidden" name="pointId" value="${pointId}" />
                <input type="hidden" name="type" value="${type}" />




                <div class="file-field input-field">

                    <c:choose>
                        <c:when test="${type eq 'video'}">

                            <div class="btn">
                                <span>上传视频</span>
                                <input type="file" name="video" accept="video/*">
                            </div>

                            <div class="file-path-wrapper">
                                <input class="file-path validate" type="text" placeholder="无">
                            </div>

                        </c:when>

                        <c:otherwise>

                            <div class="btn">
                                <span>上传文档</span>
                                <input type="file" name="document" accept="application/pdf">
                            </div>

                            <div class="file-path-wrapper">
                                <input class="file-path validate" type="text" placeholder="无">
                            </div>

                        </c:otherwise>
                    </c:choose>

                </div>

            </div>
            <div class="card-action">
                <a href="javascript:document.forms['point-file'].submit();">提交</a>
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
