/*var rawData1 = [
{name:'上海',value:[268,1,448,713]},
{name:'云南',value:[645,283,617,1896]},
{name:'内蒙古',value:[3244,1115,72,958]},
{name:'北京',value:[842,29,59,1785]},
{name:'吉林',value:[1064,450,593,480]},
{name:'四川',value:[4763,1411,130,1670]},
{name:'天津',value:[639,18,0,876]},
{name:'宁夏',value:[169,51,32,131]},
{name:'安徽',value:[2395,780,196,877]},
{name:'山东',value:[5061,687,609,3111]},
{name:'山西',value:[1700,300,255,1605]},
{name:'广东',value:[5389,416,148,2802]},
{name:'广西',value:[974,199,252,621]},
{name:'新疆',value:[1291,660,284,1014]},
{name:'江苏',value:[3806,840,427,2749]},
{name:'江西',value:[845,352,681,488]},
{name:'河北',value:[4630,1738,560,1541]},
{name:'河南',value:[2515,1835,838,1791]},
{name:'浙江',value:[3229,1110,864,3032]},
{name:'海南',value:[319,30,29,10]},
{name:'湖北',value:[1693,792,238,1087]},
{name:'湖南',value:[1455,476,637,965]},
{name:'甘肃',value:[724,736,35,291]},
{name:'福建',value:[1375,362,212,997]},
{name:'西藏',value:[18,7,0,99]},
{name:'贵州',value:[788,341,53,312]},
{name:'辽宁',value:[2439,512,136,2669]},
{name:'重庆',value:[680,687,31,384]},
{name:'陕西',value:[1679,372,625,3269]},
{name:'青海',value:[184,262,140,446]},
{name:'黑龙江',value:[3429,622,320,1157]}
];*/

var timeLineData = jsonData.timeLineData;
var geoCoordMap = {
    '安徽省': [117.17, 31.52],
    '北京市': [116.24, 39.55],
    '重庆市': [106.54, 29.59],
    '福建省': [119.18, 26.05],
    '甘肃省': [103.51, 36.04],
    '广东省': [113.14, 23.08],
    '广西壮族自治区': [108.19, 22.48],
    '贵州省': [106.42, 26.35],
    '河北省': [114.30, 38.02],
    '河南省': [113.40, 34.46],
    '黑龙江省': [128.36, 45.44],
    '湖北省': [112.27, 30.15],
    '湖南省': [112.59, 28.12],
    '吉林省': [125.19, 43.54],
    '江苏省': [118.46, 32.03],
    '江西省': [115.55, 28.40],
    '辽宁省': [123.25, 41.48],
    '内蒙古自治区': [108.41, 40.48],
    '宁夏回族自治区': [106.16, 38.27],
    '青海省': [101.48, 36.38],
    '山东省': [118.00, 36.40],
    '山西省': [112.33, 37.54],
    '陕西省': [108.57, 34.17],
    '上海市': [121.29, 31.14],
    '海南省': [108.77, 19.10],
    '四川省': [104.04, 30.40],
    '天津市': [117.12, 39.02],
    '西藏自治区': [91.08, 29.39],
    '新疆维吾尔自治区': [87.36, 43.45],
    '云南省': [102.42, 25.04],
    '浙江省': [120.10, 30.16],
    '澳门': [115.07, 21.33],
    '台湾': [121.21, 23.53]
};
var legendArray = [[ '建立在依法设置计量检定机\r\n构的社会公用计量标准', '依法授权的社\r\n会公用计量标准', '依法授权其它单位开\r\n展专项检定工作计量标准', '建立在部门、企事业单位的\r\n最高计量标准' ],
                ['计量授权-依法设置的计量检定技术机构','依法授权建立的计量检定机构','其它承担专项授权检定任务的机构','其它承担专项授权检定任务的项目','授权承担计量器具型式评价的机构','授权承担计量器具型式评价的项目'],
                ['型式批准证书-本年','型式批准证书-累证'],
                ['制造、修理计量器具-取得制造计量器具许可证的单位个体工商户-本年新增','制造、修理计量器具-取得制造计量器具许可证的单位个体工商户-本年减少','制造、修理计量器具-取得制造计量器具许可证的单位个体工商户累计','制造、修理计量器具-取得修理计量器具许可证的单位个体工商户-本年新增','制造、修理计量器具-取得修理计量器具许可证的单位个体工商户-本年减少','制造、修理计量器具-取得修理计量器具许可证的单位个体工商户']];

