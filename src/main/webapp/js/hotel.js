/**********************多条件查询js开始******************************/
//删除条件
function del(obj){
	//变为全部
	var name = $(obj).parent().attr("id");
	if(name == "price"){
		all("#priceAll");
	}else if(name == "star"){
		all("#starAll");
	}else if(name == "hotelsdd"){
		all("#hotelsddAll");
	}else{
		all("#citysddAll");
	}

	$(obj).parent().remove();

	querylist('');
}
//删除条件，全部默认为红色
function all(id){
	//删除所有同级元素样式
	$(id).parent().children("dd").removeClass();
	//设置样式
	$(id).addClass("current");
}
//添加条件
function add(obj,id,xlqy,ssfl,ywbm){
	if($(obj).text() != "全部"){
		var fig = true;
		$("#"+id+"").each(function(index,i){
			$("#spans").children("#"+id).replaceWith('<span style="color: red; margin-left: 10px;"'+
					'name="'+$(obj).attr("id")+'" id="'+id+'">'+$(obj).text()+'<a href="javascript:void(0)" onclick="del(this)">'+
					'<img src="images/x.png" alt="删除" style="padding-left: 4px; height: 14px;" />'+
			'</a></span>');
			fig = false;
		});
		attrCss(obj);
		if(fig){
			$("#spans").append('<span style="color: red; margin-left: 10px;"'+
					'name="'+$(obj).attr("id")+'" id="'+id+'">'+$(obj).text()+'<a href="javascript:void(0)" onclick="del(this)">'+
					'<img src="images/x.png" alt="删除" style="padding-left: 4px; height: 14px;" />'+
			'</a></span>');
		}
	}

	querylist('');
}
//全部
function ddAll(obj,id){
	// 删除所有同级元素样式
	$(obj).parent().children("dd").removeClass();
	// 设置样式
	$(obj).addClass("current");
	//删除条件
	var spans = $("#spans").children("span");
	spans.each(function(index,item){
		if($(item).attr("id")==id){
			$(item).remove();
		}
	});
	querylist('');	
}
//设置样式默认选中
function attrCss(obj){
	// 删除所有同级元素样式
	$(obj).parent().children("dd").removeClass();

	// 设置样式
	$(obj).addClass("current");
}
//评分排序样式选中
var attr = null;
function attrLiCss(obj){
	var className = $(obj).attr("class");
	// 删除所有同级元素样式
	$(obj).parent().children("li").removeClass();
	var names = $(obj).text();
	if(names.length == 2){
		names = $(obj).next().text().replace("↓","↑");
		$(obj).next().text(names);
		names = $(obj).next().next().text().replace("↓","↑");
		$(obj).next().next().text(names);
	}else{
		if(names.lastIndexOf("↑") >= 2 && className == "currentup"){ //样式选中才可变更
			names = names.replace("↑","↓");
			$(obj).text(names);
		}else{
			names = names.replace("↓","↑");
			$(obj).text(names);
		}
	}
	// 设置样式
	$(obj).addClass("currentup");

	//异步访问数据库所需要的参数
	var textName = $(obj).text();
	var textNameSub = textName.substring(2,3);
	if(textNameSub == "↑"){ //升序
		attr = $(obj).attr("id")+"1";
	}else{
		attr = $(obj).attr("id")+"2";
	}
	querylist('');
}
//异步加载酒店
function querylist(obj){
	var price = $("#price").attr("name");
	var star = $("#star").attr("name");
	var hotelsdd = $("#hotelsdd").attr("name");
	var citysdd = $("#citysdd").attr("name");
	var kw = $("#kw").val();
	//var array = null;
	var pageNo = null;
	//分页参数
	if(obj != null && obj != ''){ //首页
		pageNo = $("#pageNo").val();
		if(obj == "home"){
			pageNo = 1;
		}else if(obj == "upper"){ //上页
			if(pageNo <=1){
				toast("已经是第一页了");
				return;
			}else{
				pageNo = parseInt(pageNo)-1;
			}
		}else if(obj == "next"){ //下页
			if(pageNo >= $("#total").val()){
				toast("已经是最后一页了");
				return;
			}else{
				pageNo = parseInt(pageNo)+1;
			}
		}else{
			pageNo = $("#total").val(); //尾页
		}
		//设置当前页
		$("#pageNo").val(pageNo);	
	}
	$.getJSON("multipleQuery",
			{price:price,hotelRating:star,level1:hotelsdd,level2:citysdd,hotelName:kw,pageNo:pageNo,attr:attr},
			function(data){
				//显示容器
				var optlist = $("#cplist");
				//清空原来的内容
				optlist.html("");
				var showopt = '';
				if(data.length == 0){
					showopt += '<span'
						+' style="display: block; width: 418px; position: absolute; top: 45%; left: 50%; margin-left: -209px; margin-top: -143px;"><img'
						+' src="index_files/cplb_zwcp.jpg" width="418"></span>';
					optlist.html(showopt);
					return;
				}
				for (var i = 0; i < data.length; i++) {
					showopt += '<div class="cpzs">'
						+'<a'
						+'href="/hotels/hotelDetails?hotelId='+data[i].hotelId+'"'
						+'target="_blank"> <img src="'+data[i].fileUrl+'"'
						+'onerror="this.src="/xtfsq/themes/images/default.jpg?timestamp=2015090216"">'
						+'</a>'
						+'<div class="cpmc">'
						+'<div class="cppic">'
						+'<a href="/hotels/hotelDetails?hotelId='+data[i].hotelId+'" target="_blank">'+data[i].hotelName+''
						+'</a>'
						+'</div>'
						+'<div class="clear"></div>'
						+'<p>酒店详情：'+data[i].hotelIntro+'</p>'
						+'<p>酒店星级：'+data[i].hotelRating+'<span style="margin-left:60px;">酒店评分：'+data[i].hotelRatings+'</span></p>'
						+'<div class="cpxqnr">'
						+'<p>酒店地址：'+data[i].hotelAddress+'</p>'
						+'</div>'
						+'</div>'
						+'<div class="jg">'
						+'<span><em>¥</em><b>'+data[i].hotelPrice+'</b>起</span>'
						+'<button style="outline: none; margin-top: 15px;" type="button"'
						+'onclick="javascript:window.open("/hotels/hotelDetails?hotelId='+data[i].hotelId+'")">立即预订</button>'
						+'</div>'
						+'</div>'
				}
				//添加分页
				showopt += '<div class="M-box2" style="margin: 0 0 20px">'
					+'<a href="javascript:querylist(\'home\');" class="next" style="color: #000;">首页</a>'
					+'<a href="javascript:querylist(\'upper\');"'	
					+' style="color: #000;" class="next">上页</a>'
					+'<a href="javascript:querylist(\'next\');"'	
					+' style="color: #000;" class="next">下页</a>'
					+'<a href="javascript:querylist(\'end\');" style="color: #000;" class="next">末页</a>'
					+'</div>'
					//添加
					optlist.html(showopt);
				count();
			});
}
//异步加载酒店数量并添加到页面
function count(){
	$.post("counts",{},function(data){
		$("#spans").children("sp").html(data);
		//根据酒店数加载总页数
		var pageSize = $("#pageSize").val();
		var totalAll = parseInt(data) % parseInt(pageSize) == 0? (parseInt(data)/parseInt(pageSize)) : (parseInt(data)/parseInt(pageSize))+1;
		$("#total").val(parseInt(totalAll));
	});
}
//清空条件
function qktj(){
	var kw = $("#kw").val();
	window.location = "cpss?cpss="+kw;
}
/**********************多条件查询js结束******************************/
function init(){
	if(""!="")$("#yxzdscj a").html($("#zdscj").find("#").html()).parent().show();
	if(""!="")$("#yxxcts a").html($("#xcts").find("#").html()).parent().show();
	// 中文带有特殊符号使用$("#yxmdd a").html($("#mdd").find($('[id="' + "" +
	// '"]')).html()).parent().show();
	if(""!="")$("#yxmdd a").html($("#mdd").find("#").html()).parent().show();
	if(""!="")$("#yxdjbq a").html($("#djbq").find("#").html()).parent().show();
	if(""!="")$("#yxztbq a").html($("#ztbq").find("#").html()).parent().show();
	if(""!="")$("#yxppbq a").html($("#ppbq").find("#").html()).parent().show();
	if($("#mdd").height()/44>1){
		$("#mdd_gd").show();
	}
	if($("#djbq").height()/44>1){
		$("#djbq_gd").show();
	}
	if($("#ztbq").height()/44>1){
		$("#ztbq_gd").show();
	}
	if($("#ppbq").height()/44>1){
		$("#ppbq_gd").show();
	}
}

