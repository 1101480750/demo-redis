package com.yun.cache.redis.dto;

import lombok.Data;

/**
 * 	发布订阅消息类
 * @author zhouyaoming
 *
 */
@Data
public class RedisMsgDto implements java.io.Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 	消息code
	 */
	private String code;
	/**
	 * 	坐席端id
	 */
	private String workbenchUserId;
	/**
	 * 	移动端id
	 */
	private String mobileUserId;
	/**
	 * 手机设备号
	 */
	private String mobileDeviceId;
	/**
	 * 	消息来源，1：来自坐席的消息，2：来自B端账号的消息，3：来自C端车主的消息
	 */
	private String channel;
	/**
	 * 	内容
	 */
	private Object data;

	public RedisMsgDto() {

	}

	public RedisMsgDto(String code, String workbenchUserId, String mobileUserId, String mobileDeviceId, String channel) {
		this.code = code;
		this.workbenchUserId = workbenchUserId;
		this.mobileUserId = mobileUserId;
		this.mobileDeviceId = mobileDeviceId;
		this.channel = channel;
	}

	public RedisMsgDto(String code, String workbenchUserId, String mobileUserId, String mobileDeviceId, String channel, Object data) {
		this.code = code;
		this.workbenchUserId = workbenchUserId;
		this.mobileUserId = mobileUserId;
		this.mobileDeviceId = mobileDeviceId;
		this.channel = channel;
		this.data = data;
	}

}
