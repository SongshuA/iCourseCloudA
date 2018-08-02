<%@ page import="config.GlobalConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String username = (String)session.getAttribute("username");
%>


<!-- Modal Structure -->
<div id="login-modal" class="modal">
    <div class="modal-content">
        <div class="row">
            <h4 class="center-align">
                <a href="${pageContext.request.contextPath}/login">登录</a>
            </h4>

            <form name="login" class="col s12" action="/login" method="POST">

                <div class="row">
                    <div class="input-field col s12">
                        <i class="material-icons prefix">account_circle</i>
                        <input name="username" id="username" type="text" class="validate">
                        <label for="username">用户名</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s12">
                        <i class="material-icons prefix">https</i>
                        <input name="password" id="password" type="password" class="validate">
                        <label for="password">密码</label>
                    </div>
                </div>

                <div class="row">
                    <p class="error-display center-align"></p>
                </div>
            </form>

        </div>

    </div>
    <div class="modal-footer">
        <a href="#" class="modal-close waves-effect waves-green btn-flat">取消</a>
        <a href="javascript:util.submit('login')" class="submit waves-effect waves-green btn-flat">登录</a>
    </div>
</div>


<!-- Dropdown Structure -->
<ul id="user-menu" class="dropdown-content">
    <li><a href="${pageContext.request.contextPath}/personal?frame=0">我开的课</a></li>
    <li><a href="${pageContext.request.contextPath}/personal?frame=1">我选的课</a></li>
    <li class="divider"></li>
    <li><a href="javascript:util.logout()">退出登录</a></li>
</ul>


<nav>
    <div class="nav-wrapper">
        <a href="#" class="brand-logo center">
            <i class="material-icons">cloud</i><%=GlobalConfig.siteName%>
        </a>
        <ul class="left hide-on-med-and-down">
            <li><a href="${pageContext.request.contextPath}/">首页</a></li>
            <li><a href="${pageContext.request.contextPath}/search">搜索</a></li>
        </ul>

        <ul class="right hide-on-med-and-down">
        <% if(username == null){ %>

            <li><a href="${pageContext.request.contextPath}/register"><i class="material-icons">add_box</i></a></li>
            <li><a class="modal-trigger" href="#login-modal"><i class="material-icons">account_circle</i></a></li>

        <% } else {%>

            <li>
                <a class="dropdown-trigger" href="#" data-target="user-menu">
                    <%=username%><i class="material-icons right">arrow_drop_down</i>
                </a>
            </li>

        <% } %>
        </ul>

    </div>

</nav>