var statusChart = false;
var chart = "pie";
var chartShowing = "";
var myChartDay;
var myChartMonth;
var myChartYear;

$("#btnFind").prop("disabled", true);

function showModal() {
  $("#optionModal").modal("show");
}

function hideModel() {
  $("#optionModal").modal("hide");
}

function changeChart() {
  statusChart = !statusChart;
  if (statusChart) {
    chart = "polarArea";
    $("#chartPie").html("BIỂU ĐỒ VÙNG CỰC");
    $("#chartPolar").html("BIỂU ĐỒ TRÒN");
  } else {
    chart = "pie";
    $("#chartPie").html("BIỂU ĐỒ TRÒN");
    $("#chartPolar").html("BIỂU ĐỒ VÙNG CỰC");
  }

  if (chartShowing == "day") {
    angular.element("#chartPie").scope().statisticalByDay();
  }

  if (chartShowing == "month") {
    angular.element("#chartPie").scope().statisticalByMonth();
  }

  if (chartShowing == "year") {
    angular.element("#chartPie").scope().statisticalByYear();
  }

  if (chartShowing == "option") {
    angular.element("#chartPie").scope().statisticalByOption();
  }
}

function loadChartDay() {
  chartShowing = "day";
  $("#pie-chart-grouped-day").show();
  $("#pie-chart-grouped-month").hide();
  $("#pie-chart-grouped-year").hide();
  $("#pie-chart-grouped-option").hide();
}

function loadChartMonth() {
  chartShowing = "month";
  $("#pie-chart-grouped-day").hide();
  $("#pie-chart-grouped-month").show();
  $("#pie-chart-grouped-year").hide();
  $("#pie-chart-grouped-option").hide();
}

function loadChartYear() {
  chartShowing = "year";
  $("#pie-chart-grouped-day").hide();
  $("#pie-chart-grouped-month").hide();
  $("#pie-chart-grouped-year").show();
  $("#pie-chart-grouped-option").hide();
}

function loadChartOption() {
  chartShowing = "option";
  $("#pie-chart-grouped-option").hide();
  $("#pie-chart-grouped-day").hide();
  $("#pie-chart-grouped-month").hide();
  $("#pie-chart-grouped-year").hide();
}

var app = angular.module("statistical-order-app", []);

