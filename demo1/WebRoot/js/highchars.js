function piechart(tabname) {
	$.ajax({
		url : "./piechartdata",
		cache : false,
		data : {
			tabname : tabname
		},
		type : "post",
		dataType : "json",
		success : function(data) {
			drawpiechart(data);
		}

	});
}
var drawpiechart = function(json) {
	json.chart.renderTo = 'chart-tab';
	new Highcharts.Chart({
		chart : {
			defaultSeriesType : 'pie',
			renderTo : 'chart-tab'
		},
		title : json.title,
		tooltip : {
			pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		plotOptions : {
			pie : {
				allowPointSelect : true,
				cursor : 'pointer',
				dataLabels : {
					enabled : true,
					color : '#000000',
					connectorColor : '#000000',
					format : '{point.name}: {point.percentage:.1f} %'
				}
			}
		},
		series : json.series
	});
};
function linechart(tabname,sql) {
	$.ajax({
		url : "./chartdata",
		cache : false,
		data : {
			tabname : tabname,
			sql :sql
		},
		type : "post",
		dataType : "json",
		success : function(data) {
			drawlinechart(data);
		}

	});
}
var drawlinechart = function(json) {
	new Highcharts.Chart({
		chart : {
			defaultSeriesType : 'line',
			renderTo : 'chart-tab'
		},
		title : json.title,
		xAxis :{
			categories:json.xAxis.categories,
			labels : {
			step : 12
		}},
		series : json.series
	});
};
function weekchart(data) {
    $('#result2').highcharts({
        title: {
            text: 'Ad Spend is Reduced for Most Weeks',
            x: -20 //center
        },
        subtitle: {
            text: 'Weekly Ad Spend',
            x: -20
        },
        xAxis: {
            categories: data.week,
            labels:{
            	step : 2,
            	rotation:-45
            }
        },
        yAxis: {
            title: {
                text: 'Spend(MM$)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '(million dollars)'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: data.value
    });
}
$.post("./result2","",function(data,status){
	weekchart(data);
},'json');
function barchart(data){
    $('#result3').highcharts({
        chart: {
            type: 'column',
            height:500,
            width:1090
        },
        title: {
            text: 'Historical due to decomposition.'
        },
        xAxis: {
            categories:data.week,
            labels:{
            	step : 2,
            	rotation:-45
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Cost(MM$)'
            },
           
            tooltip: {
                valueSuffix: '(million dollars)'
            },
            stackLabels: {
                enabled: true,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                }
            }
        },
        legend: {
            align: 'right',
            x: -70,
            verticalAlign: 'top',
            y: 20,
            floating: true,
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
            borderColor: '#CCC',
            borderWidth: 1,
            shadow: false
        },
        tooltip: {
            formatter: function() {
                return '<b>'+ this.x +'</b><br/>'+
                    this.series.name +': '+ this.y +'<br/>'+
                    'Total: '+ this.point.stackTotal;
            }
        },
        plotOptions: {
            column: {
                stacking: 'normal',
               
            }
        },
        series:data.value
    });
}
$.post("./result3","",function(data,status){
	barchart(data);
},'json');
function result4(vehicle,data) {
    $('#result4').highcharts({
        chart: {
        },
        title: {
            text: 'Cost for'+vehicle+'par week.'
        },
       
        xAxis: [{
            categories: data.week,
            labels:{
            	step : 2,
            	rotation:-45
            }
        }],
        yAxis: [{ // Primary yAxis
            labels: {
                format: '{value}MM$',
                style: {
                    color: '#89A54E'
                }
            },
            min:0,
            title: {
                text: 'Cost',
                style: {
                    color: '#89A54E'
                }
            }
        }, { // Secondary yAxis
        	 min: 0,
            labels: {
                format: '{value}MM$',
                style: {
                    color: '#4572A7'
                }
            },
            opposite: true
        }],
        tooltip: {
            shared: true
        },
        legend: {
            layout: 'vertical',
            align: 'left',
            x: 120,
            verticalAlign: 'top',
            y: 100,
            floating: true,
            backgroundColor: '#FFFFFF'
        },
        series: [{
            name: 'optimal',
            color: '#4572A7',
            type: 'column',
            yAxis: 1,
            data: data.opt,
            tooltip: {
                valueSuffix: '$'
            }

        }, {
            name: 'plan',
            color: '#89A54E',
            type: 'spline',
            data: data.plan,
            tooltip: {
                valueSuffix: '$'
            }
        }]
    });
};
$.post("./result4",{
	vehicle:'BSTV'
},function(data,status){
	result4('BSTV',data);
},'json');
function selectvel(vehicle){
	$.post("./result4",{
		vehicle:vehicle
	},function(data,status){
		result4(vehicle,data);
	},'json');
}