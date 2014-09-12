package com.ewinsh.etone.driver.receive;

import java.util.Map;

import com.ewinsh.etone.driver.session.Sessionable;

/**
 * 空的接收处理
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014-08-28
 */
public class NoneReceiveHandler implements ReceiveHandler {

	@Override
	public void handler(Sessionable session, Map<String, String> content) {
		//none instance
	}

}
