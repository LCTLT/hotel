package controller.reception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.eclipse.jdt.internal.compiler.apt.util.EclipseFileManager;
import org.jcp.xml.dsig.internal.MacOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import pojo.Dictionarydate;
import pojo.Hotel;
import pojo.House;
import pojo.Level;
import pojo.User;
import service.reception.ReceptionService;
import util.BoolIsUtil;
import util.CheckUtil;
import util.ImageUtil;

@Controller
public class ReceptionController {

	@Autowired
	private ReceptionService receptionService;
	
	//酒店显示的数量
	private static Integer count;

	/**
	 * 
	 */
	@RequestMapping("code.do")
	public String getCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("image/jpeg");
		
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		ImageUtil imageUtil = new ImageUtil(120, 40, 5, 30);
		request.getSession().setAttribute("code", imageUtil.getCode());
		imageUtil.write(response.getOutputStream());
		return null;
	}


	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping("cpss")
	public String getCpss(HttpServletRequest request, @RequestParam(value = "cpss", required = false) String hotelName,
			@RequestParam(value = "mk", required = false) String id,
			@RequestParam(value = "mktype", required = false) String typeId,
			@RequestParam(value = "ywbm", required = false) Integer ywbm) {
		Level level = new Level();
		Hotel hotel = new Hotel();

		if (hotel.getHotelName() != "" || hotel.getHotelName() != null) {
			hotel.setHotelName(hotelName);
		}

		if (id != null && !"".equals(id) || typeId != null && !"".equals(typeId)) {
			if ("price".equals(typeId.toString())) {
				if (ywbm == 0) {
					hotel.setPriceF(0);
					hotel.setPriceL(Integer.valueOf(id));
				} else {
					String[] array = id.split("-");
					if (array.length > 1) {
						hotel.setPriceF(Double.valueOf(array[0]));
						hotel.setPriceL(Double.valueOf(array[1])-1);
					} else {
						hotel.setPriceF(Integer.valueOf(id));
						hotel.setPriceL(999999999);
					}
				}

			} else if ("star".equals(typeId.toString())) {
				hotel.setHotelRating(id.substring(0, 3));
			} else {
				if (Integer.valueOf(typeId) == 1) {
					hotel.setLevel1(Integer.valueOf(id));
				} else if (Integer.valueOf(typeId) == 2) {
					if (!"".trim().equals(hotel.getLevel1())) {
						hotel.setLevel2(Integer.valueOf(id));
					} else if (!"".trim().equals(hotel.getLevel2())) {
						hotel.setLevel3(Integer.valueOf(id));
					}
				}
			}
		}
		List<Hotel> lists = receptionService.getCpss(hotel);
		if(lists.size()==0) {
			hotel.setLevelName(hotelName);
			lists = receptionService.getCpss(hotel);
		}

		List<Level> list = receptionService.first(level);
		List<Level> list2 = receptionService.second(level);
		List<Dictionarydate> getprice = receptionService.getPrice();
		List<Dictionarydate> getstar = receptionService.getStar();
		//总页数
		//计算酒店数量
		count = receptionService.allHotelCount(hotel);
		hotel.setTotal(count % hotel.getPageSize() == 0? count / hotel.getPageSize() : (count/hotel.getPageSize())+1);
		request.setAttribute("first", list);
		request.setAttribute("second", list2);
		request.setAttribute("getprice", getprice);
		request.setAttribute("getstar", getstar);
		request.setAttribute("list", lists);
		request.setAttribute("hotel", hotel);
		request.setAttribute("cpsss", hotelName);
		request.setAttribute("count", count);
		request.setAttribute("sign", "hotel");
		request.setAttribute("mk", id);
		
		return "hotel";
	}
	/**
	 * 二级页面
	 * @param options
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("multipleQuery")
	public void multiple(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String price = request.getParameter("price");
		String hotelRating = request.getParameter("hotelRating");
		String level1 = request.getParameter("level1");
		String level2 = request.getParameter("level2");
		String hotelName = request.getParameter("hotelName");
		String pageNo = request.getParameter("pageNo");
		String attr = request.getParameter("attr");
		Hotel hotel = new Hotel();
		//价格取值
		if(BoolIsUtil.isBool(price)) {
			String[] array = price.split("-");
			if (array.length > 1) {
				hotel.setPriceF(Double.valueOf(array[0]));
				hotel.setPriceL(Double.valueOf(array[1])-1);
			} else {
				hotel.setPriceL(Double.valueOf(array[0]));
				if(hotel.getPriceL() == 200) {
					hotel.setPriceF(hotel.getPriceL()-1);
					hotel.setPriceF(0);
				}else {
					hotel.setPriceF(Double.valueOf(Double.valueOf(array[0])));
					hotel.setPriceL(999999999);
				}
			}
		}
		//星级取值
		if(BoolIsUtil.isBool(hotelRating)) {
			hotel.setHotelRating(hotelRating);
		}
		//分类一级取值
		if(BoolIsUtil.isBool(level1)) {
			hotel.setLevel1(Integer.valueOf(level1));
		}
		//分类二级取值
		if(BoolIsUtil.isBool(level2)) {
			hotel.setLevel2(Integer.valueOf(level2));
		}
		//酒店名称取值
		if(BoolIsUtil.isBool(hotelName)) {
			hotel.setHotelName(hotelName);
		}
		//分页数据取值
		if(BoolIsUtil.isBool(pageNo)) {
			hotel.setPageNo(Integer.valueOf(pageNo));
		}
		//排序取值
		if(BoolIsUtil.isBool(attr)) {
			hotel.setAttr(attr);
		}
		//计算符合条件的酒店
		count = receptionService.allHotelCount(hotel);
		List<Hotel> list = receptionService.getCpss(hotel);
		if(list.size() == 0) {
			hotel.setLevelName(hotelName);
			count = receptionService.allHotelCount(hotel);
			list = receptionService.getCpss(hotel);
		}
		System.out.println("size="+list.size());
		out.print(JSON.toJSONString(list));
	}
	@RequestMapping("counts")
	@ResponseBody
	public String counts() {
		return ""+count;
	}
	/**
	 ** 注册
	 * 
	 * @param phone
	 * @param pwd
	 * @param yanzma
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("reception.do")
	@ResponseBody
	public String getRegister(@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "pwd", required = false) String pwd,
			@RequestParam(value = "yanzma", required = false) String yanzma, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setPhone(phone);
		//对密码进行加密
		user.setPwd(CheckUtil.getSha1(pwd));
		System.out.println("加密后="+user.getPwd());

		String verification = (String) request.getSession().getAttribute("code");// 锟斤拷确锟斤拷证锟斤拷
		if (verification != null && verification.equalsIgnoreCase(yanzma)) {
			int phoneName = receptionService.getphoneName(phone);
			if (phoneName > 0) {
				return "1";
			} else {
				int i = receptionService.getRegister(user);
				if (i > 0) {
					request.getSession().setAttribute("phoneName", phone);
					return "2";
				}
				return null;
			}
		} else {
			return "3";
		}
	}

	/**
	 * 进入首页
	 * 
	 * @param request
	 * @param response
	 * @param level
	 * @return
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response, Level level) {
		List<Level> list = receptionService.first(level);
		List<Level> list2 = receptionService.second(level);
		List<Level> getAll = receptionService.allLevel(null);
		List<Dictionarydate> getprice = receptionService.getPrice();
		List<Dictionarydate> getstar = receptionService.getStar();
		List<Hotel> getimages = receptionService.getImages();
		Hotel hotel = new Hotel();
		for (Hotel hotelList : getimages) {
			hotel = hotelList;
			getimages.remove(hotelList);
			break;
		}
		request.setAttribute("hotel", hotel);
		request.setAttribute("first", list);
		request.setAttribute("second", list2);
		request.setAttribute("getAll", getAll);
		request.setAttribute("getprice", getprice);
		request.setAttribute("getstar", getstar);
		request.setAttribute("images", getimages);
		return "index";
	}

	/**
	 * 进入酒店详情
	 */
	@RequestMapping("hotelDetails")
	public String hotelDetails(@RequestParam("hotelId") Integer hotelId,
			@RequestParam(value = "cpss", required = false) String hotelName,
			@RequestParam(value = "mk", required = false) String id,
			@RequestParam(value = "mktype", required = false) String typeId,
			@RequestParam(value = "ywbm", required = false) Integer ywbm,
			HttpServletRequest request) {

		Hotel hotel = receptionService.getHotel(hotelId);

		List<House> houseList = receptionService.getHouseList(hotel.getHotelId());
		
		Level level = new Level();
		List<Level> list = receptionService.first(level);
		List<Level> list2 = receptionService.second(level);
		List<Dictionarydate> getprice = receptionService.getPrice();
		List<Dictionarydate> getstar = receptionService.getStar();

		request.setAttribute("first", list);
		request.setAttribute("second", list2);
		request.setAttribute("getprice", getprice);
		request.setAttribute("getstar", getstar);
		request.setAttribute("cpsss", hotelName);

		request.setAttribute("hotel", hotel);
		request.setAttribute("houseList", houseList);
		return "hotelDetails";
	}
	/*
	 * 房型下拉框选中加载房型数据
	 */
	@RequestMapping("houseDetails")
	public void houseDetails(@RequestParam("houseId")Integer houseId,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		House house = receptionService.getQueryDetails(houseId);
		out.print(JSON.toJSONString(house));
	}

}