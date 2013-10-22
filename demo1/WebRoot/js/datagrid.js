function datagrid(tabname, fname, sql) {
	var data;
	if (fname == "business") {
		data = [ [ {
			field : 'Item',
			title : 'item',
			width : 80
		}, {
			field : 'Bus_Btem',
			title : 'bus_item',
			width : 80
		} ] ];
	} else if (fname == "campaign") {
		data = [ [ {
			field : 'Item',
			title : 'item',
			width : 80
		}, {
			field : 'Campaign_Item',
			title : 'campaign_item',
			width : 80
		} ] ];
	} else if (fname == "item") {
		data = [ [ {
			field : 'Week',
			title : 'date',
			width : 80
		}, {
			field : 'Base_Price',
			title : 'price',
			width : 80
		} ] ];
	} else if (fname == "vehicle") {
		data = [ [ {
			field : 'Week',
			title : 'date',
			width : 80
		}, {
			field : 'Cost',
			title : 'cost',
			width : 80
		} ] ];
	}
	$('#datagrid-tab').datagrid({
		url : "./datagrid",
		queryParams : {
			sql : sql
		},
		columns : data,
		singleSelect : true,
		fit : true,
		fitColumns : true,
		pagination : true
	});
}
function datagrid2(runname) {

	$('#result1').datagrid({
		url : "./result1",
		queryParams : {
			runname : runname
		},
		columns : [ [ {
			field : 'Vehicle',
			title : 'Vehicle',
			width:80,
			align:"center"
		}, {
			field : 'Plan',
			title : 'Plan',
			width:100,
			align:"right"
		}, {
			field : 'Optimal',
			title : 'Optimal',
			width:100,
			align:"right"
		}, {
			field : 'Change',
			title : 'Change',
			width:100,
			align:"right"
		}, {
			field : 'per',
			title : 'Change%',
			width:100,
			align:"right"
		}] ],
		fit : true,
		fitColumns : true,
		singleSelect : true,
		striped : true,
		showFooter : true
	});
}
