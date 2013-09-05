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
        <th><a href="" ng-click="orderBy('id')" ng-class="{active: orderFilter == 'id', asc: reverseOrder, desc: !reverseOrder}"><s:message code="view.admin.users.table.id"/></a></th>
        <th><a href="" ng-click="orderBy('name')" ng-class="{active: orderFilter == 'name', asc: reverseOrder, desc: !reverseOrder}"><s:message code="view.admin.users.table.name"/></a></th>
        <th><a href="" ng-click="orderBy('login')" ng-class="{active: orderFilter == 'login', asc: reverseOrder, desc: !reverseOrder}"><s:message code="view.admin.users.table.login"/></a></th>
        <th><a href="" ng-click="orderBy('lastLogin')" ng-class="{active: orderFilter == 'lastLogin', asc: reverseOrder, desc: !reverseOrder}"><s:message code="view.admin.users.table.last_login"/></a></th>
        <th><a href="" ng-click="orderBy('online')" ng-class="{active: orderFilter == 'online', asc: reverseOrder, desc: !reverseOrder}"><s:message code="view.admin.users.table.online"/></a></th>
    </tr>
    <tr ng-repeat="user in filtered = (users | filter:userFilter | filter:onlineOnlyFilter | orderBy:orderFilter:reverseOrder)">
        <td>{{user.id}}</td>
        <td><a href="#/users/{{user.id}}">{{user.name}}</a></td>
        <td>{{user.login}}</td>
        <td>{{user.lastLogin | date:dateFormat}}</td>
        <td><input type="checkbox" name="user.online" id="user.online" ng-checked="user.online" disabled/></td>
    </tr>
    </thead>
</table>
<div class="text-muted"><s:message code="view.admin.users.total"/> {{filtered.length}} <s:message code="view.admin.users.of"/> {{users.length}}</div>
