function like() {
  $("#favorite").removeClass("dislike");
  $("#favorite").prop("title", "Yêu thích");
}

function disLike() {
  $("#favorite").addClass("dislike");
  $("#favorite").prop("title", "Bỏ thích");
}

function addFavorite(productId) {
  angular.element($("#content")).scope().postFavorite(productId);
}

function addComment(productId) {
  angular.element($("#content")).scope().postComment(productId);
}

var app = angular.module("favorite-app", []);

app.controller("favorite-ctrl", function ($scope, $http) {
  // Xu ly favorite
  $scope.items = {};

  $scope.form = [];

  $scope.load = function () {
    $http
      .get("/rest/favorite/" + $("#productId").val())
      .then((resp) => {
        $scope.form = resp.data;
        if (typeof $scope.form.id === "undefined") {
          like();
        } else {
          disLike();
        }
      })
      .catch((error) => {});
  };
  $scope.load();

  $scope.postFavorite = function (productId) {
    $http.post(`/rest/favorite/add/` + productId).then((resp) => {
      $scope.items = resp.data;
      if (typeof $scope.items.id === "undefined") {
        location.href = "/login";
      } else {
        if ($scope.items.id != 0) {
          disLike();
        } else {
          like();
        }
      }
    });
  };
  // Xu ly favorite

  // Xu ly comment
  $scope.itemComments = {};
  $scope.listComment = [];
  $scope.loadComment = function () {
    $http
      .get("/rest/comment/form/product/" + $("#productId").val())
      .then((resp) => {
        $scope.listComment = resp.data;
        console.log($scope.listComment);
        if ($scope.listComment.length === 0) {
          $("#review").hide();
          $("#noComment").show();
          $("#pagination").hide();
        } else {
          $("#review").show();
          $("#noComment").hide();
          $("#pagination").show();
        }
      })
      .catch((error) => {});
  };
  $scope.loadComment();

  // hien thi sao mau vang
  $scope.starYellow = function (num) {
    var data = new Array(num);
    for (var i = 0; i < data.length; i++) {
      data[i] = i;
    }
    return data;
  };

  //hien thi sao mau xam
  $scope.greyYellow = function (num) {
    var data = new Array(5 - num);
    for (var i = 0; i < data.length; i++) {
      data[i] = i;
    }
    return data;
  };

  $scope.formComment = {};
  $scope.postComment = function (productId) {
    var content = $("#input-review").val();
    var rating = $('input[name="rating"]:checked').val();
    if (content == "") {
      $("#notification").removeClass("alert-success");
      $("#notification").addClass("alert-danger");
      $("#notification").html(
        "<i class='fa fa-info-circle'></i> Vui lòng nhập nội dung đánh giá!"
      );
      $("#notification").show();
    } else {
      if (typeof rating === "undefined") {
        $("#notification").removeClass("alert-success");
        $("#notification").addClass("alert-danger");
        $("#notification").html(
          "<i class='fa fa-info-circle'></i> Vui lòng chọn số sao đánh giá!"
        );
        $("#notification").show();
      }
      else{
        $("#notification").removeClass("alert-danger");
        $("#notification").addClass("alert-success");
        $("#notification").html(
          "<i class='fa fa-check-circle'></i> Cám ơn, vì phản hồi của bạn. Nó đã được gửi cho quản trị viên web để phê duyệt!"
        );
        $("#notification").show();
        
        $scope.formComment.productId = productId;
        $scope.formComment.content = content;
        $scope.formComment.star = rating;
        
        $http.post(`/rest/comment/form`, $scope.formComment).then((resp) => {
          console.log(resp.data);  
        });

        $("#input-review").val("");
        $('input[name="rating"]').removeAttr('checked');
      }
    }
  };

  $scope.pager = {
    page: 0,
    size: 5.0,
    get items(){
        var start = this.page * this.size;
        return $scope.listComment.slice(start, start + this.size);
        
    },
    get count() {
        return Math.ceil(1.0 * $scope.listComment.length / this.size);
    },
    first(){
      this.page = 0;
    },
    prev(){
      this.page--;
      if(this.page < 0){
        this.last();
      }
      
    },
    next(){
      this.page++;
      if(this.page >= this.count){
        this.first();
      }
    },
    last(){
      this.page = this.count -1;
    }
}

  // Xy ly comment
});
