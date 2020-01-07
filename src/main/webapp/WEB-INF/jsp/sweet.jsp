<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="sweet" type="lv.helloit.bootcamp.sweeter.sweet.Sweet"--%>
<%--@elvariable id="validationError" type="java.lang.String"--%>
<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp" %>
<body>
<%@include file="navbar.jsp"%>
<br>

<p>${sweet.id}</p>
<p>${sweet.userId}</p>
<p>${sweet.content}</p>
<p>${sweet.datePosted}</p>
<p>${sweet.dateLastUpdate}</p>
<br>
<button onclick="deleteSweetAndRedirectWithConfirmationCheck(${sweet.id})">DELETE</button>
<form action="/sweet/${sweet.id}" method="post">
    <input id="sweet_user_id" name="userId" value="${sweet.userId}" type="text"/>
    <c:if test="${user_id_err}">
        <label for="sweet_user_id">Wrong field value</label>
    </c:if>
    <input id="sweet_content" name="content" value="${sweet.content}" type="text"/>
    <c:if test="${content_err}">
        <label for="sweet_content">Wrong field value</label>
    </c:if>

    <button type="submit">SUBMIT</button>
</form>

</body>
</html>