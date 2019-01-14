<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="member_files/share.css?timestamp=2015090216">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>乐游旅游</title>
<link rel="shortcut icon" type="image/x-icon"
	href="http://www.loyoyo.com/favicon.ico?timestamp=2015090216">
<link rel="stylesheet" type="text/css"
	href="member_files/hygl.css?timestamp=2015090216">
<script type="text/javascript" src="js/memberMydata.js"></script>
<!-- 您的密钥 -->
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=SsYbaysf6xEvBGFpPeGD6Xn12uhXfqpO"></script>

<link rel="stylesheet" type="text/css" href="css/member.css">
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
			function testzjh(){
				var xm = $("#xm").val();
				var xb = $("#xb").val();
				var csrq = $("#csrq").val();
				var yx = $("#yx").val();
				var dz = $("#dz").val();
				if(/^[\u4E00-\u9FA5a-zA-Z]{2,30}$/.test(xm) == false&&$.trim(xm)!=""){
					toast('姓名格式不正确');
					return false;
				}else if(xb.trim()==""){
					toast("请选择性别");
					return false;
				}else if($.trim(zjlx)==""){
					toast("请选择证件类型"); 
					return false;
				}else if($.trim(zjh)==""){
					toast("请输入证件号"); 
					return false;
				}else if(zjlx=="01"){
					var temp = validCard(zjh,csrq,xb);
					if(temp[0]!="true"){
						toast("temp[0]");
						return false;
					}
				}else if(zjlx=="02" && !/^(1[45][0-9]{7}|G[0-9]{8}|P[0-9]{7}|S[0-9]{7,8}|D[0-9]+|E[0-9]{8}|[a-zA-Z0-9]+)$/.test(zjh)){
					toast("无效护照"); 
					return false;
				}else if(zjlx=="03" && !/^[a-zA-Z0-9\(\)]+$/.test(zjh)){
					toast("证件号格式不正确"); 
					return false;
				}
				if(csrq.trim()==""){toast("请选择出生日期"); 
				return false;}
				if(new Date()<=Date.parse(csrq.replace(/-/g, "/"))){
					toast("出生日期不能大于今天"); 
					return false;
				}
				if(!(/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/.test(yx))&&$.trim(yx)!=""){
					toast('邮箱格式不正确');
					return false;
				}
				if(!(/^[^\~\`\!\@#$\%\^\&\*\(\)\_\-\+\=\{\[\}\]\|\\\:\;\"\'\<\,\>\.\?\/]+$/.test(dz))&&$.trim(dz)!=''){
					toast('地址格式不正确');
					return false;
				}
				
				return true;
			}
			
			$(function(){
				if($("#zjlx").val().trim()=="01"){
					var temp = validCard($("#zjh").val(),null,null);
					if(temp[0]=="true"){
						$("#csrq").val(temp[1]);
						$("#xb").val(temp[2]);
					}
				}
				
				
			    $("#save").click(function(){
			    	
				    	 if(!testzjh()){
				    	 }else{
					    	$.ajax({
					    		type:"POST",
					    		url:"getMyData",
					    		data:$("#form").serialize(),
					    		dataType:"json",
					    		async:false,
					    		success:function(result){
					    			toast("保存成功");
				    				//window.location.href="/module/hygl/page.do?page=edit&toast=1";
					    		},
					    		error:function(result){parent.toast("系统繁忙，请稍后重试");}
					    	});
						}
					
			    });
			    
			    $("input[type='text']").focus(function(){
			    	$($(this).parent().find("i")).css("display","inline");
			    });
			    $(".iconfont").click(function(){
			    	$($(this).parent().find("input")).val("").focus();
			    });
			    $("#zjh").blur(function(){
					if($("#zjlx").val().trim()=="01"){
						var temp = validCard($("#zjh").val(),null,null);
						if(temp[0]=="true"){
							$("#csrq").val(temp[1]);
							$("#xb").val(temp[2]);
						}
					}
			   	});
			    $("input[type='text']").blur(function(){
			    	var obj = $(this);
			    	setTimeout(function(){
			    		if(!obj.is(":focus")){
			    			$(obj.parent().find("i")).css("display","none");
			    		}
			    	},100);
			    })
			})
		</script>
</head>
<body>
	<div class="content-right">
		<ul class="wdsc">
			<li class="current" style="cursor: default;">我的资料</li>
			<li
				style="cursor: default; width: 845px; border-bottom: 2px solid #e1e1e1;"></li>
		</ul>
		<div class="wdzl">
			<form id="form">
				<input name="hyid" type="hidden"
					value="21fee1af61dd4d75970d5faff879ef56">
				<ul>
					<li><div>姓名</div> <input id="xm" name="xm" type="text"
						placeholder="请输入姓名" value="${user.name }" maxlength="30"></li>
					<li><div>性别</div> <select id="xb" name="xb">
							<option value="03">请选择</option>
							<option value="00">女</option>
							<option value="01">男</option>
					</select></li>
					<li><div>证件类型</div> <select id="zjlx" name="zjlx">
							<option value="00">请选择</option>
							<option value="01" selected="">身份证</option>
							<option value="02">护照</option>
							<option value="03">其他</option>
					</select></li>
					<li><div>证件号</div> <input id="zjh" name="zjh" type="text"
						placeholder="请输入证件号" value="${user.idcard }" maxlength="30"></li>
					<li><div>出生日期</div> <input id="csrq" name="csrq"
						readonly="readonly" type="text" placeholder="请输入出生日期"
						maxlength="10" onclick="laydate({elem: '#csrq'});"
						value="${user.birthday }"></li>
					<li><div>邮箱</div> <input id="yx" name="yx" type="text"
						placeholder="请输入邮箱" value="${user.email }" maxlength="100"></li>
					<li><div>地址</div> <input id="dz" name="dz" type="text"
						placeholder="请输入地址" value="${user.address }" maxlength="100"></li>
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
</script>
</body>
</html>