package pojo;

import java.util.Date;

/**
 * ������
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class Order {

	private int id;//主键id
	private String orderNo;//订单编号
	private int hotelId;//酒店id
	private int bookingDays;//预订天数
	private Date checkInDate;//入住日期
	private String checkInDates;//入住日期
	//入住时间
	private String checkInTime;
	private Date checkOutDate;//退房日期
	private String checkOutDates;//退房日期
	//退房时间
	private String checkOutTime;
	private int orderStatus;//订单状态
	private double payAmount;//支付金额
	private int payType;//支付类型
	private String noticePhone;//联系电话
	private String noticeEmail;//联系邮件
	private String linkUserName;//联系人姓名
	private String context;//备注
	private String place;//出发地
	private int bookType;//客户端(0.web端，1.手机端，2.其它)
	private String hotelName;
	private String fileUrl;
	private String info;
    private String productNo;//酒店编码
    private String houseType;//房间类型
    private String hotelAddress;//酒店地址
    private int houseId;
    private int houseCount; //预定房间数量
    private int userId;//下单用户
    private String remarks; //订单库存恢复状态
    
    
    
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getHouseId() {
		return houseId;
	}
	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}
	public int getHouseCount() {
		return houseCount;
	}
	public void setHouseCount(int houseCount) {
		this.houseCount = houseCount;
	}
	public String getHotelAddress() {
		return hotelAddress;
	}
	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getCheckInDates() {
		return checkInDates;
	}
	public void setCheckInDates(String checkInDates) {
		this.checkInDates = checkInDates;
	}
	public String getCheckOutDates() {
		return checkOutDates;
	}
	public void setCheckOutDates(String checkOutDates) {
		this.checkOutDates = checkOutDates;
	}
	public String getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}
	public String getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public int getBookingDays() {
		return bookingDays;
	}
	public void setBookingDays(int bookingDays) {
		this.bookingDays = bookingDays;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public String getNoticePhone() {
		return noticePhone;
	}
	public void setNoticePhone(String noticePhone) {
		this.noticePhone = noticePhone;
	}
	public String getNoticeEmail() {
		return noticeEmail;
	}
	public void setNoticeEmail(String noticeEmail) {
		this.noticeEmail = noticeEmail;
	}
	public String getLinkUserName() {
		return linkUserName;
	}
	public void setLinkUserName(String linkUserName) {
		this.linkUserName = linkUserName;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getBookType() {
		return bookType;
	}
	public void setBookType(int bookType) {
		this.bookType = bookType;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}


}
