<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>object data</title>

<script type="text/javascript" src="../js/easyui/jquery-1.8.0.min.js"></script>
  <script type="text/javascript" src="../js/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="../js/easyui/locale/easyui-lang-en.js"></script>
  <link rel="stylesheet" href="../js/easyui/themes/default/easyui.css" type="text/css"></link>
  <link rel="stylesheet" href="../js/easyui/themes/icon.css" type="text/css"></link>
</head>
<body>
<div  style="width:800px; height:400px;">
<div id="tdata"></div></div>
<script>
$('#tdata').treegrid({  
    url:'../treegrid',  
    treeField:'name', 
    idField:'id',
    fit:true,
    columns:[[  
        {title:'Name',field:'name',width:180},  
        {field:'number',title:'Number',width:80,align:'center'},{
			field : 'action',
			align:'center',
			title : 'show',
			width : 40,
			formatter : function(value, row, index) {
				return 	$.formatString('<img onclick="show(\'{0}\')" src="../img/search.png" title="showdata"/>',row.name);
			}
		},{
			title:'Description',field:'des',width:200
		}    
    ]]  
}); 
function show(){
	window.showModalDialog('../index.jsp');     
}
$.formatString = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};
</script>
</body>
</html>