var checkCode = 0;
var checkName = 0;
var checkContent1 = 0;
var checkContent2 = 0;
var checkContent3 = 0;
var checkContent4 = 0;
var checkImage1 = 0;
var checkImage2 = 0;
var checkImage3 = 0;
var checkImage4 = 0;
var checkImage5 = 0;
$(document).ready(function () {
  $("#code").keyup(function () {
    var code = this.value;
    if (code == "") {
      $("#code").addClass("is-invalid");
      $("#showErrorCode").text("Vui lòng nhập mã blog!");
      checkCode = 10;
    } else {
      var length = code.length;
      var minLength = $("#code").attr("data-minlength");
      var maxLength = $("#code").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#code").addClass("is-invalid");
        $("#showErrorCode").text("Nhập mã từ 1 đến 5 ký tự!");
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
      $("#showErrorName").text("Vui lòng nhập tên Blog!");
      checkName = 10;
    } else {
      var length = name.length;
      var minLength = $("#name").attr("data-minlength");
      var maxLength = $("#name").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#name").addClass("is-invalid");
        $("#showErrorName").text("Nhập giá trị từ 10 đến 300 ký tự!");
        checkName = 10;
      } else {
        $("#name").removeClass("is-invalid");
        $("#showErrorName").text("");
        checkName = 1;
      }
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


  $("#content1").keyup(function () {
    var description = this.value;
    if (description == "") {
      $("#content1").addClass("is-invalid");
      $("#showErrorDescription1").text("Vui lòng nhập Nội dung 1!");
      checkContent1 = 10;
    } else {
      var length = description.length;
      var minLength = $("#content1").attr("data-minlength");
      
      if (length < minLength) {
        $("#content1").addClass("is-invalid");
        $("#showErrorDescription1").text("Nội dung 1 phải lớn hơn 100 ký tự!");
        checkContent1 = 10;
      } else {
        $("#content1").removeClass("is-invalid");
        $("#showErrorDescription1").text("");
        checkContent1 = 1;
      }
    }
    handlerButtonSave();
  });
  
  $("#content2").keyup(function () {
    var description = this.value;
    if (description == "") {
      $("#content2").addClass("is-invalid");
      $("#showErrorDescription2").text("Vui lòng nhập Nội dung 2!");
      checkContent2 = 10;
    } else {
      var length = description.length;
      var minLength = $("#content2").attr("data-minlength");
      
      if (length < minLength) {
        $("#content2").addClass("is-invalid");
        $("#showErrorDescription2").text("Nội dung 2 phải lớn hơn 100 ký tự!");
        checkContent2 = 10;
      } else {
        $("#content2").removeClass("is-invalid");
        $("#showErrorDescription2").text("");
        checkContent2 = 1;
      }
    }
    handlerButtonSave();
  });

	$("#content3").keyup(function () {
    var description = this.value;
    if (description == "") {
      $("#content3").addClass("is-invalid");
      $("#showErrorDescription3").text("Vui lòng nhập Nội dung 3!");
      checkContent3 = 10;
    } else {
      var length = description.length;
      var minLength = $("#content3").attr("data-minlength");
      
      if (length < minLength) {
        $("#content3").addClass("is-invalid");
        $("#showErrorDescription3").text("Nội dung 3 phải lớn hơn 100 ký tự!");
        checkContent3 = 10;
      } else {
        $("#content3").removeClass("is-invalid");
        $("#showErrorDescription3").text("");
        checkContent3 = 1;
      }
    }
    handlerButtonSave();
  });
  
  $("#content4").keyup(function () {
    var description = this.value;
    if (description == "") {
      $("#content4").addClass("is-invalid");
      $("#showErrorDescription4").text("Vui lòng nhập Nội dung 4!");
      checkContent4 = 10;
    } else {
      var length = description.length;
      var minLength = $("#content4").attr("data-minlength");
      
      if (length < minLength) {
        $("#content4").addClass("is-invalid");
        $("#showErrorDescription4").text("Nội dung 4 phải lớn hơn 100 ký tự!");
        checkContent4 = 10;
      } else {
        $("#content4").removeClass("is-invalid");
        $("#showErrorDescription4").text("");
        checkContent4 = 1;
      }
    }
    handlerButtonSave();
  });

});

function checkForm() {
  $("#code").keyup();
  $("#name").keyup();
  $("#chooseImage1").change();
  $("#chooseImage2").change();
  $("#chooseImage3").change();
  $("#chooseImage4").change();
  $("#chooseImage5").change();
  $("#content1").keyup();
  $("#content2").keyup();
  $("#content3").keyup();
  $("#content4").keyup();
  return handlerButtonSave();
}

function handlerButtonSave() {
  var check = false;
  if (checkCode !== 10 && 
      checkName !== 10 && 
      checkImage1 !== 10 &&
      checkImage2 !== 10 &&
      checkImage3 !== 10 &&
      checkImage4 !== 10 &&
      checkImage5 !== 10 &&
      checkContent1 !== 10 &&
      checkContent2 !== 10 &&
      checkContent3 !== 10 &&
      checkContent4 !== 10 ) {
    check = true;
    $("#btnSave").prop("disabled", false);
  } else {
    check = false;
    $("#btnSave").prop("disabled", true);
  }
  return check;
}

var app = angular.module("blog-form-app", []);

app.controller("blog-form-ctrl", function ($scope, $http) {
  
  $scope.form = {};
  $scope.info = {};


  $scope.save = function () {
    if (checkForm()) {
      var item = angular.copy($scope.form);
      $http.post(`/rest/blogs`, item).then((resp) => {
		resp.data.time = new Date(resp.data.time)
        $scope.info.status = true;
        $scope.info.content = "Bạn đã thêm mới thành công một Blog!";
        $("#modalSuccess").modal("show");
        var path = "/admin/blog/list";
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
      .post("/rest/upload/blog", data, {
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
      .post("/rest/upload/blog", data, {
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
      .post("/rest/upload/blog", data, {
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
      .post("/rest/upload/blog", data, {
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
      .post("/rest/upload/blog", data, {
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
