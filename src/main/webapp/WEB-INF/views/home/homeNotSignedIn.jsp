<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="hero-unit">
    <h1><s:message code="view.index.title" /></h1>
    <p>
        Welcome to the ItSpring demo application!
        Get started quickly	by signing in as user (user/demo) or administrator (admin/admin).
    </p>
    <p>
        <a href='<s:url value="/signin" />'class="btn btn-large btn-success">Sign in</a>
    </p>
</div>