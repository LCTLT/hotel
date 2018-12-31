package util;

//订单支付环境
public class Pay {
	public Pay(){}
	public Pay(String out_trade_no,double total_amount,String subject){
		this.out_trade_no = out_trade_no;
		this.total_amount = total_amount;
		this.subject = subject;
		this.extend_params = new ExtendParams("2088102176982020");
		this.product_code = "FAST_INSTANT_TRADE_PAY";
	}
	//付款金额，必填
	private double total_amount;
	//商品名称，必填
	private String subject;
	//商品描述，可空
	private String body;
	//固定不变
	private String product_code;
	//商户
	private ExtendParams extend_params;
	//商户订单号(必填)
	private String out_trade_no;
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public ExtendParams getExtend_params() {
		return extend_params;
	}
	public void setExtend_params(ExtendParams extend_params) {
		this.extend_params = extend_params;
	}
}
