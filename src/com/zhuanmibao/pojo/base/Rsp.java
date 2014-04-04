/**
 * 
 */
package com.zhuanmibao.pojo.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author cainli 2013-12-19下午11:10:44
 */
public class Rsp {
	@JsonProperty("service")
	public String service;
	@JsonProperty("version")
	public String version;
	@JsonProperty("sessionId")
	public String sessionId;
	@JsonProperty("token")
	public String token;
	@JsonProperty("latestAppVersion")
	public String latestAppVersion;
	@JsonProperty("code")
	public int code;
	@JsonProperty("message")
	public String msg;
	@JsonProperty("body")
	public JsonNode body;
}
