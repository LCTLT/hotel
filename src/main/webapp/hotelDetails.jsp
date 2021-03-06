<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collection" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>酒店详情</title>
<link rel="stylesheet" type="text/css" href="hotelDetails_files/share.css">
<script type="text/javascript" src="hotelDetails_files/jquery.js"></script>
<script type="text/javascript" src="hotelDetails_files/common.js"></script>
<link rel="stylesheet" type="text/css" href="hotelDetails_files/cpglview.css">
<script type="text/javascript" src="hotelDetails_files/calendar.js"></script>
<script type="text/javascript" src="hotelDetails_files/jquery-qrcode.js"></script>
<link rel="stylesheet" type="text/css" href="css/hotelDetails.css">
<script src="hotelDetails_files/share.js"></script>
<link rel="stylesheet" href="hotelDetails_files/share_style0_16.css">
<!-- 引入js -->
<script type="text/javascript" src="js/hotelDetails.js"></script>
<!-- 您的密钥 -->
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=SsYbaysf6xEvBGFpPeGD6Xn12uhXfqpO"></script>
<!-- 外部地图引入 -->
<link
	href="http://api.map.baidu.com/library/TrafficControl/1.4/src/TrafficControl_min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="http://api.map.baidu.com/library/TrafficControl/1.4/src/TrafficControl_min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
