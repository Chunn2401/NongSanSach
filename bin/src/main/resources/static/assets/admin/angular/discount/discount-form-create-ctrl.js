var checkName = 0;
var checkCode = 0;
var checkPrice = 0;
var checkMoneyLimit = 0;
var checkQuality = 0;
var checkApplyDay = 0;
var checkExpiration = 0;

$(document).ready(function () {
  $("#name").keyup(function () {
    var name = this.value;
    if (name == "") {
      $("#name").addClass("is-invalid");
      $("#showErrorName").text("Vui lòng nhập tên mã!");
      checkNameManufacturer = 10;
    } else {
      var length = name.length;
      var minLength = $("#name").attr("data-minlength");
      var maxLength = $("#name").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#name").addClass("is-invalid");
        $("#showErrorName").text("Nhập giá trị từ 7 đến 50 ký tự!");
        checkNameManufacturer = 10;
      } else {
        $("#name").removeClass("is-invalid");
        $("#showErrorName").text("");
        checkName = 1;
      }
    }
    handlerButtonSave();
  });

  $("#code").keyup(function () {
    var code = this.value;
    if (code == "") {
      $("#code").addClass("is-invalid");
      $("#showErrorCode").text("Vui lòng nhập mã giảm giá!");
      checkCode = 10;
    } else {
      var length = code.length;
      var minLength = $("#code").attr("data-minlength");
      var maxLength = $("#code").attr("data-maxlength");

      if (length < minLength || length > maxLength) {
        $("#code").addClass("is-invalid");
        $("#showErrorCode").text("Nhập giá trị từ 6 đến 10 ký tự!");
        checkCode = 10;
      } else {
        $("#code").removeClass("is-invalid");
        $("#showErrorCode").text("");
        checkCode = 1;
      }
    }
    handlerButtonSave();
  });

  $("#price").keyup(function () {
    var price = this.value;
    if (price == 0) {
      $("#price").addClass("is-invalid");
      $("#showErrorPrice").text("Vui lòng nhập số tiền sẽ giảm!");
      checkPrice = 10;
    } else {
      if (price < 1000) {
        $("#price").addClass("is-invalid");
        $("#showErrorPrice").text("Giá phải lớn hơn 1 nghìn!");
        checkPrice = 10;
      } else {
        $("#price").removeClass("is-invalid");
        $("#showErrorPrice").text("");
        checkPrice = 1;
      }
    }
    handlerButtonSave();
  });

  $("#moneyLimit").keyup(function () {
    var moneyLimit = this.value;
    if (moneyLimit == 0) {
      $("#moneyLimit").addClass("is-invalid");
      $("#showErrorMoneyLimit").text("Vui lòng nhập số tiền sẽ áp dụng!");
      checkMoneyLimit = 10;
    } else {
      if (moneyLimit < 1000) {
        $("#moneyLimit").addClass("is-invalid");
        $("#showErrorMoneyLimit").text("Số tiền phải lớn hơn 1 nghìn!");
        checkMoneyLimit = 10;
      } else {
        $("#moneyLimit").removeClass("is-invalid");
        $("#showErrorMoneyLimit").text("");
        checkMoneyLimit = 1;
      }
    }
    handlerButtonSave();
  });

  $("#quality").keyup(function () {
    var quality = this.value;
    if (quality == 0) {
      $("#quality").addClass("is-invalid");
      $("#showErrorQuality").text("Vui lòng nhập số lượng mã!");
      checkQuality = 10;
    } else {
      $("#quality").removeClass("is-invalid");
      $("#showErrorQuality").text("");
      checkQuality = 1;
    }
    handlerButtonSave();
  });

  $("#applyDay").change(function() {
		var applyDay = this.value;
		if (applyDay == "") {
			$("#applyDay").addClass("is-invalid");
			$("#showErrorApplyDay").text("Vui lòng chọn ngày áp dụng!");
			checkApplyDay = 10;
		}
		else {
			$("#applyDay").removeClass("is-invalid");
			$("#showErrorApplyDay").text("");
			checkApplyDay = 1;
		}
		handlerButtonSave();
	});

  $("#expiration").change(function() {
		var expiration = this.value;
		if (expiration == "") {
			$("#expiration").addClass("is-invalid");
			$("#showErrorExpiration").text("Vui lòng chọn ngày hết hạn!");
			checkExpiration = 10;
		}
		else {
			$("#expiration").removeClass("is-invalid");
			$("#showErrorExpiration").text("");
			checkExpiration = 1;
		}
		handlerButtonSave();
	});
});

function checkForm() {
  $("#name").keyup();
  $("#code").keyup();
  $("#price").keyup();
  $("#quality").keyup();
  $("#applyDay").change();
  $("#expiration").change();
  $("#moneyLimit").keyup();
  return handlerButtonSave();
}

function handlerButtonSave() {
  var check = false;
  if (checkName !== 10 && 
      checkCode !== 10 && 
      checkPrice !== 0 &&
      checkQuality !== 10 &&
      checkApplyDay !== 10 &&
      checkExpiration !== 10 &&
      checkMoneyLimit !== 10) {
    check = true;
    $("#btnSave").prop("disabled", false);
  } else {
    check = false;
    $("#btnSave").prop("disabled", true);
  }
  return check;
}

var app = angular.module("discount-form-app", []);

app.controller("discount-form-ctrl", function ($scope, $http) {
  $scope.form = {};
  $scope.info = {};
  $scope.save = function () {
    if (checkForm()) {
      $scope.form.applyDay = String($("#applyDay").val());
      $scope.form.expiration = String($("#expiration").val());
      var item = angular.copy($scope.form);
      $http.post(`/rest/discount/form`, item).then((resp) => {
        $scope.info.status = true;
        $scope.info.content = "Bạn đã thêm mới thành công một mã giảm giá!";
        $("#modalSuccess").modal("show");
        var path = "/admin/discount/form";
        $("a").attr("href", path);
        console.log(resp);
      });
    }
  };
});

function formatDate(date) {
  var d = new Date(date),
      month = '' + (d.getMonth() + 1),
      day = '' + d.getDate(),
      year = d.getFullYear();

  if (month.length < 2) 
      month = '0' + month;
  if (day.length < 2) 
      day = '0' + day;

  return [year, month, day].join('-');
}
