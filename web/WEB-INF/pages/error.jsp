<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="config.GlobalConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误 - <%=GlobalConfig.siteName%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css" media="screen,projection">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<style>
    body{
        background-color: #f0f0f0;
    }
</style>
<body>

<jsp:include page="components/navbar.jsp" />


<div class="page-container container">

    <div class="card">
        <div class="card-content">

            <h4>${message}</h4>

        </div>

        <div class="card-action">
            <a href="javascript:history.back();">返回上个页面</a>
            <a href="${pageContext.request.contextPath}/">返回首页</a>
        </div>
    </div>

</div>


<jsp:include page="components/footer.jsp" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/materialize.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