<link rel="stylesheet"
	href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
	<!-- 日期控件 -->
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<!-- 嵌入头部 -->
	<%@ include file="common/hread.jsp"%>
	<!-- 嵌入页面导航 -->
	<%@ include file="common/left.jsp"%>

	<!--页面主体开始-->
	<div class="cpxq">
		<div class="cpxq-in">
			<div class="mbx"></div>
			<div class="cpxx">
				<div class="cp-left">
					<div class="jdtp" style="overflow: hidden;">
						<div class="tp">
							<img style="width: 500px; height: 375px;" src="${hotel.fileUrl}"
								title=""
								onerror="">
							<span class="left_button"></span> <span class="right_button"></span>
						</div>
					</div>
					<div id="allmap" style="width: 500px; height: 300px;"></div>
				</div>
				<div class="cp-right">
					<p>${hotel.hotelName}</p>
					<span class="price">¥<b>${hotel.hotelPrice}</b><em>起</em></span>
					<div class="bdsharebuttonbox bdshare-button-style0-16"
						style="margin-top: 35px;" data-bd-bind="1546396910416">
						<div style="float: right; margin: 10px 10px 0 0;">
							<a href="javascript:void(0);" id="sc" class="sc"><span>收藏</span></a>
						</div>
					</div>
					<div class="cpxxxx">
						<ul>
							<li>详细地址： <i>${hotel.hotelAddress}</i>
							</li>
						</ul>
					</div>
					<form id="form">
						<!-- 获取收藏信息（是否存在收藏表中） -->
						<input type="hidden" id="getConById" value="${getConById}">
						<!-- 获取酒店id值（用于收藏或取消收藏指定的酒店） -->
						<input type="hidden" id="hotelIds" name="hotelId"
							value="${hotel.hotelId}">
						<!-- 用于获取session用户id -->
						<input type="hidden" id="conuserids" name="userId"
							value="${user.id}">
						<!-- 地址 -->
						<input type="hidden" id="hotelAddress"
							value="${hotel.hotelAddress}">
						<!-- 酒店名称 -->
						<input type="hidden" id="hotelNames" value="${hotel.hotelName}">
						<!-- 电话 -->
						<input type="hidden" id="hotelPhones" value="${hotel.hotelphone}">
						<!-- 简介 -->
						<input type="hidden" id="hotelIntros" value="${hotel.hotelIntro}">
						<!-- 价格 -->
						<input type="hidden" id="prices" name="payAmount" value="0">
						<div class="txxx">
							<h4>订单信息</h4>
							<ul>
								<li><span style="letter-spacing: 6px;">出发地:</span>
									<input type="text" value="${sessionScope.address}" id="cfd" name="place">
								</li>
								<li><span>入住日期：</span> <input name="checkInDates"
									id="dateOpen" type="text"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm' ,minDate:'%y-%M-{%d}'});"
									placeholder="不能小于当前日期" readonly/></li>
								<li><span>退房日期：</span> <input name="checkOutDates"
									id="dateExit" type="text"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'dateOpen\',{d:1})}'});"
									placeholder="不能小于入住日期" readonly/></li>
								<li><span>房&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</span>
									<select name="houseId" id="houseList">
										<option value="0" selected>请选择</option>
										<c:forEach var="house" items="${houseList}">
											<option value="${house.houseId}">${house.houseType}</option>
										</c:forEach>
								</select></li>
								<li><span>退订服务：</span>无</li>
								<li><span>价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：</span><label
									id="crjg">0</label></li>
								<li><span>剩余库存：</span><label id="sykc_label"></label></li>
								<li><span>订购数量：</span>
								<select id="crrs" name="houseCount" style="width: 70px; vertical-align: middle;">
								<c:forEach begin="1" end="10" var="i">
										<option value="${i}" <c:if test="${i eq 1}">selected</c:if>><c:out value="${i}"></c:out></option>
								</c:forEach>
								</select>限购10间，如有需要,请提前电话预定
							</ul>
						</div>
						<div class="lxxx">
							<h4>联系信息</h4>
							<ul>
								<li>手机：<input type="text" class="texts " id="ssj" name="sj"
									value="${sessionScope.user.phone}" maxlength="11" readonly>
								</li>
							</ul>
						</div>
						<div class="yd">
							支付方式 <select name="payType" id="zflx" class="default twins"
								style="margin-right: 5px;">
								<option value="0" selected="selected">请选择</option>
								<option value="1">支付宝</option>
								<option value="2">微信</option>
							</select> 合计：<span>¥<b id="totalPrice">0</b></span><a href="javascript:;"
								class="ydan" id="submitOrder"
								style="font-size: 16px; color: #fff;"> 立即预订 </a>
							<div class="dbox" style="display: none;">
								<p class="box tsy" style="padding-top: 2px; font-size: 15px"></p>
								<p style="font-weight: normal;" class="box djs"></p>
							</div>
						</div>
						<input type="hidden" id="zje" name="zje" value="0.00"> <input
							type="hidden" id="blkh" name="blkh" value="0"> <input
							type="hidden" id="cpid" name="cpid"
							value="b210ea079b7b4f4a9e2632b04426fb5c"> <input
							type="hidden" id="cpbh" name="cpbh" value="CPCS18102510274376">
						<input type="hidden" id="htrq" name="htrq" value=""> <input
							type="hidden" id="zdqx" name="zdqx" value="0"> <input
							type="hidden" id="gysid" name="gysid"
							value="b4213d02b4134a3d8e49dce57e453617"> <input
							type="hidden" id="sfsscp" name="sfsscp" value="02"> <input
							type="hidden" id="yd_random" name="yd_random"
							value="f237433e339c4beeadc3a1056e0bbdaa">
					</form>
				</div>

			</div>
			<div class="cpgk">
				<div id="yccpdh" style="display: none; height: 45px;"></div>
				<div class="cpdh">
					<ul>
						<li class="cpmd current"><a href="javascript:;" onclick="">酒店详情</a></li>
						<li class="xxxc"><a href="javascript:;">详细信息</a></li>
					</ul>
				</div>
				<div class="one">
					<div id="cpmd">
						<h3>酒店详情</h3>
						<p style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;${hotel.hotelIntro}</p>
					</div>
				</div>
				<div id="xxxc">
					<h3>房间详细信息</h3>
					<div class="dtxc">
						<div class="jt-yc-zs-jd" id="houseDet"></div>
						<div class="dtjtxc">
							<p style="font-size: 14px;">温馨提示： 到达后与酒店前台人员办理入住。</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 验证码登录页面开始 -->
		<div class="zz" id="zz"
			style="width: 100%; height: 100%; background-color: #000; background: rgba(0, 0, 0, 0.5); filter: alpha(opacity = 50); /*IE8支持*/ position: fixed; left: 0; top: 0; /*  filter:alpha(Opacity=20);  */ z-index: 9998; display: none;"></div>
		<div class="hy" id="hy"
			style="position: fixed; left: 50%; top: 50%; margin-left: -250px; margin-top: -125px; background: #fff; z-index: 9999; display: none;">
			<h3>确定提交</h3>
			<em id="cancle"><img src="images/hotelx.jpg" alt="关闭" /></em>
			<div style="margin-bottom: 1rem;">
				<div class="yzmdl" id="yzmdl" style="display: none;">
					<ul>
						<li>手机号：<input id="phone" type="text" name="sjh"
							style="width: 255px;" placeholder="手机号" maxlength="11"></li>
						<li>密&nbsp;&nbsp;&nbsp;&nbsp;码：<input id="pwd"
							type="password" name="sjh" style="width: 255px;" placeholder="密码"
							maxlength="11" onkeydown="if(event.keyCode == 13){yzmdl()}"></li>
						<li><button class="three" onclick="yzmdl();"
								style="width: 260px; margin-top: 15px;">确&nbsp;&nbsp;&nbsp;定</button></li>
					</ul>
					<input type="reset" name="reset" style="display: none;">
				</div>
			</div>
		</div>
	</div>
	<!-- 验证码登录页面结束 -->
	<!-- 嵌入页面底部 -->
	<%@ include file="common/bottom.jsp"%>

	<div class="tczz"
		style="width: 100%; height: 100%; background-color: #000; background-color: rgba(0, 0, 0, 0.5); filter: alpha(opacity = 50); BORDER-LEFT: medium none; /*IE8支持*/ position: fixed; left: 0; top: 0; /* filter:alpha(Opacity=20);  */ z-index: 9998; display: none; pointer-events: auto;"></div>
	<div class="tck">
		<span></span>
		<div id="tsk" class="row">
			<div id="qxsc" class="col-xs-6">否</div>
			<div id="qdsc">是</div>
		</div>
	</div>
