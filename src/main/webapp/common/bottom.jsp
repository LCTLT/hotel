<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
function infomember(){
	var sj = $("#sj").val();
	if (sj != "") {
		location.href = "memberOrder";
	} else {
		if (confirm("您还未登录,是否去登录？")) {
			location.href = "login.jsp";
		}else{
			location.href="#";
		}
	}
}

</script>
<!--页面底部开始-->
<div class="footer-top">
	<div class="footer-wxbox">
		<div class="footer-box"> 
						<dl>
							<dd><a href="#" target="_blank">签证相关问题</a></dd>
							<dd><a href="#" target="_blank">旅游常见概念解释</a></dd>
							<dd><a href="#" target="_blank">旅游线路常见问题</a></dd>
							<dd><a href="#" target="_blank">网上预订相关问题</a></dd>
							<dd><a href="#" target="_blank">网上支付方式</a></dd>
						</dl>
            <div class="telbottom"><img src="index_files/telbottom.png"></div>
            <div class="clear"></div>
		</div>
	</div>
</div>
<div class="footer">
   	<div class="footer-in">
       	<div class="beian">
       		<span style="display:inline-block;">技术支持：广州云旅网络科技有限公司 &nbsp;&nbsp;&nbsp;&nbsp;Copyright&nbsp;©&nbsp;乐游旅游. All Rights Reserved.</span><br>
            <a target="_blank" href="#" style="display:inline-block;text-decoration:none;">
              	<img src="index_files/footer_beian.png" style="display: inline-block;vertical-align:middle;margin-right:3px;">
              	<i style="font-style: normal;vertical-align:middle;margin-right:3px;">粤公网安备 44010602001894号</i>
            </a> &nbsp;&nbsp;&nbsp;<a target="_blank" href="#"><i style="font-style: normal;vertical-align:middle">沪ICP备15034440号</i></a>
        </div>
    </div>
</div>
<!--页面底部结束-->
<!--页面侧栏开始-->
<div class="sidebar">
    <ul>
        <li class="dd"><a href="javaScript:infomember();"><span></span><p>订单</p></a></li>
        <li class="sc"><a href="javascript:;"><span></span> <p>收藏</p></a></li>
        <li class="db top" style=" border-bottom:none;"><a href="javascript:;"><span></span><p>TOP</p></a></li>
    </ul>
</div>
<!--页面侧栏结束-->