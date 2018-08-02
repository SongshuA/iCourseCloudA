<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="config.GlobalConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>个人中心 - <%=GlobalConfig.siteName%></title>
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
                <a href="javascript:util.setUrlParam('frame', '0')" class="collection-item<%=frame == 0 ? " active" : ""%>">我开的课</a>
                <a href="javascript:util.setUrlParam('frame', '1')" class="collection-item<%=frame == 1 ? " active" : ""%>">我选的课</a>
            </div>
        </div>

        <div class="col s9">

            <div class="row">
                <c:choose>
                    <c:when test="${frame == 0}">
                        <c:forEach items="${createdCourses}" var="course">

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
                    </c:when>
                    <c:when test="${frame == 1}">
                        <c:forEach items="${selectedCourses}" var="course">

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
                    </c:when>
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
