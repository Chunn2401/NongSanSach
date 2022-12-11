angular.module("shop-app", ["shop-app.controllers", "datatables"]);
angular
  .module("shop-app.controllers", [])
  .controller(
    "shop-ctrl",
    function (
      $scope,
      DTOptionsBuilder,
      DTColumnBuilder,
      DTColumnDefBuilder,
      $http
    ) {
      $scope.items = [];
      $scope.form = {};
      $scope.info = {};
      $scope.initialize = function () {
        $http.get("/rest/shop").then((resp) => {
          $scope.items = resp.data;
        });
      };
      $scope.initialize();

      $scope.showModal = function (item) {
        $scope.form = item;
        $("#modal").modal("show");
      };

      $scope.delete = function () {
        $http
          .delete(`/rest/shop/` + $scope.form.id)
          .then((resp) => {
            var index = $scope.items.findIndex((p) => p.id == $scope.form.id);
            $scope.items.splice(index, 1);

            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã xóa thông tin thành công!";
            $("#modalInfo").modal("show");
          })
          .catch((error) => {
            console.log(error);
          });
      };

      $scope.active = function (item) {
        //var temp = angular.copy($scope.form);
        $http
          .put("/rest/shop/form/active/" + item.id, item)
          .then((resp) => {
            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã cập nhật thông tin thành công!";
            $("#modalInfo").modal("show");
            $scope.initialize();
          })
          .catch((error) => {
            alert("Lỗi thêm sản phẩm");
            console.log(error);
          });
      };

      $scope.update = function (item) {
        var path = "/admin/shop/update/" + item.id;
        $("a").attr("href", path);
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(5).notSortable(),
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true)
        .withOption("searching", true)
        .withOption("info", true);
    }
  );
