package controller.reception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pojo.Dictionarydate;
import pojo.Level;
import service.reception.ReceptionService;

@Controller
public class MemberController {
	@Autowired
	private ReceptionService receptionService;
	
	/*
	 *进入会员中心默认查询订单 
	 */
	@RequestMapping("memberOrder")
	public String memberOrder(HttpServletRequest request,Level level) {
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
		return "member";
	}
	
}
