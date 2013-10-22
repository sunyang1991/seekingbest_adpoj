<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'tabs.jsp' starting page</title>
<script type="text/javascript" src="js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="js/easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/default/easyui.css" type="text/css"></link>
<script type="text/javascript" src="js/easyui/locale/easyui-lang-en.js"></script>
<script type="text/javascript" src="js/datagrid.js"></script>
<script type="text/javascript" src="js/highchars.js"></script>
</head>
<body>
	<table>
		<tr>
			<div id="chart-panel" class="easyui-panel" title="show chart" style="left: auto;right: auto;width:1100px;height:450px;background:#fafafa;"
			 iconCls="icon-save" closable="false" collapsible="true"  minimizable="false" maximizable="false"data-options="onBeforeDestroy:function(){return true}">
				<div id="chart-tab" fit="true"></div>
			</div>
		</tr>
		<tr id="data-tr" style="left: auto;right: auto;height:10px;">&nbsp;
		</tr>
		<div id="datagrid-panel" class="easyui-panel" title="show data" style="left: auto;right: auto;width:1100px;height:400px;background:#fafafa;" iconCls="icon-save" closable="false" collapsible="true" minimizable="false" maximizable="false">
			<div id="datagrid-tab" fit="true"></div>
		</div>
		</tr>
	</table>
	<script type="text/javascript" src="js/highcharts/highcharts.js"></script>
	<script src="js/highcharts/modules/exporting.js"></script>
	<script type="text/javascript">
		function getQueryString(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return unescape(r[2]);
			return null;
		}
			datagrid(self.frameElement.name, self.frameElement.title,
					getQueryString("sql"));
			if (self.frameElement.title == "vehicle"
					|| self.frameElement.title == "item") {
				linechart(self.frameElement.name,getQueryString("sql"));
			}else{
				//setTimeout("$('#chart-panel').panel('destroy')",500);
				piechart(self.frameElement.name);
			}
			
	</script>
</body>
</html>
