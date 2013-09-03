app = angular.module "AdminApp", ['ngRoute']

app.config ['$routeProvider', ($routeProvider) ->
  $routeProvider
    .when '/users',
      templateUrl: "#{Global.contextPath}/partials/admin/users.html",
      controller: 'UserListCtrl'
    .otherwise
      redirectTo: '/users'
]


app.controller "AdminCtrl", ($scope) ->


app.controller "UserListCtrl", ($scope, $http) ->
  $http.get("#{Global.contextPath}/admin/api/users").success (data) ->
    $scope.users = data
