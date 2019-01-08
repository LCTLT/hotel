package service.reception;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Dictionarydate;
import pojo.Mycollection;
import pojo.Order;

public interface OrderService {
	/*
	 * 查询订单
	 */
	List<Order> getQueryOrderList(@Param("status")Integer status,@Param("userId")Integer userId);
	//查询收藏
	List<Mycollection> getMycollection(@Param("phone")String phone);
	//判断收藏信息
	int getHotelById(@Param("hid")Integer hid,@Param("phone")String phone);
	
	//取消收藏
	int deleteCons(@Param("scid")Integer scid);
	//添加收藏
	int insertCons(@Param("conbyUserId")Integer conbyUserId,@Param("hotelByid")Integer hotelByid);
	/*
	 * 查询订单字典
	 */
	List<Dictionarydate> getQueryDic();
	/**
	 * 统计订单数量
	 */
	Integer getQueryCount(@Param("userId")Integer userId,@Param("status")Integer status);
}
