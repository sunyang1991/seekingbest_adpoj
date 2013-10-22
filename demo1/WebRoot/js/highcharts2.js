function weekchart(data) {
    $('#result1').highcharts({
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
$.post("./result5","",function(data,status){
	weekchart(data);
},'json');
function barchart(data){
    $('#result2').highcharts({
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
$.post("./result6","",function(data,status){
	barchart(data);
},'json');
function result4(vehicle,data) {
    $('#result3').highcharts({
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
        yAxis: [{ 
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
$.post("./result7",{
	vehicle:'BSTV'
},function(data,status){
	result4('BSTV',data);
},'json');
function selectvel(vehicle){
	$.post("./result7",{
		vehicle:vehicle
	},function(data,status){
		result4(vehicle,data);
	},'json');
}