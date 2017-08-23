package com.sl.dao.uploadtemp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sl.pojo.UploadTemp;

@Repository
public interface UploadTempMapper {

	/**
	 * 获取临时文件目录清单
	 * @param uploadTemp
	 * @return
	 */
	public List<UploadTemp> getUploadTempList(UploadTemp uploadTemp);
	
	/**
	 * 添加临时文件目录
	 * @param uploadTemp
	 * @return
	 */
	public int addUploadTemp(UploadTemp uploadTemp);
	
	/**
	 * 删除临时文件目录
	 * @param uploadTemp
	 * @return
	 */
	public int deleteUploadTemp(UploadTemp uploadTemp);
	
	
	
}
