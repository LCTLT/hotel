package dao.reception;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Dictionarydate;
import pojo.Hotel;
import pojo.House;
import pojo.Level;
import pojo.User;

public interface ReceptionMapper {


	int getRegister(User user);

	int getphoneName(@Param("phone")String phone);

	List<Hotel> getCpss(Hotel hotel);
	
	int allHotelCount(Hotel hotel);
	
	List<Hotel> getAllHotels(Hotel hotel);
	
	List<Level> allLevel(@Param("lid")Integer id);
	
	List<Level> first(Level level);

	List<Level> second(Level level);

	List<Dictionarydate> getPrice();

	List<Dictionarydate> getStar();

	List<Hotel> getImages();
	
	Hotel getHotel(@Param("hotelId")Integer hotelId);
	
	List<House> getHouseList(@Param("hotelId")Integer hotelId);	
	/*
	 * 加载房型详情
	 */
	House getQueryDetails(@Param("houseId")Integer houseId);
	
	
}
