package com.wxd.toutiao.comm;

/**
 * 结果返回结果封装类
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class Result<T> {
	private T data;
	private int code;
	private String msg;

	public Result(T data) {
		this();
		this.data = data;
	}

	public Result() {
		this(ResultStatusEnum.SUCCESS);
	}

	public Result(ResultStatusEnum resultStatus) {
		this.code = resultStatus.getCode();
		this.msg = resultStatus.getMsg();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public Result<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}
}
