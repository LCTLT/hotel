package controller.zfb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;

import util.AlipayConfig;
import util.Pay;
/**
 * 调用支付接口
 * @author Administrator
 *
 */
@Controller
public class ZfbController {
	/**
	 * 调用支付宝接口，考虑安全性只能使用Post请求
	 * 作者： lgx
	 */
	@RequestMapping(value="zfb",method=RequestMethod.POST)
	public void index(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("orderNo")String orderNo,
			@RequestParam("price")Double price,
			@RequestParam("hotelName")String hotelName) throws IOException, AlipayApiException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY,AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);

		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl("http://localhost:8080/hotels/return_url");
		alipayRequest.setNotifyUrl("http://localhost:8080/hotels/notify_url");

		Pay pay = new Pay("4",price,hotelName); //这里存放商品信息
		
		alipayRequest.setBizContent(JSON.toJSONString(pay));

		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		response.addHeader("P3P","CP=IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT");
 
		//输出
		out.println(result);
	}

	//同步
	@RequestMapping("/return_url")
	public String return_url(@RequestParam("params")Map<String,String> params) throws AlipayApiException{
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE); //调用SDK验证签名
		if(signVerified){
			System.out.println("支付成功！");
			return "redirect:memberOrder";//跳转的页面
		}else{
			System.out.println("支付失败！");
			return "redirect:memberOrder";
		}
	}
	//异步
	@RequestMapping("/notify_url")
	public String notify_url(@RequestParam("params")Map<String,String> params) throws AlipayApiException{
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE); //调用SDK验证签名
		if(signVerified){
			System.out.println("支付成功！");
			return "success";
		}else{
			System.out.println("支付失败！");
			return "fail";
		}
	}
}
