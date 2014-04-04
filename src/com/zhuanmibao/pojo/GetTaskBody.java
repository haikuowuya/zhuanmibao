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
public class GetTaskBody extends BaseBody implements ReqInterface{
	
	@JsonProperty("taskId")
	public int taskId;

	/**
	 * @param taskId
	 */
	public GetTaskBody(int taskId) {
		super();
		this.taskId = taskId;
	}


	@Override
	public String getService() {
		return Constants.BizType.GET_TASK_LIST;
	}
}
