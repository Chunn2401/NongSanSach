var checkNameCategory = 0;
var checkLogo = 0;
var checkBanner = 0;
var checkDescribe = 0;
$(document).ready(function () {
  $("#nameCategory").keyup(function () {
    var name = this.value;
    if (name == "") {
      $("#nameCategory").addClass("is-invalid");
      $("#showErrorNameCategory").text("Vui lòng nhập tên danh mục!");
      checkNameCategory = 10;
    } else {
      var length = name.length;
      var minLength = $("#nameCategory").attr("data-minlength");
      var maxLength = $("#nameCategory").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#nameCategory").addClass("is-invalid");
        $("#showErrorNameCategory").text("Nhập giá trị từ 7 đến 30 ký tự!");
        checkNameCategory = 10;
      } else {
        $("#nameCategory").removeClass("is-invalid");
        $("#showErrorNameCategory").text("");
        checkNameCategory = 1;
      }
    }
    handlerButtonSave();
  });

  $("#chooseLogo").change(function () {
    var logo = this.value;
    if (logo == "") {
      $("#valueLogo").addClass("is-invalid");
      $("#showErrorLogo").text("Vui lòng chọn logo đại diện!");
      checkLogo = 10;
    } else {
      angular.element(this).scope().imageLogoChanged(this.files);
      $("#valueLogo").removeClass("is-invalid");
      $("#showErrorLogo").text("");
      checkLogo = 1;
    }
    handlerButtonSave();
  });

  $("#chooseBanner").change(function () {
    var banner = this.value;
    if (banner == "") {
      $("#valueBanner").addClass("is-invalid");
      $("#showErrorBanner").text("Vui lòng chọn logo đại diện!");
      checkBanner = 10;
    } else {
      angular.element(this).scope().imageBannerChanged(this.files);
      $("#valueBanner").removeClass("is-invalid");
      $("#showErrorBanner").text("");
      checkBanner = 1;
    }
    handlerButtonSave();
  });

  $("#describe").keyup(function () {
    var describe = this.value;
    if (describe == "") {
      $("#describe").addClass("is-invalid");
      $("#showErrorDescribe").text("Vui lòng nhập mô tả danh mục!");
      checkDescribe = 10;
    } else {
      var length = describe.length;
      var minLength = $("#describe").attr("data-minlength");
      var maxLength = $("#describe").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#describe").addClass("is-invalid");
        $("#showErrorDescribe").text("Nhập giá trị từ 7 đến 500 ký tự!");
        checkDescribe = 10;
      } else {
        $("#describe").removeClass("is-invalid");
        $("#showErrorDescribe").text("");
        checkDescribe = 1;
      }
    }
    handlerButtonSave();
  });
});

function checkForm() {
  $("#nameCategory").keyup();
  $("#describe").keyup();
  return handlerButtonSave();
}

function handlerButtonSave() {
  var check = false;
  if (
    checkNameCategory !== 10 &&
    checkLogo !== 10 &&
    checkBanner !== 10 &&
    checkDescribe !== 10
  ) {
    check = true;
    $("#btnUpdate").prop("disabled", false);
  } else {
    check = false;
    $("#btnUpdate").prop("disabled", true);
  }
  return check;
}

var app = angular.module("category-form-app", []);

app.controller("category-form-ctrl", function ($scope, $http) {
  $scope.form = {};
  $scope.info = {};
  $scope.load = function () {
    $http
      .get("/rest/categories/form/" + $("#demo").val())
      .then((resp) => {
        $scope.form = resp.data;
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
        .put("/rest/categories/form/" + $("#demo").val(), item)
        .then((resp) => {
          $scope.info.status = true;
          $scope.info.content = "Bạn đã cập nhật danh mục thành công!";
          $("#modalSuccess").modal("show");
          var path = "/admin/categories/update/" + $scope.form.id;
          $("a").attr("href", path);
          console.log(resp);
        })
        .catch((error) => {
          alert("Lỗi thêm sản phẩm");
          console.log(error);
        });
    }
  };

  // upload logo
  $scope.imageLogoChanged = function (files) {
    var data = new FormData();
    data.append("file", files[0]);
    $http
      .post("/rest/upload/logo", data, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
        $scope.form.logo = resp.data.name;
      })
      .catch((error) => {
        alert("loi load hinh");
        console.log(error);
      });
  };

  // upload banner
  $scope.imageBannerChanged = function (files) {
    var data = new FormData();
    data.append("file", files[0]);
    $http
      .post("/rest/upload/banner", data, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
        $scope.form.banner = resp.data.name;
      })
      .catch((error) => {
        alert("loi load hinh");
        console.log(error);
      });
  };
});
