var app = angular.module("demo-app", []);

app.controller("demo-ctrl", function ($scope, $http) {
  $scope.items = [];
  $scope.initialize = function () {
    var date = new Date();
    $http.get("/rest/statistical/revenue/month/" + date.getFullYear()).then((resp) => {
      $scope.items = resp.data;
    });
  };
  $scope.initialize();
});
