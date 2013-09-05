app = angular.module "AdminApp", ['ngRoute', 'ngResource']

app.config ['$routeProvider', ($routeProvider) ->
  $routeProvider
    .when '/users',
      templateUrl: "#{Global.contextPath}/partials/admin/users.html",
      controller: 'UserListCtrl'
    .when '/users/:userId',
      templateUrl: "#{Global.contextPath}/partials/admin/user-view.html"
      controller: 'UserViewCtrl'
    .when '/users/:userId/edit',
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
    disable:
      method: 'POST',
      url: "#{Global.contextPath}/admin/api/users/disable"
    enable:
      method: 'POST',
      url: "#{Global.contextPath}/admin/api/users/enable"
  }

app.factory "Role", ($resource) ->
  $resource "#{Global.contextPath}/admin/api/roles", {}, {
    query:
      method: 'GET',
      isArray: true
  }

app.controller "AdminCtrl", ($scope) ->



app.controller "UserListCtrl", ($scope, $http, User) ->
  $scope.dateFormat = 'yyyy-MM-dd HH:mm:ss Z'
  $scope.users = User.query()
  $scope.reverseOrder = false

  $scope.orderBy = (column) ->
    if $scope.orderFilter == column
      $scope.reverseOrder = !$scope.reverseOrder
    else
      $scope.orderFilter = column
      $scope.reverseOrder = false



app.controller "UserEditCtrl", ($scope, $routeParams, $location, $filter, $http, User, Role) ->
  if $routeParams.userId != "0"
  # load existing user
    $scope.user = User.get {userId: $routeParams.userId}, (user) ->
      $scope.user.avatar = "#{Global.resources}/avatars/" + $scope.user.avatar
      $scope.roles = Role.query()
      $scope.roles.$promise.then (data) ->
        for role in $scope.roles
          role.checked = $scope.hasRole(user, role)
  else
  # create new user
    user = new User()
    user.avatar = "#{Global.resources}/avatars/default.png"
    user.id = 0
    $scope.user = user
    $scope.roles = Role.query()
    $scope.roles.$promise.then (data) ->
      for role in $scope.roles
        role.checked = false


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
        nextAction = ->
          $location.path "/users/#{data.id}"
        if $scope.files
          $scope.uploadAvatar(data.id, nextAction)
        else
          nextAction()
      (exception) ->
        switch (exception.status)
          when 422 then $scope.errors = exception.data
          else $scope.severeError = true
    )

  $scope.setFiles = (files) ->
    $scope.files = files

  $scope.uploadAvatar = (id, successCallback) ->
    fd = new FormData()
    fd.append "file", $scope.files[0]

    url = "#{Global.contextPath}/admin/api/users/#{id}/changeAvatar"
    $http.post(
      url,
      fd,
      {
        headers: {'Content-Type': undefined }
        transformRequest: angular.identity
      }
    )
      .success ->
        successCallback()
      .error (exception) ->
        $scope.errors = exception.data


app.controller "UserViewCtrl", ($scope, $routeParams, $location, $filter, $http, User, Role) ->

  $scope.dateFormat = 'yyyy-MM-dd HH:mm:ss Z'

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

  $scope.disableUser = () ->
    User.disable {id: $scope.user.id}, (data) ->
      $scope.user.enabled = false

  $scope.enableUser = () ->
    User.enable {id: $scope.user.id}, (data) ->
      $scope.user.enabled = true

  $scope.deleteUser = () ->
    if (confirm("Are you sure?"))
      User.delete {id: $scope.user.id}, (data) ->
        $location.path "/users"

