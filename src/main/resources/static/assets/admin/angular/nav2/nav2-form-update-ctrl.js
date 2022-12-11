var checkNameNav2 = 0;
var checkMenu1 = 0;
var checkNameSearch = 0;

$(document).ready(function () {
  $("#nameNav2").keyup(function () {
    var name = this.value;
    if (name == "") {
      $("#nameNav2").addClass("is-invalid");
      $("#showErrorName").text("Vui lòng nhập tên menu bậc 2!");
      checkNameNav2 = 10;
    } else {
      var length = name.length;
      var minLength = $("#nameNav2").attr("data-minlength");
      var maxLength = $("#nameNav2").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#nameNav2").addClass("is-invalid");
        $("#showErrorName").text("Nhập giá trị từ 3 đến 30 ký tự!");
        checkNameNav2 = 10;
      } else {
        $("#nameNav2").removeClass("is-invalid");
        $("#showErrorName").text("");
        checkNameNav2 = 1;
      }
    }
    handlerButtonSave();
  });

  $("#nameSearch").keyup(function () {
    var name = this.value;
    if (name == "") {
      $("#nameSearch").addClass("is-invalid");
      $("#showErrorNameSearch").text("Vui lòng nhập tên tìm kiếm!");
      checkNameSearch = 10;
    } else {
      var length = name.length;
      var minLength = $("#nameSearch").attr("data-minlength");
      var maxLength = $("#nameSearch").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#nameSearch").addClass("is-invalid");
        $("#showErrorNameSearch").text("Nhập giá trị từ 5 đến 30 ký tự!");
        checkNameSearch = 10;
      } else {
        $("#nameSearch").removeClass("is-invalid");
        $("#showErrorNameSearch").text("");
        checkNameSearch = 1;
      }
    }
    handlerButtonSave();
  });

  $("#menu1").change(function() {
		var menu1 = this.value;
		if (menu1 == "") {
			$("#menu1").addClass("is-invalid");
			$("#showErrorMenu1").text("Vui lòng chọn tên menu bậc 1!");
			checkMenu1 = 10;
		}
		else {
			$("#menu1").removeClass("is-invalid");
			$("#showErrorMenu1").text("");
			checkMenu1 = 1;
		}
		handlerButtonSave();
	});
});

function checkForm() {
  $("#nameNav2").keyup();
  $("#menu1").change();
  $("#nameSearch").keyup();
  return handlerButtonSave();
}

function handlerButtonSave() {
  var check = false;
  if (checkNameNav2 !== 10 &&
      checkMenu1 !== 10 &&
      checkNameSearch !== 10) {
    check = true;
    $("#btnSave").prop("disabled", false);
  } else {
    check = false;
    $("#btnSave").prop("disabled", true);
  }
  return check;
}

var app = angular.module("nav2-form-app", []);

app.controller("nav2-form-ctrl", function ($scope, $http) {
  $scope.items = [];
  $scope.form = {};
  $scope.info = {};
  $scope.initialize = function () {
    $http.get("/rest/nav1").then((resp) => {
      $scope.items = resp.data;
    });
  };
  $scope.initialize();
  $scope.load = function () {
    $http
      .get("/rest/nav2/form/" + $("#demo").val())
      .then((resp) => {
        $scope.form = resp.data;
        console.log($scope.form);
      })
      .catch((error) => {
        console.log(error);
      });
  };
  $scope.load();

  $scope.update = function () {
    if (checkForm()) {
      $scope.form.id = $("#demo").val();
      var item = angular.copy($scope.form);
      $http
        .put("/rest/nav2/form/" + $("#demo").val(), item)
        .then((resp) => {
          $scope.info.status = true;
          $scope.info.content = "Bạn đã cập nhật menu thành công!";
          $("#modalSuccess").modal("show");
          var path = "/admin/nav2/update/" + $scope.form.id;
          $("a").attr("href", path);
          console.log(resp);
        })
        .catch((error) => {
          alert("Lỗi thêm menu");
          console.log(error);
        });
    }
  };
  
});
