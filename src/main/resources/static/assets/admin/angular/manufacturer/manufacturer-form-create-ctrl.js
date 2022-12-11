var checkNameManufacturer = 0;
var checkLogo = 0;
var checkBanner = 0;
var checkDescribe = 0;
$(document).ready(function () {
  $("#nameManufacturer").keyup(function () {
    var name = this.value;
    if (name == "") {
      $("#nameManufacturer").addClass("is-invalid");
      $("#showErrorNameManufacturer").text("Vui lòng nhập tên thương hiệu!");
      checkNameManufacturer = 10;
    } else {
      var length = name.length;
      var minLength = $("#nameManufacturer").attr("data-minlength");
      var maxLength = $("#nameManufacturer").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#nameManufacturer").addClass("is-invalid");
        $("#showErrorNameManufacturer").text("Nhập giá trị từ 3 đến 30 ký tự!");
        checkNameManufacturer = 10;
      } else {
        $("#nameManufacturer").removeClass("is-invalid");
        $("#showErrorNameManufacturer").text("");
        checkNameManufacturer = 1;
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
      $("#showErrorDescribe").text("Vui lòng nhập mô tả thương hiệu!");
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
  $("#nameManufacturer").keyup();
  $("#chooseLogo").change();
  $("#chooseBanner").change();
  $("#describe").keyup();
  return handlerButtonSave();
}

function handlerButtonSave() {
  var check = false;
  if (
    checkNameManufacturer !== 10 &&
    checkLogo !== 10 &&
    checkBanner !== 10 &&
    checkDescribe !== 10
  ) {
    check = true;
    $("#btnSave").prop("disabled", false);
  } else {
    check = false;
    $("#btnSave").prop("disabled", true);
  }
  return check;
}

var app = angular.module("manufacturer-form-app", []);

app.controller("manufacturer-form-ctrl", function ($scope, $http) {
  $scope.form = {};
  $scope.info = {};
  $scope.save = function () {
    if (checkForm()) {
      var item = angular.copy($scope.form);
      $http.post(`/rest/manufactures/form`, item).then((resp) => {
        $scope.info.status = true;
        $scope.info.content = "Bạn đã thêm mới thành công một thương hiệu!";
        $("#modalSuccess").modal("show");
        var path = "/admin/manufactures/form";
        $("a").attr("href", path);
        console.log(resp);
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
