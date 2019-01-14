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
		if(sj == ""){
			$("#zz").css("display","");
			$("#hy").css("display","");
			$("#yzmdl").css("display","");
			return;
		}
		var d = new Date();
		var str = d.getFullYear()+"-"+((d.getMonth()+1)>=10?+(d.getMonth()+1):"0"+(d.getMonth()+1))+"-"+((d.getDate())>=10?d.getDate():'0'+d.getDate());
		if(dateOpen == ''){
			toast("请选择入住日期");
		}else if(dateOpen<str){
			toast("入住日期不能小于当前日期！");
		}else if(dateExit == ''){
			toast("请输入退房日期");
		}else if(dateOpen>=dateExit){
			toast("退房日期必须大于入住日期！");
		}else if(houseList == 0){
			toast("请选择房型");
		}else if(parseInt(sykc_label) <= 0){
			toast("房间已定完");
		}else if(sykc_label < crrs){
			toast("房间不足");
		}else if(zflx == 0){
			toast("请选择支付方式");
		}else if(zflx == 2){
			toast("暂不支持此支付方式");
		}else{
			//访问服务器
			$.ajax({
				type:"post",
	    		url:"insertOrder",
	    		data:$("#form").serialize(),
	    		dataType:"text",
	    		async:false,
	    		success:function(result){
	    			if(result == 1){
	    				toast("下单成功！")
	    				window.location = "memberOrder";
	    			}else{
	    				toast("下单失败！")
	    			}
	    		},
	    		error:function(result){
	    			alert(result.status);
	    			toast("系统繁忙，请稍后重试"); 
	    		}
			});
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
		$.post("loginUser",{phone:$("#phone").val()},function(tt){
			if(tt==2){
				toast("账号不存在!");
				return;
			}else{
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
		});
	}
}

function xzxllb(lbid,xlqy,ssfl,ywbm){
	var mk="";
	var mktype="";
	window.location.href="/hotels/cpss?cpss=&mdd="+lbid+"&mk="+xlqy+"&mktype="+ssfl+"&ywbm="+ywbm;
}

// 产品搜索
function cpss(){
	var pattern=new RegExp("\\\\","g");/// /查找所有的"\"
	var cpss=$("#kw").val().replace(pattern,"");
	window.location.href="/hotels/cpss?cpss="+cpss;
}

