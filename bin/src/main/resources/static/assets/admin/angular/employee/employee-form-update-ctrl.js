var checkFullName = 0;
var checkDepartment = 0;
var checkPhone = 0;
var checkStartDate = 0;
var checkPosition = 0;
var checkSalary = 0;
var checkRole = 0;
var checkEmail = 0;

$(document).ready(function() {
	$.get("/rest/user", function(data, status) {
		var index = data.findIndex(p => p.email == $("#email").val());
		data.splice(index, 1);
		var temp = [];
		for (const property in data) {
			temp[temp.length] = `${data[property].email}`;
		}
		localStorage.setItem("user", temp.join(", "));
	});


	$("#fullName").keyup(function() {
		var fullName = this.value;
		if (fullName == "") {
			$("#fullName").addClass("is-invalid");
			$("#showErrorFullName").text("Vui lòng nhập họ và tên!");
			checkFullName = 10;
		}
		else {
			var length = fullName.length;
			var minLength = $("#fullName").attr("data-minlength")
			var maxLength = $("#fullName").attr("data-maxlength")

			if ((length < minLength) || (length > maxLength)) {
				$("#fullName").addClass("is-invalid");
				$("#showErrorFullName").text("Nhập giá trị từ 7 đến 30 ký tự!");
				checkFullName = 10;
			}

			else {
				$("#fullName").removeClass("is-invalid");
				$("#showErrorFullName").text("");
				checkFullName = 1;
			}

		}
		handlerButtonSave();
	});

	$("#department").keyup(function() {
		var fullName = this.value;
		if (fullName == "") {
			$("#department").addClass("is-invalid");
			$("#showErrorDepartment").text("Vui lòng nhập phòng ban!");
			checkDepartment = 10;
		}
		else {
			var length = fullName.length;
			var minLength = $("#department").attr("data-minlength")
			var maxLength = $("#department").attr("data-maxlength")

			if ((length < minLength) || (length > maxLength)) {
				$("#department").addClass("is-invalid");
				$("#showErrorDepartment").text("Nhập giá trị từ 7 đến 50 ký tự!");
				checkDepartment = 10;
			}

			else {
				$("#department").removeClass("is-invalid");
				$("#showErrorDepartment").text("");
				checkDepartment = 1;
			}

		}
		handlerButtonSave();
	});

	$("#phone").keyup(function() {
		var phone = this.value;
		if (phone == "") {
			$("#phone").addClass("is-invalid");
			$("#showErrorPhone").text("Vui lòng nhập số điện thoại!");
			checkPhone = 10;
		}
		else {
			if (isVietnamesePhoneNumber(phone) == false) {
				$("#phone").addClass("is-invalid");
				$("#showErrorPhone").text("Số điện thoại không đúng định dạng!");
				checkPhone = 10;
			}

			else {
				$("#phone").removeClass("is-invalid");
				$("#showErrorPhone").text("");
				checkPhone = 1;
			}

		}
		handlerButtonSave();
	});

	$("#startDate").change(function() {
		var startDate = this.value;
		if (startDate == "") {
			$("#startDate").addClass("is-invalid");
			$("#showErrorStartDate").text("Vui lòng chọn ngày bắt đầu làm việc!");
			checkStartDate = 10;
		}
		else {
			$("#startDate").removeClass("is-invalid");
			$("#showErrorStartDate").text("");
			checkStartDate = 1;
		}
		handlerButtonSave();
	});

	$("#position").keyup(function() {
		var position = this.value;
		if (position == "") {
			$("#position").addClass("is-invalid");
			$("#showErrorPosition").text("Vui lòng nhập chức vụ!");
			checkPosition = 10;
		}
		else {
			var length = position.length;
			var minLength = $("#position").attr("data-minlength")
			var maxLength = $("#position").attr("data-maxlength")

			if ((length < minLength) || (length > maxLength)) {
				$("#position").addClass("is-invalid");
				$("#showErrorPosition").text("Nhập giá trị từ 7 đến 30 ký tự!");
				checkPosition = 10;
			}

			else {
				$("#position").removeClass("is-invalid");
				$("#showErrorPosition").text("");
				checkPosition = 1;
			}

		}
		handlerButtonSave();
	});

	$("#salary").keyup(function() {
		var salary = this.value;
		if (salary == 0) {
			$("#salary").addClass("is-invalid");
			$("#showErrorSalary").text("Vui lòng nhập lương!");
			checkSalary = 10;
		}
		else {
			if (salary < 3000000) {
				$("#salary").addClass("is-invalid");
				$("#showErrorSalary").text("Lương phải lớn hơn 3 triệu!");
				checkSalary = 10;
			}

			else {
				$("#salary").removeClass("is-invalid");
				$("#showErrorSalary").text("");
				checkSalary = 1;
			}

		}
		handlerButtonSave();
	});

	$("#role").change(function() {
		var role = this.value;
		if (role == "") {
			$("#role").addClass("is-invalid");
			$("#showErrorRole").text("Vui lòng chọn chức vụ!");
			checkRole = 10;
		}
		else {
			$("#role").removeClass("is-invalid");
			$("#showErrorRole").text("");
			checkRole = 1;
		}
		handlerButtonSave();
	});

	$("#email").keyup(function() {
		var email = this.value;
		if (email == "") {
			$("#email").addClass("is-invalid");
			$("#showErrorEmail").text("Vui lòng nhập Email!");
			checkEmail = 10;
		}
		else {
			if (isValidEmail(email) == false) {
				$("#email").addClass("is-invalid");
				$("#showErrorEmail").text("Email không đúng định dạng!");
				checkEmail = 10;
			}
			else {
				var user = localStorage.getItem("user").split(", ");
				if (user.includes(email)) {
					$("#email").addClass("is-invalid");
					$("#showErrorEmail").text("Email đã tồn tại!");
					checkEmail = 10;

				}
				else {
					$("#email").removeClass("is-invalid");
					$("#showErrorEmail").text("");
					checkEmail = 1;
				}
			}
		}
		handlerButtonSave();
	});
});

