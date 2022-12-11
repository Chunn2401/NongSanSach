function checkForm(password){
    var check = 0;
    var submit = false;
    var oldPass = $("#oldPass").val();
    if(oldPass == ""){
        $("#errorOldPass").text("Mật khẩu cũ không được bỏ trống!");
        $("#oldPass").css("border-color", "#dc3545"); 
        $("#lblOldPass").css("color", "#dc3545");
        check = 0;
    }
    else{
        if(oldPass != password){
            $("#errorOldPass").text("Mật khẩu cũ không khớp!");
            $("#oldPass").css("border-color", "#dc3545"); 
            $("#lblOldPass").css("color", "#dc3545");
            check = 0;
        }
        else{
            $("#errorOldPass").text("");
            $("#oldPass").removeAttr("style");
            $("#lblOldPass").css("color", "black"); 
        }     
        check++;
    }

    var newPass = $("#newPass").val();
    if(newPass == ""){
        $("#errorNewPass").text("Mật khẩu mới không được bỏ trống!");
        $("#newPass").css("border-color", "#dc3545"); 
        $("#lblNewPass").css("color", "#dc3545");
        check = 0;
    }
    else{     
        if(newPass.length < 7){
            $("#errorNewPass").text("Mật khẩu ít nhất 7 ký tự!");
            $("#newPass").css("border-color", "#dc3545"); 
            $("#lblNewPass").css("color", "#dc3545");
            check = 0;
        }
        else{
            if(newPass == password){
                $("#errorNewPass").text("Mật khẩu mới không được giống với mật khẩu cũ!");
                $("#newPass").css("border-color", "#dc3545"); 
                $("#lblNewPass").css("color", "#dc3545");
                check = 0;
            }
            else{
                $("#errorNewPass").text("");
                $("#newPass").removeAttr("style");
                $("#lblNewPass").css("color", "black");         
                check++;
            }          
        }      
    }

    var confirmPass = $("#confirmPass").val();
    if(confirmPass == ""){
        $("#errorConfirmPass").text("Xác nhận mật khẩu không được bỏ trống!");
        $("#confirmPass").css("border-color", "#dc3545"); 
        $("#lblConfirmPass").css("color", "#dc3545");
        check = 0;
    }
    else{     
        if(confirmPass != newPass){
            $("#errorConfirmPass").text("Xác nhận mật khẩu không khớp!");
            $("#confirmPass").css("border-color", "#dc3545"); 
            $("#lblConfirmPass").css("color", "#dc3545");
            check = 0;
        }
        else{
            $("#errorConfirmPass").text("");
            $("#confirmPass").removeAttr("style");
            $("#lblConfirmPass").css("color", "black");         
            check++;
        }
        
    }

    if(check == 3){
        submit = true;
    }

    return submit;
}

var app = angular.module("change-pass-app", []);

app.controller("change-pass-ctrl", function ($scope, $http) {
	$scope.form = {};
    $scope.formPass = {};
	$scope.initialize = function () {
    $http.get("/rest/user/account").then((resp) => {
      $scope.form = resp.data;
      $scope.form.birthday = new Date($scope.form.birthday);
	  console.log(resp.data);
    });
  };

  $scope.initialize();

  $scope.update = function(){
	  if(checkForm($scope.form.password)){
        var item = angular.copy($scope.form);
        $http.put(`/rest/user/account/change-password`, item).then(resp => {
            location.reload();
        }).catch(error => {
            alert("Lỗi thêm sản phẩm")             
        }); 
	  }
  }
})