</body>

<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
setTimeout(function(){
	var hotelAddress = $("#hotelAddress").val();
	var hotelNames = $("#hotelNames").val();

	var hotelPhones = $("#hotelPhones").val();
	//hotelPhones.text(hotelphonee.substring(0,3)+"-"+hotelphonee.substring(4,8));

	var hotelIntros = $("#hotelIntros").val();
	//创建地图实例 
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(113.03770447, 25.78226398);
	//开启鼠标滚轮缩放
	map.enableScrollWheelZoom(true);
	map.centerAndZoom(point, 20);
	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();
	// 将地址解析结果显示在地图上,并调整地图视野
	myGeo.getPoint(
					hotelAddress,
					function(point) {
						if (point) {
							map.centerAndZoom(point, 20);
							map.addOverlay(new BMap.Marker(point));

							var poi = new BMap.Point(point.lng, point.lat);
							map.centerAndZoom(poi, 20);
							map.enableScrollWheelZoom();

							var content = '<div style="margin:0;line-height:20px;padding:2px;">'
									+ '地址：'
									+ hotelAddress
									+ '<br/>电话：'
									+ (hotelPhones.substring(0, 4) + '-' + hotelPhones
											.substring(4, 8))
									+ '<br/>简介：'
									+ hotelIntros + '' + '</div>';

							//创建检索信息窗口对象
							var searchInfoWindow = null;
							searchInfoWindow = new BMapLib.SearchInfoWindow(
									map, content, {
										title : hotelNames, //标题
										width : 100, //宽度
										height : 95, //高度
										panel : "panel", //检索结果面板
										enableAutoPan : true, //自动平移
										searchTypes : [ BMAPLIB_TAB_SEARCH, //周边检索
										BMAPLIB_TAB_TO_HERE, //到这里去
										BMAPLIB_TAB_FROM_HERE //从这里出发
										]
									});
							var marker = new BMap.Marker(poi); //创建marker对象
							marker.addEventListener("click", function(e) {
								searchInfoWindow.open(marker);
							})
							searchInfoWindow.open(marker);
							map.addOverlay(marker); //在地图中添加marker
						} else {
							toast("您选择地址没有解析到结果!");
						}
					});
},500);
</script>