var colorarr = [ '#86b8e9', '#8b5cf8', '#67c730', '#f64681'];


measure_std_option = {
    baseOption:{
        timeline: {
            show : true,
            // y: 0,
            axisType: 'category',
            // realtime: false,
            // loop: false,
            autoPlay: true,
            // currentIndex: 2,
            playInterval: 1000,
            controlStyle: {
                position: 'left'
            },
            data: timeLineData,
            top:'92%',
            label: {
                formatter : function(s) {
                    return (new Date(s)).getFullYear();
                }
            }
        },
        top : '-40%',
        bottom : '-65%',
        tooltip: {
            trigger: 'axis'
        },
        geo : {
            type : 'map',
            map : 'china',
            left:'7%',

            zoom:0.7,
            label : {
                normal : {
                    textStyle : {
                        color : '#0078ff'
                    },
                    show : false
                },
                emphasis : {
                    textStyle : {
                        color : '#0078ff'
                    },
                    show : false
                }
            },
            itemStyle : {
                normal : {
                    areaColor : "rgba(0,0,0,0)",
                    borderColor : "#383c41",
                    borderWidth : 1
                },
                emphasis : {
                    areaColor : "rgba(233,0,200,0.3)"
                }
            }
        },
    },
    options: [
    ]
}

function renderEachCity(myChart,data,legendEle) {
    measure_std_option.options=[];
    for(var i=0;i<timeLineData.length;i++){
        var option = {
            legend : [],
            title:{text:timeLineData[i]},
            xAxis : [],
            yAxis : [],
            grid : [],
            series:[]
        };
        option.legend.push({
            itemWidth:5,
            itemHeight:5,
            textStyle:{
                color:'#0078ff',
                fontSize:12
            },
            orient:'vertical',
            top:'75%',
            left:'2%',
            //bottom:'5%'
            data : legendEle
        })
        echarts.util.each(data[i], function(dataItem, idx) {
            var geoCoord = geoCoordMap[dataItem.name];
            var coord = myChart.convertToPixel('geo', geoCoord);
            idx += '';

            option.xAxis.push({
                id : idx,
                gridId : idx,
                type : 'category',
                name : dataItem.name,
                nameTextStyle : {
                    color : '#0078ff',
                    fontSize : 12
                },
                nameLocation : 'middle',
                nameGap : 3,
                splitLine : {
                    show : false
                },
                axisTick : {
                    show : false
                },
                axisLabel : {
                    show : false
                },
                axisLine : {
                    show : false,
                    lineStyle : {
                        color : '#bbb'
                    }
                },
                data : [ dataItem.name ],
            });
            option.yAxis.push({
                id : idx,
                gridId : idx,
                show : false
            });
            option.grid.push({
                id : idx,
                width : 30,
                height : 50,
                left : coord[0] - 15,
                top : coord[1] - 35,
            });
            for (var i = 0; i < legendEle.length; i++) {
                option.series.push({
                    name : legendEle[i],
                    type : 'bar',
                    stack : 'bar' + idx,
                    xAxisId : idx,
                    yAxisId : idx,
                    barWidth: 12,
                    itemStyle : {
                        normal : {
                            color : colorarr[i]
                        }
                    },
                    label:{
                        normal:{color:'auto'}
                    },
                    data : [ dataItem.value[i] ]
                });
            }

        });
        measure_std_option.options.push(option);
    }
    console.log(measure_std_option)

    myChart.setOption(measure_std_option,true);
}