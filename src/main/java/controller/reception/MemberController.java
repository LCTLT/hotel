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
import pojo.Rawstock;
import pojo.Realtimeinventory;
import pojo.User;
import service.reception.OrderService;
import service.reception.ReceptionService;
import util.CheckUtil;
import util.ProductRandomUtil;

@Controller
public class MemberController{
	private static Object objs = new Object();
	
	@Autowired
	private ReceptionService receptionService;
	@Autowired
	private OrderService orderService;
	/*
	 *进入会员中心默认查询订单 
	 */
	@RequestMapping("memberOrder")
	public String memberOrder(HttpServletRequest request,Level level,
			@RequestParam(value="sjId",required=false)String sjId) {
		User user = (User)request.getSession().getAttribute("user");
		if(user == null) {
			request.setAttribute("errorLogin", "请先登录");
			return "login";
		}else {
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
			if(sjId != null && !sjId.equals("")) {
				request.setAttribute("sjId", sjId);
			}
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
		if(user == null) {
			return;
		}
		List<Order> order = orderService.getQueryOrderList(status,user.getId());

		out.print(JSON.toJSONString(order));
	}
	/**
	 * 查看订单详情
	 */
	@RequestMapping("getOrderList")
	public String getOrderList(@RequestParam("order")String orderNo,Level level,HttpServletRequest request) {
		List<Order> lists = orderService.getOrderList(orderNo);
		request.setAttribute("list",lists);
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
		return "memberDetails";
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
	public void getInfoConn(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		User user = (User)request.getSession().getAttribute("user");
		if(user == null) {
			return;
		}
		List<Mycollection> list = orderService.getMycollection(user.getPhone());
		
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
	/**
	 * 我的资料
	 */
	@RequestMapping("getmemberMyData")
	public String getmemberMyData(HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");//获取session角色用户
		request.setAttribute("user", user);
		return "memberMydata";
	}
	
	@RequestMapping("getMyData")
	public void updatememberMydata(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="xm",required=false)String xm,
			@RequestParam(value="xb",required=false)Integer xb,
			@RequestParam(value="zjh",required=false)String zjh,
			@RequestParam(value="yx",required=false)String yx,
			@RequestParam(value="dz",required=false)String dz) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		User user = new User();
		user = (User)request.getSession().getAttribute("user");
		user.setName(xm);
		user.setSex(xb);
		user.setIdcard(zjh);
		user.setEmail(yx);
		user.setAddress(dz);
		user.setPhone(user.getPhone());
		int i = orderService.updateMydata(user);
		out.print(i);
	}
	
	/**
	 * 查询库存
	 */
	@RequestMapping("selectRawstock")
	@ResponseBody
	public String selectRawstock(@RequestParam(value="houseId",required=false)Integer houseId,
			@RequestParam(value="hotelId",required=false)Integer hotelId,
			@RequestParam(value="dateExit",required=false)String dateExit,
			@RequestParam(value="dateOpen",required=false)String dateOpen,
			HttpServletRequest request) {
		//查询临时库存表是否有这个时间段的库存
		List<Realtimeinventory> realList = orderService.getQueryRealTime(dateOpen, dateExit,hotelId,houseId);
		try {
			//没有
			if(realList == null || realList.size() == 0) {
				//增加一条临时库存
				//查询实时库存
				Rawstock rawstocks = orderService.selectRawstock(houseId,hotelId);
				//增加到临时库存
				Realtimeinventory realtimeinventory = new Realtimeinventory();
				realtimeinventory.setHotelId(rawstocks.getHotelId());
				realtimeinventory.setHouseId(rawstocks.getHouseId());
				realtimeinventory.setRecordDate(dateOpen);
				realtimeinventory.setRecordStopDate(dateExit);
				realtimeinventory.setStore(rawstocks.getStore());
				int result = orderService.insertRealTime(realtimeinventory);
				//库存增加成功
				if(result > 0) {
					//查询临时库存表库存
					realList = orderService.getQueryRealTime(dateOpen, dateExit, hotelId, houseId);
				}
			}else if(realList.get(0).getStore() <= 0){
				realList.get(0).setStore(0);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ""+realList.get(0).getStore();
	}
	//添加订单
	@RequestMapping("insertOrder")
	@ResponseBody
	public String insertOrder(HttpServletRequest request,Order order)  {
		//生成订单号
		String orderNo = ProductRandomUtil.productNo();
		order.setOrderNo(orderNo);
		//验证库存是否足够
		synchronized (objs) {
			try {
				List<Realtimeinventory> realList = orderService.getQueryRealTime(order.getCheckInDates(), order.getCheckOutDates(), order.getHotelId(), order.getHouseId());
				if(realList == null) {
					return "0";
				}else if(realList.get(0).getStore() <= 0) {
					return "0";
				}else {
					//库存足够，下单
					int i = orderService.insertOrder(order);
					//下单成功，扣减库存
					if(i > 0) {
						System.out.println("本次共有"+realList.size()+"笔库存需要扣减");
						for (Realtimeinventory realtimeinventory : realList) {
							System.out.println("扣减前：库存编号="+realtimeinventory.getId()+"\t库存数"+realtimeinventory.getStore());
							//扣减
							realtimeinventory.setStore(realtimeinventory.getStore()-order.getHouseCount());
							System.out.println("扣减后：库存编号="+realtimeinventory.getId()+"\t库存数"+realtimeinventory.getStore());
							orderService.updateRealTime(realtimeinventory.getId(),realtimeinventory.getStore());
						}
						//库存扣减成功
						return "1";
						//下单失败
					}else {
						return "0";
					}
				}
			}catch(Exception ex) {
				ex.printStackTrace();
				return "0";
			}
		}
	}
}