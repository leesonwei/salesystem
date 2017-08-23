package com.sl.service.datadictionary;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.datadictionary.DataDictionaryMapper;
import com.sl.pojo.DataDictionary;

@Service
public class DataDictionaryServiceImpl implements DataDictionaryServiceAware {

	@Resource
	private DataDictionaryMapper dataDictionaryMapper;

	/**
	 * 获取数据字典列表
	 * @param dataDictionary
	 * @return
	 */
	@Override
	public List<DataDictionary> getDataDictionary(DataDictionary dataDictionary) {
		
		return dataDictionaryMapper.getDataDictionary(dataDictionary);
	}

	/**
	 * 数据字典分组
	 * @return
	 */
	@Override
	public List<DataDictionary> getDataDictionaryCategory() {
		
		return dataDictionaryMapper.getDataDictionaryCategory();
	}

	/**
	 * 获取通过类型名称（类型编码不在指定的范围的）数据字典
	 * @param dataDictionary
	 * @return
	 */
	@Override
	public List<DataDictionary> getDataDictionaryNotIn(
			DataDictionary dataDictionary) {
		
		return dataDictionaryMapper.getDataDictionaryNotIn(dataDictionary);
	}

	/**
	 * 获取数据字典的最大类型值
	 * @param dataDictionary
	 * @return
	 */
	@Override
	public int maxValueId(DataDictionary dataDictionary) {
		
		return dataDictionaryMapper.maxValueId(dataDictionary);
	}

	/**
	 * 通过类型编码或类型值id验证数据字典是否有记录数
	 * @param dataDictionary
	 * @return
	 */
	@Override
	public int typeCodeOrValueIdIsExit(DataDictionary dataDictionary) {
		
		return dataDictionaryMapper.typeCodeOrValueIdIsExit(dataDictionary);
	}

	/**
	 * 添加数据字典
	 * @param dataDictionary
	 * @return
	 */
	@Override
	public int addDataDictionary(DataDictionary dataDictionary) {
		
		return dataDictionaryMapper.addDataDictionary(dataDictionary);
	}

	/**
	 * 根据id修改数据字典
	 * @param dataDictionary
	 * @return
	 */
	@Override
	public int modifyDataDictionaryById(DataDictionary dataDictionary) {
		
		return dataDictionaryMapper.modifyDataDictionaryById(dataDictionary);
	}

	/**
	 * 根据类型编码修改数据字典
	 * @param dataDictionary
	 * @return
	 */
	@Override
	public int modifyDataDictionaryByTypeCode(DataDictionary dataDictionary) {
		
		return dataDictionaryMapper.modifyDataDictionaryByTypeCode(dataDictionary);
	}

	/**
	 * 删除数据字典
	 * @param dataDictionary
	 * @return
	 */
	@Override
	public int deleteDataDictionary(DataDictionary dataDictionary) {
		
		return dataDictionaryMapper.deleteDataDictionary(dataDictionary);
	}
	
	
	
}
