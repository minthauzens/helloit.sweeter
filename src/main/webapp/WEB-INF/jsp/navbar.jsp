<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--DEPENDS ON BOOTSTRAP--%>
<%
    String address =  request.getRequestURI();
    String[] parts = address.split("/");
    String currentPage = parts[parts.length - 1];
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/sweets">Sweeter</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item
                    <%
                        if("sweets.jsp".equals(currentPage)) {
                            out.println("active");
                        }
                    %>">
                <a class="nav-link" href="/sweets">
                    <fmt:message key="button.home.label"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link
                    <%
                        if("edit-sweet.jsp".equals(currentPage)) {
                            out.println("active");
                        }
                    %>" href="/create-sweet"><fmt:message key="sweet.new"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout"><fmt:message key="logout.label"/></a>
            </li>
        </ul>
    </div>

    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <fmt:message key="lang.label"/>
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="?sessionLocale=lv"><fmt:message key="lang.latvian" /></a>
            <a class="dropdown-item" href="?sessionLocale=en"><fmt:message key="lang.english" /></a>
        </div>
    </div>
</nav>