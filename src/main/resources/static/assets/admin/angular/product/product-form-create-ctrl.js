var checkCode = 0;
var checkName = 0;
var checkPrice = 0;
var checkPoint = 0;
var checkQuality = 0;
var checkImage1 = 0;
var checkImage2 = 0;
var checkImage3 = 0;
var checkImage4 = 0;
var checkImage5 = 0;
var checkNameSearch = 0;
var checkCategory = 0;
var checkManufacturer = 0;
var checkActive = 0;
var checkDescription = 0;
var checkSpecification = 0;
$(document).ready(function () {
  $("#code").keyup(function () {
    var code = this.value;
    if (code == "") {
      $("#code").addClass("is-invalid");
      $("#showErrorCode").text("Vui lòng nhập mã sản phẩm!");
      checkCode = 10;
    } else {
      var length = code.length;
      var minLength = $("#code").attr("data-minlength");
      var maxLength = $("#code").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#code").addClass("is-invalid");
        $("#showErrorCode").text("Nhập giá trị từ 5 đến 10 ký tự!");
        checkCode = 10;
      } else {
        $("#code").removeClass("is-invalid");
        $("#showErrorCode").text("");
        checkCode = 1;
      }
    }
    handlerButtonSave();
  });

  $("#name").keyup(function () {
    var name = this.value;
    if (name == "") {
      $("#name").addClass("is-invalid");
      $("#showErrorName").text("Vui lòng nhập tên sản phẩm!");
      checkName = 10;
    } else {
      var length = name.length;
      var minLength = $("#name").attr("data-minlength");
      var maxLength = $("#name").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#name").addClass("is-invalid");
        $("#showErrorName").text("Nhập giá trị từ 10 đến 50 ký tự!");
        checkName = 10;
      } else {
        $("#name").removeClass("is-invalid");
        $("#showErrorName").text("");
        checkName = 1;
      }
    }
    handlerButtonSave();
  });

  $("#price").keyup(function () {
    var price = this.value;
    if (price == 0) {
      $("#price").addClass("is-invalid");
      $("#showErrorPrice").text("Vui lòng nhập giá sản phẩm!");
      checkPrice = 10;
    } else {
      if (price < 1000) {
        $("#price").addClass("is-invalid");
        $("#showErrorPrice").text("Giá sản phẩm phải lớn hơn 1 nghìn!");
        checkPrice = 10;
      } else {
        $("#price").removeClass("is-invalid");
        $("#showErrorPrice").text("");
        checkPrice = 1;
      }
    }
    handlerButtonSave();
  });

  $("#point").keyup(function () {
    var point = this.value;
    if (point < 0) {
      $("#point").addClass("is-invalid");
      $("#showErrorPoint").text("Điểm đổi thưởng là số dương!");
      checkPoint = 10;
    } else {
      $("#point").removeClass("is-invalid");
      $("#showErrorPoint").text("");
      checkPoint = 1;
    }
    handlerButtonSave();
  });

  $("#quality").keyup(function () {
    var quality = this.value;
    if (quality <= 0) {
      $("#quality").addClass("is-invalid");
      $("#showErrorQuality").text("Vui lòng nhập giá số lượng sản phẩm!");
      checkQuality = 10;
    } else {
      $("#quality").removeClass("is-invalid");
      $("#showErrorQuality").text("");
      checkQuality = 1;
    }
    handlerButtonSave();
  });

  $("#chooseImage1").change(function () {
    var image1 = this.value;
    if (image1 == "") {
      $("#valueImage1").addClass("is-invalid");
      $("#showErrorImage1").text("Vui lòng chọn hình ảnh 1!");
      checkImage1 = 10;
    } else {
      angular.element(this).scope().imageImage1Changed(this.files);
      $("#valueImage1").removeClass("is-invalid");
      $("#showErrorImage1").text("");
      checkImage1 = 1;
    }
    handlerButtonSave();
  });

  $("#chooseImage2").change(function () {
    var image2 = this.value;
    if (image2 == "") {
      $("#valueImage2").addClass("is-invalid");
      $("#showErrorImage2").text("Vui lòng chọn hình ảnh 2!");
      checkImage2 = 10;
    } else {
      angular.element(this).scope().imageImage2Changed(this.files);
      $("#valueImage2").removeClass("is-invalid");
      $("#showErrorImage2").text("");
      checkImage2 = 1;
    }
    handlerButtonSave();
  });

  $("#chooseImage3").change(function () {
    var image3 = this.value;
    if (image3 == "") {
      $("#valueImage3").addClass("is-invalid");
      $("#showErrorImage3").text("Vui lòng chọn hình ảnh 3!");
      checkImage3 = 10;
    } else {
      angular.element(this).scope().imageImage3Changed(this.files);
      $("#valueImage3").removeClass("is-invalid");
      $("#showErrorImage3").text("");
      checkImage3 = 1;
    }
    handlerButtonSave();
  });

  $("#chooseImage4").change(function () {
    var image4 = this.value;
    if (image4 == "") {
      $("#valueImage4").addClass("is-invalid");
      $("#showErrorImage4").text("Vui lòng chọn hình ảnh 4!");
      checkImage4 = 10;
    } else {
      angular.element(this).scope().imageImage4Changed(this.files);
      $("#valueImage4").removeClass("is-invalid");
      $("#showErrorImage4").text("");
      checkImage4 = 1;
    }
    handlerButtonSave();
  });

  $("#chooseImage5").change(function () {
    var image5 = this.value;
    if (image5 == "") {
      $("#valueImage5").addClass("is-invalid");
      $("#showErrorImage5").text("Vui lòng chọn hình ảnh 5!");
      checkImage5 = 10;
    } else {
      angular.element(this).scope().imageImage5Changed(this.files);
      $("#valueImage5").removeClass("is-invalid");
      $("#showErrorImage5").text("");
      checkImage5 = 1;
    }
    handlerButtonSave();
  });

  $("#nameSearch").keyup(function () {
    var nameSearch = this.value;
    if (nameSearch == "") {
      $("#nameSearch").addClass("is-invalid");
      $("#showErrorNameSearch").text("Vui lòng nhập tên tìm kiếm sản phẩm!");
      checkNameSearch = 10;
    } else {
      var length = nameSearch.length;
      var minLength = $("#nameSearch").attr("data-minlength");
      var maxLength = $("#nameSearch").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#nameSearch").addClass("is-invalid");
        $("#showErrorNameSearch").text("Nhập giá trị từ 5 đến 50 ký tự!");
        checkNameSearch = 10;
      } else {
        $("#nameSearch").removeClass("is-invalid");
        $("#showErrorNameSearch").text("");
        checkNameSearch = 1;
      }
    }
    handlerButtonSave();
  });

  $("#category").change(function () {
    var category = this.value;
    if (category == "") {
      $("#category").addClass("is-invalid");
      $("#showErrorCategory").text("Vui lòng chọn tên danh mục!");
      checkCategory = 10;
    }
    else {
      $("#category").removeClass("is-invalid");
      $("#showErrorCategory").text("");
      checkCategory = 1;
    }
    handlerButtonSave();
  });

  $("#manufacturer").change(function () {
    var manufacturer = this.value;
    if (manufacturer == "") {
      $("#manufacturer").addClass("is-invalid");
      $("#showErrorManufacturer").text("Vui lòng chọn tên nhà sản xuất!");
      checkManufacturer = 10;
    }
    else {
      $("#manufacturer").removeClass("is-invalid");
      $("#showErrorManufacturer").text("");
      checkManufacturer = 1;
    }
    handlerButtonSave();
  });

  $("#active").change(function () {
    var active = this.value;
    if (active == "") {
      $("#active").addClass("is-invalid");
      $("#showErrorActive").text("Vui lòng chọn trạng thái!");
      checkActive = 10;
    }
    else {
      $("#active").removeClass("is-invalid");
      $("#showErrorActive").text("");
      checkActive = 1;
    }
    handlerButtonSave();
  });

  $("#description").keyup(function () {
    var description = this.value;
    if (description == "") {
      $("#description").addClass("is-invalid");
      $("#showErrorDescription").text("Vui lòng nhập mô tả sản phẩm!");
      checkDescription = 10;
    } else {
      var length = description.length;
      var minLength = $("#description").attr("data-minlength");

      if (length < minLength) {
        $("#description").addClass("is-invalid");
        $("#showErrorDescription").text("Mô tả phải lớn hơn 200 ký tự!");
        checkDescription = 10;
      } else {
        $("#description").removeClass("is-invalid");
        $("#showErrorDescription").text("");
        checkDescription = 1;
      }
    }
    handlerButtonSave();
  });

  $("#specification").keyup(function () {
    var specification = this.value;
    if (specification == "") {
      $("#specification").addClass("is-invalid");
      $("#showErrorSpecification").text("Vui lòng nhập thông số sản phẩm!");
      checkSpecification = 10;
    } else {
      var length = specification.length;
      var minLength = $("#specification").attr("data-minlength");

      if (length < minLength) {
        $("#specification").addClass("is-invalid");
        $("#showErrorSpecification").text("Thông số phải lớn hơn 7 ký tự!");
        checkSpecification = 10;
      } else {
        $("#specification").removeClass("is-invalid");
        $("#showErrorSpecification").text("");
        checkSpecification = 1;
      }
    }
    handlerButtonSave();
  });

});

