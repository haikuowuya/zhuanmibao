/**
 * 
 */
package com.zhuanmibao.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhuanmibao.Constants;
import com.zhuanmibao.pojo.base.BaseBody;
import com.zhuanmibao.pojo.base.ReqInterface;

/**
 * @author cainli
 * 2013-12-21下午2:26:24
 */
public class LoginBody extends BaseBody implements ReqInterface{

	@JsonProperty("loginName")
	public String loginName;
	@JsonProperty("password")
	public String password;
	/**
	 * @param name
	 * @param password2
	 */
	public LoginBody(String name, String password) {
		this.loginName = name;
		this.password = password;
	}
	/* (non-Javadoc)
	 * @see com.example.zhuanmibaoserver.bean.ReqInterface#getService()
	 */
	@Override
	@JsonIgnore
	public String getService() {
		return Constants.BizType.LOGIN;
	}
}
