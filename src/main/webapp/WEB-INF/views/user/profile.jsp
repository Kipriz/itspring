<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authentication property="principal.username" var="username"/>

<h2><s:message code="view.profile.header"/></h2>

<form class="form-horizontal" role="form" name="userForm" novalidate>
    <input type="hidden" name="user.id"/>

    <div class="form-group">
        <label class="col-lg-2 control-label"><s:message code="label.avatar"/></label>

        <div class="col-lg-10" style="max-width: 300px;">
            <img src="<c:url value="/resources/avatars/${user.avatar}"/>" class="img-responsive" alt="Responsive image">
        </div>
    </div>

    <div class="form-group">
        <label class="col-lg-2 control-label"><s:message code="label.login"/></label>

        <div class="col-lg-10">
            <p class="form-control-static">${user.login}</p>
        </div>
    </div>

    <div class="form-group">
        <label class="col-lg-2 control-label"><s:message code="label.name"/></label>

        <div class="col-lg-10">
            <p class="form-control-static">${user.name}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label"><s:message code="label.roles"/></label>
        <div class="col-lg-10">
            <ul class="form-control-static">
                <c:forEach var="role" items="${user.roles}">
                    <li>${role.name}</li>
                </c:forEach>
            </ul>
        </div>
    </div>
</form>