$(document).ready(function () {
    disableMethod();
    disableConfirm();
    useAddressExits();
});

function disableAddress() {
    $("#txtAddress").text("Bước 2: Địa chỉ giao hàng");
    $("#btnAddress").css("display", "none");
    $("#collapse-payment-address").css("display", "none");
}

function enableAddress() {
    // $("#txtMethod").text("");
    $("#btnAddress").removeAttr("style");
    $("#collapse-payment-address").removeAttr("style");
}

function disableMethod() {
    $("#txtMethod").text("Bước 3: Phương thức vận chuyển");
    $("#btnMethod").css("display", "none");
    $("#collapse-shipping-method").css("display", "none");
}

function enableMethod() {
    // $("#txtMethod").text("");
    $("#btnMethod").removeAttr("style");
    $("#collapse-shipping-method").removeAttr("style");
}

function disableConfirm() {
    $("#txtConfirm").text("Bước 4: Xác nhận đơn hàng");
    $("#btnConfirm").css("display", "none");
    $("#collapse-checkout-confirm").css("display", "none");
}

function enableConfirm() {
    // $("#txtMethod").text("");
    $("#btnConfirm").removeAttr("style");
    $("#collapse-checkout-confirm").removeAttr("style");
}

function useAddressExits() {
    $("#btnAddressExits").css("display", "block");
    $("#btnAddressAnother").css("display", "none");
}

function useAddressAnother() {
    $("#btnAddressExits").css("display", "none");
    $("#btnAddressAnother").css("display", "block");
}

function checkForm() {
    var check = 0;
    var submit = false;
    var fullName = $("#fullName").val();
    if (fullName == "") {
        $("#errorFullName").text("Họ và tên không được bỏ trống!");
        $("#fullName").css("border-color", "#dc3545");
        $("#lblFullName").css("color", "#dc3545");
        check = 0;
    }
    else {
        $("#errorFullName").text("");
        $("#fullName").removeAttr("style");
        $("#lblFullName").css("color", "black");
        check++;
    }

    var phone = $("#phone").val();
    if (phone == "") {
        $("#errorPhone").text("Số điện thoại không được bỏ trống!");
        $("#phone").css("border-color", "#dc3545");
        $("#lblPhone").css("color", "#dc3545");
        check = 0;
    }
    else {
        if (isVietnamesePhoneNumber(phone) == false) {
            $("#errorPhone").text("Số điện thoại không đúng định dạng!");
            $("#phone").css("border-color", "#dc3545");
            $("#lblPhone").css("color", "#dc3545");
            check = 0;
        }
        else {
            $("#errorPhone").text("");
            $("#phone").removeAttr("style");
            $("#lblPhone").css("color", "black");
            check++;
        }
    }

    var detail = $("#detail").val();
    if (detail == "") {
        $("#errorDetail").text("Địa chỉ nhận hàng không được bỏ trống!");
        $("#detail").css("border-color", "#dc3545");
        $("#lblDetail").css("color", "#dc3545");
        check = 0;
    }
    else {
        if (detail.length < 10 || detail.length > 200) {
            $("#errorDetail").text("Địa chỉ nhận hàng phải từ 10 đến 200 ký tự!");
            $("#detail").css("border-color", "#dc3545");
            $("#lblDetail").css("color", "#dc3545");
            check = 0;
        }
        else {
            $("#errorDetail").text("");
            $("#detail").removeAttr("style");
            $("#lblDetail").css("color", "black");
            check++;
        }
    }

    var province = $("#province").val();
    if (province == "") {
        $("#errorProvince").text("Vui lòng chọn tỉnh/thành!");
        $("#province").css("border-color", "#dc3545");
        $("#lblProvince").css("color", "#dc3545");
        check = 0;
    }
    else {
        $("#errorProvince").text("");
        $("#province").removeAttr("style");
        $("#lblProvince").css("color", "black");
        check++;
    }

    var district = $("#district").val();
    if (district == "") {
        $("#errorDistrict").text("Vui lòng chọn quận/huyện!");
        $("#district").css("border-color", "#dc3545");
        $("#lblDistrict").css("color", "#dc3545");
        check = 0;
    }
    else {
        $("#errorDistrict").text("");
        $("#district").removeAttr("style");
        $("#lblDistrict").css("color", "black");
        check++;
    }

    var ward = $("#ward").val();
    if (ward == "") {
        $("#errorWard").text("Vui lòng chọn phường/xã!");
        $("#ward").css("border-color", "#dc3545");
        $("#lblWard").css("color", "#dc3545");
        check = 0;
    }
    else {
        $("#errorWard").text("");
        $("#ward").removeAttr("style");
        $("#lblWard").css("color", "black");
        check++;
    }

    if (check == 6) {
        submit = true;
    }

    return submit;
}


function isVietnamesePhoneNumber(number) {
    return /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/.test(number);
}

var app = angular.module("checkout-app", []);

app.controller("checkout-ctrl", function ($scope, $http) {
    $scope.province = [];
    $scope.district = [];
    $scope.ward = [];
    $scope.form = {};
    $scope.initialize = function () {
        $http.get("/rest/province").then((resp) => {
            $scope.province = resp.data;
        });
    };

    $scope.initialize();

    $("#province").change(function () {
        var id = $("#province").val();
        $http.get("/rest/district/" + id).then((resp) => {
            $scope.district = resp.data;
        });
    });

    $("#district").change(function () {
        var idProvince = $("#province").val();
        var idDistrict = $("#district").val();
        $http.get("/rest/ward/" + idProvince + "/" + idDistrict).then((resp) => {
            $scope.ward = resp.data;
        });
    });

    $scope.addAddress = function () {
        if (checkForm()) {
            $scope.form.province = $("#province").val();
            $scope.form.district = $("#district").val();
            $scope.form.ward = $("#ward").val();
            var item = angular.copy($scope.form);
            $http.post(`/rest/address/form`, item).then((resp) => {
                location.reload();
            });
        }
    }
});

function transferAddress() {
    enableAddress();
    disableMethod();
    disableConfirm();
}

function transferMethod() {
    disableAddress();
    enableMethod();
    disableConfirm();
}

function transferConfirm() {
    disableAddress();
    disableMethod();
    enableConfirm();
}
