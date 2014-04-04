/**
 * 
 */
package com.zhuanmibao.pojo.base;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author cainli
 * 2013-12-21下午1:42:56
 */
public abstract class BaseBody {
	@JsonProperty("nonce")
	public String getNonice(){
		return null;
	}
}
