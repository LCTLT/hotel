package service.reception;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Dictionarydate;
import pojo.Order;

public interface OrderService {
	/*
	 * 查询订单
	 */
	List<Order> getQueryOrderList(@Param("status")Integer status);
}
