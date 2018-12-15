package pojo;

import java.util.Date;

/**
 * ʵʱ���
 * @author Administrator
 *
 */

public class Realtimeinventory {
	
	private int id;//实时库存
	private int hotelId;//酒店id
	private int houseId;//房型id
	private Date recordDate;//记录日期
	private int store;//库存数	
	private Date creationDate;//创建日期
	private int createdBy;//创建人
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getHotelId() {
			return hotelId;
		}
		public void setHotelId(int hotelId) {
			this.hotelId = hotelId;
		}
		public int getHouseId() {
			return houseId;
		}
		public void setHouseId(int houseId) {
			this.houseId = houseId;
		}
		public int getStore() {
			return store;
		}
		public void setStore(int store) {
			this.store = store;
		}
		public int getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(int createdBy) {
			this.createdBy = createdBy;
		}
		public Date getRecordDate() {
			return recordDate;
		}
		public void setRecordDate(Date recordDate) {
			this.recordDate = recordDate;
		}
		public Date getCreationDate() {
			return creationDate;
		}
		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}
		
}
