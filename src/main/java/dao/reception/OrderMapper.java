package dao.reception;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import pojo.Order;

public interface OrderMapper {
	/*
	 * 查询订单
	 */
	List<Order> getQueryOrderList(@Param("status")Integer status);
}