function checkForm() {
  $("#code").keyup();
  $("#name").keyup();
  $("#price").keyup();
  $("#point").keyup();
  $("#quality").keyup();
  $("#chooseImage1").change();
  $("#chooseImage2").change();
  $("#chooseImage3").change();
  $("#chooseImage4").change();
  $("#chooseImage5").change();
  $("#nameSearch").keyup();
  $("#category").change();
  $("#manufacturer").change();
  $("#active").change();
  $("#quality").keyup();
  $("#description").keyup();
  $("#specification").keyup();
  return handlerButtonSave();
}

function handlerButtonSave() {
  var check = false;
  if (checkCode !== 10 &&
    checkName !== 10 &&
    checkPrice !== 10 &&
    checkPoint !== 10 &&
    checkQuality !== 10 &&
    checkImage1 !== 10 &&
    checkImage2 !== 10 &&
    checkImage3 !== 10 &&
    checkImage4 !== 10 &&
    checkImage5 !== 10 &&
    checkNameSearch !== 10 &&
    checkManufacturer !== 10 &&
    checkCategory !== 10 &&
    checkActive !== 10 &&
    checkSpecification !== 10 &&
    checkDescription !== 10) {
    check = true;
    $("#btnSave").prop("disabled", false);
  } else {
    check = false;
    $("#btnSave").prop("disabled", true);
  }
  return check;
}

