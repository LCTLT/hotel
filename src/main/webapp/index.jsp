<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="index_files/share.css">
<script type="text/javascript" src="index_files/jquery.js"></script>
<script type="text/javascript" src="index_files/common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="index_files/cpglindex.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=SsYbaysf6xEvBGFpPeGD6Xn12uhXfqpO"></script>
</head>
<body id="main">
	<!-- 嵌入头部 -->
	<%@ include file="common/hread.jsp"%>
	<!-- 嵌入页面导航 -->
	<%@ include file="common/left.jsp"%>
	<!--页面内容开始-->
	<div class="content">
		<div class="content-in">
			<div class="banner_box">
				<div class="banner">
					<ul>
					<c:forEach var="img" items="${images}">
						<li class="index"><a
							href="/hotels/hotelDetails?hotelId=${img.hotelId}"><img src="${img.fileUrl}"
								onerror="/xtfsq/themes/images/default.jpg?timestamp=2015090216'"
								width="770" height="460" /></a><span class="gg_img">广告</span></li>
					
					</c:forEach>
					</ul>
					<ol>
						<li class="current"></li>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
					</ol>
				</div>
				<div class="lydt_cptm">
					<div class="lydt">
						<span style="margin-top: 5px;">热门头条<a href="#">更多</a></span>
						<ul>
							<li><a href="#" target="_blank">资讯丨乐游商学院赋能培训模式启动，给你看得见的未来</a></li>
							<li><a href="#" target="_blank">湖北丨第三季度供应商开放共建大会暨乐游门店复盘大会</a></li>
							<li><a href="#" target="_blank">天津 |
									乐游旅游与民生银行达成合作，分期付团款，0手续费</a></li>
							<li><a href="#" target="_blank">四川 | “秋意浓
									松坪沟”乐游首届万人彩林节开幕，报名送豪礼</a></li>
							<li><a href="#" target="_blank">乐游包船喜悦号 |
									海上笑工场专治不开心！（内含双十一秒杀价）</a></li>
						</ul>
						<span style="padding: 3px 8px;">行业资讯<a href="#">更多</a></span>
						<ul>
							<li><a href="#" target="_blank">资讯 | 马来西亚:调整中国公民赴马签证政策
									提升效率</a></li>
							<li><a href="#" target="_blank">签证丨带娃出国游！儿童签证技能GET！</a></li>
							<li><a href="#" target="_blank">签证丨日本签证需要注意的地方？</a></li>
							<li><a href="#" target="_blank">签证丨美国签证面试，不会英文怎么办？</a></li>
							<li><a href="#" target="_blank">签证丨嫌弃中国护照不好用？老外办中国签证都快哭了</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="cp">
		<div class="cp-in">
			<div class="cp_zby">
				<div class="heading">
					<em class="zby"></em><span>热门推荐</span> <span class="gnyby">感受别样生活，精彩就在身边</span>
				</div>
				<div class="cpzs">
					<ul>
						<li class="dacp" style="margin-top: 0;">
							<div class="dacp_tp">
								<a
									href="/hotels/hotelDetails?hotelId=${hotel.hotelId}"
									target="_blank"><img src="${hotel.fileUrl }"
									onerror="this.src='/xtfsq/themes/images/default.jpg?timestamp=2015090216'"
									width="585"></a>
							</div>
							<div class="dacpmc">
								<a
									href="/hotels/hotelDetails?hotelId=${hotel.hotelId}"
									target="_blank"><p>${hotel.hotelName }</p></a> <i>¥<b>${hotel.hotelPrice}</b><em>起</em></i>
							</div>
						</li>
						<c:forEach items="${images}" var="img">
							<li style="margin-top: 0;">
								<div class="cptp">
									<a
										href="/hotels/hotelDetails?hotelId=${img.hotelId}"
										target="_blank"><img src="${img.fileUrl }"
										onerror="this.src='/xtfsq/themes/images/default.jpg?timestamp=2015090216'"
										width="284" height="212"></a>
								</div>
								<div class="cpmc">
									<a
										href="/hotels/hotelDetails?hotelId=${img.hotelId}"
										target="_blank"><p>${img.hotelName }</p></a>
								</div>
								<p class="cpjs">
									<i>¥<b>${img.hotelPrice}</b><em>起</em></i>
								</p>
							</li>
						</c:forEach>
					</ul>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
	<!--页面内容结束-->

	<!-- 嵌入页面底部 -->
	<%@ include file="common/bottom.jsp"%>
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
</body>




</html>