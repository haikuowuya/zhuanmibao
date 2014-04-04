/**
 * 
 */
package com.zhuanmibao.server;

/**
 * 
 * @author cainli  2013年12月22日下午2:44:42
 *
 */
public interface Subject {

	public void addObserver(Observer observer);
	
	public void removeObserver(Observer observer);
	
	public void removeAllObserver();
	
	public void notifyObservers(int respCode,String str);
	
}
