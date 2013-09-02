<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<div>
    <security:authentication property="principal.username" var="username"/>
    <security:authentication property="principal.roleNames" var="roles"/>
    <div><s:message code="label.user"/>: ${username}</div>
    <div><s:message code="label.roles"/>:  ${fn:join(roles, ", ")}</div>
    <div><a href='<s:url value="/logout"></s:url>'><s:message code="label.logout"/></a></div>
</div>