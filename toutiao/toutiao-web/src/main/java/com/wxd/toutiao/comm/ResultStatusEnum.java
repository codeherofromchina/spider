package com.wxd.toutiao.comm;

/**
 * 返回结果枚举状态信息
 * @author wxd
 *
 */
public enum ResultStatusEnum {
	SUCCESS(0, "成功");
	private int code;
	private String msg;

	private ResultStatusEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
