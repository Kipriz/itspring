<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<h2><s:message code="view.admin.users.users"/></h2>

<div>
<p class="pull-right">
    <a href="#/users/0/edit" class="btn btn-primary"><s:message code="view.admin.users.add_user"/></a>
</p>
<div>
    <label for="userSearch"><s:message code="view.admin.users.search"/>:</label>
    <input type="text" name="userSearch" id="userSearch" ng-model="userFilter" class="form-control" style="width: auto; display: inline" placeholder="<s:message code="view.admin.users.search.placeholder"/>"/>
    <label style="padding: 0 10px;"><input type="checkbox" name="onlineOnly" id="onlyOnly" ng-model="onlineOnlyFilter.online"/> <s:message code="view.admin.users.table.online_only"/></label>
</div>
</div>

<table class="table table-hover">
    <thead>
    <tr>
        <th><s:message code="view.admin.users.table.id"/></th>
        <th><s:message code="view.admin.users.table.name"/></th>
        <th><s:message code="view.admin.users.table.login"/></th>
        <th><s:message code="view.admin.users.table.last_login"/></th>
        <th><s:message code="view.admin.users.table.online"/></th>
    </tr>
    <tr ng-repeat="user in filtered = (users | filter:userFilter | filter:onlineOnlyFilter)">
        <td>{{user.id}}</td>
        <td><a href="#/users/{{user.id}}">{{user.name}}</a></td>
        <td>{{user.login}}</td>
        <td>{{user.lastLogin | date:dateFormat}}</td>
        <td><input type="checkbox" name="user.online" id="user.online" ng-checked="user.online" disabled/></td>
    </tr>
    </thead>
</table>
<div class="text-muted"><s:message code="view.admin.users.total"/> {{filtered.length}} <s:message code="view.admin.users.of"/> {{users.length}}</div>
