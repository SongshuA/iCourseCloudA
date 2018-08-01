<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="config.GlobalConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${course.name} - <%=GlobalConfig.siteName%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
<jsp:include page="components/navbar.jsp" />


<% int frame = (Integer) request.getAttribute("frame"); %>

<div class="page-container container">

    <div class="row">
        <div class="col s3">
            <div class="collection">
                <a href="javascript:util.setUrlParam('frame', '0')" class="collection-item<%=frame == 0 ? " active" : ""%>">课程详情</a>
                <a href="javascript:util.setUrlParam('frame', '1')" class="collection-item<%=frame == 1 ? " active" : ""%>">课程知识点</a>
                <a href="javascript:util.setUrlParam('frame', '2')" class="collection-item<%=frame == 2 ? " active" : ""%>">课程资源</a>
                <a href="javascript:util.setUrlParam('frame', '3')" class="collection-item<%=frame == 3 ? " active" : ""%>">课程作业</a>
            </div>
        </div>
        <div class="col s9">

            <c:choose>

                <c:when test="${frame == 0}">
                    <img class="materialboxed" width="100%" src="${cover}">

                    <div class="card">
                        <div class="card-content">
                            <span class="card-title"><h5>${course.name}</h5></span>
                            <p>${course.description}</p>
                        </div>


                        <c:if test="${isCrator == true}">

                            <div class="card-action">
                                <a href="#">修改此课程</a>
                            </div>

                        </c:if>

                    </div>
                </c:when>

                <c:when test="${frame == 1}">

                    <%@ include file="components/chapterList.jsp" %>

                </c:when>

                <c:when test="${frame == 2}">

                    <ul class="collection with-header">
                        <li class="collection-header"><h4>${course.name} 的资源目录</h4></li>

                        <c:forEach items="${resources}" var="filename">
                            <a href="${resourceFolderURL}/${filename}" class="collection-item">
                                    ${filename}
                            </a>
                        </c:forEach>

                    </ul>

                </c:when>

                <c:when test="${frame == 3}">

                    <ul class="collection with-header">
                        <li class="collection-header"><h4>${course.name} 的作业列表</h4></li>

                        <c:forEach items="${homeworks}" var="homework">
                            <a href="${pageContext.request.contextPath}/homework?homeworkId=${homework.id}" class="collection-item">

                                ${homework.name}

                            </a>
                        </c:forEach>
                    </ul>

                </c:when>
            </c:choose>

        </div>
    </div>

</div>

<jsp:include page="components/footer.jsp" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/materialize.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
