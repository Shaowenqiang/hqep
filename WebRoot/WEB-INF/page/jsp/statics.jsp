<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
      <script type="text/javascript" src="../js/highcharts.js"></script>
      <script src="../js/modules/data.js"></script>
	<script src="../js/modules/drilldown.js"></script>
      <script src="../js/modules/exporting.js"></script>
       
        <meta charset="UTF-8">
        <title>问卷调查统计</title>
        <style type="text/css">
            * {
                padding: 0;
                margin: 0;
                list-style: none;
            }
            html,
            body {
                height: 100%;
                padding-top :20px;
            }
            #menu {
                margin: 0 auto;
                display: flex;
                /*当前块为弹性盒*/
                flex-flow: column-reverse;
                overflow: hidden;
            }
            #menu li{
                border: 1px solid cornflowerblue;
                margin-bottom: 5px;
                text-align: center;
            }
            #menu li a {
                text-decoration: none;
                vertical-align: middle;
            }
            
        </style>
    </head>
    <body>
        <div style="text-align: center;font-size: 28;color: blue;" >问卷调查统计</div>
        <ol id="menu">
        <li  >
               <div id="suboption5"  style="height: 300px;width: 40%;float: left;"></div><div id="suboption6"  style="height: 300px;width: 60%;float: left;"></div>
              
            </li>
         <li  >
               <div id="suboption"  style="height: 300px;width: 25%;float: left;"></div><div id="suboption2"  style="height: 300px;width: 25%;float: left;"></div>
               <div id="suboption3"  style="height: 300px;width: 25%;float: left;"></div><div id="suboption4"  style="height: 300px;width: 25%;float: left;"></div>
            </li>
            <li class="a" >
               <div id="optionsum" style="height: 500px"></div>
            </li>
            <li class="b">
            <div id="sexcontainer" style="height:300px;width: 25%;float: left;" ></div><div id="xlcontainer" style="height:300px;width: 25%;float: left;"></div>
              <div id="zccontainer" style="height:300px;width: 25%;float: left;"></div>  <div id="agecontainer" style="height:300px;width: 25%;float: left;"></div>  
            </li>
            <li class="c">
            <div id="rycontainer" style="height:300px;width: 50%;float: left;" ></div><div id="rycolumn" style="height:300px;width: 50%;float: left;"></div>
            </li>
        </ol>
        <script>
    $.get('/hqep/static/ry', {}, function(v_data) {
			$(function () {
    var colors = Highcharts.getOptions().colors,
        categories = eval(v_data.catagory),
        data = eval(v_data.data),
        browserData = [],
        versionsData = [],
        i,
        j,
        dataLen = data.length,
        drillDataLen,
        brightness;
    // 构建数据数组
    for (i = 0; i < dataLen; i += 1) {
        // 添加浏览器数据
        browserData.push({
            name: categories[i],
            y: data[i].y,
            color: data[i].color
        });
        // 添加版本数据
        drillDataLen = data[i].drilldown.data.length;
        for (j = 0; j < drillDataLen; j += 1) {
            brightness = 0.2 - (j / drillDataLen) / 5;
            versionsData.push({
                name: data[i].drilldown.categories[j],
                y: data[i].drilldown.data[j],
                color: Highcharts.Color(data[i].color).brighten(brightness).get()
            });
        }
    }
    // 创建图表
    $('#rycontainer').highcharts({
        chart: {
            type: 'pie'
        },
        title: {
            text: '人员占比'
        },
        subtitle: {
            text: '内环为各个子类占比，外环为科研机构占比'
        },
        yAxis: {
            title: {
                text: '总百分比人员占比'
            }
        },
        plotOptions: {
            pie: {
                shadow: false,
                center: ['50%', '50%']
            }
        },
        tooltip: {
            valueSuffix: '%'
        },
        series: [{
            name: '人员分类',
            data: browserData,
            size: '60%',
            dataLabels: {
                formatter: function () {
                    return this.y > 5 ? this.point.name : null;
                },
                color: 'white',
                distance: -30
            }
        }, {
            name: '人员占比',
            data: versionsData,
            size: '80%',
            innerSize: '60%',
            dataLabels: {
                formatter: function () {
                    // 大于1则显示
                    return this.y > 1 ? '<b>' + this.point.name + ':</b> ' + this.y + '%'  : null;
                }
            }
        }]
    });
});

		//人员占比柱形图
	$(function () {
    var bigdata = eval(v_data.bignum),
        smalldata  =eval(v_data.smallnum);
    Highcharts.chart('rycolumn', {
        chart: {
            type: 'column'
        },
        title: {
            text: '各科研院所问卷调查人数'
        },
        subtitle: {
            text: '点击可查看具体的类别人数'
        },
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: '参加问卷人数'
            }
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true
                   
                }
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b><br/>'
        },
        series: [{
            name: '大类参加人数',
            colorByPoint: true,
            data: bigdata
        }],
        drilldown: {
            series: smalldata
        }
    });
});
		}); 
		
		 $.get('/hqep/static/tj', {}, function(data) {
		 var i=0;
		 	$(function () {
    $('#optionsum').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '各个题选项分布情况'
        },
        subtitle: {
            text: '数据为参与调查问卷数据，如有疑问请联系管理员'
        },
        xAxis: {
            categories: eval(data.categorys),
            crosshair: true,
            labels:{
            formatter:function() {
                var str  =eval(data.qnum)[i];
                i++;
	           return str;
                }
            }
        },
        yAxis: {
            title: {
                text: '人数'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{point.description}: </td>' +
            '<td style="padding:0"><b>{point.y} </b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        series: [{
            name: 'A',
            data:eval(data.astr),
                 events: {
        click: function(e) {
                var qid = e.point.index;
                var answerid = e.point.series.name;
	                kinddetail(qid);
	                drillkindstatic(qid,answerid);
        }}
        }, {
            name: 'B',
            data:eval(data.bstr),
             events: {
        click: function(e) {
	                var qid = e.point.index;
                var answerid = e.point.series.name;
	                kinddetail(qid);
	                drillkindstatic(qid,answerid);
        }}
        }, {
            name: 'C',
            data: eval(data.cstr),
             events: {
        click: function(e) {
	               var qid = e.point.index;
                var answerid = e.point.series.name;
	                kinddetail(qid);
	                drillkindstatic(qid,answerid);
        }}
        }, {
            name: 'D',
            data: eval(data.dstr),
             events: {
        click: function(e) {
	               var qid = e.point.index;
                var answerid = e.point.series.name;
	                kinddetail(qid);
	                drillkindstatic(qid,answerid);
        }}
        }]
    });
});	
		 
		 });
		
		 $.get('/hqep/static/subry', {}, function(v_data) {
		$(function () {
    $('#sexcontainer').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: v_data.sump+'人参加问卷调查：性别占比图'
        },
        subtitle:{
            text:'可能会由于数据精度问题总和不为100%'
        },
        tooltip: {
            headerFormat: '{series.name}<br>',
            pointFormat: '{point.name}: <b>{point.percentage:.2f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [{
            type: 'pie',
            name: '性别占比',
            data: eval(v_data.sexdata)
        }]
    });
});
//学历占比
$(function () {
    $('#xlcontainer').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: v_data.sump+'人参加问卷调查：学历占比图'
        },
        subtitle:{
            text:'可能会由于数据精度问题总和不为100%'
        },
        tooltip: {
            headerFormat: '{series.name}<br>',
            pointFormat: '{point.name}: <b>{point.percentage:.2f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [{
            type: 'pie',
            name: '学历占比',
            data: eval(v_data.xldata)
        }]
    });
});
//职称占比
$(function () {
    $('#zccontainer').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: v_data.sump+'人参加问卷调查：职称占比图'
        },
        subtitle:{
            text:'可能会由于数据精度问题总和不为100%'
        },
        tooltip: {
            headerFormat: '{series.name}<br>',
            pointFormat: '{point.name}: <b>{point.percentage:.2f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [{
            type: 'pie',
            name: '职称占比',
            data: eval(v_data.zcdata)
        }]
    });
});
//年龄占比
$(function () {
    $('#agecontainer').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: v_data.sump+'人参加问卷调查：年龄占比图'
        },
        subtitle:{
            text:'可能会由于数据精度问题总和不为100%'
        },
        tooltip: {
            headerFormat: '{series.name}<br>',
            pointFormat: '{point.name}: <b>{point.percentage:.2f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [{
            type: 'pie',
            name: '年龄占比',
            data: eval(v_data.agedata)
        }]
    });
});



});


