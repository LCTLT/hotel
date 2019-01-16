package pojo;

/**
 * ʵʱ���
 * @author Administrator
 *
 */

public class Realtimeinventory {
	
	private int id;//实时库存
	private int hotelId;//酒店id
	private int houseId;//房型id
	private String recordDate;//记录日期
	private String recordStopDate; //记录结束日期
	private int store;//库存数
	
	public String getRecordStopDate() {
		return recordStopDate;
	}
	public void setRecordStopDate(String recordStopDate) {
		this.recordStopDate = recordStopDate;
	}
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
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public int getStore() {
		return store;
	}
	public void setStore(int store) {
		this.store = store;
	}

}
