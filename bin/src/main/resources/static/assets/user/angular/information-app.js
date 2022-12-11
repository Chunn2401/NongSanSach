function checkForm() {
    var submit = false;
    var fullName = $("#fullName").val();
    if (fullName == "") {
        $("#errorFullName").text("Họ và tên không được bỏ trống!");
        $("#fullName").css("border-color", "#dc3545");
        $("#lblFullName").css("color", "#dc3545");
        submit = false;
    }
    else {
        $("#errorFullName").text("");
        $("#fullName").removeAttr("style");
        $("#lblFullName").css("color", "black");
        submit = true;
    }
    return submit;
}

var app = angular.module("information-app", []);

app.controller("information-ctrl", function ($scope, $http) {
    $scope.form = {};
    $scope.initialize = function () {
        $http.get("/rest/user/account").then((resp) => {
            $scope.form = resp.data;
            $scope.form.birthday = new Date($scope.form.birthday);
            console.log(resp.data);
        });
    };

    $scope.initialize();

    $scope.update = function () {
        if (checkForm()) {
            $scope.form.birthday = String($("#birthday").val());
            var item = angular.copy($scope.form);
            $http.put(`/rest/user/account/update`, item).then(resp => {
                location.reload();
            }).catch(error => {
                alert("Lỗi thêm sản phẩm")
            });
        }
    }
})