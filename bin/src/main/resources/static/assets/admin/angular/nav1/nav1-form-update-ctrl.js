var checkNameNav1 = 0;
var checkCategory = 0;
var checkNameSearch = 0;

$(document).ready(function () {
  $("#nameNav1").keyup(function () {
    var name = this.value;
    if (name == "") {
      $("#nameNav1").addClass("is-invalid");
      $("#showErrorName").text("Vui lòng nhập tên menu bậc 1!");
      checkNameNav1 = 10;
    } else {
      var length = name.length;
      var minLength = $("#nameNav1").attr("data-minlength");
      var maxLength = $("#nameNav1").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#nameNav1").addClass("is-invalid");
        $("#showErrorName").text("Nhập giá trị từ 5 đến 30 ký tự!");
        checkNameNav1 = 10;
      } else {
        $("#nameNav1").removeClass("is-invalid");
        $("#showErrorName").text("");
        checkNameNav1 = 1;
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

  $("#category").change(function() {
		var category = this.value;
		if (category == "") {
			$("#category").addClass("is-invalid");
			$("#showErrorCategory").text("Vui lòng chọn danh mục!");
			checkCategory = 10;
		}
		else {
			$("#category").removeClass("is-invalid");
			$("#showErrorCategory").text("");
			checkCategory = 1;
		}
		handlerButtonSave();
	});
});

function checkForm() {
  $("#nameNav1").keyup();
  $("#category").change();
  $("#nameSearch").keyup();
  return handlerButtonSave();
}

function handlerButtonSave() {
  var check = false;
  if (checkNameNav1 !== 10 &&
      checkCategory !== 10 &&
      checkNameSearch !== 10) {
    check = true;
    $("#btnSave").prop("disabled", false);
  } else {
    check = false;
    $("#btnSave").prop("disabled", true);
  }
  return check;
}

var app = angular.module("nav1-form-app", []);

app.controller("nav1-form-ctrl", function ($scope, $http) {
  $scope.items = [];
  $scope.form = {};
  $scope.info = {};
  $scope.initialize = function () {
    $http.get("/rest/categories").then((resp) => {
      $scope.items = resp.data;
    });
  };
  $scope.initialize();
  $scope.load = function () {
    $http
      .get("/rest/nav1/form/" + $("#demo").val())
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
        .put("/rest/nav1/form/" + $("#demo").val(), item)
        .then((resp) => {
          $scope.info.status = true;
          $scope.info.content = "Bạn đã cập nhật menu thành công!";
          $("#modalSuccess").modal("show");
          var path = "/admin/nav1/update/" + $scope.form.id;
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
