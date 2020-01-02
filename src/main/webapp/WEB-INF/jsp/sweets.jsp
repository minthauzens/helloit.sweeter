<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp"%>
<body>

<%@include file="navbar.jsp"%>
<div class="container">
    <%--@elvariable id="sweets" type="java.util.List"--%>
    <%--@elvariable id="sweet" type="lv.helloit.sweeter.Sweet"--%>
    <c:if test="${sweets == null || sweets.isEmpty()}">
        Sorry, nothing !!!
    </c:if>

    <div class="container-fluid">
        <div class="row">
            <c:forEach items="${sweets}" var="sweet">
                <div class="card text-center" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">${sweet.author}</h5>
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