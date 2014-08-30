package com.ewinsh.etone.driver.session.listener;

import com.ewinsh.etone.driver.session.Sessionable;

/**
 * None Listener
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014-08-28
 */
public class NoneListener implements Listenerable {

	@Override
	public void event(Sessionable session) {
		// none instance
	}

	@Override
	public void throwEvent(Sessionable session, Throwable t) {
		// none instance
		
	}

}
