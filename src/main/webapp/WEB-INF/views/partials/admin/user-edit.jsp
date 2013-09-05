<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<h2 ng-show="user.id != 0"><s:message code="view.admin.user.edit"/></h2>
<h2 ng-show="user.id == 0"><s:message code="view.admin.user.add"/></h2>

<div class="alert alert-danger" ng-show="severeError">
    <s:message code="view.admin.user.severe_error"/>
</div>

<div class="alert alert-warning" ng-show="errors">
    <p><strong><s:message code="view.admin.user.errors"/></strong></p>
    <ul>
        <li ng-repeat="error in errors">{{error.message}}</li>
    </ul>
</div>

<form class="form-horizontal" role="form" name="userForm" novalidate>
    <input type="hidden" name="user.id"/>

    <div class="form-group">
        <label for="login" class="col-lg-2 control-label"><s:message code="label.avatar"/></label>

        <div class="col-lg-5" style="max-width: 300px;">
            <img ng-src="{{user.avatar}}" class="img-responsive" alt="Responsive image" ng-model="file">
        </div>
        <div class="col-lg-5" style="max-width: 500px;">
            <input type="file" name="avatar" id="avatar" onchange="angular.element(this).scope().setFiles(this.files)"/>
        </div>
    </div>

    <div class="form-group">
        <label for="login" class="col-lg-2 control-label"><s:message code="label.login"/></label>

        <div class="col-lg-10">
            <input type="text" class="form-control" id="login" name="user.login"
                   placeholder="<s:message code="label.login"/>"
                   ng-model="user.login">
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-lg-2 control-label"><s:message code="label.password"/></label>

        <div class="col-lg-10">
            <input type="password" class="form-control" id="password" name="user.password" placeholder="<s:message code="label.password"/>" ng-model="user.password">
        </div>
    </div>
    <div class="form-group">
        <label for="name" class="col-lg-2 control-label"><s:message code="label.name"/></label>

        <div class="col-lg-10">
            <input type="text" class="form-control" id="name" name="user.name"
                   placeholder="<s:message code="label.name"/>"
                   ng-model="user.name">
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label"><s:message code="label.roles"/></label>

        <div class="col-lg-10">
            <div class="checkbox" ng-repeat="role in roles">
                <label>
                    <input type="checkbox" value="" ng-checked="role.checked" ng-model="role.checked" ng-value="role.name"> {{role.name}}
                </label>
            </div>
        </div>
    </div>


    <div class="form-group">
        <div class="col-lg-offset-2 col-lg-10">
            <button type="submit" class="btn btn-primary" ng-click="save()"><s:message code="view.admin.user.save"/></button>
            <button type="submit" class="btn btn-default" ng-click="cancel()"><s:message code="view.admin.user.cancel"/></button>
        </div>
    </div>
</form>