var app = angular.module("product-form-app", []);

app.controller("product-form-ctrl", function ($scope, $http) {
  $scope.itemCate = [];
  $scope.itemManu = [];
  $scope.form = {};
  $scope.info = {};

  $scope.initializeCate = function () {
    $http.get("/rest/categories").then((resp) => {
      $scope.itemCate = resp.data;
    });
  };

  $scope.initializeManu = function () {
    $http.get("/rest/manufactures").then((resp) => {
      $scope.itemManu = resp.data;
    });
  };
  $scope.initializeCate();
  $scope.initializeManu();

  $scope.save = function () {
    if (checkForm()) {
      var item = angular.copy($scope.form);
      $http.post(`/rest/product/form`, item).then((resp) => {
        $scope.info.status = true;
        $scope.info.content = "Bạn đã thêm mới thành công một sản phẩm!";
        $("#modalSuccess").modal("show");
        var path = "/admin/product/form";
        $("a").attr("href", path);
        console.log(resp);
      });
    }
  };

  // upload image 1
  $scope.imageImage1Changed = function (files) {
    var data = new FormData();
    data.append("file", files[0]);
    $http
      .post("/rest/upload/imageProduct", data, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
        $scope.form.image1 = resp.data.name;
      })
      .catch((error) => {
        alert("loi load hinh");
        console.log(error);
      });
  };

  // upload image 2
  $scope.imageImage2Changed = function (files) {
    var data = new FormData();
    data.append("file", files[0]);
    $http
      .post("/rest/upload/imageProduct", data, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
        $scope.form.image2 = resp.data.name;
      })
      .catch((error) => {
        alert("loi load hinh");
        console.log(error);
      });
  };

  // upload image 3
  $scope.imageImage3Changed = function (files) {
    var data = new FormData();
    data.append("file", files[0]);
    $http
      .post("/rest/upload/imageProduct", data, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
        $scope.form.image3 = resp.data.name;
      })
      .catch((error) => {
        alert("loi load hinh");
        console.log(error);
      });
  };

  // upload image 4
  $scope.imageImage4Changed = function (files) {
    var data = new FormData();
    data.append("file", files[0]);
    $http
      .post("/rest/upload/imageProduct", data, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
        $scope.form.image4 = resp.data.name;
      })
      .catch((error) => {
        alert("loi load hinh");
        console.log(error);
      });
  };

  // upload image 5
  $scope.imageImage5Changed = function (files) {
    var data = new FormData();
    data.append("file", files[0]);
    $http
      .post("/rest/upload/imageProduct", data, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
        $scope.form.image5 = resp.data.name;
      })
      .catch((error) => {
        alert("loi load hinh");
        console.log(error);
      });
  };
  
});
