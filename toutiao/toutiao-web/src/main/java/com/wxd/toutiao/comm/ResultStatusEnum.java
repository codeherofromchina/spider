package com.wxd.toutiao.comm;

/**
 * 返回结果枚举状态信息
 * @author wxd
 *
 */
public enum ResultStatusEnum {
	SUCCESS(0, "成功"),FAIL(1,"失败"),EMPTY(2,"结果为空"),SERVER_INIT_ERROR(3,"服务器初始化异常"),SERVER_TIMEOUT(4,"服务器获取数据超时"),SERVER_ERROR(5,"服务器异常"),PAGE_NOT_EXIST(6,"页面不存在");
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
