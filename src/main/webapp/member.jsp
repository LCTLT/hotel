<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="member_files/share.css">
<script type="text/javascript" src="member_files/jquery.js"></script>
<script type="text/javascript" src="member_files/common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>乐游旅游</title>
<link rel="shortcut icon" type="image/x-icon"
	href="http://www.loyoyo.com/xtfsq/themes/default/ppgl/loyoyo.com/favicon.ico?timestamp=2015090216">
<link rel="stylesheet" type="text/css" href="member_files/hygl.css">

<link rel="stylesheet" type="text/css" href="css/member.css">
<script type="text/javascript" src="js/member.js"></script>
<!-- 您的密钥 -->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=SsYbaysf6xEvBGFpPeGD6Xn12uhXfqpO"></script>
<style type="text/css">
	.tc{ min-height:0rem;width:20rem; padding: 10px; float:right; background:#FFF; border-radius:4px; position:relative;}
	.tc em{position:absolute; background:#fff;  right:10px; top:12px; height:14px; width:14px; display:inline-block; font-style:normal; cursor:pointer; background: url(/xtfsq/themes/images/cancle.png) no-repeat;}
	.tc p{width:90%;/* position:absolute;left:50%;margin-left: -40%; */margin:.5rem auto;font-size:16px; text-align:center;}
	 
	/*弹出框*/
	.tck{width:70%;width:280px; height:auto;z-index:9999; position:fixed; left:50%; top:50%; margin-left:-140px; margin-top:-100px; display:none; border-radius:5px; background:#fff; border:1px solid #fff; color:#333; padding:0px;}
	.tck .row{border-top:1px solid #eee;width:100%;margin:0px;background:#fff;padding:3px 0px 3px;}
	.tck .row div{text-align:center;line-height:30px;cursor: pointer;}
	.tck .row div:first-child{ border-right:1px solid #eee;}
	.tck span{ padding:15px; display:inline-block;}
	.col-xs-6 {width: 50%;float: left;}
</style>

</head>
<body style="margin: 0; padding: 0;">
	<!-- 嵌入头部 -->
	<%@ include file="common/hread.jsp"%>
	<!-- 嵌入页面导航 -->
	<%@ include file="common/left.jsp"%>

	<div class="content">
		<div class="content-in">
			<div class="content-left">
				<h4>会员中心</h4>
				<ul>
					<li class="current"><a
						href="javascript:openHycenter('memberMyOrder.jsp');">我的订单</a></li>
					<li id="wdsc"><a
						href="javascript:openHycenter('memberMyCollection.jsp');">我的收藏</a>
						</li>
					<li><a href="javascript:openHycenter('memberMydata.jsp');">我的资料</a></li>
					<li><a
						href="javascript:openHycenter('memeberMyPassword.jsp');">修改密码</a></li>
				</ul>
			</div>
	<div>
	</div>
		<iframe id="hycenter" name="hycenter"
			style="width: 953px; height: 620px; z-index: 2; visibility: inherit; margin-left: 20px; padding-bottom: 20px; background: rgb(255, 255, 255) none repeat scroll 0% 0%;"
			scrolling="no"
			frameborder="0">
		</iframe>
		</div>
	</div>

	<!-- 嵌入页面底部 -->
	<%@ include file="common/bottom.jsp"%>

	<div class="tczz" style="width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5); border-left: medium none; position: fixed; left: 0px; top: 0px; z-index: 9998; pointer-events: auto; display: none;"></div>
	<div class="tck" style="display: none;">
    	<span>确定取消收藏吗？</span>
    	<div id="tsk" class="row">
        <div id="qxsc" class="col-xs-6">否</div>
        <div id="qdsc">是</div>
    </div>
</div>
<form action='zfb' method='post' name='form1' style='display:none'>
	<input type='hidden' name='orderId' id='orderId' value="">
	<input type='hidden' name='orderNo' id='orderNo' value="">
	<input type='hidden' name='price' id='price' value="">
	<input type='hidden' name='hotelName' id='hotelName' value="">
</form>
<input type="hidden" value="${message}" id="message">
<script type="text/javascript">
		$(function() {
			var phone = $("#phoneM");
			var item = phone.text();
			phone.text(item.substring(0, 3) + "****" + item.substring(7, 11));
			
			var message = $("#message").val();
			if(message != null && message == "finl"){
				toast("支付失败");
			}
		});
		function sub(){
			document.form1.submit();
		}
		function loginout() {
			if (confirm("确认退出登录吗？")) {
				location.href = "loginout";
			}
		}
</script>
<input type="hidden" id="sjId" value="${sjId}">
</body>
</html>
