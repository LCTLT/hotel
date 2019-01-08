<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="member_files/share.css?timestamp=2015090216">
<script type="text/javascript"
	src="member_files/jquery.min.js?timestamp=2015090216"></script>
<script type="text/javascript"
	src="member_files/common.js?timestamp=2015090216"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>乐游旅游</title>
<link rel="stylesheet" type="text/css"
	href="member_files/hygl.css?timestamp=2015090216">
<link rel="stylesheet" type="text/css"
	href="member_files/pagination.css?timestamp=2015090216">
<script type="text/javascript"
	src="member_files/jquery-qrcode.min.js?timestamp=2015090216"></script>
</head>
<body>
	<div class="content-right" style="padding: 20px 30px 40px 30px">
		<ul class="ddzt">
			<li class="current" onclick="liCurrent(this)" value="-1">全部</li>
		</ul>
		<div>
			<ul class="ddxx-tou">
				<li>订单信息</li>
				<li>入住日期</li>
				<li>订单金额</li>
				<li>订单状态</li>
				<li>退房日期</li>
				<li class="ddxx-cz">操作</li>
			</ul>
			<ul class="ddxx" id="ddxx">
				
			</ul>
		</div>
	</div>
	<img id="img-buffer" style="display: none;"
		src="/xtfsq/themes/images/weixin.png?timestamp=2015090216">
	<script type="text/javascript" src="member_files/jquery.js"></script>
	<script type="text/javascript">
	$(function(){
		dicOrder();
		queryOrder(-1);
	});

	//选择不同的订单状态
	function liCurrent(obj){
		$(obj).parent().children("li").removeClass();
		//添加样式
		$(obj).addClass("current");
		//访问数据库获得订单信息
		queryOrder($(obj).attr("value"));
	}
	//加载订单字典
	function dicOrder(){
		$.getJSON("dicOrder",function(data){
			var ddzt = $(".ddzt");
			var option = '';
			for (var i = 0; i < data.length; i++) {
				option += '<li onclick="liCurrent(this)" value="'+data[i].dictCode+'">'+data[i].info+'</li>';
			}
			ddzt.append(option);
		});
	}
	//加载订单数据
	function queryOrder(orderStatus){
		$.getJSON("queryOrder",{status:orderStatus},function(data){
			var ddxx = $("#ddxx");
			var option = '';
			for (var i = 0; i < data.length; i++) {
				data[i].hotelName = data[i].hotelName.substring(0,5)+"...";
				option += '<li><span style="background: #f7f7f7;">订单编号：'+data[i].orderNo+'</span>'
				+'<div><a href="#" target="_blank"><img src="'+data[i].fileUrl+'" onerror="" style="text-decoration:none" target="_blank">'+data[i].hotelName+'</a></p>'
				+'<span style="margin-left:-70px;">'+data[i].checkInDates+'<br/>'+data[i].checkInTime+'</span><span class="orange">¥'+data[i].payAmount+'</span>'
				+'<span class="orange">'+data[i].info+'</span><span class="sj">'+data[i].checkOutDates+'<br/>'+data[i].checkOutTime+'</span>';
				if(data[i].orderStatus == 0){
					option +='<span class="ddxx-box" style="margin-left:0"><button class="zf" onclick="zfb(this,'+data[i].id+')">立即支付</button><a href="getOrderList?order='+data[i].orderNo+'" style="margin-left:12px;margin-top:10px;height:28px; line-height:30px;display: inline-block;" target="_blank">订单详情</a></span></div></li>';
				}else{
					option +='<a href="getOrderList?order='+data[i].orderNo+'" style="margin-left:55px;height:28px; line-height:65px;display: inline-block;" target="_blank">订单详情</a></span></div></li>';
				}
			}
			//计算页面高度
			var pageHeight = 90;
			var height = parseInt(data.length)*178+pageHeight;
			window.parent.document.getElementById("hycenter").style.height=height+"px";
			ddxx.html(option);
			
		});
	}
	//支付
	function zfb(obj,id){
		var $obj = $(obj);
		//订单号
		var orderNo = $obj.parent().parent().prev().text();
		var array = orderNo.split("：");
		//总金额
		var pricePram = $obj.parent().prev().prev().prev().text();
		var price = pricePram.substr(1);
		//酒店名称
		var hotelName = $obj.parent().parent().children("a:first").text();
		//访问支付接口,考虑安全性参数不直接显示 
		window.parent.document.getElementById("orderId").value=id;
		window.parent.document.getElementById("orderNo").value=array[1];
		window.parent.document.getElementById("price").value=price;
		window.parent.document.getElementById("hotelName").value=hotelName;
		window.parent.sub();  
	}
</script>
</body>
</html>