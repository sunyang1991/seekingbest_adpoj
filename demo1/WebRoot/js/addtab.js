function addTab(tabname) {
	$('#centertab').tabs('add', {
		title : tabname,
		content:'<iframe src="jsp/tabs.jsp" frameborder="0" id="'+tabname+'" name="'+tabname+'" fit="true" border="0" width="100%" height="100%"></iframe> ',
		iconCls : 'icon-save',
		closable : true
	});
}
