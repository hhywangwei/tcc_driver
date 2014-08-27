package com.ewinsh.etone.driver.session;

import java.util.Collection;

import com.ewinsh.etone.driver.receive.ReceiveHandler;

/**
 * 连接CTI服务Session,维护用户连接用户CTI生命周期
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 */
public interface Sessionable {
	
	void setReviceHandlers(Collection<ReceiveHandler> handlers);
	
	void addTimeoutListener();
	
	String operator();
	
	/**
	 * 电话震铃
	 * 
	 * @return true:振铃
	 */
	boolean isBell();
	
	/**
	 * 通话中
	 * 
	 * @return
	 */
	boolean isCalling();
	
	/**
	 * 开始Session服务,如启动心跳保持与服务通讯
	 * 
	 * @return
	 */
	boolean start();

	/**
	 * 关闭Session
	 */
	void close();
}
