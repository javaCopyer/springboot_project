package com.zc.service;

public class TestAutoService {
	private String msg;

	public String say() {
		return "say:" + msg;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
