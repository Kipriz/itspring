<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<form class="form-narrow form-horizontal" action='<s:url value="/j_spring_security_check"/>' method="post">
    <c:if test="${not empty param['error']}">
        <div class="alert alert-error">
           <s:message code="view.signin.error.signin"/>
        </div>
    </c:if>
    <fieldset>
        <legend><s:message code="view.signin.label.please_sign_in"/></legend>
        <div class="form-group">
            <label for="inputLogin" class="col-lg-2 control-label"><s:message code="label.login"/></label>
            <div class="col-lg-10">
                <input type="text" class="form-control" id="inputLogin" placeholder="<s:message code="label.login"/>" name="j_username">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword" class="col-lg-2 control-label"><s:message code="label.password"/></label>
            <div class="col-lg-10">
                <input type="password" class="form-control" id="inputPassword" placeholder="<s:message code="label.password"/>" name="j_password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="_spring_security_remember_me"><s:message code="label.remember_me"/>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <button type="submit" class="btn btn-default"><s:message code="view.signin.button.signin"/></button>
            </div>
        </div>
    </fieldset>
</form>
