Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

let url = "http://localhost:8080/bootcamp-management/admin";

function getAllEmployee() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/employee-all',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

function getAllUser() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/user-all',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

function getAllSite() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/site-all',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

function getAllClass() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/class-all',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

function getEmployeeDev() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/employee-dev',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

function getEmployeeTrainer() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/employee-trainer',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

var empAll = getAllEmployee().data.data;
$('#employee').text(empAll);

var siteAll = getAllSite().data.data;
$('#site').text(siteAll);

var userAll = getAllUser().data.data;
$('#user').text(userAll);

var classAll = getAllClass().data.data;
$('#class').text(classAll);

var empDev = getEmployeeDev().data.data;

var empTrainer = getEmployeeTrainer().data.data;

var ctx = document.getElementById("myPieJob");
var myPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: ["Trainer", "Developer"],
    datasets: [{
      data: [empTrainer, empDev],
      backgroundColor: ['#37B9CC', '#F6C23E'],
      hoverBackgroundColor: ['#68B9CC', '#FFC23E'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 80,
  },
});