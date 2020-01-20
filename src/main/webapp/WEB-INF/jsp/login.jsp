<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="jsp_base_dependecies.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp" %>
<body>


<div class="container">
    <h1><fmt:message key="login.page"/></h1>
    <c:if test="${param.error}" >
        <div class="alert alert-danger" role="alert">
            <fmt:message key="wrong.credentials.message"/>
        </div>
    </c:if>
    <form action="/login" method="post">
        <sec:csrfInput />
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><input type="submit" class="btn btn-primary" value="Sign In"/></div>
        <br>
        <div><a href="<c:url value="/sign-up"/>" class="btn btn-secondary">Register</a></div>
    </form>
</div>
</body>
</html>
