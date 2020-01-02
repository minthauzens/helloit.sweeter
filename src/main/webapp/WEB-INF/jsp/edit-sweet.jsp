<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp"%>
<body>
<%@include file="navbar.jsp"%>
<%--@elvariable id="sweet" type="lv.helloit.sweetter.Sweet"--%>

<form action="/sweet" method="post">
    <input name="author">

    <c:if test="${author_err}">
        <label style="color: red;">Wrong field value</label>
    </c:if>

    <input name="content">

    <c:if test="${content_err}">
        <label style="color: red;">Wrong field value</label>
    </c:if>

    <button type="submit">Submit</button>
</form>

</body>
</html>