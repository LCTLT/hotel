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
<title>乐游旅游</title>
<link rel="shortcut icon" type="image/x-icon"
	href="http://www.loyoyo.com/favicon.ico?timestamp=2015090216">
<link rel="stylesheet" type="text/css"
	href="member_files/hygl.css?timestamp=2015090216">
	<!-- 您的密钥 -->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=SsYbaysf6xEvBGFpPeGD6Xn12uhXfqpO"></script>
	
	<link rel="stylesheet" type="text/css" href="css/member.css">
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#save").click(function() {
								if ($.trim($("#ymm").val()) == "") {
									parent.toast("请输入原密码");
								} else if ($.trim($("#xmm").val()) == "") {
									parent.toast("请输入新密码");
								} else if ($.trim($("#xmm").val()) == $.trim($(
										"#ymm").val())) {
									parent.toast("新密码与原密码不能相同");
								} else if (!/^[a-zA-Z0-9\~\`\!\@#$\%\^\&\*\(\)\_\-\+\=\{\[\}\]\|\\\:\;\"\'\<\,\>\.\?\/]{6,32}$/
										.test($.trim($("#ymm").val()))) {
									parent.toast("原密码要求至少6位的数字或字母");
								} else if (!(/^[a-zA-Z0-9\~\`\!\@#$\%\^\&\*\(\)\_\-\+\=\{\[\}\]\|\\\:\;\"\'\<\,\>\.\?\/]{6,32}$/
										.test($("#xmm").val()))) {
									parent.toast('新密码要求至少6位的数字或字母');
								} else if ($("#xmm").val() != $("#qrmm").val()) {
									parent.toast("新密码和确认新密码不一致");
								} else {
									$.ajax({
										type : "POST",
										url : "modifyPwd",
										data : $("#form").serialize(),
										dataType : "text",
										async : false,//将异步同步化
										success : function(data) {
											if (data == 1) {
												parent.toast("已成功更新密码");
												location.reload();
											} else if (data == 2) {
												parent.toast("操作有误，导致无法更新密码");
												parent.toast(result.value);
											}else if(data == 3){
												parent.toast("原密码有错误");
												$("#ymm").focus();
												$("#ymm").val("");
											}
										},
										error : function() {
											parent.toast("1系统繁忙，请稍后重试1");
										}
									});
								}
						});
	});
</script>
</head>
<body>
	<div class="content-right">
		<ul class="wdsc">
			<li class="current" style="cursor: default;">修改密码</li>
			<li
				style="cursor: default; width: 845px; border-bottom: 2px solid #e1e1e1;"></li>
		</ul>
		<div class="xgmm">
			<form id="form">
			<input type="hidden" name="phoneT" value="${user.phone}">
				<ul>
					<li><div>原密码</div> <input id="ymm" name="ymm" type="password"
						placeholder="原密码" maxlength="100"></li>
					<li><div>新密码</div> <input id="xmm" name="xmm" type="password"
						placeholder="设置新密码" maxlength="100"></li>
					<li><div>确认新密码</div> <input id="qrmm" name="qrmm"
						type="password" placeholder="确认新密码" maxlength="100"></li>
					<li><span id="save">保存</span></li>
				</ul>
			</form>
		</div>
	</div>


	<script type="text/javascript">
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
</body>
</html>