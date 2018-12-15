package pojo;
/**
 * �����
 * @author Administrator
 *
 */
public class Level {
	
	
	private int id;//分类id
	private String name;//分类名称
	private int parentId;//父级分类
	private String type;//所属分类
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
