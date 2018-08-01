<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="config.GlobalConfig" %>
<%@ page import="domain.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="service.CourseService" %>
<%@ page import="domain.Point" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页 - <%=GlobalConfig.siteName%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
<jsp:include page="components/navbar.jsp" />


<div class="page-container container">

    <div class="row">

        <div class="col s3">

            <%@ include file="components/chapterList.jsp" %>

        </div>

        <div class="col s9">

            <div class="card">

                <div class="card-content">
                    <span class="card-title">${point.name}</span>
                    <p>${point.description}</p>
                </div>

                <c:choose>
                    <c:when test="${fn:length(videos) > 0 || fn:length(documents) > 0}">
                        <c:forEach items="${videos}" var="video">
                            <video controls="controls" width="100%" src="${videoFolderURL}/${video}"></video>
                            <div class="card-content">&nbsp;</div>
                        </c:forEach>

                        <c:forEach items="${documents}" var="document">
                            <iframe src="${documentFolderURL}/${document}" width="100%" height="960px" style="border:none"></iframe>
                            <div class="card-content">&nbsp;</div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>

                        <div class="card-content">

                            <p>该知识点无任何资源</p>

                        </div>

                    </c:otherwise>
                </c:choose>


            </div>

        </div>

    </div>

</div>

<jsp:include page="components/footer.jsp" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/materialize.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
