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
	 * @param args 扩张参数
	 */
	void event(Sessionable session, Object... args);

}
