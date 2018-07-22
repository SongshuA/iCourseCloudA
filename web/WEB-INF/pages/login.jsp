<%@ page import="config.GlobalConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录 - <%=GlobalConfig.siteName%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form.css">
</head>
<style>
    .page-container{max-height: 440px;}
    body{ background-image: url("/image/bg1.jpg"); }
</style>

<body>
<jsp:include page="components/navbar.jsp" />


<div class="page-container z-depth-2">
    <div class="row">
        <form name="login-static" class="col s12" method="POST" action="/login">
            <h4 class="center-align">登录</h4>

            <div class="row">
                <div class="input-field col s12">
                    <i class="material-icons prefix">account_circle</i>
                    <input name="username" id="username-static" type="text" class="validate">
                    <label for="username-static">用户名</label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <i class="material-icons prefix">https</i>
                    <input name="password" id="password-static" type="password" class="validate">
                    <label for="password-static">密码</label>
                </div>
            </div>

            <div class="row">
                <p class="error-display center-align"></p>
            </div>

            <div class="right">
                <a href="javascript:util.submit('login-static')" class="waves-effect waves-green btn-flat">登录</a>
            </div>
        </form>
    </div>

</div>


<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/materialize.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
