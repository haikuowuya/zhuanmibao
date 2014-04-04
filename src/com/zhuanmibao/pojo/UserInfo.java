package com.zhuanmibao.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhuanmibao.pojo.base.BaseBody;

/**
 * 
 * @author cainli 2013-12-19下午9:51:35
 */
public class UserInfo extends BaseBody{
	@JsonProperty("uid")
	public String uid;
	@JsonProperty("nickName")
	public String nickName;
	@JsonProperty("rank")
	public String rank;
	@JsonProperty("balance")
	public String balance;
	@JsonProperty("loginName")
	public String loginName;
	@JsonProperty("password")
	public String password;
	@JsonProperty("phone")
	public String phone;
	@JsonProperty("qq")
	public String qq;
	@JsonProperty("email")
	public String email;
	@JsonProperty("alipay")
	public String alipay;
}
