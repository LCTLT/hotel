<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="register_files/share.css">
<script type="text/javascript" src="register_files/jquery.js"></script>
<script type="text/javascript" src="register_files/common.js"></script>
<title>乐游旅游</title>
<style>
#dxyzm{
	position: absolute;
	margin-left: -14%;
	margin-top: -4px;
}

</style>
<link rel="stylesheet" type="text/css"
	href="register_files/hygllogin.css">
<script type="text/javascript">
	var handlerflag = true;
	var dxfsxz = false;
	function settime(time, fn) {
		var timer = setInterval(function() {
			if (!dxfsxz && time >= 0) {
				fn("重新获取(" + time + ")");
				--time;
			} else {
				clearInterval(timer);
				fn("获取验证码");
			}
		}, 1000);
	}
	function zc() {
		if (handlerflag) {
			handlerflag = false;
			if (!(/(^[1][3456789][0-9]{9}$)/.test($("#sjh").val()))) {
				$("#loginTip").removeClass("loginTipInfo").addClass(
						"loginTipWarn");
				$("#sjh").focus();
				$("#loginTip").html("手机格式不正确");
			} else if ($.trim($("#mm").val()) == "") {
				$("#loginTip").removeClass("loginTipInfo").addClass(
						"loginTipWarn");
				$("#mm").focus();
				$("#loginTip").html('请输入密码');
			} else if (!(/^[a-zA-Z0-9\~\`\!\@#$\%\^\&\*\(\)\_\-\+\=\{\[\}\]\|\\\:\;\"\'\<\,\>\.\?\/]{6,32}$/
					.test($("#mm").val()))) {
				$("#loginTip").removeClass("loginTipInfo").addClass(
						"loginTipWarn");
				$("#mm").focus();
				$("#loginTip").html('密码要求至少6位的数字或字母');
			} else if ($("#mm").val() != $("#qrmm").val()) {
				$("#loginTip").removeClass("loginTipInfo").addClass(
						"loginTipWarn");
				$("#qrmm").focus();
				$("#loginTip").html('密码和确认密码不一致');
			}else if($.trim($("#dxyzm").val()) == ""){
				$("#loginTip").removeClass("loginTipInfo").addClass(
				"loginTipWarn");
				$("#dxyzm").focus();
				$("#loginTip").html('请输入验证码');
			} else {
				var phone = $("#sjh").val();
				var pwd = $("#mm").val();
				var yan = $("#dxyzm").val();
				$.post("reception.do", {
					phone : phone,
					pwd : pwd,
					yanzma : yan
				}, function(data) {
					if (data == "1") {
						$("#loginTip").removeClass("loginTipWarn");
						$("#loginTip").text("");
						toast("手机号已存在，请重新确认！");
						$("#sjh").focus();
						$("#dxyzm").val("");
						change();
					} else if (data == "2") {
						$("#loginTip").removeClass("loginTipWarn");
						$("#loginTip").text("");
						toast("注册成功！");
						setTimeout(function() {
							location.href = '/hotels/login.jsp';
						}, 1000);
					}else if(data == "3"){
						$("#loginTip").removeClass("loginTipInfo").addClass(
						"loginTipWarn");
						$("#loginTip").html("验证码输入错误！");
						$("#dxyzm").focus();
						$("#dxyzm").val("");
						change();
					}
				});
			}
			handlerflag = true;
		}
	}
	 $(function() {
		$(".sidebar").hide();
		$("#sjh").on("keyup", function(e) {
			var tmptxt = $(this).val();
			$(this).val(tmptxt.replace(/\D/g, ''));
		})
		$("#hqyzm")
				.click(
						function() {
							if (handlerflag) {
								handlerflag = false;
								if (!(/(^[1][3456789][0-9]{9}$)/.test($("#sjh")
										.val()))) {
									$("#loginTip").removeClass("loginTipInfo")
											.addClass("loginTipWarn");
									$("#loginTip").html("手机格式不正确");
									handlerflag = true;
									return;
								} else {

								}
								handlerflag = true;
							}
						});
	}); 
</script>
</head>
<body>
	<div class="header" style="height: 85px;">
		<div class="logo-in">
			<a href="index" class="logo" style="margin-top: 8px;"><img
				src="register_files/dllogo.jpg"></a>
			<div class="title_icon">
				<img src="register_files/dl_bg2.png" style="display: inline-block">
			</div>
		</div>
	</div>
	<div class="content" style="height: 460px;">
		<div class="content-in">
			<form id="form" class="zc">
				<div class="i18n">会员注册</div>
				<span id="loginTip" class="loginTipInfo"></span>
				<ul style="margin-top: 28px;">
					<li><input id="sjh" name="sjh" type="text" maxlength="11"
						class="username" placeholder="手机号"></li>
					<li><input id="mm" name="mm" type="password" maxlength="100"
						class="password" placeholder="设置密码"></li>
					<li><input id="qrmm" type="password" maxlength="100"
						class="password"
						onkeydown="if(event.keyCode == 13){zc();return false;}"
						placeholder="确认密码"></li>
					<li style="width: 320px;">
					<input type="text" id="dxyzm" name="dxyzm" placeholder="验证码"
				class="usercode" style="width: 70px; vertical-align: middle;"> 
				<img src="code.do" id="kanbuq" style="width: 80px; margin-left: 210px; margin-top: 15px;">
				<a href="javascript:change();" style="width: 99px; margin-left: 290px; position: absolute; left: 3px; bottom: 144px;">看不清，换一张</a>
		</li>
					
					<li style="margin: 10px 0 5px;">
						<button class="three" onclick="zc();return false;" type="button">注&nbsp;&nbsp;&nbsp;册</button>
					</li>
					<p>
						<span class="kszc"><a href="login.jsp">立即登录</a></span>
					</p>
				</ul>
			</form>
		</div>
	</div>
	<div class="footer"
		style="background: #fff; border-top: none; margin-top: 30px; font-size: 14px;">
		<p style="text-align: center; color: #999; margin-bottom: 10px;">技术支持：广州云旅网络科技有限公司&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Copyright&nbsp;©&nbsp;乐游旅游.
			All Rights Reserved.</p>
		<div class="beian"
			style="border-top: none; text-align: center; background: #fff; color: #999; width: 100%">
			<a target="_blank"
				href="http://www.beian.gov.cn/portal/registerSystemInfo"
				style="display: inline-block; text-decoration: none; height: 20px; color: #9d9d9d; line-height: 20px;"><img
				src="register_files/footer_beian.png"
				style="display: inline-block; vertical-align: middle; margin-right: 3px;"><i
				style="font-style: normal; vertical-align: middle; margin-right: 3px;">粤公网安备
					44010602001894号</i></a> &nbsp;&nbsp;&nbsp;<a target="_blank"
				href="http://www.miitbeian.gov.cn/"
				style="display: inline-block; text-decoration: none; height: 20px; color: #9d9d9d;"><i
				style="font-style: normal; vertical-align: middle; margin-right: 3px;">沪ICP备15034440号</i></a>
		</div>
	</div>
	<script type="text/javascript">
		//图片验证码
		function change(){
			var img=document.getElementById("kanbuq");
			//因为浏览器有缓存，需要发送不同参数
			img.src="code.do?a="+new Date().getTime();
		}
	</script>
</body>
</html>