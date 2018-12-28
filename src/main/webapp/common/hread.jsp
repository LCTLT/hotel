<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$(function() {
	var phone = $("#phoneM");
	var item = phone.text();
	phone.text(item.substring(0, 3) + "****" + item.substring(7, 11));

});

function loginout() {
	if (confirm("确认退出登录吗？")) {
		location.href = "loginout";
	}
}
</script>
<!--页面顶部开始-->
<div class="headpiece">
	<div class="headpiece-in">
		<span class="left"> <c:if test="${empty user}">
				<a class="dl" href="login.jsp">请登录</a>
				<a href="register.jsp" class="zc">快速注册</a>
			</c:if> <c:if test="${not empty user}">
				您好，<a class="dl" id="phoneM" href="javaScript:void(0);">${user.phone}</a>
				<a href="javascript:loginout();" class="zc">退出</a>
			</c:if> <a class="hyzx" href="memberOrder">会员中心</a></span> <span class="right">
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
			<a href="index"><img src="hotelDetails_files/sylogo.png"
				width="204" height="110"></a>
		</div>
		<div class="search">
			<div class="cfd"></div>
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
						<input type="text" class="search_text" placeholder="酒店省份、酒店名称"
						id="kw" onkeydown="if(event.keyCode == 13){cpss();}" value="${cpsss}"> <a
						class="search_button" href="javascript:cpss();">搜索</a>
					</c:when>
					<c:otherwise>
						<input type="text" class="search_text" placeholder="酒店省份"
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
<!--页面头部结束-->