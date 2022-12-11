$(document).ready(function () {});

angular.module("employee-app", ["employee-app.controllers", "datatables"]);
angular
  .module("employee-app.controllers", [])
  .controller(
    "employee-ctrl",
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
        $http.get("/rest/employees").then((resp) => {
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
          .delete(`/rest/employees/` + $scope.form.user.id)
          .then((resp) => {
            var index = $scope.items.findIndex(
              (p) => p.user.id == $scope.form.user.id
            );
            $scope.items.splice(index, 1);

            $scope.info.status = true;
            $scope.info.alert = "Thành Công!";
            $scope.info.content = "Bạn đã xóa tài khoản thành công!";
            $("#modalInfo").modal("show");

            //alert("Xoá sản phẩm thành công!");
          })
          .catch((error) => {
            $scope.info.status = false;
            $scope.info.alert = "Cảnh Báo!";
            $scope.info.content =
              "Bạn không được phép xóa tài khoản đang đăng nhập!";
            $("#modalInfo").modal("show");
            //alert("Lỗi xoá sản phẩm")
            console.log(error);
          });
      };

      $scope.update = function (item) {
        if (item.user.email == $("#username").val()) {
          $scope.info.status = false;
          $scope.info.alert = "Cảnh Báo!";
          $scope.info.content =
            "Bạn không được phép cập nhật tài khoản đang đăng nhập!";
          $("#modalInfo").modal("show");
        } else {
          var path = "/admin/employees/update/" + item.user.id;
          $("a").attr("href", path);
        }
      };

      $scope.vm = {};
      $scope.vm.dtInstance = {};
      $scope.vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(8).notSortable(),
      ];
      $scope.vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption("paging", true)
        .withOption("searching", true)
        .withOption("info", true);
    }
  );