$(function(){
	init();
	var mddNum = 0;
	$("#mdd_gd").click(function() {
		if(mddNum == 0){
			if($("#xzmdd").attr("class")=="mddheight")$("#xzmdd").removeClass("mddheight").addClass("mddheight2");
			else $("#xzmdd").removeClass("mddheight2").addClass("mddheight");
			$("#mdd_gd").html("收起<i class='sq'></i>");
			mddNum = 1;
		}else{
			if($("#xzmdd").attr("class")=="mddheight")$("#xzmdd").removeClass("mddheight").addClass("mddheight2");
			else $("#xzmdd").removeClass("mddheight2").addClass("mddheight");
			$("#mdd_gd").html("更多<i></i>");
			mddNum = 0;
		}
	});
	var	djbqNum = 0;
	$("#djbq_gd").click(function() {
		if(djbqNum == 0){
			if($("#xzdjbq").attr("class")=="mddheight")$("#xzdjbq").removeClass("mddheight").addClass("mddheight2");
			else $("#xzdjbq").removeClass("mddheight2").addClass("mddheight");
			$("#djbq_gd").html("收起<i class='sq'></i>");
			djbqNum = 1;
		}else{
			if($("#xzdjbq").attr("class")=="mddheight")$("#xzdjbq").removeClass("mddheight").addClass("mddheight2");
			else $("#xzdjbq").removeClass("mddheight2").addClass("mddheight");
			$("#djbq_gd").html("更多<i></i>");
			djbqNum = 0;
		}
	}); 
	var	ztbqNum = 0;
	$("#ztbq_gd").click(function() {
		if(ztbqNum == 0){
			if($("#xzztbq").attr("class")=="mddheight")$("#xzztbq").removeClass("mddheight").addClass("mddheight2");
			else $("#xzztbq").removeClass("mddheight2").addClass("mddheight");
			$("#ztbq_gd").html("收起<i class='sq'></i>");
			ztbqNum = 1;
		}else{
			if($("#xzztbq").attr("class")=="mddheight")$("#xzztbq").removeClass("mddheight").addClass("mddheight2");
			else $("#xzztbq").removeClass("mddheight2").addClass("mddheight");
			$("#ztbq_gd").html("更多<i></i>");
			ztbqNum = 0;
		}
	});
	var	ppbqNum = 0;
	$("#ppbq_gd").click(function() {
		if(ppbqNum == 0){
			if($("#xzppbq").attr("class")=="mddheight")$("#xzppbq").removeClass("mddheight").addClass("mddheight2");
			else $("#xzppbq").removeClass("mddheight2").addClass("mddheight");
			$("#ppbq_gd").html("收起<i class='sq'></i>");
			ppbqNum = 1;
		}else{
			if($("#xzppbq").attr("class")=="mddheight")$("#xzppbq").removeClass("mddheight").addClass("mddheight2");
			else $("#xzppbq").removeClass("mddheight2").addClass("mddheight");
			$("#ppbq_gd").html("更多<i></i>");
			ppbqNum = 0;
		}
	});
});
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
	$(".sidebar .top").on("click",function(){ $('body,html').animate({scrollTop: "0px"}, 300); });	
	$(".sidebar .dd").on("click",function(e){
		window.location.href="/module/hygl/page.do?page=index";
	});
	$(".sidebar .sc").on("click",function(e){
		window.location.href="/module/hygl/page.do?page=index&lm=sc";
	});
	$("#tccancle").on("click",function(e){$(".tczz,.tc").hide()});
});