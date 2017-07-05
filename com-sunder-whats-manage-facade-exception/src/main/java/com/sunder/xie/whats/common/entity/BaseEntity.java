package com.sunder.xie.whats.common.entity;

import java.io.Serializable;

import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonUnwrapped;


public class BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1449875354570226204L;
	//頁碼
	@Transient

	protected Integer pageNum;
//	每頁多少條 
	@Transient
	
	protected Integer pageSize;
	@JsonUnwrapped
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	@JsonUnwrapped
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	

}
