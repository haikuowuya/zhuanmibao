/**
 * 
 */
package com.zhuanmibao.pojo.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhuanmibao.Constants;

/**
 * TODO 暂时这么写，后面在发包的地方重新设计
 * @author cainli
 * 2013-12-21下午1:19:05
 */
public class Req<T> {
	
	@JsonProperty("service")
	public String service;
	@JsonProperty("version")
	public String version = Constants.AppConfig.API_VERSION;
	@JsonProperty("deviceUuid")
	public String deviceUuid = Constants.AppConfig.IMEI;
	@JsonProperty("appId")
	public long appId = Constants.AppConfig.APP_ID;
	@JsonProperty("appVersion")
	public int appVersion = Constants.AppConfig.APP_VERSION_CODE;
	@JsonProperty("mobile")
	public String mobile = Constants.AppConfig.MOIBLE;
	@JsonProperty("token")
	public String token = Constants.AppConfig.TOKEN;
	@JsonProperty("sessionId")
	public String sessionId;
	@JsonProperty("body")//TODO 
	public T body;
}
