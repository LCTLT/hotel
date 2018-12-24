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

	//ע��
	int getRegister(User user);

	//��֤�ֻ����Ƿ���� 
	int getphoneName(@Param("phone")String phone);

	/*�������Լ�����ҳ��*/
	List<Hotel> getCpss(Hotel hotel);
//	,
//	@Param("currentPageNo")Integer currentPageNo,
//	@Param("totalPageCount")Integer totalPageCount
	
	//������ҳ��
	int allHotelCount(Hotel hotel);
	
	List<Hotel> getAllHotels(Hotel hotel);
	
	List<Level> allLevel(@Param("lid")Integer id);
	
	
	/**
	 ** ��ѯ�Ƶ�����
	 * @param level
	 * @return
	 */
	List<Level> first(Level level);

	/**
	 ** ��ѯ���ڳ���
	 * @param level
	 * @return
	 */
	List<Level> second(Level level);

	/**
	 ** ��ѯ�۸�Χ
	 * @return
	 */
	List<Dictionarydate> getPrice();

	/**
	 ** ��ѯ�Ǽ�����
	 * @return
	 */
	List<Dictionarydate> getStar();

	/**
	 ** ��ȡ�����Ƶ�ͼƬ
	 * @return
	 */
	List<Hotel> getImages();
	
	/**
	 * ��ѯĳһ���Ƶ�
	 * @param hotelId
	 * @return
	 */
	Hotel getHotel(@Param("hotelId")Integer hotelId);
	
	/**
	 * ��ĳһ���Ƶ��µķ���
	 */
	List<House> getHouseList(@Param("hotelId")Integer hotelId);
	
	/**
	 * ��ѯ����ͼƬ
	 */
	List<HouseImage> getHouseImageList(@Param("houseId")Integer houseId);
	
	
	
	
}
