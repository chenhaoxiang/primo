$(document).ready(function () {

  "use strict";
  var options = {
    chart: {
      id: 'sparkline1',
      type: 'area',
      height: 80,
      sparkline: {
        enabled: true
      },
    },
    stroke: {
      curve: 'smooth'
    },
    fill: {
      opacity: 1,
    },
    series: [{
      name: 'Sales',
      data: [40, 15, 55, 32, 20, 50, 41]
    }],
    labels: [1, 2, 3, 4, 5, 6, 7],
    yaxis: {
      min: 0,
      max: 60
    },
    colors: ['#DCE6EC']
  }

  var chart = new ApexCharts(document.querySelector("#widget-stats-chart1"), options);
  chart.render();

  var options2 = {
    chart: {
      id: 'sparkline2',
      type: 'area',
      height: 80,
      sparkline: {
        enabled: true
      },
    },
    stroke: {
      curve: 'smooth'
    },
    fill: {
      opacity: 1,
    },
    series: [{
      name: 'Sales',
      data: [14, 40, 35, 20, 50, 25, 49]
    }],
    labels: [1, 2, 3, 4, 5, 6, 7],
    yaxis: {
      min: 0,
      max: 60
    },
    colors: ['#FFDDB8']
  }

  var chart2 = new ApexCharts(document.querySelector("#widget-stats-chart2"), options2);
  chart2.render();


  var options3 = {
    chart: {
      id: 'sparkline3',
      type: 'area',
      height: 80,
      sparkline: {
        enabled: true
      },
    },
    stroke: {
      curve: 'smooth'
    },
    fill: {
      opacity: 1,
    },
    series: [{
      name: 'Sales',
      data: [50, 20, 50, 40, 55, 15, 58]
    }],
    labels: [1, 2, 3, 4, 5, 6, 7],
    yaxis: {
      min: 0,
      max: 60
    },
    colors: ['#ffccce']
  }

  var chart3 = new ApexCharts(document.querySelector("#widget-stats-chart3"), options3);
  chart3.render();

  var options4 = {
    chart: {
        height: 350,
        type: 'bar',
        toolbar: {
          show: false
        }
    },
    plotOptions: {
        bar: {
            horizontal: false,
            columnWidth: '55%',
            endingShape: 'rounded',
            borderRadius: 10
        },
    },
    dataLabels: {
        enabled: false
    },
    stroke: {
        show: true,
        width: 2,
        colors: ['transparent']
    },
    series: [{
        name: 'Net Profit',
        data: [44, 55, 57, 56, 61, 58, 63, 60, 66]
    }, {
        name: 'Revenue',
        data: [76, 85, 101, 98, 87, 105, 91, 114, 94]
    }, {
        name: 'Free Cash Flow',
        data: [35, 41, 36, 26, 45, 48, 52, 53, 41]
    }],
    xaxis: {
        categories: ['Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct'],
        labels: {
            style: {
                colors: 'rgba(94, 96, 110, .5)'
            }
        }
    },
    yaxis: {
        title: {
            text: '$ (thousands)'
        }
    },
    fill: {
        opacity: 1

    },
    tooltip: {
        y: {
            formatter: function (val) {
                return "$ " + val + " thousands"
            }
        }
    },
    grid: {
        borderColor: '#e2e6e9',
        strokeDashArray: 4
    }
}

var chart4 = new ApexCharts(
    document.querySelector("#apex-earnings"),
    options4
);

chart4.render();
});