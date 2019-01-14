package service.reception;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Dictionarydate;
import pojo.Mycollection;
import pojo.Order;
import pojo.Rawstock;
import pojo.Realtimeinventory;
import pojo.User;

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
	//修改我的资料
	int updateMydata(User user);
	/*
	 * 查询订单字典
	 */
	List<Dictionarydate> getQueryDic();
	/**
	 * 查看订单详情
	 */
	List<Order> getOrderList(@Param("orderNo")String orderNo);
	/**
	 * 支付成功修改订单状态已付款
	 */
	Integer updateOrderStatus(Integer id);
	
	//添加订单
	int insertOrder(Order order);
	//查询库存
	Rawstock selectRawstock(Integer houseId,Integer hotelId);
	//查询临时库存
	List<Realtimeinventory> getQueryRealTime(@Param("dateOpen")String dateOpen,@Param("dateExit")String dateExit,
			@Param("hotelId")Integer hotelId,@Param("houseId")Integer houseId);
	//增加临时库存
	int insertRealTime(Realtimeinventory realtimeinventory);
	//下单成功扣减库存
	int updateRealTime(@Param("id")Integer id,@Param("store")Integer store);
}
