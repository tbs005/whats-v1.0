package com.sunder.xie.whats.common.pojo;

import java.io.Serializable;


/**
 * 消息队列的名称 多个以,号隔开
 * @author zhu_shangjin
 * @version 2016年12月2日 下午3:14:10
 */
public class ActiveMqQuenuesName implements Serializable{
	public String quenuesNames;

	public String getQuenuesNames() {
		return quenuesNames;
	}

	public void setQuenuesNames(String quenuesNames) {
		this.quenuesNames = quenuesNames;
	}
	
	
}
