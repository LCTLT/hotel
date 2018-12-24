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

	/*搜索栏以及二级页面*/
	List<Hotel> getCpss(Hotel hotel);
//	,
//	@Param("currentPageNo")Integer currentPageNo,
//	@Param("totalPageCount")Integer totalPageCount
	
	//计算总页数
	int allHotelCount(Hotel hotel);
	
	List<Hotel> getAllHotels(Hotel hotel);
	
	List<Level> allLevel(@Param("lid")Integer id);
	
	
	/**
	 ** 查询酒店类型
	 * @param level
	 * @return
	 */
	List<Level> first(Level level);

	/**
	 ** 查询所在城市
	 * @param level
	 * @return
	 */
	List<Level> second(Level level);

	/**
	 ** 查询价格范围
	 * @return
	 */
	List<Dictionarydate> getPrice();

	/**
	 ** 查询星级档次
	 * @return
	 */
	List<Dictionarydate> getStar();

	/**
	 ** 获取其他酒店图片
	 * @return
	 */
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
