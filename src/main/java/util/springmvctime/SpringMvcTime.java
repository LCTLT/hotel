package util.springmvctime;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import dao.reception.OrderMapper;
import pojo.Order;
import pojo.Realtimeinventory;

/**
 * springmvc定时器
 * @author Administrator
 *
 */
@Component
public class SpringMvcTime {
	private static Object objs = new Object();
    /**
     * CRON表达式                含义 
    "0 0 12 * * ?"            每天中午十二点触发 
    "0 15 10 ? * *"            每天早上10：15触发 
    "0 15 10 * * ?"            每天早上10：15触发 
    "0 15 10 * * ? *"        每天早上10：15触发 
    "0 15 10 * * ? 2005"    2005年的每天早上10：15触发 
    "0 * 14 * * ?"            每天从下午2点开始到2点59分每分钟一次触发 
    "0 0/5 14 * * ?"        每天从下午2点开始到2：55分结束每5分钟一次触发 
    "0 0/5 14,18 * * ?"        每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发 
    "0 0-5 14 * * ?"        每天14:00至14:05每分钟一次触发 
    "0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发 
    "0 15 10 ? * MON-FRI"   每个周一、周二、周三、周四、周五的10：15触发
    0/5 * * * * ? 间隔5秒执行
     */
	
	@Autowired
	private OrderMapper orderMapper;
	/**
	 * 订单超时及订单回库
	 */
	 @Scheduled(cron = "0 0/50 * * * ?") // 间隔30分钟执行
	 public void orderTime() {
		 synchronized (objs) {
			 try {
				 SimpleDateFormat forms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    //订单超时
					int result = orderMapper.updateOrderTime();
					System.out.println("本次共超时"+result+"笔订单!");
					//库存回库
					//1.查询需要回库的库存
					List<Order> orderListCount = orderMapper.getOrderListCount();
					System.out.println("本次共有"+orderListCount.size()+"种库存需要回库");
					//2.进行回库
					for (Order order : orderListCount) {
						//拿出具体的库存
						List<Realtimeinventory> queryRealTime = orderMapper.getQueryRealTime(forms.format(order.getCheckInDate()), forms.format(order.getCheckOutDate()), order.getHotelId(), order.getHouseId());
						for (Realtimeinventory realtimeinventory : queryRealTime) {
							//回库
							//System.out.println("回库前库存：编号="+realtimeinventory.getId()+"\t库存数="+realtimeinventory.getStore());
							realtimeinventory.setStore(realtimeinventory.getStore()+order.getHouseCount());
							//System.out.println("回库后库存：编号="+realtimeinventory.getId()+"\t库存数="+realtimeinventory.getStore());
							
							int count = orderMapper.updateRealTime(realtimeinventory.getId(), realtimeinventory.getStore());
							if(count > 0) {
								System.out.println("回库成功！");
								//更新订单回库状态
								order.setRemarks("1");
								orderMapper.updateOrderRollback(order);
							}else {
								System.out.println("不需要回库");
							}
						}
					}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("系统出现异常");
			}
		 }
	 }
}
