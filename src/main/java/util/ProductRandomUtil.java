package util;

import java.util.Random;

/**
 * 生成订单号
 * @author Administrator
 *
 */
public class ProductRandomUtil {
	/**
	 * 生成随机订单号	
	 * @return
	 */
	public static String productNo() {
		StringBuffer sb = new StringBuffer();
		char[] A_z = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        Random r = new Random(); 
        for(int i=0;i<4;i++) {
        	 int sub = r.nextInt(A_z.length);
        	sb.append(A_z[sub]);	
        }
        for(int y=0;y<13;y++) {
        	int num = (int)(Math.random()*10+1);
        	sb.append(num);
        }
        return sb.toString();
	}
}