function checkForm() {
	$("#fullName").keyup();
	$("#department").keyup();
	$("#phone").keyup();
	$("#startDate").change();
	$("#position").keyup();
	$("#salary").keyup();
	$("#role").change();
	$("#email").keyup();
	return handlerButtonSave();
}

function handlerButtonSave() {
	var check = false;
	if ((checkFullName !== 10)
		&& (checkDepartment !== 10)
		&& (checkPhone !== 10)
		&& (checkStartDate !== 10)
		&& (checkPosition !== 10)
		&& (checkSalary !== 10)
		&& (checkRole !== 10)
		&& (checkEmail !== 10)) {
		check = true;
		$("#btnUpdate").prop('disabled', false);
	}
	else {
		check = false;
		$("#btnUpdate").prop('disabled', true);
	}
	return check;
}


var app = angular.module("employee-form-app", []);

app.controller("employee-form-ctrl", function($scope, $http) {
	$scope.form = {};
	$scope.info = {};
	$scope.load = function() {
		$http.get("/rest/form/employee/"+$("#demo").val()).then(resp => {
			$scope.form = resp.data;
			$("#startDate").val($scope.form.startday);
			$scope.form.role = String($scope.form.role);
		}).catch(error => {
			console.log(error);
		});
	}
	$scope.load();

	$scope.update = function() {
		if (checkForm()) {		
			$scope.form.startday = String($("#startDate").val());
			$scope.form.id = $("#demo").val();
			var item = angular.copy($scope.form);
            $http.put("/rest/form/employee/"+$("#demo").val(), item).then(resp => {
				$scope.info.status = true;
				$scope.info.content = "Bạn đã cập nhật tài khoản thành công!";
				$("#modalSuccess").modal("show");
				var path = "/admin/employees/update/" + $scope.form.id;
				$("a").attr("href", path);
				console.log(resp);
            }).catch(error => {
                alert("Lỗi thêm sản phẩm")             
                console.log(error);
            });         
		}
	}
});

function isVietnamesePhoneNumber(number) {
	return /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/.test(number);
}

function isValidEmail(email) {
	return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email);
}

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