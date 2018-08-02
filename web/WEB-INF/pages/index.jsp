<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="config.GlobalConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页 - <%=GlobalConfig.siteName%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css" media="screen,projection">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<jsp:include page="components/navbar.jsp" />


<c:if test="${fn:length(hotCourses) > 0}">
    <div class="carousel carousel-slider center">

        <c:forEach items="${hotCourses}" var="course">
            <div class="carousel-item cover-display white-text"
                 style="background-image: url('${covers[course]}')">
                <a href="${pageContext.request.contextPath}/detail?courseId=${course.id}">
                    <h2 class="text-shadow white-text">${course.name}</h2>
                    <p class="text-shadow white-text">${course.description}</p>
                </a>
            </div>
        </c:forEach>

    </div>
</c:if>


<div class="page-container container">

    <div class="row s12">

        <c:if test="${fn:length(newCourses) eq 0 and fn:length(hotCourses) eq 0}">
            <div class="card">
                <div class="card-content">
                    <p>当前尚未创建任何课程</p>
                </div>
            </div>
        </c:if>

        <c:if test="${fn:length(newCourses) > 0}">
            <c:forEach items="${newCourses}" var="course">
                <div class="col s6">
                    <div class="card">
                        <div class="thumbnail" style="background-image: url('${covers[course]}');"></div>
                        <div class="card-content">
                            <h5>${course.name}</h5>
                            <p>${course.description}</p>
                        </div>
                        <div class="card-action">
                            <a href="${pageContext.request.contextPath}/detail?courseId=${course.id}">查看详情</a>
                        </div>
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
