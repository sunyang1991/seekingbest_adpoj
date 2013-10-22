<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
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
<body>
	<div id="accordion-tab" class="easyui-accordion" fit="true">
		<div title="Object List" iconCls="icon-tip" id="west-tree" class="easyui-tree" url="./treedata"></div>
		<div title="Optima" iconCls="icon-tip" id="optima-tree" class="easyui-tree" url="./optree" style="overflow:auto;padding:10px;"></div>
	</div>
	<div id="add-config-panel" style="width:250px;height:110px;"></div>
	<script type="text/javascript">
		function addnewcfg(name,statu) {
			$('#maintable').tabs('add', {
				title : statu+" Configuration",
				href : "jsp/optimaset.jsp?name=" + name+"&statu="+statu,
				closable : true
			});
		}
		function addplan() {
			$('#maintable').tabs('add',{
						title :  "show plan data",
						content : '<iframe src="jsp/showinput.jsp" fit="true" border="0" width="100%" height="100%"></iframe> ',
						iconCls : 'icon-save',
						closable : true
					});
		}
		function addTab(tabname, fname, sql) {
			$('#maintable')
					.tabs(
							'add',
							{
								title : tabname,
								content : '<iframe src="jsp/tabs.jsp?sql='
										+ sql
										+ '" frameborder="0" id="'
										+ tabname
										+ '" title="'
										+ fname
										+ '" name="'
										+ tabname
										+ '" fit="true" border="0" width="100%" height="100%"></iframe> ',
								iconCls : 'icon-save',
								closable : true
							});
		}
		function showresult(tabname,runname) {
			$('#maintable')
					.tabs(
							'add',
							{
								title : tabname,
								content : '<iframe src="jsp/showresult.jsp?runame='
										+ runname
										+ '" fit="true" border="0" width="100%" height="100%"></iframe> ',
								iconCls : 'icon-save',
								closable : true
							});
		}
		$('#west-tree').tree(
				{
					onClick : function(node) {
						if(node.id==7){
							if ($('#maintable').tabs('exists', 'show plan data')) {
								$('#maintable').tabs('select', 'show plan data');
							} else {//如果页面存在则选中页面
								addplan();
							}
						}
						if (!(node.id < 8)) {
							if ($('#maintable').tabs('exists', node.text)) {
								$('#maintable').tabs('select', node.text);
							} else {//如果页面存在则选中页面
								addTab(node.text, node.attributes.fname,
										node.attributes.sql);
							}
						}
					}
				});
		$('#optima-tree')
				.tree(
						{
							onClick : function(node) {
								if(node.attributes.action=="config"){
									if ($('#maintable').tabs('exists', "load Configuration")) {
										$('#maintable').tabs('select', "load Configuration");
									}else{
									addnewcfg(node.text,"load");
									}
								}
								if(node.attributes.action=="showresult"){
									if ($('#maintable').tabs('exists', "Show "+node.text+" result")) {
										$('#maintable').tabs('select', "Show "+node.text+" result");
									}else{
										showresult( "Show "+node.text+" result", node.text);
									}
								}
								if (node.attributes.action == "add") {
									if ($('#maintable').tabs('exists',
											"new Configuration")) {
										$('#maintable').tabs('select',
												"new Configuration");
									} else {//如果页面存在则选中页面
										$('#add-config-panel')
												.dialog(
														{
															title : "SET NAME",
															closable : true,
															content : '<div style="position: absolute;top: 30%;">Name:<input type="text"id="config-name" value="Enter the name."></input></div>',
															buttons : [
																	   {
																		id : 'ok',
																		text : 'ok',
																		handler : function() {
																			var value = $('#config-name').val() ;
																			if (value == ""|| value == "Enter the name.") {
																				$.messager
																						.alert(
																								'Warning',
																								'Please enter the name!');
																			} else {
																				$.post("./addnewcfg",{name:value},function(data,status){
																					if(data.msg=="success"){
																						$('#add-config-panel').dialog('close');
																						addnewcfg(value,"new");
																						$('#optima-tree').tree('reload');
																					}else{
																						$.messager
																						.alert(
																								'Warning',
																								'Name already exists!');
																					}
																				},'json');
																				
																			}
																		}
																	},
																	{
																		id : 'cancel',
																		text : 'cancel',
																		handler : function() {
																			$('#add-config-panel').dialog('close');
																		}
																	} ],
															onBeforeClose : function() {
																return true;
															}
														});
									}
								}
							}
						});
	</script>
</body>
</html>
