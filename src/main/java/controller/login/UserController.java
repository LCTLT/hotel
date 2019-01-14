package controller.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.User;
import service.login.UserService;
import util.CheckUtil;

/*
 *登录
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value="login",method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam("phone")String phone,@RequestParam("password") String password,
			@RequestParam(value="callback",required=false)String callback, HttpServletRequest request) {
		User user = userService.loginInfo(phone, CheckUtil.getSha1(password));
		if (user != null) {
			request.getSession().setAttribute("user", user);

			if (callback != "" && !"null".equals(callback)) {
				return callback;
			}
			return "1";
		} else {
			return "2";
		}
	}

	@RequestMapping("loginout")
	public String loginout(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "redirect:index";
	}

	@RequestMapping(value = "loginUser",method = RequestMethod.POST)
	@ResponseBody
	public String loginUser(@RequestParam("phone") String phone) {
		int result = userService.loginUser(phone);
		if (result > 0) {
			return "1";
		} else {
			return "2";
		}
	}

}
