package pojo;

/**
 **��ҳ��
 * @author Administrator
 *
 */
public class Paging {
	
	private int currentPageNo;//��ǰҳ
	private int totalPageCount;//ҳ��
	private int totalCount;//�ܼ�¼��
	private String url;//·�������ã�
	
	
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
