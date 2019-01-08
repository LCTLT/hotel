package controller.reception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import pojo.Dictionarydate;
import pojo.Level;
import pojo.Mycollection;
import pojo.Order;
import pojo.User;
import service.reception.OrderService;
import service.reception.ReceptionService;
import util.CheckUtil;

@Controller
public class MemberController {
	@Autowired
	private ReceptionService receptionService;
	@Autowired
	private OrderService orderService;
	/*
	 *进入会员中心默认查询订单 
	 */
	@RequestMapping("memberOrder")
	public String memberOrder(HttpServletRequest request,Level level,@RequestParam(value="pagelist",required=false)String pagelist) {
		//统计订单数量
		User user = (User)request.getSession().getAttribute("user");
		if(user == null) {
			request.setAttribute("errorLogin", "请先登录");
			return "login";
		}else {
			Integer count = orderService.getQueryCount(user.getId(),-1);
			request.setAttribute("count", count);
			//left.jsp查询
			List<Level> list = receptionService.first(level);
			List<Level> list2 = receptionService.second(level);
			List<Level> getAll = receptionService.allLevel(null);
			List<Dictionarydate> getprice = receptionService.getPrice();
			List<Dictionarydate> getstar = receptionService.getStar();
			request.setAttribute("first", list);
			request.setAttribute("second", list2);
			request.setAttribute("getAll", getAll);
			request.setAttribute("getprice", getprice);
			request.setAttribute("getstar", getstar);
		}
		return "member";
	}
	
	/*
	 * 查询订单
	 */
	@RequestMapping("queryOrder")
	public void queryOrder(@RequestParam("status")Integer status,HttpServletResponse response,HttpServletRequest request) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		User user = (User)request.getSession().getAttribute("user");
		List<Order> order = orderService.getQueryOrderList(status,user.getId());
		
		out.print(JSON.toJSONString(order));
	}
	/**
	 * 订单字典
	 * @param status
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("dicOrder")
	public void dicOrder(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		List<Dictionarydate> queryDic = orderService.getQueryDic();
		out.print(JSON.toJSONString(queryDic));
	}
	/**
	 * 修改密码
	 * @param request
	 * @param ymm 旧密码
	 * @param xmm 新密码
	 * @param phoneT 手机号条件
	 * @return
	 */
	@RequestMapping("modifyPwd")
	@ResponseBody
	public String modifyPwd(HttpServletRequest request,
			@RequestParam(value="ymm",required=false)String ymm,
			@RequestParam(value="xmm",required=false)String xmm,
			@RequestParam(value="phoneT")String phoneT) {
		System.out.println("session手机号："+phoneT);
		int res = receptionService.pdpassword(CheckUtil.getSha1(ymm), phoneT);//判断原密码
		int i = receptionService.modifyPwds(CheckUtil.getSha1(xmm), CheckUtil.getSha1(ymm), phoneT);
		if(res < 1) {
			System.out.println("原密码错误");
			return "3";
		}else {
			if(i>0) {
				System.out.println("修改成功！");
				return "1";
			}else {
				System.out.println("修改失败！");
				return "2";
			}
		}
	}
	
	
	/**
	 * 查询收藏
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getInfoCollection")
	public void getInfoConn(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="phone")String phone) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		List<Mycollection> list = orderService.getMycollection(phone);
		
		out.print(JSON.toJSONString(list));
	}
	
	//取消收藏
	@RequestMapping("deleteCon")
	public void deleteConn(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="scid",required=false)Integer scid) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(orderService.deleteCons(scid));
	}
	
	//添加收藏
	@RequestMapping("insertCon")
	public void insertConn(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="conuserid",required=false)Integer conbyUserId,
			@RequestParam(value="hotelid",required=false)Integer hotelByid) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println(orderService.insertCons(conbyUserId, hotelByid));
	}
	
	
}
