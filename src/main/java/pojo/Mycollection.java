package pojo;

import java.sql.Date;

/**
 * 我的收藏表
 * @author Administrator
 *
 */
public class Mycollection {
	
	private Integer id;//id主键列
	private Integer conbyUserId;//用户表外键
	private Integer myconbyId;//收藏表外键
	private Integer hotelById;//收藏表外键
	private String hotelByConTime;//收藏时间
	
	private String hotelName;
	private String fileUrl;
	
	
	public Integer getConbyUserId() {
		return conbyUserId;
	}
	public void setConbyUserId(Integer conbyUserId) {
		this.conbyUserId = conbyUserId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMyconbyId() {
		return myconbyId;
	}
	public void setMyconbyId(Integer myconbyId) {
		this.myconbyId = myconbyId;
	}
	public Integer getHotelById() {
		return hotelById;
	}
	public void setHotelById(Integer hotelById) {
		this.hotelById = hotelById;
	}
	public String getHotelByConTime() {
		return hotelByConTime;
	}
	public void setHotelByConTime(String hotelByConTime) {
		this.hotelByConTime = hotelByConTime;
	}
	
}
