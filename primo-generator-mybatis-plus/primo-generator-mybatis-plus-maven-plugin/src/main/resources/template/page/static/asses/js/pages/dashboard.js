$(document).ready(function () {

  "use strict";
  var options1 = {
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
  var chart1 = new ApexCharts(
    document.querySelector("#apex-earnings"),
    options1
  );

  chart1.render();


  var options2 = {
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
      data: [14, 40, 35, 20, 50, 25, 49]
    }],
    labels: [1, 2, 3, 4, 5, 6, 7],
    yaxis: {
      min: 0,
      max: 60
    },
    colors: ['#FFDDB8']
  }

  var chart2 = new ApexCharts(document.querySelector("#widget-stats-chart1"), options2);
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

  var chart3 = new ApexCharts(document.querySelector("#widget-stats-chart2"), options3);
  chart3.render();

  var options4 = {
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

  var chart4 = new ApexCharts(document.querySelector("#widget-stats-chart3"), options4);
  chart4.render();


    // chart 2
    var optionsLine = {
        chart: {
            foreColor: '#9ba7b2',
            //绘图的高
            height: 360,
            type: 'line',
            zoom: {
                //右上角的放大缩小工具
                enabled: true
            },
            // 阴影
            dropShadow: {
                enabled: false,
                top: 3,
                left: 2,
                blur: 4,
                //不透明度
                opacity: 0.1,
            }
        },
        //线
        stroke: {
            curve: 'smooth',
            //线宽度
            width: 3
        },
        colors: ["#e72e2e", '#0c971a', '#0c471a'],
        // 统计数据 ，Y坐标
        series: [{
            name: "Music",
            data: [1, 15, 56, 20, 33, 27, 15, 56, 20, 56,1, 15, 56, 20, 33, 27, 15, 56, 20, 560]
        }, {
            name: "Photos",
            data: [30, 33, 21, 42, 30, 33, 21, 42, 19, 32,30, 33, 21, 42, 30, 33, 21, 42, 19, 32]
        }, {
            name: "Photos2",
            data: [30, 34, 46, 67, 89, 12, 31, 42, 139, 312,30, 34, 46, 67, 89, 12, 31, 42, 139, 312]
        }],
        title: {
            text: '标题',
            align: 'left',
            offsetY: 25,
            offsetX: 20
        },
        subtitle: {
            text: '副标题',
            offsetY: 55,
            offsetX: 20
        },
        // 图标点
        markers: {
            // 大小
            size: 2,
            // 空白宽度
            strokeWidth: 0,
            hover: {
                // 鼠标移动时触发的大小
                size: 4
            }
        },
        // 网格
        grid: {
            show: true,
            padding: {
                bottom: 0
            }
        },
        // labels: ['01/15/2002', '01/16/2002', '01/17/2002', '01/18/2002', '01/19/2002', '01/20/2002'],
        xaxis: {
            // type: 'datetime',
            //X坐标的值
            categories: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10','11','12','13','14','15','16','17','18','19','20'],
        },
        //series name的展示位置
        legend: {
            position: 'top',
            horizontalAlign: 'right',
            offsetY: -20
        }
    }
    var chartLine = new ApexCharts(document.querySelector('#mychart1'), optionsLine);
    chartLine.render();

    // 两根折线图对比的图表
    // // chart 2
    // var optionsLine = {
    //     chart: {
    //         foreColor: '#b5bbc2',
    //         //绘图的高
    //         height: 360,
    //         type: 'line',
    //         zoom: {
    //             //右上角的放大缩小工具
    //             enabled: false
    //         },
    //         // 阴影
    //         dropShadow: {
    //             enabled: false,
    //             top: 3,
    //             left: 2,
    //             blur: 4,
    //             //不透明度
    //             opacity: 0.1,
    //         }
    //     },
    //     //线
    //     stroke: {
    //         curve: 'smooth',
    //         //线宽度
    //         width: 3
    //     },
    //     colors: ["#0066ea", 'rgba(0,102,234,0.23)'],
    //     // 统计数据 ，Y坐标
    //     series: [{
    //         name: "今天",
    //         data: [1, 15, 56, 20, 33, 27, 15,0,0,0,0,0]
    //     }, {
    //         name: "昨天",
    //         data: [30, 33, 21, 42, 30, 33, 21,10,10,10,10,10]
    //     } ],
    //     title: {
    //         text: '文章数统计',
    //         align: 'left',
    //         offsetY: 25,
    //         offsetX: 20
    //     },
    //     // 副标题
    //     subtitle: {
    //         text: '',
    //         offsetY: 55,
    //         offsetX: 20
    //     },
    //     // 图标点
    //     markers: {
    //         // 大小
    //         size: 2,
    //         // 空白宽度
    //         strokeWidth: 0,
    //         hover: {
    //             // 鼠标移动时触发的大小
    //             size: 4
    //         }
    //     },
    //     // 网格
    //     grid: {
    //         show: true,
    //         padding: {
    //             bottom: 0
    //         }
    //     },
    //     // labels: ['01/15/2002', '01/16/2002', '01/17/2002', '01/18/2002', '01/19/2002', '01/20/2002'],
    //     xaxis: {
    //         // type: 'datetime',
    //         //X坐标的值
    //         categories: ['0-1', '1-2', '2-3', '3-4', '4-5', '5-6', '6-7', '7-8'],
    //     },
    //     //series name的展示位置
    //     legend: {
    //         position: 'top',
    //         horizontalAlign: 'right',
    //         offsetY: -20
    //     }
    // }
    // var chartLine = new ApexCharts(document.querySelector('#blog-chart2'), optionsLine);
    // chartLine.render();

});
