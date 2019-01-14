<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$(function() {
	var phone = $("#phoneM");
	var item = phone.text();
	phone.text(item.substring(0, 3) + "****" + item.substring(7, 11));

});

function sessionphone() {
		var sj = $("#sj").val();
		if (sj != "") {
			location.href = "memberOrder";
		} else {
			 $(".tczz,.tck",parent.document).show();
				$(".tck span",parent.document).html("您还未登录，是否去登录？");
				$("#qdsc", parent.document).unbind("click").bind("click",function() {
							location.href="login.jsp?callBack="+location.href;
						});
				
				$("#qxsc", parent.document).bind("click", function() {
					$("#qxsc", parent.document).unbind("click");
					$(".tczz,.tck", parent.document).hide();
				});
		}
	}
function loginHref(){
	location = "login.jsp?callback="+location.href;
}

function loginout() {
	$(".tczz,.tck",parent.document).show();
	$(".tck span",parent.document).html("确认退出登录，您将失去很多体验？");
	$("#qdsc", parent.document).unbind("click").bind(
			"click",function() {
				$.ajax({
					type:"POST",
					url:"loginout",
					dataType:"text",
					async:false,
					success:function(result){
						if(result == "1"){
							location = location.href;
						}
						
					},
					error:function(){toast("系统繁忙，请稍后重试");}
				});
			});
	
	$("#qxsc", parent.document).bind("click", function() {
		$("#qxsc", parent.document).unbind("click");
		$(".tczz,.tck", parent.document).hide();
	});
}
		
	
</script>
<!--页面顶部开始-->
<div class="headpiece">
	<div class="headpiece-in">
	<input type="hidden" id="sj" value="${user.phone}">
		<span class="left">
		 <c:if test="${empty user}">
				<a class="dl" href="javascript:loginHref();">请登录</a>
				<a href="register.jsp" class="zc">快速注册</a>
			</c:if> <c:if test="${not empty user}">
				您好，<a class="dl" id="phoneM" href="javaScript:void(0);">${user.phone}</a>
				<a href="javascript:loginout();" class="zc">退出</a>
			</c:if> <a class="hyzx" href="javascript:sessionphone();">会员中心</a></span> <span class="right">
			<a href="#" target="_blank">关于乐游</a> <a href="#" target="_blank">乐游模式</a>
			<a href="#" target="_blank">乐游动态</a> <a href="#" target="_blank">加入乐游</a>
			<a href="#" target="_blank">帮助中心</a>
		</span>
	</div>
</div>
<!--页面顶部结束-->
<!--页面头部开始-->
<div class="header">
	<div class="header-in">
		<div class="logo">
			<a href="index"><img src="hotelDetails_files/sylogo.png" width="204" height="110"></a>
		</div>
		
		<div class="search">
			<div class="cfd">
				<div id="allmapa"></div>
				<span id="allmaps" style="font-size: 16px;"><img src="static/images/mp28941887_1440383069404_3.gif" width="50px" height="50px"></span>
			</div>
		
			<div class="search-in">
				<div class="xl_search">
					<p>线路</p>
					<input type="hidden" id="search_xlqy" value=""> <input
						type="hidden" id="search_option" value=""> <span
						class="icon_arrow"
						style="background: url(/xtfsq/themes/images/header_jt.jpg) no-repeat;"></span>
				</div>
				<c:choose>
					<c:when test="${empty sign}">
						<input type="text" class="search_text" placeholder="酒店所在省份、酒店名称"
						id="kw" onkeydown="if(event.keyCode == 13){cpss();}" value="${cpsss}"> <a
						class="search_button" href="javascript:cpss();">搜索</a>
					</c:when>
					<c:otherwise>
						<input type="text" class="search_text" placeholder="酒店所在省份、酒店名称"
						id="kw" onkeydown="if(event.keyCode == 13){querylist('');}" value="${cpsss}"> <a
						class="search_button" href="javascript:querylist('')">搜索</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="ewm">
			<img src="hotelDetails_files/tel.png">
		</div>
	</div>
</div>
<input type="hidden" id="address" value="${sessionScope.address}">
<c:choose>
<c:when test="${empty sessionScope.address}">
<!--页面头部结束-->
<script type="text/javascript">
	var mapa = $("#allmaps");
	var address;
	// 百度地图API功能
	var map = new BMap.Map("allmapa");
	var point = new BMap.Point(113.03770447,25.78226398);

	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mks = new BMap.Marker(r.point);
			map.addOverlay(mks);
			map.panTo(r.point);
			address = r.address.city;
			mapa.html("您当前的位置：<br><span><b id='maps'>"+address+"</b></span>");
			//请求session保存
			$.ajax({
				type:"POST",
				url:"address",
				data:{address:address},
				dataType:"text",
				async:false,
				success:function(result){
				},
				error:function(){toast("系统繁忙，请稍后重试");}
			});
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true})
	//关于状态码
	//BMAP_STATUS_SUCCESS	检索成功。对应数值“0”。
	//BMAP_STATUS_CITY_LIST	城市列表。对应数值“1”。
	//BMAP_STATUS_UNKNOWN_LOCATION	位置结果未知。对应数值“2”。
	//BMAP_STATUS_UNKNOWN_ROUTE	导航结果未知。对应数值“3”。
	//BMAP_STATUS_INVALID_KEY	非法密钥。对应数值“4”。
	//BMAP_STATUS_INVALID_REQUEST	非法请求。对应数值“5”。
	//BMAP_STATUS_PERMISSION_DENIED	没有权限。对应数值“6”。(自 1.1 新增)
	//BMAP_STATUS_SERVICE_UNAVAILABLE	服务不可用。对应数值“7”。(自 1.1 新增)
	//BMAP_STATUS_TIMEOUT	超时。对应数值“8”。(自 1.1 新增)		 	
</script>
</c:when>
<c:otherwise>
<script>
	$(function(){
		if($("#address").val() != ""){
			$("#allmaps").html("您当前的位置：<br><span><b id='maps'>"+$("#address").val()+"</b></span>");
		}
	});
</script>
</c:otherwise>
</c:choose>