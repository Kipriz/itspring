<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<h2><s:message code="view.admin.user.edit"/></h2>

<form class="form-horizontal" role="form" name="userForm" novalidate>
    <input type="hidden" name="user.id"/>
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
            <input type="password" class="form-control" id="password" name="user.password" placeholder="<s:message code="label.password"/>">
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