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
<title>Configuration Optimizations</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<script type="text/javascript" src="js/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui/locale/easyui-lang-en.js"></script>
<link rel="stylesheet" href="js/easyui/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="js/easyui/themes/icon.css" type="text/css"></link>

</head>

</head>
<body  >
<div style="background-color:#E0ECFF">
	<link rel="stylesheet" href="./css/save.css" type="text/css"></link>
	<script type="text/javascript" src="./js/save.js"></script>

	<h1 align="left">Configure your optimization parameters</h1>

	<hr>
	<form id="optform">
		<table name="total-cost" width="800">
			<tr>
				<td background="./img/title.jpg"><span class="STYLE5">1.set optima ad cost </span></td>
			</tr>
			<tr>
				<td><input name="min-tot-ad-cost" type="text" value="0.85" style="width:50px;" onChange="savedata(this,'1','all','all','all')">
*
  <input name="tot-ad-cost" type="text" style="width:100px;" readonly="true">
≤(current ad cost)optima ad cost≤ 
<input name="max-tot-ad-cost" type="text" value="0.85" style="width:50px;" onChange="savedata(this,'1','all','all','all')"> 
			  * <input name="tot-ad-cost" type="text" style="width:100px;" readonly="true"> (current ad cost)</td>
			</tr>
		</table>
		<!--const1 out-->
		<table name="print" width="806">
			<tr>
				<td colspan="3"background="./img/title.jpg"><span class="STYLE5">2.set print cost </span></td>
			</tr>
			<tr>
				<td width="82" nowrap><div align="right">Simple :</div></td>
				<td width="650" colspan="2" nowrap><input name="low-print-ad" type="text" value="0.85" style="width:50px;"onChange="savedata(this,'21','all','all','all')"> 
			  * <input name="total-plan-print" type="text" style="width:100px;" readonly="true"> ≤total plan ad ≤ <input name="max-print-ad" type="text" value="1.45" style="width:50px;"onChange="savedata(this,'22','all','all','all')"> * <input name="total-plan-print" type="text" style="width:100px;" readonly="true"> (current total plan ad)</td>
			</tr>
			<tr>
				<td width="82" nowrap><div align="right">Detailed :</div></td>
				<td width="324">
					<p>
						Vehicle: <select id="print-select1" onChange="nextselect1('print-select1','print-select2')">
							<option value="all" selected="selected">select</option>
							<option value="CORE">CORE</option>
							<option value="ROP">ROP</option>
							<option value="VERTICAL">VERTICAL</option>
							<option value="WEEKEND">WEEKEND</option>
						</select> Business: <select id="print-select2" onChange="nextselect('print-select1','print-select2','print-select3')">
							<option value="all" selected="selected">select</option>
						</select>
					</p>
					<p>
						Item: <select id="print-select3" onChange="getweek('print-select1','print-select3','print-week1','print-week2')">
							<option value="all" selected="selected">select</option>
						</select> From: <select id="print-week1">
						</select> To: <select id="print-week2">
						</select>
					    <input name="button" type="button" onClick="finditem('print-select1','print-select3','print-detal-set','print-week1','print-week2','2')" value="search" />
					</p>			  </td>
		        <td width="324">&nbsp;</td>
			</tr>
		</table>

		<table id="print-detal-set" border="1px" bordercolor="#000000" cellspacing="0px" style="border-collapse:collapse">

		</table>
		<!--const2 out-->
		<table name="broad" width="806">
			<tr>
				<td colspan="2"background="./img/title.jpg"><span class="STYLE5">3.set broad cost </span></td>
			</tr>
			<tr>
				<td width="82" nowrap><div align="right">Simple :</div></td>
				<td nowrap><input name="low-broad-ad" type="text" value="0.85" style="width:50px;"onChange="savedata(this,'31','all','all','all')"> * <input name="total-plan-broad" type="text" style="width:100px;" readonly="true"> ≤total plan ad ≤ <input name="max-broad-ad" type="text" value="1.45" style="width:50px;"onChange="savedata(this,'32','all','all','all')"> * <input name="total-plan-broad" type="text" style="width:100px;" readonly="true"> (current total plan ad)</td>
			</tr>
			<tr>
				<td width="82" nowrap><div align="right">Detailed :</div></td>
				<td width="625">
					<p>
						Vehicle: <select id="broad-select1" onChange="nextselect1('broad-select1','broad-select2')">
							<option value="all" selected="selected">select</option>
							<option value="BSTV">BSTV</option>
							<option value="PBRADIO">PBRADIO</option>
							<option value="PBTV">PBTV</option>
							<option value="PSRADIO">PSRADIO</option>
							<option value="PSTV">PSTV</option>
						</select> Business: <select id="broad-select2" onChange="nextselect('broad-select1','broad-select2','broad-select3')">
							<option value="all" selected="selected">select</option>
						</select>
					</p>
					<p>
						Item: <select id="broad-select3" onChange="getweek('broad-select1','broad-select3','broad-week1','broad-week2')">
							<option value="all" selected="selected">select</option>
						</select> From: <select id="broad-week1">
						</select> To: <select id="broad-week2">
						</select>
					    <input name="button2" type="button" onClick="finditem('broad-select1','broad-select3','broad-detal-set','broad-week1','broad-week2','3')" value="search" />
					</p>				</td>
		    </tr>
		</table>

		<table id="broad-detal-set" border="1px" bordercolor="#000000" cellspacing="0px" style="border-collapse:collapse">

		</table>
		<!--const3 out-->
		<table name="week" width="806">
			<tr>
				<td colspan="2"background="./img/title.jpg"><span class="STYLE5">4.set week cost </span></td>
			</tr>
			<tr>
				<td width="625" height="63">
					<p>
						Vehicle: <select id="week-select1" onChange="weekset('week-select1','week-week1')">
							<option value="all" selected="selected">total</option>
							<option value="BSTV">BSTV</option>
							<option value="PBRADIO">PBRADIO</option>
							<option value="PBTV">PBTV</option>
							<option value="PSRADIO">PSRADIO</option>
							<option value="PSTV">PSTV</option>
							<option value="CORE">CORE</option>
							<option value="ROP">ROP</option>
							<option value="VERTICAL">VERTICAL</option>
							<option value="WEEKEND">WEEKEND</option>
						</select> Week: <select id="week-week1">
							<option value="2007-01-06" selected="selected">2007-01-06</option>
							<option value="2007-01-13">2007-01-13</option>
							<option value="2007-01-20">2007-01-20</option>
							<option value="2007-01-27">2007-01-27</option>
							<option value="2007-02-03">2007-02-03</option>
							<option value="2007-02-10">2007-02-10</option>
							<option value="2007-02-17">2007-02-17</option>
							<option value="2007-02-24">2007-02-24</option>
							<option value="2007-03-03">2007-03-03</option>
							<option value="2007-03-10">2007-03-10</option>
							<option value="2007-03-17">2007-03-17</option>
							<option value="2007-03-24">2007-03-24</option>
							<option value="2007-03-31">2007-03-31</option>
							<option value="2007-04-07">2007-04-07</option>
							<option value="2007-04-14">2007-04-14</option>
							<option value="2007-04-21">2007-04-21</option>
							<option value="2007-04-28">2007-04-28</option>
							<option value="2007-05-05">2007-05-05</option>
							<option value="2007-05-12">2007-05-12</option>
							<option value="2007-05-19">2007-05-19</option>
							<option value="2007-05-26">2007-05-26</option>
							<option value="2007-06-02">2007-06-02</option>
							<option value="2007-06-09">2007-06-09</option>
							<option value="2007-06-16">2007-06-16</option>
							<option value="2007-06-23">2007-06-23</option>
							<option value="2007-06-30">2007-06-30</option>
							<option value="2007-07-07">2007-07-07</option>
							<option value="2007-07-14">2007-07-14</option>
							<option value="2007-07-21">2007-07-21</option>
							<option value="2007-07-28">2007-07-28</option>
							<option value="2007-08-04">2007-08-04</option>
							<option value="2007-08-11">2007-08-11</option>
							<option value="2007-08-18">2007-08-18</option>
							<option value="2007-08-25">2007-08-25</option>
							<option value="2007-09-01">2007-09-01</option>
							<option value="2007-09-08">2007-09-08</option>
							<option value="2007-09-15">2007-09-15</option>
							<option value="2007-09-22">2007-09-22</option>
							<option value="2007-09-29">2007-09-29</option>
							<option value="2007-10-06">2007-10-06</option>
							<option value="2007-10-13">2007-10-13</option>
							<option value="2007-10-20">2007-10-20</option>
							<option value="2007-10-27">2007-10-27</option>
							<option value="2007-11-03">2007-11-03</option>
							<option value="2007-11-10">2007-11-10</option>
							<option value="2007-11-17">2007-11-17</option>
							<option value="2007-11-24">2007-11-24</option>
							<option value="2007-12-01">2007-12-01</option>
							<option value="2007-12-08">2007-12-08</option>
							<option value="2007-12-15">2007-12-15</option>
							<option value="2007-12-22">2007-12-22</option>
							<option value="2007-12-29">2007-12-29</option>
						</select>
					    <input name="button3" type="button" onClick="findweek('week-select1','week-week1','week-detal-set','4')" value="search" />
			  </p></td>
			  <td width="83">&nbsp;</td>
			</tr>
		</table>

		<table id="week-detal-set" border="1px" bordercolor="#000000" cellspacing="0px" style="border-collapse:collapse">

		</table>
		<!--const4 out-->
		<table name="item" width="806">
			<tr>
				<td colspan="2"background="./img/title.jpg"><span class="STYLE5">5.set item cost </span></td>
			</tr>
			<tr>
				<td width="625">
					<p>
						Vehicle: <select id="item-select1" onChange="nextselect1('item-select1','item-select2')">
							<option value="all" selected="selected">total</option>
							<option value="BSTV">BSTV</option>
							<option value="PBRADIO">PBRADIO</option>
							<option value="PBTV">PBTV</option>
							<option value="PSRADIO">PSRADIO</option>
							<option value="PSTV">PSTV</option>
							<option value="CORE">CORE</option>
							<option value="ROP">ROP</option>
							<option value="VERTICAL">VERTICAL</option>
							<option value="WEEKEND">WEEKEND</option>
						</select> Business: <select id="item-select2" onChange="nextselect('item-select1','item-select2','item-select3')">
							<option value="all" selected="selected">select</option>
						</select> Item: <select id="item-select3">
						</select>
					    <input name="button4" type="button" onClick="finditem2('item-select1','item-select3','item-detal-set','5')" value="search" />
					</p>			  </td>
			  <td width="83">&nbsp;</td>
			</tr>
		</table>

		<table id="item-detal-set" border="1px" bordercolor="#000000" cellspacing="0px" style="border-collapse:collapse">

		</table>
		<!--const5 out-->
		<table name="NXmas" width="806">
			<tr>
				<td colspan="2"background="./img/title.jpg"><span class="STYLE5">6.set NXmas cost </span></td>
			</tr>
			<tr>
				<td width="625" height="63">
					<p>
						Vehicle: <select id="NXmas-select1" onChange="NXmasset('NXmas-select1','NXmas-week1')">
							<option value="all" selected="selected">total</option>
							<option value="BSTV">BSTV</option>
							<option value="PBRADIO">PBRADIO</option>
							<option value="PBTV">PBTV</option>
							<option value="PSRADIO">PSRADIO</option>
							<option value="PSTV">PSTV</option>
							<option value="CORE">CORE</option>
							<option value="ROP">ROP</option>
							<option value="VERTICAL">VERTICAL</option>
							<option value="WEEKEND">WEEKEND</option>
						</select> Week: <select id="NXmas-week1">
							<option value="2007-01-06" selected="selected">2007-01-06</option>
							<option value="2007-01-13">2007-01-13</option>
							<option value="2007-01-20">2007-01-20</option>
							<option value="2007-01-27">2007-01-27</option>
							<option value="2007-02-03">2007-02-03</option>
							<option value="2007-02-10">2007-02-10</option>
							<option value="2007-02-17">2007-02-17</option>
							<option value="2007-02-24">2007-02-24</option>
							<option value="2007-03-03">2007-03-03</option>
							<option value="2007-03-10">2007-03-10</option>
							<option value="2007-03-17">2007-03-17</option>
							<option value="2007-03-24">2007-03-24</option>
							<option value="2007-03-31">2007-03-31</option>
							<option value="2007-04-07">2007-04-07</option>
							<option value="2007-04-14">2007-04-14</option>
							<option value="2007-04-21">2007-04-21</option>
							<option value="2007-04-28">2007-04-28</option>
							<option value="2007-05-05">2007-05-05</option>
							<option value="2007-05-12">2007-05-12</option>
							<option value="2007-05-19">2007-05-19</option>
							<option value="2007-05-26">2007-05-26</option>
							<option value="2007-06-02">2007-06-02</option>
							<option value="2007-06-09">2007-06-09</option>
							<option value="2007-06-16">2007-06-16</option>
							<option value="2007-06-23">2007-06-23</option>
							<option value="2007-06-30">2007-06-30</option>
							<option value="2007-07-07">2007-07-07</option>
							<option value="2007-07-14">2007-07-14</option>
							<option value="2007-07-21">2007-07-21</option>
							<option value="2007-07-28">2007-07-28</option>
							<option value="2007-08-04">2007-08-04</option>
							<option value="2007-08-11">2007-08-11</option>
							<option value="2007-08-18">2007-08-18</option>
							<option value="2007-08-25">2007-08-25</option>
							<option value="2007-09-01">2007-09-01</option>
							<option value="2007-09-08">2007-09-08</option>
							<option value="2007-09-15">2007-09-15</option>
							<option value="2007-09-22">2007-09-22</option>
							<option value="2007-09-29">2007-09-29</option>
							<option value="2007-10-06">2007-10-06</option>
							<option value="2007-10-13">2007-10-13</option>
							<option value="2007-10-20">2007-10-20</option>
							<option value="2007-10-27">2007-10-27</option>
							<option value="2007-11-03">2007-11-03</option>

						</select>
					    <input name="button5" type="button" onClick="findweek('NXmas-select1','NXmas-week1','NXmas-detal-set','6')" value="search" />
			  </p></td>
			  <td width="83">&nbsp;</td>
			</tr>
		</table>

		<table id="NXmas-detal-set" border="1px" bordercolor="#000000" cellspacing="0px" style="border-collapse:collapse">

		</table>
		<!--const6 out-->
		<table name="Xmas" width="806">
			<tr>
				<td colspan="2"background="./img/title.jpg"><span class="STYLE5">7.set Xmas cost </span></td>
			</tr>
			<tr>
				<td width="625" height="63">
					<p>
						Vehicle: <select id="Xmas-select1" onChange="Xmasset('Xmas-select1','Xmas-week1')">
							<option value="all" selected="selected">total</option>
							<option value="BSTV">BSTV</option>
							<option value="PBRADIO">PBRADIO</option>
							<option value="PSRADIO">PSRADIO</option>
							<option value="PSTV">PSTV</option>
							<option value="CORE">CORE</option>
							<option value="ROP">ROP</option>
							<option value="VERTICAL">VERTICAL</option>
							<option value="WEEKEND">WEEKEND</option>
						</select> Week: <select id="Xmas-week1">
							<option value="2007-11-10" selected="selected">2007-11-10</option>
							<option value="2007-11-17">2007-11-17</option>
							<option value="2007-11-24">2007-11-24</option>
							<option value="2007-12-01">2007-12-01</option>
							<option value="2007-12-08">2007-12-08</option>
							<option value="2007-12-15">2007-12-15</option>
							<option value="2007-12-22">2007-12-22</option>
							<option value="2007-12-29">2007-12-29</option>
						</select>
					    <input name="button6" type="button" onClick="findweek('Xmas-select1','Xmas-week1','Xmas-detal-set','7')" value="search" />
			  </p></td>
			  <td width="83">&nbsp;</td>
			</tr>
		</table>

		<table id="Xmas-detal-set" border="1px" bordercolor="#000000" cellspacing="0px" style="border-collapse:collapse">

		</table>
		<!--const7 out-->
		<table width="800" border="0">
			<tr>
				<td width="475">&nbsp;</td>
				<td width="58"><input type="button" onClick="saveopt()" value="submit" /></td>
				<td width="253">&nbsp;</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
	<%String name = (String) request.getParameter("name");%>
    <%String statu = (String) request.getParameter("statu");%>
	/*********select模块*************/
	function getweek(id1,id2,id3,id4){
		$.post("./getweek",{value1:$('#'+id1).val(),value2:$('#'+id2).val()},function(data,status){
			$('#'+id3).empty();
			for(i=0;i<data.length;i++){
			$('#'+id3).append('<option value="'+data[i]+'">'+data[i]+'</option>');
			}
			$('#'+id4).empty();
			for(i=0;i<data.length;i++){
			$('#'+id4).append('<option value="'+data[i]+'">'+data[i]+'</option>');
			}
		},'json');
	}
	function weekset(id1,id2){
		$.post("./weekset",{value1:$('#'+id1).val()},function(data,status){
			$('#'+id2).empty();
			for(i=0;i<data.length;i++){
			$('#'+id2).append('<option value="'+data[i]+'">'+data[i]+'</option>');
			}
		},'json');
	}
	function Xmasset(id1,id2){
		$.post("./Xmasset",{value1:$('#'+id1).val()},function(data,status){
			$('#'+id2).empty();
			for(i=0;i<data.length;i++){
			$('#'+id2).append('<option value="'+data[i]+'">'+data[i]+'</option>');
			}
		},'json');
	}
	function NXmasset(id1,id2){
		$.post("./NXmasset",{value1:$('#'+id1).val()},function(data,status){
			$('#'+id2).empty();
			for(i=0;i<data.length;i++){
			$('#'+id2).append('<option value="'+data[i]+'">'+data[i]+'</option>');
			}
		},'json');
	}
	function nextselect1(id1,id2){
		$.post("./selectbus",{value1: $('#'+id1).val()},function(data,status){
			$('#'+id2).empty();
			$('#'+id2).append('<option value="all"selected="selected">select</option> ' );
			for(i=0;i<data.length;i++){
			$('#'+id2).append('<option value="'+data[i]+'">'+data[i]+'</option>');
			}
		},'json');
	}
	function nextselect(id1,id2,id3){
		$.post("./selectitem",{value1: $('#'+id1).val(),value2:$('#'+id2).val()},function(data,status){
			$('#'+id3).empty();
			$('#'+id3).append('<option value="all"selected="selected">select</option> ' );
			for(i=0;i<data.length;i++){
			$('#'+id3).append('<option value="'+data[i]+'">'+data[i]+'</option>');
			}
		},'json');
	}
	/*********详细设置数据*************/
	function finditem(id1,id2,id3,id4,id5,type){
		$('#'+id3).empty();
		$('#'+id3).append(
				'<tr >'+
				'<td width="60"><div align="center">Vehicle</div>'+
				'</td>'+
				'  <td width="90"><div align="center">Week</div>'+
				'</td>'+
				'<td width="60"><div align="center">Item</div>'+
				'</td>'+
				'<td width="715">&nbsp;</td>'+
			    '</tr>'
		);
	$.post("./itemdata",{
		value1: $('#'+id1).val(),
		value2:$('#'+id2).val(),
		value3: $('#'+id4).val(),
		value4: $('#'+id5).val(),
	},  function(data,status){
		        for( i=0;i<data.length;i++){
		        	adddetal(data[i].vehicle,data[i].week,data[i].item,parseFloat(data[i].value).toFixed(2), id3,type);
		        }
		        
			  },'json');
	}
	function finditem2(id1,id2,id3,type){
		$('#'+id3).empty();
		$('#'+id3).append(
				'<tr >'+
				'<td width="60"><div align="center">Vehicle</div>'+
				'</td>'+
				'<td width="60"><div align="center">Item</div>'+
				'</td>'+
				'<td width="715">&nbsp;</td>'+
			    '</tr>'
		);
	$.post("./itemdata",{
		value1: $('#'+id1).val(),
		value2:$('#'+id2).val(),
	},  function(data,status){
		        for( i=0;i<data.length;i++){
		        	adddetal2(data[i].vehicle,data[i].item,parseFloat(data[i].value).toFixed(2), id3,type);
		        }
		        
			  },'json');
	}
	function findweek(id1,id2,id3,type){
		$('#'+id3).empty();
		$('#'+id3).append(
				'<tr >'+
				'<td width="60"><div align="center">Vehicle</div>'+
				'</td>'+
				'  <td width="90"><div align="center">Week</div>'+
				'</td>'+
				'<td width="715">&nbsp;</td>'+
			    '</tr>'
		);
	$.post("./weekdata",{
		value1: $('#'+id1).val(),
		value2:$('#'+id2).val(),
	},  function(data,status){
		        for( i=0;i<data.length;i++){
		        	addweek(data[i].vehicle,data[i].week,parseFloat(data[i].value).toFixed(2), id3,type);
		        }
			  },'json');
	}
