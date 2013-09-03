app = angular.module "AdminApp", ['ngRoute']

app.config ['$routeProvider', ($routeProvider) ->
  $routeProvider
    .when '/users',
      templateUrl: "#{Global.resources}/partials/admin/users.html",
      controller: 'UserListCtrl'
    .otherwise
      redirectTo: '/users'
]


app.controller "AdminCtrl", ($scope) ->
  $scope.greet = "AdminCtrl var"


app.controller "UserListCtrl", ($scope) ->
  $scope.greet = "UserListCtrl var"