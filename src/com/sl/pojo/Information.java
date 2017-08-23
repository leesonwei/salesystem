package com.sl.pojo;

import java.util.Date;
/**
 * 数据表Information
 **/
public class Information extends Base {
	
	/**
	 * 成员变量
	 * */
	private Integer id;        //主键id
	private String title;      //标题
	private String content;    //内容
	private Integer state;     //发布状态（0、未发布 1、发布）默认为发布
	private String publisher;  //发布人
	private Date publishtime;  //发布时间
	private Integer typeid;    //附件类型id
	private String typename;   //附件类型名称
	private String filename;   //附件文件名称
	private String filepath;   //附件存放路径
	private Double filesize;   //附件大小
	private Date uploadtime;   //上传时间

	/**
	 * 封装方法
	 * */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Date publishTime) {
		this.publishtime = publishTime;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Double getFilesize() {
		return filesize;
	}
	public void setFilesize(Double filesize) {
		this.filesize = filesize;
	}
	public Date getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}
	
	
	
}
