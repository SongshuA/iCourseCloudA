<%@ page import="config.GlobalConfig" %>
<%@ page import="domain.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="service.CourseService" %>
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


<%
    List<Course> hotCourses = (List<Course>)request.getAttribute("hottest");

    List<Course> newCourses = (List<Course>) request.getAttribute("newest");

%>

<div class="carousel carousel-slider center">

    <% for(Course course : hotCourses){ %>

        <div class="carousel-item cover-display white-text"
             style="background-image: url('<%=CourseService.getInstance().getCoverURL(course.getId())%>')">
            <a href="${pageContext.request.contextPath}/course?courseId=<%=course.getId()%>">
                <h2 class="text-shadow white-text"><%=course.getName()%></h2>
                <p class="text-shadow white-text"><%=course.getDescription()%></p>
            </a>
        </div>

    <% } %>
</div>


<div class="page-container container">

    <div class="row s12">

        <% for(Course course : newCourses){ %>

            <div class="col s6">
                <div class="card">
                    <div class="card-image">
                        <img src="<%=CourseService.getInstance().getCoverURL(course.getId())%>">
                        <span class="card-title"><%=course.getName()%></span>
                    </div>
                    <div class="card-content">
                        <p><%=course.getDescription()%></p>
                    </div>
                    <div class="card-action">
                        <a href="${pageContext.request.contextPath}/course?courseId=<%=course.getId()%>">查看详情</a>
                    </div>
                </div>
            </div>

        <% } %>
    </div>

</div>


<jsp:include page="components/footer.jsp" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/materialize.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
