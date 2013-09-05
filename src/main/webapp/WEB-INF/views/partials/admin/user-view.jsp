<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<h2><s:message code="view.admin.user.view"/></h2>

<form class="form-horizontal" role="form" name="userForm" novalidate>
    <input type="hidden" name="user.id"/>

    <div class="form-group">
        <label class="col-lg-2 control-label"><s:message code="label.avatar"/></label>

        <div class="col-lg-5" style="max-width: 300px;">
            <img ng-src="{{user.avatar}}" class="img-responsive" alt="Responsive image" ng-model="file">
        </div>
    </div>

    <div class="form-group">
        <label class="col-lg-2 control-label"><s:message code="label.login"/></label>

        <div class="col-lg-10">
            <p class="form-control-static">{{user.login}}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label"><s:message code="label.name"/></label>

        <div class="col-lg-10">
            <p class="form-control-static">{{user.name}}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label"><s:message code="label.roles"/></label>

        <div class="col-lg-10">
            <div class="checkbox" ng-repeat="role in roles">
                <label>
                    <input type="checkbox" value="" ng-checked="role.checked" ng-model="role.checked" ng-value="role.name" disabled> {{role.name}}
                </label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label"><s:message code="view.admin.user.enabled"/></label>

        <div class="col-lg-10">
            <input type="checkbox" name="user.online" id="user.enabled" ng-checked="user.enabled" disabled/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label"><s:message code="view.admin.user.online_status"/></label>

        <div class="col-lg-10">
            <input type="checkbox" name="user.online" id="user.online" ng-checked="user.online" disabled/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label"><s:message code="view.admin.user.last_login_date"/></label>

        <div class="col-lg-10">
            <p class="form-control-static">{{user.lastLogin | date:dateFormat}}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label"><s:message code="view.admin.user.created_date"/></label>

        <div class="col-lg-10">
            <p class="form-control-static">{{user.createdDate | date:dateFormat}}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label"><s:message code="view.admin.user.last_modified_date"/></label>

        <div class="col-lg-10">
            <p class="form-control-static">{{user.lastModifiedDate | date:dateFormat}}</p>
        </div>
    </div>


    <div class="form-group">
        <div class="col-lg-offset-2 col-lg-10">
            <a href="#/users/{{user.id}}/edit" class="btn btn-primary"><s:message code="view.admin.user.edit"/></a>
            <button ng-click="disableUser()" ng-show="user.enabled" class="btn btn-default"><s:message code="view.admin.user.disable"/></button>
            <button ng-click="enableUser()" ng-hide="user.enabled" class="btn btn-default"><s:message code="view.admin.user.enable"/></button>
            <button ng-click="deleteUser()" class="btn btn-default"><s:message code="view.admin.user.delete"/></button>
            <a href="#/users" class="btn btn-default"><s:message code="view.admin.user.back"/></a>
        </div>
    </div>
</form>