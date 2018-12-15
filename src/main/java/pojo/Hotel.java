package pojo;

import java.util.Date;

/**
 * �Ƶ��
 * @author Administrator
 *
 */

public class Hotel {
	
	private int hotelId;    //酒店id
	private String productNo;  //产品编号
	private String hotelName;  //酒店名称
	private String hotelRating;  //酒店星级
	private String hotelAddress;  //酒店地址
	private double hotelPrice;  //酒店价格
	private String hotelIntro;  //酒店介绍
	private String hotelTraffic;  //酒店交通
	private double hotelRatings;  //酒店评分
	private int level1;  //一级分类地址
	private int level2;  //二级分类地址
	private int level3;  //三级分类地址
	private Date hotelCheckinTime;  //入住时间
	private Date hotelCheckoutTime; //退房时间
	private String fileUrl;  //酒店图片
	private String hotelphone;  //酒店电话
	
	
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelRating() {
		return hotelRating;
	}
	public void setHotelRating(String hotelRating) {
		this.hotelRating = hotelRating;
	}
	public String getHotelAddress() {
		return hotelAddress;
	}
	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}
	public String getHotelIntro() {
		return hotelIntro;
	}
	public void setHotelIntro(String hotelIntro) {
		this.hotelIntro = hotelIntro;
	}
	public String getHotelTraffic() {
		return hotelTraffic;
	}
	public void setHotelTraffic(String hotelTraffic) {
		this.hotelTraffic = hotelTraffic;
	}
	public double getHotelRatings() {
		return hotelRatings;
	}
	public void setHotelRatings(double hotelRatings) {
		this.hotelRatings = hotelRatings;
	}
	public int getLevel1() {
		return level1;
	}
	public void setLevel1(int level1) {
		this.level1 = level1;
	}
	public int getLevel2() {
		return level2;
	}
	public void setLevel2(int level2) {
		this.level2 = level2;
	}
	public int getLevel3() {
		return level3;
	}
	public void setLevel3(int level3) {
		this.level3 = level3;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getHotelphone() {
		return hotelphone;
	}
	public void setHotelphone(String hotelphone) {
		this.hotelphone = hotelphone;
	}
	public double getHotelPrice() {
		return hotelPrice;
	}
	public void setHotelPrice(double hotelPrice) {
		this.hotelPrice = hotelPrice;
	}
	public Date getHotelCheckinTime() {
		return hotelCheckinTime;
	}
	public void setHotelCheckinTime(Date hotelCheckinTime) {
		this.hotelCheckinTime = hotelCheckinTime;
	}
	public Date getHotelCheckoutTime() {
		return hotelCheckoutTime;
	}
	public void setHotelCheckoutTime(Date hotelCheckoutTime) {
		this.hotelCheckoutTime = hotelCheckoutTime;
	}
	
	
}
