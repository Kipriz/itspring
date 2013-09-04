app = angular.module "AdminApp", ['ngRoute', 'ngResource']

app.config ['$routeProvider', ($routeProvider) ->
  $routeProvider
    .when '/users',
      templateUrl: "#{Global.contextPath}/partials/admin/users.html",
      controller: 'UserListCtrl'
    .when '/users/:userId',
      templateUrl: "#{Global.contextPath}/partials/admin/user-edit.html"
      controller: 'UserEditCtrl'
    .otherwise
      redirectTo: '/users'
]

app.factory "User", ($resource) ->
  $resource "#{Global.contextPath}/admin/api/users/:userId", {}, {
    query:
      method: 'GET'
      url: "#{Global.contextPath}/admin/api/users"
      isArray: true
  }

app.factory "Role", ($resource) ->
  $resource "#{Global.contextPath}/admin/api/roles", {}, {
    query:
      method: 'GET',
      isArray: true
  }

app.controller "AdminCtrl", ($scope) ->


app.controller "UserListCtrl", ($scope, $http, User) ->
  $scope.users = User.query()



app.controller "UserEditCtrl", ($scope, $routeParams, $location, $filter, User, Role) ->

  $scope.user = User.get {userId: $routeParams.userId}, (user) ->
    $scope.user.avatar = "#{Global.resources}/avatars/" + $scope.user.avatar
    $scope.roles = Role.query()
    $scope.roles.$promise.then (data) ->
      for role in $scope.roles
        role.checked = $scope.hasRole(user, role)


  $scope.hasRole = (user, role) ->
    for userRole in user.roles
      if userRole.code == role.code
        return true
    return false

  $scope.cancel = () ->
    $location.path "/users"

  $scope.save = () ->
    $scope.severeError = false
    $scope.errors = []
    roles = $filter('filter')($scope.roles, {checked: true})
    $scope.user.roles = roles
    $scope.user.$save(
      (data) ->
        alert "Success"
      (exception) ->
        switch (exception.status)
          when 422 then $scope.errors = exception.data
          else $scope.severeError = true
    )
