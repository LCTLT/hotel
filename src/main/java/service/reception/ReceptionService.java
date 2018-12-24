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
	
	//ע��
	public int getRegister(User user);

	//��֤�ֻ����Ƿ����
	public int getphoneName(@Param("phone") String phone);
	
	/*������*/
	List<Hotel> getCpss(Hotel hotel);
	/*������ҳ��*/
	int allHotelCount(Hotel hotel);
	
	List<Hotel> getAllHotels(Hotel hotel);
	
	List<Level> allLevel(Integer lid);

	//��ѯ�Ƶ�����
	public List<Level> first(Level level);

	//��ѯ���ڳ���
	public List<Level> second(Level level);

	//��ѯ�۸�Χ
	public List<Dictionarydate> getPrice();

	//��ѯ�Ǽ�����
	public List<Dictionarydate> getStar();
	
	//��ȡ�����Ƶ�ͼƬ
	public List<Hotel> getImages();
	
	/**
	 * ��ѯ����ҳ��Ƶ�
	 */
	public Hotel getHotel(Integer hotelId);
	/**
	 * ��ĳһ���Ƶ��µķ���
	 */
	List<House> getHouseList(@Param("houseId")Integer houseId);
	/**
	 * ��ѯ����ͼƬ
	 */
	List<HouseImage> getHouseImageList(@Param("houseId")Integer houseId);
}
