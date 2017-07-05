package com.sunder.xie.whats.common.pojo;

import java.io.Serializable;

/**
 * zhushangjin
 * 自定义响应结构
 * 用于dubbox的rest返回仅返回主键的 dto
 */
public class DubboxResult implements Serializable{

   
    // 响应业务状态
    private String status;

    // 响应消息
    private String msg;

    // 响应中的数据主键
    private String dataId;
    
    /**
     * 
     * @param status
     * @param msg
     * @param dataid
     * @return
     */
     public static DubboxResult build(String status, String msg, String dataid) {
         return new DubboxResult(status, msg, dataid);
     }
    
    public DubboxResult() {

    } 

    public DubboxResult(String status, String msg, String dataId) {
        this.status = status;
        this.msg = msg;
        this.dataId = dataId;
    }

    public DubboxResult(String dataId) {
        this.status = "200";
        this.msg = "OK";
        this.dataId = dataId;
    }


    public String getDataId() {
		return dataId;
	}


	public void setDataId(String dataId) {
		this.dataId = dataId;
	}


	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
