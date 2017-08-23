package com.sl.service.uploadtemp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.uploadtemp.UploadTempMapper;
import com.sl.pojo.UploadTemp;
@Service
public class UploadTempServiceImpl implements UploadTempServiceAware {

	@Resource
	private UploadTempMapper uploadTempMapper;
	
	/**
	 * 获取临时文件目录清单
	 * @param uploadTemp
	 * @return
	 */
	@Override
	public List<UploadTemp> getUploadTempList(UploadTemp uploadTemp) {		
		return uploadTempMapper.getUploadTempList(uploadTemp);
	}

	/**
	 * 添加临时文件目录
	 * @param uploadTemp
	 * @return
	 */
	@Override
	public int addUploadTemp(UploadTemp uploadTemp) {		
		return uploadTempMapper.addUploadTemp(uploadTemp);
	}

	/**
	 * 删除临时文件目录
	 * @param uploadTemp
	 * @return
	 */
	@Override
	public int deleteUploadTemp(UploadTemp uploadTemp) {		
		return uploadTempMapper.deleteUploadTemp(uploadTemp);
	}

}