app.controller("statistical-order-ctrl", function ($scope, $http) {
  $scope.items = [];
  $scope.statisticalByDay = function () {
    loadChartDay();
    var date = new Date();

    $http
      .get(
        "/rest/statistical/order/day/" +
          date.getDate() +
          "/" +
          (date.getMonth() + 1) +
          "/" +
          date.getFullYear()
      )
      .then((resp) => {
        $scope.items = resp.data;
        var arrDay = [];
        arrDay[0] = resp.data.orderSuccess;
        arrDay[1] = resp.data.orderTransport;
        arrDay[2] = resp.data.orderWait;
        arrDay[3] = resp.data.orderCancel;

        var dataMyPieDay = {
          type: "pie",
          data: {
            labels: ["Thành công", "Chờ vận chuyển", "Chờ duyệt", "Đã hủy"],
            datasets: [
              {
                data: arrDay,
                backgroundColor: [
                  Config.colors("green", 400),
                  Config.colors("blue", 400),
                  Config.colors("yellow", 400),
                  Config.colors("red", 400),
                ],
                hoverBackgroundColor: [
                  Config.colors("green", 600),
                  Config.colors("blue", 600),
                  Config.colors("yellow", 600),
                  Config.colors("red", 600),
                ],
              },
            ],
          },
          options: {
            title: {
              display: true,
              text: "Biểu đồ đơn hàng theo ngày",
            },
          },
        };

        var ctx = document
          .getElementById("pie-chart-grouped-day")
          .getContext("2d");
        if (myChartDay) {
          myChartDay.destroy();
        }

        dataMyPieDay.type = chart;
        myChartDay = new Chart(ctx, dataMyPieDay);
      });
  };

  $scope.statisticalByMonth = function () {
    loadChartMonth();
    var date = new Date();

    $http
      .get(
        "/rest/statistical/order/month/" +
          (date.getMonth() + 1) +
          "/" +
          date.getFullYear()
      )
      .then((resp) => {
        $scope.items = resp.data;
        var arrDay = [];
        arrDay[0] = resp.data.orderSuccess;
        arrDay[1] = resp.data.orderTransport;
        arrDay[2] = resp.data.orderWait;
        arrDay[3] = resp.data.orderCancel;

        var dataMyPieMonth = {
          type: "pie",
          data: {
            labels: ["Thành công", "Chờ vận chuyển", "Chờ duyệt", "Đã hủy"],
            datasets: [
              {
                data: arrDay,
                backgroundColor: [
                  Config.colors("green", 400),
                  Config.colors("blue", 400),
                  Config.colors("yellow", 400),
                  Config.colors("red", 400),
                ],
                hoverBackgroundColor: [
                  Config.colors("green", 600),
                  Config.colors("blue", 600),
                  Config.colors("yellow", 600),
                  Config.colors("red", 600),
                ],
              },
            ],
          },
          options: {
            title: {
              display: true,
              text: "Biểu đồ đơn hàng theo tháng",
            },
          },
        };

        var ctx = document
          .getElementById("pie-chart-grouped-month")
          .getContext("2d");
        if (myChartMonth) {
          myChartMonth.destroy();
        }

        dataMyPieMonth.type = chart;
        myChartMonth = new Chart(ctx, dataMyPieMonth);
      });
  };

  $scope.statisticalByYear = function () {
    loadChartYear();
    var date = new Date();

    $http
      .get("/rest/statistical/order/year/" + date.getFullYear())
      .then((resp) => {
        $scope.items = resp.data;
        var arrDay = [];
        arrDay[0] = resp.data.orderSuccess;
        arrDay[1] = resp.data.orderTransport;
        arrDay[2] = resp.data.orderWait;
        arrDay[3] = resp.data.orderCancel;

        var dataMyPieYear = {
          type: "pie",
          data: {
            labels: ["Thành công", "Chờ vận chuyển", "Chờ duyệt", "Đã hủy"],
            datasets: [
              {
                data: arrDay,
                backgroundColor: [
                  Config.colors("green", 400),
                  Config.colors("blue", 400),
                  Config.colors("yellow", 400),
                  Config.colors("red", 400),
                ],
                hoverBackgroundColor: [
                  Config.colors("green", 600),
                  Config.colors("blue", 600),
                  Config.colors("yellow", 600),
                  Config.colors("red", 600),
                ],
              },
            ],
          },
          options: {
            title: {
              display: true,
              text: "Biểu đồ đơn hàng theo tháng",
            },
          },
        };

        var ctx = document
          .getElementById("pie-chart-grouped-year")
          .getContext("2d");
        if (myChartYear) {
          myChartYear.destroy();
        }

        dataMyPieYear.type = chart;
        myChartYear = new Chart(ctx, dataMyPieYear);
      });
  };

  $scope.itemYear = [];
  $scope.loadYear = function () {
    $http.get("/rest/statisticalOrder/year").then((resp) => {
      $scope.itemYear = resp.data;
      console.log($scope.itemYear);
    });
  };

  $scope.statisticalByOption = function () {
    var year = $("#year").val();
    var month = $("#month").val();
    var day = $("#day").val();
	hideModel();
	loadChartOption();
    $http.get("/rest/statistical/order/option/" + day + "/" + month + "/" + year).then((resp) => {
        $scope.items = resp.data;
        var arrDay = [];
        arrDay[0] = resp.data.orderSuccess;
        arrDay[1] = resp.data.orderTransport;
        arrDay[2] = resp.data.orderWait;
        arrDay[3] = resp.data.orderCancel;

        var dataMyPieDay = {
          type: "pie",
          data: {
            labels: ["Thành công", "Chờ vận chuyển", "Chờ duyệt", "Đã hủy"],
            datasets: [
              {
                data: arrDay,
                backgroundColor: [
                  Config.colors("green", 400),
                  Config.colors("blue", 400),
                  Config.colors("yellow", 400),
                  Config.colors("red", 400),
                ],
                hoverBackgroundColor: [
                  Config.colors("green", 600),
                  Config.colors("blue", 600),
                  Config.colors("yellow", 600),
                  Config.colors("red", 600),
                ],
              },
            ],
          },
          options: {
            title: {
              display: true,
              text: "Biểu đồ đơn hàng theo ngày",
            },
          },
        };

        var ctx = document
          .getElementById("pie-chart-grouped-day")
          .getContext("2d");
        if (myChartDay) {
          myChartDay.destroy();
        }

        dataMyPieDay.type = chart;
        myChartDay = new Chart(ctx, dataMyPieDay);
      });
  };

  $scope.statisticalByDay();
  $scope.loadYear();
});

$("#year").change(function () {
  var year = this.value;
  if (year != 0) {
    destroyMonth();
    loadMonth();
    $("#btnFind").prop("disabled", false);
  } else {
    destroyMonth();
    destroyDay();
    $("#btnFind").prop("disabled", true);
  }
});

$("#month").change(function () {
  var month = this.value;
  if (month != 0) {
    destroyDay();
    loadDay();
  } else {
    destroyDay();
  }
});

function loadMonth() {
  for (var i = 1; i <= 12; i++) {
    $("#month").append("<option value=" + i + ">" + i + "</option>");
  }
}

function destroyMonth() {
  for (var i = 1; i <= 12; i++) {
    $("#month option[value='" + i + "']").remove();
  }
}

function loadDay() {
  var year = $("#year").val();
  var month = $("#month").val();
  var maxDay = new Date(year, month, 0).getDate();
  for (var i = 1; i <= maxDay; i++) {
    $("#day").append("<option value=" + i + ">" + i + "</option>");
  }
}

function destroyDay() {
  for (var i = 1; i <= 31; i++) {
    $("#day option[value='" + i + "']").remove();
  }
}
