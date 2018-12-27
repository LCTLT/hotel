var islogin = false;
var dxfsxz = false;
var handlerflag = true;

$(function() {
	$(".cpdh ul li").click(
			function(e) {
				if ($("#" + $(this).attr("class"))) {
					window.scroll(0, $("#" + $(this).attr("class"))
							.offset().top - 45);
					$(this).addClass("current").siblings().removeClass(
					"current");
				}
			});
	var height = $('.cpdh').offset().top;
	$(window).on(
			'scroll',
			function() {
				if (height <= $(document).scrollTop() - 30) {
					$('.cpdh').addClass('fixed_top');
					$("#yccpdh").show();
					if ($("#cpmd").length > 0
							&& $("#cpmd").offset().top
							+ $("#cpmd").height() - 45 >= $(
									document).scrollTop())
						$(".cpmd").addClass("current").siblings()
						.removeClass("current");
					else if ($("#cxhd").length > 0
							&& $("#cxhd").offset().top
							+ $("#cxhd").height() - 45 >= $(
									document).scrollTop())
						$(".cxhd").addClass("current").siblings()
						.removeClass("current");
					else if ($("#xxxc").length > 0
							&& $("#xxxc").offset().top
							+ $("#xxxc").height() - 45 >= $(
									document).scrollTop())
						$(".xxxc").addClass("current").siblings()
						.removeClass("current");
					else if ($("#fyh").length > 0
							&& $("#fyh").offset().top + $("#fyh").height()
							- 45 >= $(document).scrollTop())
						$(".fyh").addClass("current").siblings()
						.removeClass("current");
					else if ($("#fybh").length > 0
							&& $("#fybh").offset().top
							+ $("#fybh").height() - 45 >= $(
									document).scrollTop())
						$(".fybh").addClass("current").siblings()
						.removeClass("current");
					else if ($("#qzxz").length > 0
							&& $("#qzxz").offset().top
							+ $("#qzxz").height() - 45 >= $(
									document).scrollTop())
						$(".qzxz").addClass("current").siblings()
						.removeClass("current");
					else if ($("#wxts").length > 0
							&& $("#wxts").offset().top
							+ $("#wxts").height() - 45 >= $(
									document).scrollTop())
						$(".wxts").addClass("current").siblings()
						.removeClass("current");
					else if ($("#bcxy").length > 0
							&& $("#bcxy").offset().top
							+ $("#bcxy").height() - 45 >= $(
									document).scrollTop())
						$(".bcxy").addClass("current").siblings()
						.removeClass("current");
				} else {
					$('.cpdh').removeClass('fixed_top').find("li").eq(0)
					.addClass("current").siblings().removeClass(
					"current")
					$("#yccpdh").hide();
				}
				isclick = false;
			});
	$("#submitOrder").click(function(){
		var dateOpen = $("#dateOpen").val(); //入住日期
		var dateExit = $("#dateExit").val(); //退房日期
		var houseList = $("#houseList").val(); //房型
		var sykc_label = $("#sykc_label").text(); //剩余库存
		var crrs = $("#crrs").val(); //预定数量
		var sj = $("#ssj").val(); //手机
		var zflx = $("#zflx").val(); //支付方式
		console.log("dateOpen"+dateOpen);
		console.log("dateExit"+dateExit);
		console.log("houseList"+houseList);
		console.log("sykc_label"+sykc_label);
		console.log("crrs"+crrs);
		console.log(sj == "");
		console.log("zflx"+zflx)
		if(sj == ""){
			$("#zz").css("display","");
			$("#hy").css("display","");
			$("#yzmdl").css("display","");
			return;
		}
		if(dateOpen == ''){
			toast("请选择入住日期");
		}else if(dateExit == ''){
			toast("请输入退房日期");
		}else if(houseList == 0){
			toast("请选择房型");
		}else if(sykc_label == "0"){
			toast("房间已定完");
		}else if(sykc_label < crrs){
			toast("房间不足");
		}else if(zflx == 0){
			toast("请选择支付方式");
		}else if(zflx == 2){
			toast("暂不支持此支付方式");
		}else{
			//访问服务器
			toast("下单成功");
		}
	});
	$("#cancle").click(function(){
		//弹出层关闭
		$("#zz").css("display","none");
		$("#hy").css("display","none");
		$("#yzmdl").css("display","none");
	});
});
/*
 * 访问登录
 */
function yzmdl(){
	if(!(/(^[1][3456789][0-9]{9}$)/.test($("#phone").val()))){
		toast("手机格式不正确");
	}else if($("#mm").val() == ""||!(/^[a-zA-Z0-9\~\`\!\@#$\%\^\&\*\(\)\_\-\+\=\{\[\}\]\|\\\:\;\"\'\<\,\>\.\?\/]{6,32}$/.test($("#pwd").val()))){
		toast("密码格式不正确");
	}else{
		$.post("loginUser",{phone:$("#sjh").val(),},function(tt){
			if(tt==2){
				toast("账号不存在!");
				return;
			} 
		});
		$.post("login",{phone:$("#phone").val(),password:$("#pwd").val()},function(dd){
			if(dd==1){ 
				toast("信息验证成功!");
				//弹出层关闭
				$("#zz").css("display","none");
				$("#hy").css("display","none");
				$("#yzmdl").css("display","none");
				//页面刷新一次
				setTimeout(function(){
					location.reload();
				}, 1300);
			}else if(dd==2){
				toast("账号或密码错误!");
			}							
		});

	}
}





