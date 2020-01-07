<%@include file="jsp_base_dependecies.jsp"%>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<%@include file="header.jsp"%>
<body>

<%@include file="navbar.jsp"%>
<div class="container">
    <%--@elvariable id="sweets" type="java.util.List"--%>
    <%--@elvariable id="sweet" type="lv.helloit.sweeter.Sweet"--%>
    <c:if test="${sweets == null || sweets.isEmpty()}">
        <fmt:message key="sweet.none" />
    </c:if>

    <div class="container-fluid">
        <div class="row">
            <c:forEach items="${sweets}" var="sweet">
                <div class="card text-center" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">${sweet.user_id}</h5>
                        <p class="card-text">${sweet.content}</p>
                        <a href="/sweet/${sweet.id}" class="btn btn-primary">Edit</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>