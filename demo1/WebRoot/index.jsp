<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script type="text/javascript" src="js/easyui/jquery-1.8.0.min.js"></script>
  <script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="js/easyui/locale/easyui-lang-en.js"></script>
  <link rel="stylesheet" href="js/easyui/themes/default/easyui.css" type="text/css"></link>
  <link rel="stylesheet" href="js/easyui/themes/icon.css" type="text/css"></link></head>
  
  <body>
  <div id="main-layout" class="easyui-layout" fit="true">  
    <div region="west"   href="jsp/tree.jsp" title="Menu" split="true" style="width:200px;"></div>      
    <div region="center" href="jsp/center.jsp" title="Data Analysis" style="padding:5px;background:#eee;"></div>  
</div>  
  </body>
  </html>
