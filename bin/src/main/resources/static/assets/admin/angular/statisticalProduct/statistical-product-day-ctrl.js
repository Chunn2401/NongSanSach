angular.module("top-product-day-app", ["top-product-day-app.controllers", "datatables"]);
angular
  .module("top-product-day-app.controllers", [])
  .controller(
    "top-product-day-ctrl",
    function (
      $scope,
      DTOptionsBuilder,
      DTColumnBuilder,
      DTColumnDefBuilder,
      $http
    ) {
      $scope.items = [];

      $scope.info = {};
      $scope.initialize = function () {
        $http.get("/rest/statistical/product/day").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

      $scope.vm = {};
      $scope.vm.dtInstance = {};
    //   $scope.vm.dtColumnDefs = [
    //     DTColumnDefBuilder.newColumnDef(6).notSortable(),
    //   ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true)
        .withOption("searching", true)
        .withOption("info", true);
    }
  );
