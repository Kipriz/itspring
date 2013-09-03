<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authentication property="principal.username" var="username"/>

<div ng-app="AdminApp" ng-controller="AdminCtrl">
    <div ng-view></div>
</div>