package pojo;
/**
 * ԭʼ���
 * @author Administrator
 *
 */
public class Rawstock {
	
	private int id;//主键id
	private int productType;//酒店类型
	private int hotelId;//房型id
	private int store;//库存
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getProductType() {
			return productType;
		}
		public void setProductType(int productType) {
			this.productType = productType;
		}
		public int getHotelId() {
			return hotelId;
		}
		public void setHotelId(int hotelId) {
			this.hotelId = hotelId;
		}
		public int getStore() {
			return store;
		}
		public void setStore(int store) {
			this.store = store;
		}
}
