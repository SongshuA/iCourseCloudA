<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="config.GlobalConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>搜索 - <%=GlobalConfig.siteName%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
<jsp:include page="components/navbar.jsp" />

<div class="page-container container">

    <div class="row">

        <div class="card col s12">
            <div class="card-content">
                <span class="card-title">
                    <h5>搜索</h5>
                </span>
                <form name="search">
                    <div class="row">

                        <div class="input-field col s12">
                            <input id="keyword" type="text" name="keyword" class="validate" value="${keyword}">
                            <label for="keyword">输入关键词</label>
                        </div>

                        <div class="input-field col s6">
                            <select name="order">
                                <c:choose>
                                    <c:when test="${order eq 'createTime'}">
                                        <option value="createTime" selected>按照创建时间排序</option>
                                        <option value="engagement">按照热度排序</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="createTime">按照创建时间排序</option>
                                        <option value="engagement" selected>按照热度排序</option>
                                    </c:otherwise>
                                </c:choose>

                            </select>
                            <label>排序方式</label>
                        </div>

                        <div class="input-field col s6">
                            <select name="type">
                                <c:choose>
                                    <c:when test="${type eq 'name'}">
                                        <option value="name" selected>搜索课程名称</option>
                                        <option value="creatorName">搜索开课老师</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="name">搜索课程名称</option>
                                        <option value="creatorName" selected>搜索开课老师</option>
                                    </c:otherwise>
                                </c:choose>

                            </select>
                            <label>筛选</label>
                        </div>

                    </div>

                    <a href="javascript:document.forms['search'].submit();" class="waves-effect waves-light btn">搜索</a>
                </form>
            </div>
        </div>



        <div class="row">
            <c:choose>

                <c:when test="${empty result}">
                    <div class="card-content center-align">

                        <p>无搜索结果</p>

                    </div>
                </c:when>

                <c:otherwise>
                    <c:forEach items="${result}" var="course">
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
                </c:otherwise>

            </c:choose>
        </div>

        <c:if test="${pageCount > 1}">

            <div class="card-content center-align">

                <c:forEach begin="1" end="${pageCount}" var="i">
                    <a href="javascript:util.setUrlParam('pageNum', '${i}');" class="waves-effect waves-light btn-small">${i}</a>
                </c:forEach>

            </div>

        </c:if>

    </div>

</div>

<jsp:include page="components/footer.jsp" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/materialize.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
