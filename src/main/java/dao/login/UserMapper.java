package dao.login;

import org.apache.ibatis.annotations.Param;

import pojo.User;

public interface UserMapper {
	
	//��¼
	public User loginInfo(@Param("phone")String phone,@Param("password")String pwd);
	//��֤�˺��Ƿ����
	public int loginUser(@Param("phone")String phone);
}
