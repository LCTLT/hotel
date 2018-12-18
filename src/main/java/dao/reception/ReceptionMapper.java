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

	List<Level> first(Level level);

	List<Level> second(Level level);

	List<Dictionarydate> getPrice();

	List<Dictionarydate> getStar();

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
