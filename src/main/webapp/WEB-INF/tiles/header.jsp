<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">ItSpring</a>
        <div class="nav-collapse collapse">
            <ul class="nav navbar-nav">
                <c:set var="currentPath" value='${pageContext.request.getAttribute("javax.servlet.forward.request_uri")}'/>

                <security:authorize access="isAuthenticated()">
                    <s:url value="/profile" var="profileUrl"/>
                    <li class="${profileUrl == currentPath ? 'active' : ''}"><a href="${profileUrl}"><s:message code="view.header.profile"/></a></li>
                </security:authorize>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <s:url value="/admin/home" var="adminHomeUrl"/>
                    <li class="${adminHomeUrl == currentPath ? 'active' : ''}"><a href="${adminHomeUrl}"><s:message code="view.header.user_administration"/></a></li>
                </security:authorize>
            </ul>
            <ul class="nav navbar-nav pull-right">
                <security:authorize access="!isAuthenticated()">
                    <li><a href='<s:url value="/signin"></s:url>'><s:message code="label.signin"/></a></li>
                </security:authorize>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</div>