/*********添加详细配置数据*************/
  function adddetal(Vehicle, Week, Item, value, id,type) {
	$('#' + id).append(
					'<tr>'
					+'<td width="60" ><div align="center">'+ Vehicle+'</div></td>'
					+'<td width="90"><div align="center">'+ Week+'</div></td>'
					+'<td width="60"><div align="center">'+ Item+ '</div></td>'
					+'<td width="715">'
					+'<input name="low-ad-'+Vehicle+'-'+Week+'-'+Item+'" type="text" value="0.85"  style="width:50px;"onChange="savedata(this,\''+ type+"1" +'\',\''+Vehicle+'\',\''+Week+'\',\''+Item+'\')">'
					+'*<input name="total-plan-'+Vehicle+'-'+Week+'-'+Item+'"  type="text"  style="width:100px;" readonly="true"value="'+value+'">'
					+' ≤total plan ad ≤<input name="max-ad-'+Vehicle+'-'+Week+'-'+Item+'" type="text" value="1.45"  style="width:50px;"onChange="savedata(this,\''+ type+"2" +'\',\''+Vehicle+'\',\''+Week+'\',\''+Item+'\')">'
				    +'*<input name="total-plan-'+Vehicle+'-'+Week+'-'+Item+'"  type="text"  style="width:100px;" readonly="true" value="'+value+'">'
				    + '(current total plan ad)</td></tr >');
}
  function addweek(Vehicle, Week,value, id,type) {
	$('#' + id).append(
					'<tr>'
					+'<td width="60" ><div align="center">'+ Vehicle+'</div></td>'
					+'<td width="90"><div align="center">'+ Week+'</div></td>'
					+'<td width="715">'
					+'<input name="low-ad-'+Vehicle+'-'+Week+'" type="text" value="0.85"  style="width:50px;"onChange="savedata(this,\''+ type+"1" +'\',\''+Vehicle+'\',\''+Week+'\',\'all\')">'
					+'*<input name="total-plan-'+Vehicle+'-'+Week+'"  type="text"  style="width:100px;" readonly="true"value="'+value+'">'
					+' ≤total plan ad ≤<input name="max-ad-'+Vehicle+'-'+Week+'" type="text" value="1.45"  style="width:50px;"onChange="savedata(this,\''+ type+"2" +'\',\''+Vehicle+'\',\''+Week+'\',\'all\')">'
				    +'*<input name="total-plan-'+Vehicle+'-'+Week+'"  type="text"  style="width:100px;" readonly="true" value="'+value+'">'
				    + '(current total plan ad)</td></tr >');
}
function adddetal2(Vehicle,item, value, id,type) {
	$('#' + id).append(
			'<tr>'
			+'<td width="60" ><div align="center">'+ Vehicle+'</div></td>'
			+'<td width="90"><div align="center">'+ item+'</div></td>'
			+'<td width="715">'
			+'<input name="low-ad-'+Vehicle+'" type="text" value="0.85"  style="width:50px;"onChange="savedata(this,\''+ type+"1" +'\',\''+Vehicle+'\',\'all\',\''+item+'\')">'
			+'*<input name="total-plan-'+Vehicle+'"  type="text"  style="width:100px;" readonly="true"value="'+value+'">'
			+' ≤total plan ad ≤<input name="max-ad-'+Vehicle+'" type="text" value="1.45"  style="width:50px;"onChange="savedata(this,\''+ type+"2" +'\',\''+Vehicle+'\',\'all\',\''+item+'\')">'
		    +'*<input name="total-plan-'+Vehicle+'"  type="text"  style="width:100px;" readonly="true" value="'+value+'">'
		    + '(current total plan ad)</td></tr >');
}
  /*********展开控件*************/
	function openShutManager(oSourceObj, oTargetObj, shutAble, oOpenTip,
			oShutTip) {
		var sourceObj = typeof oSourceObj == "string" ? document
				.getElementById(oSourceObj) : oSourceObj;
		var targetObj = typeof oTargetObj == "string" ? document
				.getElementById(oTargetObj) : oTargetObj;
		var openTip = oOpenTip || "";
		var shutTip = oShutTip || "";
		if (targetObj.style.display != "none") {
			if (shutAble)
				return;
			
			targetObj.style.display = "none";
			if (openTip && shutTip) {
				sourceObj.src = shutTip;
			}
		} else {
			targetObj.style.display = "block";
			if (openTip && shutTip) {
				sourceObj.src = openTip;
			}
		}
	}
  /***************发送数据到后台*******************/
  function savedata(dom,type,vehicle,week,item){
	  $.post("./savedata",{
		  runname:'<%=name%>',
		  type:type,
		  vehicle:vehicle,
		  week:week,
		  item:item,
		  value:dom.value
	  },function(data,status){
		  
	  },'json');
  }
  function saveopt(){
	  $.messager.confirm('Confirm','Are you sure you want to submit you set and begin otpima?',function(r){  
		    if (r){  
		    	$('#maintable').tabs('close', "load Configuration")
		    }  
		}); 
		}
		$.post("./middata", "", function(data, status) {
			$("input[name='tot-ad-cost']").val(
					parseFloat(data.totalcost).toFixed(2));
			$("input[name='total-plan-broad']").val(
					parseFloat(data.broad).toFixed(2));
			$("input[name='total-plan-print']").val(
					parseFloat(data.print).toFixed(2));
			for (i = 0; i < data.printlist.length; i++) {
				adddetal2(data.printlist[i].vehicle, parseFloat(
						data.printlist[i].value).toFixed(2), 'print-tag');
			}
			for (i = 0; i < data.broadlist.length; i++) {
				adddetal2(data.broadlist[i].vehicle, parseFloat(
						data.broadlist[i].value).toFixed(2), 'broad-tag');
			}
		}, 'json');
	</script>
	</div>
</body>
</html>