$(function(){
	if(""!="") $("#kw").val("");
	$(".xllb").mouseenter(function(e) {
		$(this).children(".xllb-mb").css({"display":"block"});
	});
	$(".xllb").mouseleave(function(e) {
		$(this).children(".xllb-mb").css({"display":"none"});
	});
	$(".xllb-mb ul li").mouseenter(function(e) {
		$(this).find("li").css({"color":"#333"});
		$(this).find("span").css({"color":"#333"});
		$(this).css({"background":"#fff"});
		$(this).addClass("current");
		$(this).children(".xllb-mbzk").css({"display":"block"});
	});
	$(".xllb-mb ul li").mouseleave(function(e) {
		$(this).find("li").css({"color":"#fff"});
		$(this).find("span").css({"color":"#fff"});
		$(this).css({"background":"none"});
		$(this).removeClass("current");
		$(this).children(".xllb-mbzk").css({"display":"none"});
	});
	$(".xllb-mb li.xllb-mbfl:last-child()").mouseenter(function(e) {
		$(this).css({"-webkit-box-shadow":"7px 4px 5px rgba(0,0,0,0.22)"});
	});
	$(".xllb-mb li.xllb-mbfl:last-child()").mouseleave(function(e) {
		$(this).css({"-webkit-box-shadow":"none"});
	});
	$(".change_tab,.xzcfd").mouseenter(function(e) {
		$(".xzcfd").css("display","block");
		$(".change_tab u").removeClass('qhcfdjt').addClass("qhcfd2");
		$(".change_tab").addClass('change_tabh');
		$(".cfdz").hide();
	});
	$(".change_tab,.xzcfd").mouseleave(function(e) {
		$(".xzcfd").css("display","none");
		$(".change_tab").removeClass('change_tabh');
		$(".change_tab u").removeClass('qhcfd2').addClass("qhcfd");
		if("1"!=null&&"1"!="")$(".cfdz").show();
	}); 
	$(".headpiece-in span .wsc").mouseenter(function(e) {
		$(".wxbox").css("display","block");
	});
	$(".headpiece-in span .wsc").mouseleave(function(e) {
		$(".wxbox").css("display","none");
	});
	if("false"=="true"||"false"=="true"){	
		if(getCookie("www.dw.com")!=null){
			fbid=getCookie("www.dw.com");
			if(typeof $("li[fbid="+fbid+"]").find("a").html()!="undefined"){
				$("#city span").html($("li[fbid='"+fbid+"']").find("a").html());
				xzfb(fbid);
			}else{
				zddw();
			}
		}else{
			zddw();
		}
	}
	$(".xzcfd ul li").click(function(){
		if(handlerflag){
			handlerflag=false;
			$(".xzcfd").hide();
			fbid=$(this).attr("fbid");
			$.ajax({
				type:"POST",
				url:"/module/cpgl/xzfb.do",
				data:{fbid:fbid,page:"qhfb"},
				dataType:"json",
				async:false,
				success:function(result){
					window.location.href="/";
				},
				error:function(){toast("系统繁忙，请稍后重试");}
			});
			handlerflag=true;
		} 
	});
	if("false"=="true"||"[{ljdx=关于乐游, cjsj=2017-03-30 22:40:31, lmmc=关于乐游, ljlx=02, cjr=9cd3066ad8434af1a8d9c1b21a1e4501, lmpx=2017-03-30 13:40:09, sjlm=cf3d46f118ba4ca5a676bf0b3b916c63, xjlist=[{ljdx=a77fe17b1ecf48cea8363705c4e28fc5, cjsj=2017-03-31 14:17:56, lmmc=董事长致辞, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-03-31 14:16:33, sjlm=28f8d9ede0754a6ba468b5e9606bbef1, lmid=e79ffd7b23d342bda7b8806add560d7b, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=79f928388ac349d7857364cb92039cc4, cjsj=2017-03-31 14:17:14, lmmc=乐游简介, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-03-31 14:17:02, sjlm=28f8d9ede0754a6ba468b5e9606bbef1, lmid=64e21b26baaa41a2ac59642956e79419, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=9d1396edd73044068542072d2805a4f3, cjsj=2017-03-31 14:18:33, lmmc=发展历程, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-03-31 14:18:25, sjlm=28f8d9ede0754a6ba468b5e9606bbef1, lmid=59b41f6268184e9cad98e519cdceb4e4, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=5f147ff15f03436d95143aa0fc74f547, cjsj=2017-03-31 14:19:03, lmmc=历史荣誉, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-03-31 14:18:52, sjlm=28f8d9ede0754a6ba468b5e9606bbef1, lmid=08230cb92cf14f2aa541dfa74b7a4ee3, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=e97501e9a660494cb566cb4ea1f986f8, cjsj=2017-03-31 14:37:48, lmmc=乐游大事记, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-03-31 14:20:40, sjlm=28f8d9ede0754a6ba468b5e9606bbef1, lmid=9b897cf4a9b14ed5806369e76a5eefc2, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=28db9c23e3804dcda7c8a9b59300ca62, cjsj=2017-03-31 14:28:59, lmmc=旗下子公司, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-03-31 14:20:43, sjlm=28f8d9ede0754a6ba468b5e9606bbef1, lmid=3038ca9189774c5e88f28e0d747b47c8, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=/module/ppgl/page.do?page=md, cjsj=2017-04-11 18:00:19, lmmc=乐游门店, ljlx=02, cjr=9cd3066ad8434af1a8d9c1b21a1e4501, lmpx=2017-03-31 14:21:00, sjlm=28f8d9ede0754a6ba468b5e9606bbef1, lmid=4fd8d33384de4f209624b80e2da5a039, ppid=a1d051e647f74bf6959c53e8c518d4b5}], lmid=28f8d9ede0754a6ba468b5e9606bbef1, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=乐游模式, cjsj=2017-03-31 14:22:00, lmmc=乐游模式, ljlx=02, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-03-31 14:21:22, sjlm=cf3d46f118ba4ca5a676bf0b3b916c63, xjlist=[{ljdx=170d1cf6e9ca4b6aa63ae53cd84361e5, cjsj=2017-03-31 14:22:26, lmmc=乐游特色, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-03-31 14:22:18, sjlm=01453a4c046749b6b774fa64c7cbb39d, lmid=afd9cb7fbabe401fb232b590bfa75507, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=a80338e85e2f4c4283d5356aca41c7d5, cjsj=2017-03-31 14:22:44, lmmc=运作模式, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-03-31 14:22:33, sjlm=01453a4c046749b6b774fa64c7cbb39d, lmid=0232aade0b204938ba4f03d5723dbbbd, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=edb1d00a4c3a4f7c962862ae2e19a9ee, cjsj=2017-03-31 14:23:10, lmmc=核心优势, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-03-31 14:24:55, sjlm=01453a4c046749b6b774fa64c7cbb39d, lmid=3ca88aa2e14f44a19399191f00a595fe, ppid=a1d051e647f74bf6959c53e8c518d4b5}], lmid=01453a4c046749b6b774fa64c7cbb39d, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=乐游动态, cjsj=2017-03-31 14:12:18, lmmc=乐游动态, ljlx=02, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-03-31 14:25:00, sjlm=cf3d46f118ba4ca5a676bf0b3b916c63, xjlist=[{ljdx=/module/ppgl/page.do?page=dt&lx=01, cjsj=2017-03-31 19:00:34, lmmc=热门头条, ljlx=02, cjr=9cd3066ad8434af1a8d9c1b21a1e4501, lmpx=2017-03-31 14:25:01, sjlm=9565b543c81a40a496a14cf3cc27065c, lmid=794152f786b04602be7ac0cc61a630d9, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=/module/ppgl/page.do?page=dt&lx=02, cjsj=2017-03-31 19:01:21, lmmc=行业资讯, ljlx=02, cjr=9cd3066ad8434af1a8d9c1b21a1e4501, lmpx=2017-03-31 14:25:02, sjlm=9565b543c81a40a496a14cf3cc27065c, lmid=6143acccad0b40debf9708d6431ed8cb, ppid=a1d051e647f74bf6959c53e8c518d4b5}], lmid=9565b543c81a40a496a14cf3cc27065c, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=加入乐游, cjsj=2017-03-31 14:52:26, lmmc=加入乐游, ljlx=02, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-03-31 14:52:17, sjlm=cf3d46f118ba4ca5a676bf0b3b916c63, xjlist=[{ljdx=d5bb2cb4ba5c47b1a9c3674cbf3c7ac6, cjsj=2017-03-31 14:52:46, lmmc=门店合作, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-03-31 14:52:38, sjlm=2074a8318161497eba8e504a9768e9bb, lmid=33f4c89f887246329e5be780255be307, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=c9d02b8dff9543e09972d4ec66874a63, cjsj=2017-03-31 14:53:00, lmmc=资源合作, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-03-31 14:52:53, sjlm=2074a8318161497eba8e504a9768e9bb, lmid=0b8c4c1f129945d99b2a1407b4c632f3, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=a818e63ad8744149ae8a4c0cbd6a360d, cjsj=2017-03-31 14:53:17, lmmc=人才招聘, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-03-31 14:53:10, sjlm=2074a8318161497eba8e504a9768e9bb, lmid=6b97fe2ce1ec4af1bf10535eacbb6e02, ppid=a1d051e647f74bf6959c53e8c518d4b5}], lmid=2074a8318161497eba8e504a9768e9bb, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=帮助中心, cjsj=2017-04-06 18:43:35, lmmc=帮助中心, ljlx=02, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-04-06 18:43:19, sjlm=cf3d46f118ba4ca5a676bf0b3b916c63, xjlist=[{ljdx=77d897ad7e204b97aa57c6f9f1e91677, cjsj=2017-04-06 18:49:24, lmmc=签证相关问题, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-04-06 18:45:47, sjlm=20886be9a2dc493796eeedc2407d5375, lmid=8d5fb4376ddc4fa7b3af939694df9e4f, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=c857762882e94825ab814279653cdb3a, cjsj=2017-04-06 18:54:11, lmmc=旅游常见概念解释, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-04-06 18:50:20, sjlm=20886be9a2dc493796eeedc2407d5375, lmid=b4774d6510234db08fb4acce92a09100, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=ef310f5b8af440b9897739385ff26632, cjsj=2017-04-06 19:06:03, lmmc=旅游线路常见问题, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-04-06 19:01:48, sjlm=20886be9a2dc493796eeedc2407d5375, lmid=ed8db429d86c45cb9343e5ac903f406b, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=4ce22cd607ff4784a33144adfb8c0ddf, cjsj=2017-04-06 19:12:11, lmmc=网上预订相关问题, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-04-06 19:07:03, sjlm=20886be9a2dc493796eeedc2407d5375, lmid=94b84f8145574d40b742800c74848982, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=31c7084a3d48402a9ac30cae3ffc6e41, cjsj=2017-04-06 19:20:59, lmmc=网上支付方式, ljlx=01, cjr=8b47c67ea7314e42aae2995e6aeb642a, lmpx=2017-04-06 19:17:13, sjlm=20886be9a2dc493796eeedc2407d5375, lmid=34179947430d4bd68d2d6f848ab0b396, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=a4b4c16140b9497f9f550873f2fce521, cjsj=2017-06-06 14:18:26, lmmc=护照、签证及港澳通行证, ljlx=01, cjr=68851066fdec46e8b2ec8fca9dcc2b7d, lmpx=2017-06-06 14:29:08, sjlm=20886be9a2dc493796eeedc2407d5375, lmid=78e410e62e4548b4be66d7e039fde92a, ppid=a1d051e647f74bf6959c53e8c518d4b5}, {ljdx=2dd3d4a46e904f1faeef8296d29a2254, cjsj=2017-06-06 15:34:44, lmmc=各国插座形式、电压一览表, ljlx=01, cjr=68851066fdec46e8b2ec8fca9dcc2b7d, lmpx=2017-06-06 15:27:25, sjlm=20886be9a2dc493796eeedc2407d5375, lmid=980f85ebbfe04ffa850fb097f95892c0, ppid=a1d051e647f74bf6959c53e8c518d4b5}], lmid=20886be9a2dc493796eeedc2407d5375, ppid=a1d051e647f74bf6959c53e8c518d4b5}]"==null||$(".right").find("a").length<2){
		$.ajax({
			type:"POST",
			url:"/module/ppgl/cxyjlm.do",
			dataType:"json",
			success:function(result){
				var html = "";
				var data = $.parseJSON(result.value);
				$.each(data,function(i,item){
					if(item.lmmc!='帮助中心'){
						html+='<a href="/module/ppgl/index.do?lmid='+item.lmid+'"  target="_blank">'+item.lmmc+'</a>';
						if(i!==data.length-1){
							html+="丨";
						}
					}else{
						html+='<a href="/module/ppgl/page.do?page=bz&lmid='+item.lmid+'"  target="_blank">'+item.lmmc+'</a>';
						if(i!==data.length-1){
							html+="丨";
						}
					}
				})
				$(".wsc_box").before(html);
			},
			error:function(){}
		})
	}
	$(".cfdz").click(function(){
		window.open("/module/ppgl/index.do?page=md&jgid="+getCookie("www.dw.com"));
	});

	if("false"=="true"||"1"==null){
		$.ajax({
			type:"POST",
			url:"/module/ppgl/ismddz.do",
			dataType:"json",
			success:function(result){
				if(result.key=="1") $(".cfdz").show();
			},
			error:function(){}
		});
	}

});

$(function(){
	$(".sidebar .top").on("click",function(){ $('body,html').animate({scrollTop: "0px"}, 300); });	
	$("#tccancle").on("click",function(e){$(".tczz,.tc").hide()});
});
$(function(){
	$(".xllb-mb").css({"display":"none"});
	$(".xllb").mouseenter(function(e) {
		$(this).children(".xllb-mb").css({"display":"block"});
	});
	$(".xllb").mouseleave(function(e) {
		$(this).children(".xllb-mb").css({"display":"none"});
	});
});

