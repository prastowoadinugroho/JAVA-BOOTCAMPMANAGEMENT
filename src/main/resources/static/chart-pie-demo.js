Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

let url = "http://localhost:8080/bootcamp-management/rm";

function getEmployeeAvail() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/employee-avail',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

function getEmployeeNotAvail() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/employee-notavail',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

function getEmployeeOnSite() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/employee-onsite',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

function getAllRequest() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/request-all',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

function getRequestNeedAction() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/request-null',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

function getRequestUndone() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/request-undone',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

function getRequestDone() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/request-done',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

function getRequestRejected() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/request-rejected',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

var employeeAvail = getEmployeeAvail().data.data;
$('#available').text(employeeAvail);

var employeeNotAvail = getEmployeeNotAvail().data.data;
$('#unavailable').text(employeeNotAvail);

var employeeOnSite = getEmployeeOnSite().data.data;
$('#onsite').text(employeeOnSite);

var requestAll = getAllRequest().data.data;
$('#request').text(requestAll);

var requestNeed = getRequestNeedAction().data.data;

var requestUndone = getRequestUndone().data.data;

var requestDone = getRequestDone().data.data;

var requestRejected = getRequestRejected().data.data;

var ctx = document.getElementById("myPieRequest");
var myPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: ["Need Action", "Undone", "Done", "Rejected"],
    datasets: [{
      data: [requestNeed, requestUndone, requestDone, requestRejected],
      backgroundColor: ['#37B9CC', '#F6C23E', '#1CC88A', '#E74B3B'],
      hoverBackgroundColor: ['#68B9CC', '#FFC23E', '#44C88A','#FF4B3B'],
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

function getEmployeeJava() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/employee-java',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}
function getEmployeePython() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/employee-python',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}
function getEmployeePhp() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/employee-php',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}
function getEmployeeReact() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/employee-react',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}
function getEmployeeNet() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/employee-net',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}

var java = getEmployeeJava().data.data;

var python = getEmployeePython().data.data;

var php = getEmployeePhp().data.data;

var react = getEmployeeReact().data.data;

var net = getEmployeeNet().data.data;

// Pie Chart Example
var ctx = document.getElementById("myPieSkill");
var myPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: ["Java", "Pyhton", "PHP","React",".Net"],
    datasets: [{
      data: [java, python, php, react, net],
      backgroundColor: ['#36A5FF', '#1CC88A', '#36b9cc', '#E74B3B','#858797'],
      hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
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

function getRejectJava() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/reject-java',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}
var rejectJava = getRejectJava().data.data;

function getRejectPython() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/reject-python',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}
var rejectPython = getRejectPython().data.data;

function getRejectPhp() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/reject-php',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}
var rejectPhp = getRejectPhp().data.data;

function getRejectReact() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/reject-react',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}
var rejectReact = getRejectReact().data.data;

function getRejectNet() {
    return JSON.parse($.ajax({
        type: 'GET',
        url: url + '/reject-react',
        dataType: 'json',
        global: false,
        async: false,
        success: function(response) {
            return response;
        }
    }).responseText);
}
var rejectNet = getRejectNet().data.data;

// Pie Chart Example
var ctx = document.getElementById("myRejectSkill");
var myPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: ["Java", "Pyhton", "PHP","React",".Net"],
    datasets: [{
      data: [rejectJava, rejectPython, rejectPhp, rejectReact, rejectNet],
      backgroundColor: ['#36A5FF', '#1CC88A', '#36b9cc', '#E74B3B','#858797'],
      hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
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

setAngka = (angka) => {
    $('#angka').val(angka);
}