package controller.reception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.Dictionarydate;
import pojo.Hotel;
import pojo.House;
import pojo.HouseImage;
import pojo.Level;
import pojo.User;
import service.reception.ReceptionService;
import util.ImageUtil;

@Controller
public class ReceptionController {

	@Autowired
	private ReceptionService receptionService;

	/**
	 * ͼƬ��֤��
	 */
	@RequestMapping("code.do")
	public String getCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("image/jpeg");
		// ��ֹͼ�񻺴�
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		ImageUtil imageUtil = new ImageUtil(120, 40, 5, 30);
		request.getSession().setAttribute("code", imageUtil.getCode());
		imageUtil.write(response.getOutputStream());
		return null;
	}

	@RequestMapping("reception.do")
	@ResponseBody
	public String getRegister(@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "pwd", required = false) String pwd,
			@RequestParam(value = "yanzma", required = false) String yanzma, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setPhone(phone);
		user.setPwd(pwd);

		String verification = (String) request.getSession().getAttribute("code");// ��ȷ��֤��
		if (verification != null && verification.equalsIgnoreCase(yanzma)) {
			int phoneName = receptionService.getphoneName(phone);
			System.out.println("phoneName���:" + phoneName);
			if (phoneName > 0) {
				System.out.println("�ֻ����Ѵ���");
				return "1";// �ֻ��Ŵ���
			} else {
				int i = receptionService.getRegister(user);
				System.out.println("���1��" + i);
				if (i > 0) {
					System.out.println("ע��ɹ���");
					request.getSession().setAttribute("phoneName", phone);
					return "2";// �ɹ�ע��
				}
				return null;
			}
		} else {
			return "3";// ��֤�����
		}
	}
	/**
	 * ��ҳ��ѯ
	 * @param request
	 * @param response
	 * @param level
	 * @return
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request,HttpServletResponse response,Level level) {
		List<Level> list = receptionService.first(level);
		List<Level> list2 = receptionService.second(level);
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
		request.setAttribute("getprice", getprice);
		request.setAttribute("getstar", getstar);
		request.setAttribute("images", getimages);
		return "index";
	}
	/**
	 * ����ҳ����ʾ
	 */
	@RequestMapping("hotelDetails")
	public String hotelDetails(@RequestParam("hotelId")Integer hotelId,HttpServletRequest request) {
		int index = 0;
		//��ѯ�Ƶ�
		Hotel hotel = receptionService.getHotel(hotelId);
		//��ѯ����
		List<House> houseList = receptionService.getHouseList(hotel.getHotelId());
		//��ѯ����ͼƬ
		List<HouseImage> houseImageList = new ArrayList<HouseImage>();
		for (House house : houseList) {
			List<HouseImage> houImage = receptionService.getHouseImageList(house.getHouseId());
			for (HouseImage item : houImage) {
				index++;
				houseImageList.add(item);
				if(index >= 4) {
					break;
				}
			}
			if(houseImageList.size() > 4) {
				break;
			}	
		}
		
		request.setAttribute("hotel",hotel );
		request.setAttribute("houseList",houseList );
		request.setAttribute("houseImageList",houseImageList );
		return "hotelDetails";
	}
}
