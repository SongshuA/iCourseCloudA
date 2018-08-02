<%@ page contentType="text/html;charset=UTF-8" language="java" %>




<c:if test="${isCreator ne null and isCreator}">
    <script>
        function setModalChapterId(chapterId) {
            $('form[name="create-point"] input[name="chapterId"]').val(chapterId);
        }
    </script>


    <!-- Modal Structure -->
    <div id="create-chapter-modal" class="modal">

        <div class="modal-content">
            <div class="row">
                <h4 class="col s12">创建章节</h4>

                <form name="create-chapter" class="col s12" action="${pageContext.request.contextPath}/createChapter" method="POST">

                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix">account_circle</i>
                            <input type="hidden" name="courseId" value="${course.id}" />
                            <input name="name" id="chapter-name" type="text" class="validate" />
                            <label for="chapter-name">章节名</label>
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
            <a href="javascript:util.submit('create-chapter')" class="waves-effect waves-green btn-flat">创建</a>
        </div>
    </div>



    <div id="create-point-modal" class="modal">

        <div class="modal-content">
            <div class="row">
                <h4 class="col s12">创建知识点</h4>

                <form name="create-point" class="col s12" action="${pageContext.request.contextPath}/createPoint" method="POST">

                    <div class="row">
                        <div class="input-field col s12">
                            <input type="hidden" name="chapterId" value="" />
                            <input name="name" id="point-name" type="text" class="validate" />
                            <label for="point-name">知识点名</label>
                        </div>

                        <div class="input-field col s12">
                            <textarea class="materialize-textarea" name="description"></textarea>
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
            <a href="javascript:util.submit('create-point')" class="waves-effect waves-green btn-flat">创建</a>
        </div>
    </div>
</c:if>


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

                        <c:if test="${isCreator ne null and isCreator}">
                            <div class="collection">
                                <a href="#create-point-modal" onclick="setModalChapterId(${chapter.id})" class="modal-trigger collection-item">
                                    <i class="material-icons">add</i><i>新知识点</i>
                                </a>
                            </div>
                        </c:if>

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

<c:if test="${isCreator ne null and isCreator}">
    <div class="card">
        <div class="card-action">
            <a class="modal-trigger" href="#create-chapter-modal">创建章节</a>
        </div>
    </div>
</c:if>
