package com.sl.pojo;

/**
 * 数据表data_dictionary(数据字典)
 **/
public class DataDictionary extends Base{
	
	/**
	 * 成员变量
	 * */
	private Integer id;        //主键id
	private String typeCode;   //类型编码
	private String typeName;   //类型名称
	private Integer valueId;   //类型值id
	private String valueName;  //类型值名称
	
	/**
	 * 封装方法
	 * */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public void currentUser(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getValueId() {
		return valueId;
	}
	public void setValueId(Integer valueId) {
		this.valueId = valueId;
	}
	public String getValueName() {
		return valueName;
	}
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
	
	
}
