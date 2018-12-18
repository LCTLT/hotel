package service.reception.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.reception.ReceptionMapper;
import pojo.Dictionarydate;
import pojo.Hotel;
import pojo.House;
import pojo.HouseImage;
import pojo.Level;
import pojo.User;
import service.reception.ReceptionService;


@Service
public class ReceptionServiceImpl implements ReceptionService {

	@Autowired
	private ReceptionMapper receptionMapper;
	
	
	public int getRegister(User user) {
		return receptionMapper.getRegister(user);
	}

	public int getphoneName(String phone) {
		return receptionMapper.getphoneName(phone);
	}

	public List<Level> first(Level level) {
		return receptionMapper.first(level);
	}

	public List<Level> second(Level level) {
		return receptionMapper.second(level);
	}

	public List<Dictionarydate> getPrice() {
		return receptionMapper.getPrice();
	}

	public List<Dictionarydate> getStar() {
		return receptionMapper.getStar();
	}

	public List<Hotel> getImages() {
		return receptionMapper.getImages();
	}
	/**
	 * 查询酒店三级页面
	 */
	public Hotel getHotel(Integer hotelId) {
		return receptionMapper.getHotel(hotelId);
	}
	/**
	 * 查某一个酒店下的房型
	 */
	public List<House> getHouseList(Integer houseId) {
		
		return receptionMapper.getHouseList(houseId);
	}
	/**
	 * 查询房型图片
	 */
	public List<HouseImage> getHouseImageList(Integer houseId) {
		// TODO Auto-generated method stub
		return receptionMapper.getHouseImageList(houseId);
	}
	
}
