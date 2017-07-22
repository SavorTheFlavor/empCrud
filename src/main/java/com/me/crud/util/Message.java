package com.me.crud.util;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.me.crud.bean.Employee;

/**
 * 通用消息传送类
 * @author Administrator
 *
 */
public class Message {
	private int state;
	private String msg;
	private Map<Object, Object> data = new HashMap<>();
	
	public static Message success(){
		Message msg = new Message();
		msg.setState(200);
		msg.setMsg("success!!!");
		return msg;
	}
	
	public static Message failure(){
		Message msg = new Message();
		msg.setState(500);
		msg.setMsg("failed!!!");
		return msg;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<Object, Object> getData() {
		return data;
	}
	public void setData(Map<Object, Object> data) {
		this.data = data;
	}
	public Message addObject(Object key, Object value) {
		this.data.put(key, value);
		return this;
	}
	
}
