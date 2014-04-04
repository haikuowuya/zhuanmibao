/**
 * 
 */
package com.zhuanmibao.ui.base;

/**
 * @author cainli 2014年1月21日上午12:28:45
 * 
 */
public interface WConstants {

	public static final int MONEY = 0;
	public static final int EXCHANGE = MONEY + 1;
	public static final int LUCK = EXCHANGE + 1;
	public static final int MORE = LUCK + 1;
	// 首页
	public static final int TASK_NEWER = MORE + 1;
	public static final int NEWER_TASK_1 = TASK_NEWER + 1;
	public static final int NEWER_TASK_2 = NEWER_TASK_1 + 1;

	public static final int TASK_DOWN = NEWER_TASK_2 + 1;
	public static final int TASK_ACTIVE = TASK_DOWN + 1;
	public static final int TASK_PROPS = TASK_ACTIVE + 1;

	// 碰运气
	public static final int LUCK_BIGWHEEL = TASK_PROPS + 1;
	public static final int LUCK_SHAKE = LUCK_BIGWHEEL + 1;
	public static final int LUCK_SLOTMACHINE = LUCK_SHAKE + 1;

	// more
	public static final int ACCOUNT_INFO = LUCK_SLOTMACHINE + 1;
	public static final int ACCOUNT_MGR = ACCOUNT_INFO + 1;
	public static final int SHARE_TASK = ACCOUNT_MGR + 1;
	public static final int TOBE_GENIUS = SHARE_TASK + 1;
	public static final int EXCHANGE_DETAIL = TOBE_GENIUS + 1;
	public static final int ABOUT = EXCHANGE_DETAIL + 1;
	public static final int FEEDBACK = ABOUT + 1;
	public static final int QA = FEEDBACK + 1;
	
	
	public static final int LOGIN = QA + 1;
	public static final int GUID_LOGIN = LOGIN + 1;
	public static final int REGISTER = GUID_LOGIN + 1;
	
	
	public static final int TEST = REGISTER + 1;
	
	
}