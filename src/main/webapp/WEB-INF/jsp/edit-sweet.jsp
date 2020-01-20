<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp"%>
<body>
<%@include file="navbar.jsp"%>
<%--@elvariable id="sweet" type="lv.helloit.sweetter.Sweet"--%>

<form action="/sweet" method="post">
    <sec:csrfInput />
    <input name="content">

    <c:if test="${content_err}">
        <label style="color: red;">Wrong field value</label>
    </c:if>

    <button type="submit">Submit</button>
</form>

</body>
</html>