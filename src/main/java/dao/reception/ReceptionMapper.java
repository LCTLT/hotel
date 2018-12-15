package dao.reception;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Dictionarydate;
import pojo.Hotel;
import pojo.Level;
import pojo.User;

public interface ReceptionMapper {
	
	//ע��
	public int getRegister(User user);
	
	//��֤�ֻ����Ƿ���� 
	public int getphoneName(@Param("phone")String phone);
	
	public List<Level> first(Level level);
	
	public List<Level> second(Level level);
	
	public List<Dictionarydate> getPrice();
	
	public List<Dictionarydate> getStar();
	
	public List<Hotel> getImages();
	
}
