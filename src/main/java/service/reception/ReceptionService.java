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
