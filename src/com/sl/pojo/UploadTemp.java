package com.sl.pojo;
/**
 * 数据表uploadtemp
 **/
public class UploadTemp  extends Base{

	/**
	 * 成员变量
	 * */
	private String uploader;        //上传者编码
	private String uploadType;      //上传类型
	private String uploadFilePath;  //上传文件路径
	
	/**
	 * 封装方法
	 * */
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public String getUploadType() {
		return uploadType;
	}
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	public String getUploadFilePath() {
		return uploadFilePath;
	}
	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}
	
}
