<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="hero-unit">
    <h1><s:message code="view.index.title" /></h1>
    <p>
        <s:message code="view.index.message.hello_not_authorized" htmlEscape="false"/>
    </p>
    <p>
        <a href='<s:url value="/signin" />'class="btn btn-large btn-success"><s:message code="label.signin"/></a>
    </p>
</div>