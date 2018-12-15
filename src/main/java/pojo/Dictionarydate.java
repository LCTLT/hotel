package pojo;

/**
 * �ֵ���ϸ
 * @author Administrator
 *
 */
public class Dictionarydate {
	
	private int id;   //字典id
	private String typeCode;  //外键
	private int dictCode;    //业务代码编号
	private String info;    //配置项名称
	private String typeName;  //类型名称
		
		
		public String getTypeName() {
			return typeName;
		}
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTypeCode() {
			return typeCode;
		}
		public void setTypeCode(String typeCode) {
			this.typeCode = typeCode;
		}
		public int getDictCode() {
			return dictCode;
		}
		public void setDictCode(int dictCode) {
			this.dictCode = dictCode;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		
}
