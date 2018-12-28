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
<link rel="shortcut icon" type="image/x-icon"
	href="http://www.loyoyo.com/favicon.ico?timestamp=2015090216">
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
			<li class="current" onclick="liCurrent(this)">全部</li>
			<li onclick="liCurrent(this)">待支付</li>
			<li onclick="liCurrent(this)">已支付</li>
			<li onclick="liCurrent(this)">已超时</li>
			<li onclick="liCurrent(this)">已取消</li>
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
			<ul class="ddxx">
				<li><span style="background: #f7f7f7;">订单编号：DDCS18122810432119</span>
					<div>
						<a
							href="#"
							target="_blank"> <img
							src="#"
							onerror="#"></a>
						<p>
							<a
								href="#"
								style="text-decoration: none" target="_blank">坐火车泡酒店---曼巴6天5晚游</a>
						</p>
						<span>2019-01-31</span><span class="orange">¥12000.00</span><span
							class="orange">待支付</span><span class="sj">2018-12-28<br>14:08:06
						</span><span class="ddxx-box" style="margin-left: 0"><button
								ddid="75373f0ecef14469a0995a04d20b4e06"
								appid="wxbdc6d42e39fb5642"
								ssfb="e756a3d11075413cac20f1a9675dcca6" class="zf">立即支付</button>
							<a
							href="/module/ddgl/page.do?page=view&amp;ddid=75373f0ecef14469a0995a04d20b4e06"
							style="margin-left: 13px; height: 28px; line-height: 30px; display: inline-block;"
							target="_blank">订单详情</a></span>
					</div></li>
				<li><span style="background: #f7f7f7;">订单编号：DDCS18112710416163</span>
					<div>
						<a
							href="/module/cpgl/view.do?cpid=b210ea079b7b4f4a9e2632b04426fb5c"
							target="_blank"> <img
							src="http://sc-fx-fxtfsq.cloudtravel.net/fxtfsq/xltt/2018/10/25/9fd1ee780a7c4d539322c7ea1d79c0a4.jpg"
							onerror="this.src='/xtfsq/themes/images/default.jpg?cachesj=2015090217'"></a>
						<p>
							<a
								href="/module/cpgl/view.do?cpid=b210ea079b7b4f4a9e2632b04426fb5c"
								style="text-decoration: none" target="_blank">五星泰国海景沙美版6天5晚跟团游，1晚沙美岛豪华度假酒店小别墅+2晚芭提雅国五海景房布莱顿酒店海景房+1晚曼谷皇家喜来登酒店180°落地河景房+1晚曼谷高人气绿宝石酒店</a>
						</p>
						<span>2018-11-28</span><span class="orange">¥3990.00</span><span
							class="orange">已取消</span><span class="sj">2018-11-27<br>11:27:39
						</span><span class="ddxx-box" style="margin-left: 0"><button
								onclick="bllk('700c1c5090324592abd04cac7f4f80fa','d8b27d38407f48d097e2bfac2b317970');">补录旅客</button>
							<a
							href="/module/ddgl/page.do?page=view&amp;ddid=d8b27d38407f48d097e2bfac2b317970"
							style="margin-left: 13px; height: 28px; line-height: 30px; display: inline-block;"
							target="_blank">订单详情</a></span>
					</div></li>
			</ul>
		</div>
	</div>
	<img id="img-buffer" style="display: none;"
		src="/xtfsq/themes/images/weixin.png?timestamp=2015090216">
	<script type="text/javascript" src="member_files/jquery.js"></script>
	<script type="text/javascript">
	//选择不同的订单状态
	function liCurrent(obj){
		$(obj).parent().children("li").removeClass();
		//添加样式
		$(obj).addClass("current");
		//访问数据库获得订单信息
		
	}
</script>
</body>
</html>