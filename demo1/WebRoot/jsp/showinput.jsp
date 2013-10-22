<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="js/easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/default/easyui.css" type="text/css"></link>
<script type="text/javascript" src="js/easyui/locale/easyui-lang-en.js"></script>
<script type="text/javascript" src="js/highcharts2.js"></script>
</head>

<body>
	<table>
		<div id="panel1" class="easyui-panel" title="Pay for each week." style="left: auto;right: auto;width:1100px;height:450px;background:#fafafa;" iconCls="icon-save" closable="false" collapsible="true" minimizable="false" maximizable="false">
		<div id="result1" style="width:1090px">	</div>		
		</div>
		</tr>
		<tr>
		<tr  style="left: auto;right: auto;height:10px;">&nbsp;
		</tr>
			<div id="panel2" class="easyui-panel" title="Historical due to decomposition." style="left: auto;right: auto;width:1100px;height:550px;background:#fafafa;" iconCls="icon-save" closable="false" collapsible="true" minimizable="false" maximizable="false" data-options="onBeforeDestroy:function(){return true}">
				<div id="result2" style="width:1090px;height:500px;left:auto;left:auto">	</div>		
			</div>
		</tr>
		<tr>
		<tr  style="left: auto;right: auto;height:10px;">&nbsp;
		</tr>
			<div id="panel3" class="easyui-panel" title="Cost for each vehicle." style="left: auto;right: auto;width:1100px;height:520px;background:#fafafa;" iconCls="icon-save" closable="false" collapsible="true" minimizable="false" maximizable="false" data-options="onBeforeDestroy:function(){return true}">
				<div >Vehicle:
				<select  onChange="selectvel(this.value)">
							<option value="BSTV" selected="selected">BSTV</option>
							<option value="PBRADIO">PBRADIO</option>
							<option value="PBTV">PBTV</option>
							<option value="PSRADIO">PSRADIO</option>
							<option value="PSTV">PSTV</option>
							<option value="CORE">CORE</option>
							<option value="ROP">ROP</option>
							<option value="VERTICAL">VERTICAL</option>
							<option value="WEEKEND">WEEKEND</option>
				</select>
				</div>
				<div id="result3" fit="true"></div>
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
	</script>
</body>
</html>
