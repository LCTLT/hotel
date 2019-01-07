package service.reception.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dao.reception.ReceptionMapper;
import pojo.Dictionarydate;
import pojo.Hotel;
import pojo.House;
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
	@Cacheable("getPrice") //开启redis缓存，key为getPrice
	public List<Dictionarydate> getPrice() {
		return receptionMapper.getPrice();
	}
	@Cacheable("getStar")
	public List<Dictionarydate> getStar() {
		return receptionMapper.getStar();
	}
	@Cacheable("getImages")
	public List<Hotel> getImages() {
		return receptionMapper.getImages();
	}
	public Hotel getHotel(Integer hotelId) {
		return receptionMapper.getHotel(hotelId);
	}
	public List<House> getHouseList(Integer houseId) {
		return receptionMapper.getHouseList(houseId);
	}

	public List<Hotel> getCpss(Hotel hotel) {
		if(hotel.getPageNo() != null) {
			if(hotel.getPageNo() == 0) {
				hotel.setPageNo(1);
			}
			hotel.setPageNo((hotel.getPageNo()-1)*hotel.getPageSize()); //当前页
		}
		return receptionMapper.getCpss(hotel);
	}

	public List<Level> allLevel(Integer lid) {
		return receptionMapper.allLevel(lid);
	}

	public List<Hotel> getAllHotels(Hotel hotel) {
		return receptionMapper.getAllHotels(hotel);
	}

	public int allHotelCount(Hotel hotel) {
		return receptionMapper.allHotelCount(hotel);
	}
	/*
	 * 加载房型详情
	 */
	public House getQueryDetails(@Param("houseId")Integer houseId) {
		return receptionMapper.getQueryDetails(houseId);
	}

	public int modifyPwds(String newPwd, String oldPwd, String phoneT) {
		return receptionMapper.modifyPwds(newPwd, oldPwd, phoneT);
	}

	public int pdpassword(String pdpass, String phonees) {
		return receptionMapper.pdpassword(pdpass, phonees);
	}
	
}
