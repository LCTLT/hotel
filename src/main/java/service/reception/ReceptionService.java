package service.reception;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Dictionarydate;
import pojo.Hotel;
import pojo.House;
import pojo.HouseImage;
import pojo.Level;
import pojo.User;

public interface ReceptionService {

	public int getRegister(User user);

	public int getphoneName(@Param("phone") String phone);

	public List<Level> first(Level level);

	public List<Level> second(Level level);

	public List<Dictionarydate> getPrice();

	public List<Dictionarydate> getStar();
	
	public List<Hotel> getImages();
	
	/**
	 * 查询三级页面酒店
	 */
	public Hotel getHotel(Integer hotelId);
	/**
	 * 查某一个酒店下的房型
	 */
	List<House> getHouseList(@Param("houseId")Integer houseId);
	/**
	 * 查询房型图片
	 */
	List<HouseImage> getHouseImageList(@Param("houseId")Integer houseId);
}
