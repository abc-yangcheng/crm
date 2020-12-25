<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ECharts</title>
    <script src="${ctx}/echarts/echarts.js"></script>
    <script src="${ctx}/echarts/china.js"></script>
    <script src="${ctx}/echarts/jquery-3.4.1.js"></script>
    <#include "../common.ftl">
    <style>
        *{margin:0;padding:0}
        html,body{
            width:100%;
            height:100%;
        }
        /*#main{*/
        /*    width:600px;*/
        /*    height:450px;*/
        /*    margin: 150px auto;*/
        /*    border:1px solid #ddd;*/
        /*}*/
        /*默认长宽比0.75*/
        .aa{
            /*width: 200px;*/
            /*height: 200px;*/
            text-align: center;
            background: #000;
            color: #fff;
            font-size: 28px;
        }
    </style>
</head>
<body class="childrenBody">
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<h1 class="aa">全国客户分布可视化系统</h1>
<div id="main" style="width: 500px;height:400px;position:absolute;top:100px"></div>
<div id="main2" style="width: 500px;height:400px;position:absolute;top:100px;left:550px"></div>
<div id="main3" style="width: 500px;height:400px;position:absolute;top:100px;left:1050px"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 新建productName与nums数组来接受数据，因为我们
    var Name = [];
    var nums = [];
    var datatemp = [];
    //AJAX接收数据主体
    $.ajax({
        type:"GET",
        url:"/getdata",
        dataType:"json",
        async:false,
        success:function (result) {
            for (var i = 0; i < result.length; i++){
                Name.push(result[i].name);
                nums.push(result[i].nums);
                var ob = {name:"",value:""};
                ob.name = result[i].name;
                ob.value = result[i].nums;
                datatemp.push(ob);
            }

        },
        error :function(errorMsg) {
            alert("获取后台数据失败！");
        }
    });
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '全国客户分布'
        },
        tooltip: {},
        legend: {
            data:['客户数量']
        },
        xAxis: {
            //结合
            data: Name
        },

        yAxis: {},
        series: [{
            name: '客户数量',
            type: 'bar',
            //结合
            data: nums
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    //饼图
    var myChart2 = echarts.init(document.getElementById('main3'));
    var option2 = {
        title : {
            text: '全国客户分布情况',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:(function () {

                    var datas = [];
                    $.ajax({
                        type:"POST",
                        url:"/getdata",
                        dataType:"json",
                        async:false,
                        success:function (result) {

                            for (var i = 0; i < result.length; i++){
                                datas.push({
                                    "value":result[i].nums, "name":result[i].name
                                })
                            }

                        }
                    })
                    return datas;

                })(),
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    myChart2.setOption(option2);


    //中国地图
    var myChart3 = echarts.init(document.getElementById('main2'));
    option = {
        tooltip: {
            formatter:function(params,ticket, callback){
                return params.seriesName+'<br />'+params.name+'：'+params.value
            }
        },

        visualMap: {
            min: 0,
            max: 1500,
            left: 'left',
            top: 'bottom',
            text: ['高','低'],
            inRange: {
                color: ['#e5e0e0', '#490104']
            },
            show:true
        },
        geo: {
            map: 'china',
            roam: false,
            zoom:1.23,
            label: {
                normal: {
                    show: true,
                    fontSize:'10',
                    color: 'rgba(0,0,0,0.7)'
                }
            },
            itemStyle: {
                normal:{
                    borderColor: 'rgba(0, 0, 0, 0.2)'
                },
                emphasis:{
                    areaColor: '#F3B329',
                    shadowOffsetX: 0,
                    shadowOffsetY: 0,
                    shadowBlur: 20,
                    borderWidth: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        },

        series : [
            {
                name: '客户分布',
                type: 'map',
                geoIndex: 0,
                data:datatemp,
            }

        ]
    };
    myChart3.setOption(option);
</script>
</body>