function kinddetail(qid){

$.get('/hqep/static/kindstatic', {qid:qid}, function(v_data) {
$(function () {
    $('#suboption').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '该题选项情况分布统计'
        },
        xAxis: {
            categories: eval(v_data.catedata),
            labels:{
            autoRotation:false
            }
        },
        yAxis: {
            title: {
                text: '数量'
            }
        },
        tooltip: {
            pointFormatter: function () {
                return this.series.stackKey.replace('column','')+' '+  this.series.name + ': ' + this.y + '<br/>占比:'+this.percentage+'%<br/>' +'总量: '+this.total ;
            },
            headerFormatter: function () {
                
                return  '<b>' + this.x+ '</b>';
            }
        },
        plotOptions: {
            column: {
                stacking: 'normal'
            }
        },
        series: eval(v_data.detaildata)
    });
});
});
}

function drillkindstatic(qid,answerid){
$.get('/hqep/static/anskindstatic', {qid:qid,answerid:answerid}, function(v_data) {
$(function () {
    // Create the chart
    Highcharts.chart('suboption2', {
        chart: {
            type: 'column'
        },
        title: {
            text: '该题该选项科研院所分布情况'
        },
        subtitle: { 
            text: '请点击具体题与选项，查看科研院所分布情况'
        },
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: '人数'
            }
        },
        legend: {
            enabled: true
        },
        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b> 人<br/>占比为:<b>{point.y}/'+v_data.nums+'<b/>'
        },
        series: [{
            name: '科研机构',
            colorByPoint: true,
            data: eval(v_data.kindata)
        }],
        drilldown: {
            series: eval(v_data.subkinddata)
        }
    });
});
//性别分布
$(function () {
    $('#suboption3').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '该题该选项性别分布情况'
        },
        xAxis: {
            categories:eval(v_data.sex),
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: '人数'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{series.name}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{point.name}: </td>' +
            '<td style="padding:0"><b>{point.y}</b></td></tr>',
            footerFormat: '</table>占比为:<b>{point.y}/'+v_data.nums+'<b/>',
            shared: true,
            useHTML: true
        },
        series: [{
            name:'性别',
            data: eval(v_data.sexstr)
        }
        ]
    });
});
//学历占比
$(function () {
    $('#suboption4').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '该题该选项学历分布情况'
        },
        xAxis: {
            categories: eval(v_data.xl),
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: '人数'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{series.name}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{point.name}: </td>' +
            '<td style="padding:0"><b>{point.y}</b></td></tr>',
            footerFormat: '</table>占比为:<b>{point.y}/'+v_data.nums+'<b/>',
            shared: true,
            useHTML: true
        },
        series: [{
            name:'学历',
            data: eval(v_data.xlstr)
        }
        ]
    });
});
//职称占比
$(function () {
    $('#suboption5').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '该题该选项职称分布情况'
        },
        xAxis: {
            categories: eval(v_data.zc),
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: '人数'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{series.name}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{point.name}: </td>' +
            '<td style="padding:0"><b>{point.y}</b></td></tr>',
            footerFormat: '</table>占比为:<b>{point.y}/'+v_data.nums+'<b/>',
            shared: true,
            useHTML: true
        },
        series: [{
            name:'职称',
            data: eval(v_data.zcstr)
        }
        ]
    });
});
//年龄占比
$(function () {
    $('#suboption6').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '该题该选项年龄分布情况'
        },
        xAxis: {
            categories: ['25岁以下','26-34岁','35-44岁','45岁以上'],
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: '人数'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{series.name}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{point.name}: </td>' +
            '<td style="padding:0"><b>{point.y}</b></td></tr>',
            footerFormat: '</table>占比为:<b>{point.y}/'+v_data.nums+'<b/>',
            shared: true,
            useHTML: true
        },
        series: [{
            name:'职称',
            data: eval(v_data.agestr)
        }
        ]
    });
});


});
}

		   
</script>
    </body>
</html>
