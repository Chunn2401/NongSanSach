app.controller("blog-ctrl", function ($scope, $http) {
	// table
	$scope.items = [];
	//form
	$scope.form = {};


	$scope.initialize = function () {
		// load product
		$http.get("/home").then(resp => {
			// lấy sp về bỏ vào items
			$scope.items = resp.data;
			/*$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate)
			})*/
		})
	}
	// khởi đầu
	$scope.initialize();


	// xóa form
	$scope.reset = function () {
		$scope.form = {
			createDate: new Date(),
			image: 'cloud-upload.jpg',
			available: true,
		};
	}

	// hiển thị lên form
	$scope.edit = function (item) {
		// copy nó ra r buộc lên form
		$scope.form = angular.copy(item);
		// chuyển nó qa tabs đầu tiên
		$(".nav-tabs a:eq(0)").tab('show');
	}

	// thêm sp
	$scope.create = function () {
		// copy nó ra cái item
		var item = angular.copy($scope.form);
		$http.post(`/rest/products`, item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Tạo mới sản phẩm thành công !");
		}).catch(error => {
			alert("Lỗi Tạo mới sản phẩm !");
			console.log(error);
		})

	}

	// cập nhật sp
	$scope.update = function () {
		var item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			$scope.reset();
			alert("Cập nhật sản phẩm thành công !");
		}).catch(error => {
			alert("Lỗi Cập nhật sản phẩm !");
			console.log(error);
		})
	}

	// xóa sp
	$scope.delete = function (item) {
		var item = angular.copy($scope.form);
		$http.delete(`/rest/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			// splice tại vị trí index cắt 1 ký tự
			$scope.items.splice(index, 1);
			$scope.reset();
			alert("Xóa sản phẩm thành công !");
		}).catch(error => {
			alert("Xóa sản phẩm thất bại !");
			console.log(error);
		})
	}

	// upload sp
	$scope.imageChanged = function (files) {
		// tạo formdata
		var data = new FormData();
		// lấy file ngừ ta chọn bỏ vô form data
		data.append('file', files[0]);
		// post lên sv
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			// lấy name của data bỏ vô cái form
			$scope.form.image = resp.data.name;
			alert("Upload hình ảnh thành công !");
		}).catch(error => {
			alert("Lỗi upload hình ảnh !");
			console.log("Error", error)
		})
	}

	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			// slice: dùng để cắt
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		},
	}
});
