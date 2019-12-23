<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="sweets" type="java.util.List<lv.helloit.bootcamp.Sweet>"--%>
<%--@elvariable id="sweet" type="lv.helloit.bootcamp.Sweet"--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest sweets</title>

</head>
<body>
<c:if test="${sweets.isEmpty()}">
Sorry, no sweets for you!
</c:if>
<c:forEach items="${sweets}" var="sweet">
<p style="font-weight: bold;">${sweet.author}: </p>
<a href="/sweet/${sweet.id}">
    ${sweet.content}
</a>
    <br />
</c:forEach>
</body>
</html>