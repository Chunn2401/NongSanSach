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

    // // upload image 1
    // $scope.imageImageChanged = function (files) {
    //     var data = new FormData();
    //     data.append("file", files[0]).name;
   
    //     $http
    //         .post("/rest/upload/user", data, {
    //             transformRequest: angular.identity,
    //             headers: { "Content-Type": undefined },
    //         })
    //         .then((resp) => {
    //             $scope.form.img = resp.data.name;
    //         })
    //         .catch((error) => {
    //             alert("loi load hinh");
    //             console.log(error);
    //         });
    // };

    $scope.update = function () {
        if (checkForm()) {
            // $scope.form.birthday = String($("#birthday").val());
            // $scope.form.img = String($("#img").val().split('C:\\fakepath\\'));
            var item = angular.copy($scope.form);
            $http.put(`/rest/user/account/update`, item).then(resp => {

                alert("Cập nhật thông tin thành công")
                location.reload();
            }).catch(error => {
                alert("Lỗi cập nhật thông tin")
            });
        }
    }
})