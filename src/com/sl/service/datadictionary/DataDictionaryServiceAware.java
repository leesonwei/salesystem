package com.sl.service.datadictionary;

import java.util.List;

import com.sl.pojo.DataDictionary;

public interface DataDictionaryServiceAware {

	/**
	 * 获取数据字典列表
	 * @param dataDictionary
	 * @return
	 */
	public List<DataDictionary> getDataDictionary(DataDictionary dataDictionary);
	
	/**
	 * 数据字典分组
	 * @return
	 */
	public List<DataDictionary> getDataDictionaryCategory();
	
	/**
	 * 获取通过类型名称（类型编码不在指定的范围的）数据字典
	 * @param dataDictionary
	 * @return
	 */
	public List<DataDictionary> getDataDictionaryNotIn(DataDictionary dataDictionary);
	
	/**
	 * 获取数据字典的最大类型值
	 * @param dataDictionary
	 * @return
	 */
	public int maxValueId(DataDictionary dataDictionary);
	
	/**
	 * 通过类型编码或类型值id验证数据字典是否有记录数
	 * @param dataDictionary
	 * @return
	 */
	public int typeCodeOrValueIdIsExit(DataDictionary dataDictionary);
	
	/**
	 * 添加数据字典
	 * @param dataDictionary
	 * @return
	 */
	public int addDataDictionary(DataDictionary dataDictionary);
	
	/**
	 * 根据id修改数据字典
	 * @param dataDictionary
	 * @return
	 */
	public int modifyDataDictionaryById(DataDictionary dataDictionary);
	
	/**
	 * 根据类型编码修改数据字典
	 * @param dataDictionary
	 * @return
	 */
	public int modifyDataDictionaryByTypeCode(DataDictionary dataDictionary);
	
	/**
	 * 删除数据字典
	 * @param dataDictionary
	 * @return
	 */
	public int deleteDataDictionary(DataDictionary dataDictionary);
	
}
