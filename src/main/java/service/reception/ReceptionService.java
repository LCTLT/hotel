package service.reception;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Dictionarydate;
import pojo.Hotel;
import pojo.House;
import pojo.Level;
import pojo.User;

public interface ReceptionService {

	int getRegister(User user);

	int getphoneName(@Param("phone") String phone);

	List<Hotel> getCpss(Hotel hotel);

	int allHotelCount(Hotel hotel);

	List<Hotel> getAllHotels(Hotel hotel);

	List<Level> allLevel(Integer lid);

	List<Level> first(Level level);

	List<Level> second(Level level);

	List<Dictionarydate> getPrice();

	List<Dictionarydate> getStar();

	List<Hotel> getImages();

	Hotel getHotel(Integer hotelId);

	List<House> getHouseList(@Param("houseId")Integer houseId);
	/*
	 * 加载房型详情
	 */
	House getQueryDetails(@Param("houseId")Integer houseId);
	
	
	int modifyPwds(@Param("newPwd")String newPwd,
			@Param("oldPwd")String oldPwd,@Param("phoneT")String phoneT);
	
	int pdpassword(@Param("pdpass")String pdpass,@Param("phonees")String phonees);
}
