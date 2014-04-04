/**
 * 
 */
package com.zhuanmibao.server;

/**
 * 
 * @author cainli  2013年12月22日下午2:57:56
 *
 */
public interface Observer {
	
	public void update(int code,String service,Object body);
	
	public void error(int code,String service, String msg);
}
