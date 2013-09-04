<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<div>
    <security:authentication property="principal.username" var="username"/>
    <security:authentication property="principal.roleNames" var="roles"/>
    <security:authentication property="principal.avatar" var="avatar"/>

    <div style="max-width: 100px;"><img src="<c:url value="/resources/avatars/${avatar != null ? avatar : 'default.png'}"/>" class="img-responsive" alt="Responsive image"></div>

    <div><s:message code="label.user"/>: ${username}</div>
    <div><s:message code="label.roles"/>:  ${fn:join(roles, ", ")}</div>
    <div><a href='<s:url value="/logout"></s:url>'><s:message code="label.logout"/></a></div>
</div>