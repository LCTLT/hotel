<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="member_files/share.css?cachesj=2015090217">
<script type="text/javascript"
	src="member_files/common.js?cachesj=2015090217"></script>
<title>乐游旅游</title>
<link rel="stylesheet" type="text/css"
	href="member_files/pagination.css?cachesj=2015090217">
<link rel="stylesheet" type="text/css"
	href="member_files/hygl.css?cachesj=2015090217">
	<link rel="stylesheet" type="text/css" href="css/member.css">
	<!-- 您的密钥 -->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=SsYbaysf6xEvBGFpPeGD6Xn12uhXfqpO"></script>
	
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	function loadScList() {
		var phone = $("#phone").val();
			$.ajax({
				type : "POST",
				url : "getInfoCollection",
				data : {phone:phone},
				dataType : "json",
				async : false,
				success : function(result) {
						var wdsclb = $(".wdsclb");
						var option = '';
						for (var i = 0; i < result.length; i++) {
							var times = result[i].hotelByConTime;
							option += '<li><div><a href="/hotels/hotelDetails?hotelId='+result[i].hotelById+'" target="_blank"> <img src="'+result[i].fileUrl+'"'
							+' onerror="this.src="/hotels/hotelDetails?hotelId='+result[i].hotelById+'"></a><a class="hygl_scmc"'
								+' href="/hotels/hotelDetails?hotelId='+result[i].hotelById+'" target=" _blank" style="border: 1px solid while; width: 260px;">'+result[i].hotelName+'</a><span>收藏时间：'+times.substring(0,19)+'</span><span'
								+' class="wdsc_qx" onclick="del('+result[i].hotelById+',this);"'
								+' style="display: none;">取消收藏</span></div></li>';
						}
						wdsclb.html(option);
						var pageHeight = 90;
						if(result.length <= 3){
							height = 380;
						}else{
							var height = parseInt(result.length)*160+pageHeight;
						}
						window.parent.document.getElementById("hycenter").style.height=height+"px";
				},
				error : function() {
					parent.toast("系统繁忙，请稍后重试");
				}
			});
	}

	function del(scid,dels) {
			 $(".tczz,.tck",parent.document).show();
			$(".tck span",parent.document).html("确定取消收藏吗？");
			$("#qdsc", parent.document).unbind("click").bind(
					"click",function() {
						$.ajax({
							type : "POST",
							url : "deleteCon",
							data : {
								scid : scid
							},
							dataType : "json",
							async : false,
							success : function(result) {
								$("#qdsc", parent.document).unbind("click");
								$(".tczz,.tck", parent.document).hide();
								parent.toast("取消成功");
								$(dels).parent().parent().remove();
							},
							error : function(result) {
								parent.toast("系统繁忙，请稍后重试");
							}
						});
					});
			$("#qxsc", parent.document).bind("click", function() {
				$("#qxsc", parent.document).unbind("click");
				$(".tczz,.tck", parent.document).hide();
			});
			
		
	}

	$(function() {
		loadScList();
		$(".wdsclb li").mouseenter(function(e) {
			$(".wdsc_qx", this).show();
		});
		$(".wdsclb li").mouseleave(function(e) {
			$(".wdsc_qx", this).hide();
		});
	});
</script>
</head>
<body>
	<div class="content-right">
	<input type="hidden" id="phone" value="${user.phone}">
		<ul class="wdsc">
			<li class="current">我的收藏</li>
			<li
				style="cursor: default; width: 845px; border-bottom: 2px solid #e1e1e1;"></li>
		</ul>
		<div>
			<ul class="wdsclb" style="padding-left: 27px;">
				
			</ul>
			<div class="M-box2" style="margin-top: 20px; margin-right: 27px;"></div>
		</div>
	</div>
	<div class="tczz" style="width: 100%;
	 height: 100%; background-color: rgba(0, 0, 0, 0.5);
	  border-left: medium none; position: fixed;
	   left: 0px; top: 0px; z-index: 9998; pointer-events: auto; display: none;"></div>
	   
	   <div class="tck" style="display: none;">
	    	<span>确定取消收藏吗？</span>
	    	<div id="tsk" class="row">
	        <div id="qxsc" class="col-xs-6">否</div>
	        <div id="qdsc">是</div>
	    	</div>
		</div>
	<div id="qb-sougou-search" style="display: none; opacity: 0;">
		<p>搜索</p>
		<p class="last-btn">复制</p>
		<iframe src=""></iframe>
	</div>
</body>
</html>