<script type="text/javascript">
	$(function() {
		//获得价格容器
		var crjg = $("#crjg");
		$("#houseList").change(function() {
			if($("#houseList").val() == 0){
				$("#houseDet").html("");
				$("#sykc_label").html("");
			}
			var hotelIds = $("#hotelIds").val();
			var houseId = $(this).val();
			var dateOpen = $("#dateOpen").val();
			var dateExit = $("#dateExit").val();
			if(dateOpen == "" || dateExit == ""){
					toast("请选择入住日期和退房日期");
					return;
							}
							if (houseId == 0) {
								$("#crjg").html("0");
								$("#totalPrice").html("0");
								return;
							}
							//请求库存
							$.ajaxSettings.async = false;
							$.post("selectRawstock", {
								houseId : houseId,
								hotelId : hotelIds,
								dateOpen : dateOpen,
								dateExit : dateExit
							}, function(data) {
									$("#sykc_label").html(data);
							});
							$.ajaxSettings.async = false;
							$.post("houseDetails",{houseId : houseId},function(result) {
												result = JSON.parse(result);
												//获取房型容器
												var houseDet = $("#houseDet");
												//动态加载价格
												crjg.html(result.housePrice);
												houseDet.html("");
												if (result.isHavingBreakfast == "0") {
													result.isHavingBreakfast = "早餐(无)";
												} else {
													result.isHavingBreakfast = "早餐(有)";
												}
												var option = '<span><i>房型设施：</i>'
														+ result.contentOne
														+ '</span><br/>'
														+ '<span><i>媒体科技：</i>'
														+ result.contentTow
														+ '</span><br/>'
														+ '<span><i>早&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐：</i>'
														+ result.isHavingBreakfast
														+ '</span><br/>'
														+ '<span><i>浴&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;室：</i>'
														+ result.contentFour
														+ '</span><br/>'
														+ '<span><i>室外景观：</i>'
														+ result.contentFive
														+ '</span>';
												houseDet.html(option);
												sumPrice();
											});
						});

		//房间数量选中事件
		$("#crrs").change(function() {
			sumPrice();
		});

	});

	function sumPrice() {
		//总计价格
		var totalPrice = $("#totalPrice");
		//房间订购数量
		var count = $("#crrs").val();
		//单间价格
		var crjg = $("#crjg").text();
		//天数
		var dateOpen = $("#dateOpen").val();
		var dateExit = $("#dateExit").val();
		var day = daysBetween(dateOpen,dateExit);
		//计算总价格
		var sum = 0;
		if (crjg == "0") {
			sum = parseInt(crjg);
		}
		if (count == "0") {
			sum = parseInt(count);
		} else {
			sum = parseInt(count) * parseInt(crjg) * parseInt(day);
		}
		totalPrice.text(sum);
		$("#prices").val(sum);
	}
	function daysBetween(sDate1,sDate2){
		//Date.parse() 解析一个日期时间字符串，并返回1970/1/1 午夜距离该日期时间的毫秒数
		var time1 = Date.parse(new Date(sDate1));
		var time2 = Date.parse(new Date(sDate2));
		var nDays = Math.abs(parseInt((time2 - time1)/1000/3600/24));
		return nDays;
	};
</script>
<script type="text/javascript">
	// 百度地图API功能
	var ctrl = new BMapLib.TrafficControl({
		showPanel : true
	//是否显示路况提示面板
	});
	map.addControl(ctrl);
	ctrl.setAnchor(BMAP_ANCHOR_BOTTOM_RIGHT);
</script>

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		var getConById = $("#getConById").val();
		if (getConById == 1) {
			$("#sc").removeClass();
			$("#sc").addClass("ysc");
		} else {
			$("#sc").removeClass();
			$("#sc").addClass("sc");
		}
		
		 $("#sc").click(function(){
				var hotelIds = $("#hotelIds").val();
				var conuserId = $("#conuserids").val();
				var phone = $("#ssj").val();
			 if(phone==""){
				 $(".tczz,.tck",parent.document).show();
					$(".tck span",parent.document).html("您还未登录，是否去登录？");
					$("#qdsc", parent.document).unbind("click").bind("click",function() {
								location.href="login.jsp?callback="+location.href;
							});
					
					$("#qxsc", parent.document).bind("click", function() {
						$("#qxsc", parent.document).unbind("click");
						$(".tczz,.tck", parent.document).hide();
					});
			 }else{
				if($(this).attr("class") == "sc"){
					$(this).removeClass();
					$(this).addClass("ysc");
					$.get("insertCon",{conuserid:conuserId,hotelid:hotelIds},function(data){
						
					})
				}else{
					$(this).removeClass();
					$(this).addClass("sc");
					$.get("deleteCon",{scid:hotelIds},function(data){
						toast("取消收藏成功");
					});
				}
			 }
		});
	});
</script>
</html>