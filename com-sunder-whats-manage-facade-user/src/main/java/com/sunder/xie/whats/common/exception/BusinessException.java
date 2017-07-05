package com.sunder.xie.whats.common.exception;

public class BusinessException extends RuntimeException{

	private String msg;

	public BusinessException() {
		super();
	}

	public BusinessException(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
