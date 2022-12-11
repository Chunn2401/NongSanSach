angular.module("order-pending-app", ["order-pending-app.controllers", "datatables"]);
angular
  .module("order-pending-app.controllers", [])
  .controller(
    "order-pending-ctrl",
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
        $http.get("/rest/order/pending").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

      $scope.formDetail = [];
      $scope.modalDetail = function (detail) {
        $http.get("/rest/order/pending/" + detail.id).then((resp) => {
          $scope.formDetail = resp.data;
        });
        $("#modalDetail").modal("show");
      };

      $scope.formApprove = {};
      $scope.showModalApprove = function (item) {
        $scope.formApprove = item;
        $("#modalApprove").modal("show");
      };

      $scope.approve = function () {
        $http
          .put(`/rest/order/approve/` + $scope.formApprove.id)
          .then((resp) => {
            var index = $scope.items.findIndex(
              (p) => p.id == $scope.formApprove.id
            );
            $scope.items.splice(index, 1);
            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã duyệt thành công đơn hàng này!";
            $("#modalInfo").modal("show");
          })
          .catch((error) => {
            console.log(error);
          });
      };

      $scope.formCancel = {};
      $scope.showModal = function (item) {
        $scope.formCancel = item;
        $("#modal").modal("show");
      };

      $scope.cancel = function () {
        $http
          .put(`/rest/order/cancel/` + $scope.formCancel.id)
          .then((resp) => {
            var index = $scope.items.findIndex(
              (p) => p.id == $scope.formCancel.id
            );
            $scope.items.splice(index, 1);

            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã hủy đơn hàng thành công!";
            $("#modalInfo").modal("show");

          })
          .catch((error) => {          
          });
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(6).notSortable(),
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true)
        .withOption("searching", true)
        .withOption("info", true);
    }
  );
