package dao.reception;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Dictionarydate;
import pojo.Hotel;
import pojo.House;
import pojo.HouseImage;
import pojo.Level;
import pojo.User;

public interface ReceptionMapper {

	//注册
	int getRegister(User user);

	//验证手机号是否存在 
	int getphoneName(@Param("phone")String phone);

	List<Level> first(Level level);

	List<Level> second(Level level);

	List<Dictionarydate> getPrice();

	List<Dictionarydate> getStar();

	List<Hotel> getImages();
	/**
	 * 查询某一个酒店
	 * @param hotelId
	 * @return
	 */
	Hotel getHotel(@Param("hotelId")Integer hotelId);
	/**
	 * 查某一个酒店下的房型
	 */
	List<House> getHouseList(@Param("hotelId")Integer hotelId);
	/**
	 * 查询房型图片
	 */
	List<HouseImage> getHouseImageList(@Param("houseId")Integer houseId);
	
	
	
	
}
