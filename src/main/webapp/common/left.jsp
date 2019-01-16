<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--页面导航开始-->
<div class="navigation">
	<div class="navigation-in">
    	<div class="xllb"><div class="mddh">旅游目的地导航</div>
        	<div class="xllb-mb" style="z-index:12;display:none; padding-top:15px; ">
            	<ul>
                     <li class="xllb-mbfl">
                    	<div class="xllb_zbybt"><em class="zbytb"></em><span>酒店宾馆</span><i></i></div>
                        <ul style="height:50px; ">
                         	<c:forEach items="${first}" var="first">
                         	<li class="xlxl" onclick="xzxllb('${first.id}','hotelsdd','${first.name}','1')">${first.name}</li>
                         	</c:forEach>
                        </ul>
                       <div class="xllb-mbzk xllb-zby">
                           <div class="xllb_diqu xllb_zby">
                          	<span style="font-size: 16px; width: 400px;"><em><img alt="" src="index_files/hotel.png" height="25px" width="25px"></em>酒店类型(Hotel type)</span><br>
                          	<c:forEach items="${first}" var="first">
                          	<a href="javascript:xzxllb('${first.id}','hotelsdd','${first.name}','1');">${first.name}</a>
                           	</c:forEach>
                          	<br><span style="font-size: 16px; width: 400px;"><em><img alt="" src="index_files/city.png" height="25px" width="25px"></em>所在城市(City where)</span><br>
                           	<c:forEach items="${second}" var="second">
                           	<a href="javascript:xzxllb('${second.id}','citysdd','${second.name}','3');">${second.name}</a>
                           	</c:forEach>
                           	<br><br><span style="font-size: 16px; width: 400px;"><em><img alt="" src="index_files/money.png" height="25px" width="25px"></em>价格范围(Price range)</span><br>
                            <c:forEach items="${getprice}" var="price" varStatus="ss">
	                            <a href="javascript:xzxllb('${price.info}','${price.typeCode}');">
		                             ${price.info}元
		                            <c:if test="${ss.first}">
		                           		以下
		                            </c:if>
		                            <c:if test="${ss.last}">
		                           		以上
		                            </c:if>
	                            </a>
                           	</c:forEach>
                           	<br><span style="font-size: 16px; width: 400px;"><em><img alt="" src="index_files/stars.png" height="25px" width="25px"></em>星级档次(Star grade)</span><br>
                           	<c:forEach items="${getstar}" var="star">
                           		<a href="javascript:xzxllb('${star.dictCode}','${star.typeCode}','${star.info}');">${star.info}</a>
                           	</c:forEach>
                            </div>
                      </div>
                    </li>
                    <li class="xllb-mbfl">
                    	<div class="xllb_zbybt"><em class="gnytb"></em><span>国内酒店</span><i></i></div>
                        <ul style="height:110px;">
                         	<li onclick="javascript:void(0);">华东五市</li>
                         	<li onclick="javascript:void(0);">上海</li>
                         	<li onclick="javascript:void(0);">江苏</li>
                         	<li onclick="javascript:void(0);">浙江</li>
                         	<li onclick="javascript:void(0);">安徽</li>
                         	<li onclick="javascript:void(0);">福建</li>
                         	<li onclick="javascript:void(0);">山东</li>
                         	<li onclick="javascript:void(0);">海南</li>
                         	<li onclick="javascript:void(0);">广西</li>
                         	<li onclick="javascript:void(0);">江西</li>
                         	<li onclick="javascript:void(0);">湖南</li>
                         	<li onclick="javascript:void(0);">广东</li>
                        </ul>
                      <div class="xllb-mbzk">
                           <div class="xllb-mbzk-diqu">
                           	 	<div class="xllb_dy">华东</div><div class="xllb_diqu">
                           	<a href="javascript:void(0);">华东五市</a>
                           	<a href="javascript:void(0);">上海</a>
                           	<a href="javascript:void(0);">江苏</a>
                           	<a href="javascript:void(0);">浙江</a>
                           	<a href="javascript:void(0);">安徽</a>
                           	<a href="javascript:void(0);">福建</a>
                           	<a href="javascript:void(0);">山东</a>
                           	 	</div><div class="clear"></div><div class="xllb_dy">华南</div><div class="xllb_diqu">
                           	<a href="javascript:void(0);">海南</a>
                           	<a href="javascript:void(0);">广西</a>
                           	<a href="javascript:void(0);">江西</a>
                           	<a href="javascript:void(0);">湖南</a>
                           	<a href="javascript:void(0);">广东</a>
                           	 	</div><div class="clear"></div><div class="xllb_dy">华北</div><div class="xllb_diqu">
                           	<a href="javascript:void(0);">北京</a>
                           	<a href="javascript:void(0);">河北</a>
                           	<a href="javascript:void(0);">陕西</a>
                           	<a href="javascript:void(0);">河南</a>
                           	<a href="javascript:void(0);">新疆</a>
                           	<a href="javascript:void(0);">东北三省</a>
                           	 	</div><div class="clear"></div><div class="xllb_dy">华西</div><div class="xllb_diqu">
                           	<a href="javascript:void(0);">云南</a>
                           	<a href="javascript:void(0);">西藏</a>
                           	<a href="javascript:void(0);">贵州</a>
                           	<a href="javascript:void(0);">四川</a>
                           	<a href="javascript:void(0);">湖北</a>
                           	 	</div><div class="clear"></div><div class="xllb_dy">自由行</div><div class="xllb_diqu">
                           	<a href="javascript:void(0);">汽车票</a>
                           	<a href="javascript:void(0);">车辆</a>
                           	<a href="javascript:void(0);">机+X</a>
                             </div>
                        </div>
                    </div></li>
                    <li style="border-bottom:none;" class="xllb-mbfl">
                    	<div class="xllb_zbybt"><em class="cjytb"></em><span>境外酒店</span><i></i></div>
                        <ul style="height:80px;">
                         	<li onclick="javascript:void(0);">日本</li>
                         	<li onclick="javascript:void(0);">香港</li>
                         	<li onclick="javascript:void(0);">港澳</li>
                         	<li onclick="javascript:void(0);">泰国</li>
                         	<li onclick="javascript:void(0);">泰新马</li>
                         	<li onclick="javascript:void(0);">芽庄</li>
                         	<li onclick="javascript:void(0);">马来西亚</li>
                         	<li onclick="javascript:void(0);">新马</li>
                         	<li onclick="javascript:void(0);">老挝</li>
                        </ul>
                        <div class="xllb-mbzk">
                             <div class="xllb-mbzk-diqu">
                           	 <div class="xllb_dy">东亚</div><div class="xllb_diqu">
                          	<a href="javascript:void(0);">日本</a>
                          	<a href="javascript:void(0);">香港</a>
                          	<a href="javascript:void(0);">港澳</a>
                          		</div><div class="clear"></div><div class="xllb_dy">东南亚</div><div class="xllb_diqu">
                          	<a href="javascript:void(0);">泰国</a>
                          	<a href="javascript:void(0);">泰新马</a>
                          	<a href="javascript:void(0);">芽庄</a>
                          	<a href="javascript:void(0);">马来西亚</a>
                          	<a href="javascript:void(0);">新马</a>
                          	<a href="javascript:void(0);">老挝</a>
                          	<a href="javascript:void(0);">越南</a>
                          	<a href="javascript:void(0);">清迈</a>
                          	<a href="javascript:void(0);">广西+越南</a>
                          	<a href="javascript:void(0);">柬埔寨</a>
                          	<a href="javascript:void(0);">越柬</a>
                          		</div><div class="clear"></div><div class="xllb_dy">南亚</div><div class="xllb_diqu">
                          	<a href="javascript:void(0);">尼泊尔</a>
                          		</div><div class="clear"></div><div class="xllb_dy">境外海岛</div><div class="xllb_diqu">
                          	<a href="javascript:void(0);">普吉岛</a>
                          	<a href="javascript:void(0);">苏梅岛</a>
                          	<a href="javascript:void(0);">长滩岛</a>
                          	<a href="javascript:void(0);">沙巴</a>
                          	<a href="javascript:void(0);">巴厘岛</a>
                          	<a href="javascript:void(0);">斯里兰卡</a>
                          		</div><div class="clear"></div><div class="xllb_dy">美洲</div><div class="xllb_diqu">
                          	<a href="javascript:void(0);">美国</a>
                          		</div><div class="clear"></div><div class="xllb_dy">欧洲</div><div class="xllb_diqu">
                          	<a href="javascript:void(0);">欧洲</a>
                          	<a href="javascript:void(0);">英国</a>
                          	<a href="javascript:void(0);">俄罗斯</a>
                          	<a href="javascript:void(0);">西班牙</a>
                          		</div><div class="clear"></div><div class="xllb_dy">非洲中东</div><div class="xllb_diqu">
                          	<a href="javascript:void(0);">迪拜</a>
                          	<a href="javascript:void(0);">埃及</a>
                          	<a href="javascript:void(0);">土耳其</a>
                          	<a href="javascript:void(0);">埃土</a>
                          	<a href="javascript:void(0);">南非</a>
                          		</div><div class="clear"></div><div class="xllb_dy">大洋洲南极洲</div><div class="xllb_diqu">
                          	<a href="javascript:void(0);">澳大利亚</a>
                          		</div><div class="clear"></div><div class="xllb_dy">邮轮</div><div class="xllb_diqu">
                          	<a href="javascript:void(0);">丽星邮轮</a>
                          	<a href="javascript:void(0);">歌诗达邮轮</a>
                          	<a href="javascript:void(0);">诺唯真邮轮</a>
                          		</div><div class="clear"></div><div class="xllb_dy">自由行</div><div class="xllb_diqu">
                          	<a href="javascript:void(0);">签证</a>
                             </div>
                        </div>
                    </div></li>
                </ul> 
            </div>
        </div>
        <ul class="fl">
        	<li id="sy" class="current"><a href="index">首页</a></li>
            <li id="zby" class=""><a href="#">周边游</a></li>
            <li id="gny" class=""><a href="#">国内游</a></li>
            <li id="cjy" class=""><a href="#">出境游</a></li>
            <li id="mgy" class=""><a href="#">美国游</a></li>
            <li id="yly" class=""><a href="#">邮轮游</a></li>
            <li id="hdy" class=""><a href="#">海岛游</a></li>
            <li id="zyx" class=""><a href="javascript:cpss();">酒店</a></li>
        </ul>
    </div>
</div>
<!--页面导航结束-->
<script>
	function xzxllb(name,type,id,level){
		if(type == "price"){
			if(name <= 1000){
				id=name+"元以下";
			}else if(name >=2000){
				id=name+"元以上";
			}else{
				id=name+"元";
			}
		}else if(level == null || level == undefined){
			level = 0;
		}
		location = "/hotels/cpss?cpss=&mk="+id+"&mktype="+type+"&ywbm="+name+"&level="+level;
	}
</script>