package pojo;

import java.util.Date;

/**
 * ����
 * @author Administrator
 *
 */
public class House {
	
	private int houseId;//房型ID
	private int hotelId;//酒店id外键
	private String  houseType;//房间床型
	private double housePrice;//房间价格
	private String isHavingBreakfast;//是否有早餐
	private String contentOne;//酒店设施
	private String contentTow;//媒体科技
	private String contentThree;//食品饮品
	private String contentFour;//浴室
	private String contentFive;//室外景观
	private Date creationDate;//创建时间
	private String createBy;//创建人
	private Date modifyDate;//修改时间
	private String modifide;//修改人
	
	
	public int getHouseId() {
		return houseId;
	}
	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public double getHousePrice() {
		return housePrice;
	}
	public void setHousePrice(double housePrice) {
		this.housePrice = housePrice;
	}
	public String getIsHavingBreakfast() {
		return isHavingBreakfast;
	}
	public void setIsHavingBreakfast(String isHavingBreakfast) {
		this.isHavingBreakfast = isHavingBreakfast;
	}
	public String getContentOne() {
		return contentOne;
	}
	public void setContentOne(String contentOne) {
		this.contentOne = contentOne;
	}
	public String getContentTow() {
		return contentTow;
	}
	public void setContentTow(String contentTow) {
		this.contentTow = contentTow;
	}
	public String getContentThree() {
		return contentThree;
	}
	public void setContentThree(String contentThree) {
		this.contentThree = contentThree;
	}
	public String getContentFour() {
		return contentFour;
	}
	public void setContentFour(String contentFour) {
		this.contentFour = contentFour;
	}
	public String getContentFive() {
		return contentFive;
	}
	public void setContentFive(String contentFive) {
		this.contentFive = contentFive;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getModifide() {
		return modifide;
	}
	public void setModifide(String modifide) {
		this.modifide = modifide;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
