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
import pojo.HouseImage;
import pojo.Level;
import pojo.Paging;
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

	/**
	 ** ������(�������ҳ��)
	 * 
	 * @param request
	 * @param hotelName ��������ѯ
	 * @param id �������ҳ���idֵ
	 * @param typeId ����
	 * @param ywbm �жϼ۸�Χ���Ǽ�
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping("cpss")
	public String getCpss(HttpServletRequest request, @RequestParam(value = "cpss", required = false) String hotelName,
			@RequestParam(value = "mk", required = false) String id,
			@RequestParam(value = "mktype", required = false) String typeId,
			@RequestParam(value = "ywbm", required = false) Integer ywbm) {
		System.out.println("-------------------------------------------------------------------------------�������ҳ��");
		Level level = new Level();
		Hotel hotel = new Hotel();
		//Paging paging = new Paging();

		System.out.println("cpss:---->" + hotelName+"\t---<<"+hotel.getLevelName()+">>---");
		System.out.println(">>>>>>>>����ҳ����Ҫ��idֵ<<---mk---<<<<<<��" + id);
		System.err.println("ҳ�洫�����ͣ�" + typeId);
		System.err.println("ҳ�洫�ĵȼ���" + ywbm);
		System.out.println("hotelName:" + hotel.getHotelName());

		if (hotel.getHotelName() != "" || hotel.getHotelName() != null) {
			hotel.setHotelName(hotelName);
		}

		if (id != null && !"".equals(id) || typeId != null && !"".equals(typeId)) {
			if ("price".equals(typeId.toString())) {
				System.err.println("price11111");
				if (ywbm == 0) {
					hotel.setPriceF(0);
					hotel.setPriceL(Integer.valueOf(id));
				} else {
					String[] array = id.split("-");
					if (array.length > 1) {
						hotel.setPriceF(Double.valueOf(array[0]));
						hotel.setPriceL(Double.valueOf(array[1]));
					} else {
						hotel.setPriceF(Integer.valueOf(id));
						hotel.setPriceL(999999999);
					}
				}

			} else if ("star".equals(typeId.toString())) {
				System.err.println("star111111");
				System.out.println("id.substring(2):-->" + id.substring(0, 3));
				hotel.setHotelRating(id.substring(0, 3));
			} else {
				List<Level> levellist = receptionService.allLevel(Integer.valueOf(id));
				for (int i = 0; i < levellist.size(); i++) {
					System.out.println("aaaaaa:" + levellist.size());
					System.out.println("<<<<<<<<level---type>>>>>>>>>>:" + levellist.get(i).getType());
				}
				if (Integer.valueOf(typeId) == 1) {
					System.out.println("111111111111111");
					hotel.setLevel1(Integer.valueOf(id));
				} else if (Integer.valueOf(typeId) == 2) {
					System.out.println("222222222222222");
					if (!"".trim().equals(hotel.getLevel1())) {
						hotel.setLevel2(Integer.valueOf(id));
						System.out.println("222222222����");
					} else if (!"".trim().equals(hotel.getLevel2())) {
						System.out.println("33333333333333");
						hotel.setLevel3(Integer.valueOf(id));
					}
				}
			}
		}
		List<Hotel> lists = receptionService.getCpss(hotel);
		if(lists.size()==0) {
			hotel.setLevelName(hotelName);
			System.out.println("------<<"+hotel.getLevelName()+">>------");
			lists = receptionService.getCpss(hotel);
		}

		List<Level> list = receptionService.first(level);
		List<Level> list2 = receptionService.second(level);
		List<Dictionarydate> getprice = receptionService.getPrice();
		List<Dictionarydate> getstar = receptionService.getStar();
		
		request.setAttribute("first", list);
		request.setAttribute("second", list2);
		request.setAttribute("getprice", getprice);
		request.setAttribute("getstar", getstar);
		request.setAttribute("list", lists);
		request.setAttribute("hotel", hotel);
		request.setAttribute("cpsss", hotelName);

		return "hotel";
	}

	@RequestMapping("multipleQuery")
	public void multiple(@RequestParam("options")String options,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Map mapTypes = JSON.parseObject(options); 
		Hotel hotel = new Hotel();
		for (Object obj : mapTypes.keySet()){  
			if(obj.equals("price") && !(mapTypes.get(obj).toString()).equals("undefined")) {
				//hotel.setHotelPrice(Double.parseDouble(mapTypes.get(obj).toString()));
				String[] array = mapTypes.get(obj).toString().split("-");
				if (array.length > 1) {
					hotel.setPriceF(Double.valueOf(array[0]));
					hotel.setPriceL(Double.valueOf(array[1]));
				} else {
					hotel.setPriceL(Double.valueOf(array[0]));
					if(hotel.getPriceL() == 200) {
						hotel.setPriceF(0);
					}else {
						hotel.setPriceF(Double.valueOf(Double.valueOf(array[0])));
						hotel.setPriceL(999999999);
					}
				}
			}else if(obj.equals("star") && !(mapTypes.get(obj).toString()).equals("undefined")) {
				hotel.setHotelRating(mapTypes.get(obj).toString());
			}else if(obj.equals("hotelsdd") && !(mapTypes.get(obj).toString()).equals("undefined")) {
				hotel.setLevel1(Integer.valueOf(mapTypes.get(obj).toString()));
			}else if(obj.equals("citysdd") && !(mapTypes.get(obj).toString()).equals("undefined")) {
				hotel.setLevel2(Integer.valueOf(mapTypes.get(obj).toString()));
			}
		} 
		out.print(JSON.toJSONString(receptionService.getCpss(hotel)));
	}

	/**
	 ** ע����֤
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
	 * ����ҳ����ʾ
	 */
	@RequestMapping("hotelDetails")
	public String hotelDetails(@RequestParam("hotelId") Integer hotelId,
			@RequestParam(value = "cpss", required = false) String hotelName,
			@RequestParam(value = "mk", required = false) String id,
			@RequestParam(value = "mktype", required = false) String typeId,
			@RequestParam(value = "ywbm", required = false) Integer ywbm,
			HttpServletRequest request) {
		int index = 0;
		// ��ѯ�Ƶ�
		Hotel hotel = receptionService.getHotel(hotelId);
		// ��ѯ����
		List<House> houseList = receptionService.getHouseList(hotel.getHotelId());
		// ��ѯ����ͼƬ
		List<HouseImage> houseImageList = new ArrayList<HouseImage>();
		for (House house : houseList) {
			List<HouseImage> houImage = receptionService.getHouseImageList(house.getHouseId());
			for (HouseImage item : houImage) {
				index++;
				houseImageList.add(item);
				if (index >= 4) {
					break;
				}
			}
			if (houseImageList.size() > 4) {
				break;
			}
		}
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
		request.setAttribute("houseImageList", houseImageList);
		return "hotelDetails";
	}

}