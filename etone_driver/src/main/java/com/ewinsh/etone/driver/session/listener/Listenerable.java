package com.ewinsh.etone.driver.session.listener;

import com.ewinsh.etone.driver.session.Sessionable;

/**
 * Session Listener接口，处理session事件如timeout等
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014-08-28
 */
public interface Listenerable {
	
	/**
	 * 事件处理
	 * 
	 * @param session {@link Sessionable}
	 */
	void event(Sessionable session);
	

	/**
	 * 异常事件处理
	 * 
	 * @param sesion  {@link session}
	 * @param t       异常
	 */
	void throwEvent(Sessionable session,Throwable t);

}
