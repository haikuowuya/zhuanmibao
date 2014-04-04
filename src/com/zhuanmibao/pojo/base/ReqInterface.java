/**
 * 
 */
package com.zhuanmibao.pojo.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author cainli 2013-12-21下午1:42:56
 */
public interface ReqInterface {

	@JsonIgnore
	public abstract String getService();

}
