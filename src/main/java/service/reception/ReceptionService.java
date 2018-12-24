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
	
	//注册
	public int getRegister(User user);

	//验证手机号是否存在
	public int getphoneName(@Param("phone") String phone);
	
	/*搜索栏*/
	List<Hotel> getCpss(Hotel hotel);
	/*计算总页数*/
	int allHotelCount(Hotel hotel);
	
	List<Hotel> getAllHotels(Hotel hotel);
	
	List<Level> allLevel(Integer lid);

	//查询酒店类型
	public List<Level> first(Level level);

	//查询所在城市
	public List<Level> second(Level level);

	//查询价格范围
	public List<Dictionarydate> getPrice();

	//查询星级档次
	public List<Dictionarydate> getStar();
	
	//获取其他酒店图片
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
