package service.reception.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dao.reception.OrderMapper;
import pojo.Dictionarydate;
import pojo.Mycollection;
import pojo.Order;
import pojo.Rawstock;
import pojo.Realtimeinventory;
import pojo.User;
import service.reception.OrderService;
@Service
public class OrderServiceImpl implements OrderService{
	static SimpleDateFormat forms = null;
	static {
		forms = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
	}
	
	@Autowired
	OrderMapper orderMapper;
	/*
	 * 查询订单
	 */
	public List<Order> getQueryOrderList(Integer status,Integer userId){
		List<Order> queryOrderList = orderMapper.getQueryOrderList(status,userId);
		try {
			for (Order order : queryOrderList) {
				String[] item = forms.format(order.getCheckInDate()).split("/");
				order.setCheckInDates(item[0]);
				order.setCheckInTime(item[1]);
				item = forms.format(order.getCheckOutDate()).split("/");
				order.setCheckOutDates(item[0]);
				order.setCheckOutTime(item[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return queryOrderList;
	}
	public List<Mycollection> getMycollection(String phone) {
		return orderMapper.getMycollection(phone);
	}
	public int getHotelById(Integer hid,String phone) {
		return orderMapper.getHotelById(hid,phone);
	}
	public int deleteCons(Integer scid) {
		return orderMapper.deleteCons(scid);
	}
	public int insertCons(Integer conbyUserId, Integer hotelByid) {
		return orderMapper.insertCons(conbyUserId, hotelByid);
	}
	//修改我的资料
	public int updateMydata(User user) {
		return orderMapper.updateMydata(user);
	}
	/*
	 * 查询订单字典
	 */
	@Cacheable("getQueryDic")
	public List<Dictionarydate> getQueryDic(){
		return orderMapper.getQueryDic();
	}
	/**
	 * 查看订单详情
	 */
	public List<Order> getOrderList( String orderNo) {
		// TODO Auto-generated method stub
		return orderMapper.getOrderList(orderNo);
	}
	/**
	 * 支付成功修改订单状态已付款
	 */
	public Integer updateOrderStatus(Integer id) {
		return orderMapper.updateOrderStatus(id);
	}
	
	public int insertOrder(Order order) {
		return orderMapper.insertOrder(order);
	}
	public Rawstock selectRawstock(Integer houseId, Integer hotelId) {
		return orderMapper.selectRawstock(houseId, hotelId);
	}
	//查询临时库存
	public List<Realtimeinventory> getQueryRealTime(@Param("dateOpen")String dateOpen,@Param("dateExit")String dateExit,
			@Param("hotelId")Integer hotelId,@Param("houseId")Integer houseId){
		return orderMapper.getQueryRealTime(dateOpen, dateExit,hotelId,houseId);
	}
	//增加临时库存
	public int insertRealTime(Realtimeinventory realtimeinventory) {
		return orderMapper.insertRealTime(realtimeinventory);
	}
	//下单成功修改库存
	public int updateRealTime(@Param("id")Integer id,@Param("store")Integer store) {
		return orderMapper.updateRealTime(id, store);
	}
}
