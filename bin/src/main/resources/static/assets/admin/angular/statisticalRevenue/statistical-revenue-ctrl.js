var statusChart = false;
var chart = "bar";
var chartShowing = "";

function changeChart() {
  statusChart = !statusChart;
  if (statusChart) {
    chart = "line";
    $("#chartBar").html("BIỂU ĐỒ ĐƯỜNG");
    $("#chartLine").html("BIỂU ĐỒ CỘT");
  } else {
    chart = "bar";
    $("#chartBar").html("BIỂU ĐỒ CỘT");
    $("#chartLine").html("BIỂU ĐỒ ĐƯỜNG");
  }

  if (chartShowing == "day") {
	angular.element("#chartLine").scope().statisticalByDay();
  }

  if (chartShowing == "month") {
	angular.element("#chartLine").scope().statisticalByMonth();
  }

  if (chartShowing == "year") {
	angular.element("#chartLine").scope().statisticalByYear();
  }
  
}

function loadChartDay() {
  chartShowing = "day";
  $("#bar-chart-grouped-day").show();
  $("#bar-chart-grouped-month").hide();
  $("#bar-chart-grouped-year").hide();
}

function loadChartMonth() {
  chartShowing = "month";
  $("#bar-chart-grouped-day").hide();
  $("#bar-chart-grouped-month").show();
  $("#bar-chart-grouped-year").hide();
}

function loadChartYear() {
  chartShowing = "year";
  $("#bar-chart-grouped-day").hide();
  $("#bar-chart-grouped-month").hide();
  $("#bar-chart-grouped-year").show();
}

var app = angular.module("statistical-revenue-app", []);

app.controller("statistical-revenue-ctrl", function ($scope, $http) {
  $scope.items = [];
  $scope.statisticalByDay = function () {
    loadChartDay();
    var date = new Date();
    $http
      .get(
        "/rest/statistical/revenue/day/" +
          (date.getMonth() + 1) +
          "/" +
          date.getFullYear()
      )
      .then((resp) => {
        $scope.items = resp.data;
        var arrDay = [];
        var arrPrice = [];
        var length = resp.data.length;
        for (var i = 0; i < length; i++) {
          arrDay[i] = i + 1;
          arrPrice[i] = resp.data[i].price;
        }
        new Chart(document.getElementById("bar-chart-grouped-day"), {
          type: chart,
          data: {
            labels: arrDay,
            datasets: [
              {
                label: "Triệu đồng",
                backgroundColor: "#3e95cd",
                data: arrPrice,
              },
            ],
          },
          options: {
            title: {
              display: true,
              text:
                "Biểu đồ doanh thu " +
                length +
                " ngày của tháng " +
                (date.getMonth() + 1) +
                " năm " +
                date.getFullYear(),
            },
          },
        });
      });
  };

  $scope.statisticalByMonth = function () {
    loadChartMonth();
    var date = new Date();
    $http
      .get("/rest/statistical/revenue/month/" + date.getFullYear())
      .then((resp) => {
        $scope.items = resp.data;
        var arrYear = [];
        var arrPrice = [];
        for (var i = 0; i < resp.data.length; i++) {
          arrYear[i] = i + 1;
          arrPrice[i] = resp.data[i].price;
        }
        new Chart(document.getElementById("bar-chart-grouped-month"), {
          type: chart,
          data: {
            labels: arrYear,
            datasets: [
              {
                label: "Triệu đồng",
                backgroundColor: "#3e95cd",
                data: arrPrice,
              },
            ],
          },
          options: {
            title: {
              display: true,
              text: "Biểu đồ doanh thu 12 tháng của năm " + date.getFullYear(),
            },
          },
        });
      });
  };

  $scope.statisticalByYear = function () {
    loadChartYear();
    var date = new Date();
    $http
      .get("/rest/statistical/revenue/year/" + date.getFullYear())
      .then((resp) => {
        $scope.items = resp.data;
        var arrYear = [];
        var arrPrice = [];
        for (var i = 0; i < resp.data.length; i++) {
          arrYear[i] = resp.data[i].date;
          arrPrice[i] = resp.data[i].price;
        }
        new Chart(document.getElementById("bar-chart-grouped-year"), {
          type: chart,
          data: {
            labels: arrYear,
            datasets: [
              {
                label: "Triệu đồng",
                backgroundColor: "#3e95cd",
                data: arrPrice,
              },
            ],
          },
          options: {
            title: {
              display: true,
              text:
                "Biểu đồ doanh thu 10 năm từ năm " +
                arrYear[0] +
                " đến năm " +
                date.getFullYear(),
            },
          },
        });
      });
  };

  $scope.statisticalByDay();
});
