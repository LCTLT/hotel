package dao.reception;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Mycollection;
import pojo.Order;

public interface OrderMapper {
	/*
	 * 查询订单
	 */
	List<Order> getQueryOrderList(@Param("status")Integer status);
	//查询收藏
	List<Mycollection> getMycollection(@Param("phone")String phone);
	//判断收藏信息
	int getHotelById(@Param("hid")Integer hid,@Param("phone")String phone);
	//取消收藏
	int deleteCons(@Param("scid")Integer scid);
	//添加收藏
	int insertCons(@Param("conbyUserId")Integer conbyUserId,@Param("hotelByid")Integer hotelByid);
}
