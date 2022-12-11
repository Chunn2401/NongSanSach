angular.module("product-app", ["product-app.controllers", "datatables"]);
angular
  .module("product-app.controllers", [])
  .controller(
    "product-ctrl",
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
        $http.get("/rest/product").then((resp) => {
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
          .delete(`/rest/product/` + $scope.form.id)
          .then((resp) => {
            var index = $scope.items.findIndex((p) => p.id == $scope.form.id);
            $scope.items.splice(index, 1);

            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã xóa sản phẩm thành công!";
            $("#modalInfo").modal("show");

            //alert("Xoá sản phẩm thành công!");
          })
          .catch((error) => {
            console.log(error);
          });
      };

      $scope.update = function (item) {
        var path = "/admin/product/update/" + item.id;
        $("a").attr("href", path);
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(7).notSortable(),
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true)
        .withOption("searching", true)
        .withOption("info", true);
    }
  );
