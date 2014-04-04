/**
 * 
 */
package com.zhuanmibao.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhuanmibao.Constants;
import com.zhuanmibao.pojo.base.BaseBody;
import com.zhuanmibao.pojo.base.ReqInterface;

/**
 * @author cainli 2013-12-21下午1:36:58
 */
public class ActivateBody extends BaseBody implements ReqInterface {
	
	@JsonProperty("refere")
	public String refere;
	
	
	/**
	 * @param referer
	 */
	public ActivateBody(String referer) {
		this.refere = referer;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.zhuanmibaoserver.bean.ReqInterface#getService()
	 */
	@Override
	public String getService() {
		return Constants.BizType.QUICKLY_REGISTER;
	}
}
