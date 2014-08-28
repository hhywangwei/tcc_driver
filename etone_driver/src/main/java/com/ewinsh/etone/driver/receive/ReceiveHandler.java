package com.ewinsh.etone.driver.receive;

import java.util.Map;

import com.ewinsh.etone.driver.session.Sessionable;

/**
 * 接受CTI服务器消息处理接口
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 */
public interface ReceiveHandler {

	/**
	 * 处理接收CTI服务器消息
	 * 
	 * @param session 用户连接CTI服务器sesion
	 * @param content 接收服务消息，解析成Map
	 */
	void handler(Sessionable session, Map<String,String> content);
}
