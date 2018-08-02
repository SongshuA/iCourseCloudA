<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="config.GlobalConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${point.name} - <%=GlobalConfig.siteName%></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
<jsp:include page="components/navbar.jsp" />


<c:if test="${isCreator}">

    <%-- Modal Structure --%>

    <div id="edit-point-modal" class="modal">

        <div class="modal-content">
            <div class="row">
                <h4 class="col s12">修改知识点</h4>

                <form name="edit-point" class="col s12" action="${pageContext.request.contextPath}/createPoint" method="POST">

                    <div class="row">
                        <div class="input-field col s12">
                            <input type="hidden" name="pointId" value="${point.id}" />
                            <input name="name" id="point-name" type="text" class="validate" value="${point.name}" />
                            <label for="point-name">知识点名</label>
                        </div>

                        <div class="input-field col s12">
                            <textarea class="materialize-textarea" name="description">${point.description}</textarea>
                            <label>知识点简介</label>
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
            <a href="javascript:util.submit('edit-point')" class="waves-effect waves-green btn-flat">修改</a>
        </div>
    </div>

</c:if>


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
                    <c:when test="${accessible}">
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
                    </c:when>

                    <c:otherwise>
                        <div class="card-content">

                            <p>要查看本资源，请选课后重试</p>

                        </div>
                    </c:otherwise>
                </c:choose>

            </div>

            <c:if test="${isCreator}">

                <div class="card">
                    <div class="card-action">
                        <a href="${pageContext.request.contextPath}/pointFile?type=video&pointId=${point.id}">上传视频</a>
                        <a href="${pageContext.request.contextPath}/pointFile?type=document&pointId=${point.id}">上传文档</a>
                        <a href="#edit-point-modal" class="modal-trigger">修改知识点</a>
                    </div>
                </div>

            </c:if>

        </div>

    </div>

</div>

<jsp:include page="components/footer.jsp" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/materialize.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
