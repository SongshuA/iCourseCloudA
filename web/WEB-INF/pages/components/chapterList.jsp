<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${fn:length(chapters) > 0}">

        <ul class="collapsible">

            <c:forEach items="${chapters}" var="chapter">

                <li>
                    <div class="collapsible-header"><i class="material-icons">filter_drama</i>${chapter.name}</div>
                    <div class="collapsible-body">

                        <c:choose>
                            <c:when test="${fn:length(points[chapter]) > 0}">

                                <div class="collection">
                                    <c:forEach items="${points[chapter]}" var="point">
                                        <a href="${pageContext.request.contextPath}/point?pointId=${point.id}" class="collection-item">
                                                ${point.name}
                                        </a>
                                    </c:forEach>
                                </div>

                            </c:when>
                            <c:otherwise>

                                <p>该章节无任何知识点</p>

                            </c:otherwise>
                        </c:choose>

                    </div>
                </li>

            </c:forEach>

        </ul>
    </c:when>

    <c:otherwise>
        <div class="card">
            <div class="card-content">
                <span class="card-title">当前课程未开设任何章节</span>
            </div>
        </div>
    </c:otherwise>

</c:choose>