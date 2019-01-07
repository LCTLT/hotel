package service.reception.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.reception.OrderMapper;
import pojo.Mycollection;
import pojo.Order;
import service.reception.OrderService;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderMapper orderMapper;
	/*
	 * 查询订单
	 */
	public List<Order> getQueryOrderList(@Param("status")Integer status){
		List<Order> queryOrderList = orderMapper.getQueryOrderList(status);
		try {
			SimpleDateFormat forms = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
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
}
