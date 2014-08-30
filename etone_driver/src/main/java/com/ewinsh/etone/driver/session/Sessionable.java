package com.ewinsh.etone.driver.session;

import java.util.Collection;

import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.driver.receive.ReceiveHandler;
import com.ewinsh.etone.driver.session.listener.Listenerable;

/**
 * 连接CTI服务Session,维护用户连接用户CTI生命周期
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 */
public interface Sessionable {
	
	/**
	 * 设置接收消息处理对象集合
	 * 
	 * @param handlers 消息处理对象集合
	 */
	void setReviceHandlers(Collection<ReceiveHandler> handlers);
	
	/**
	 * 侦听接收超时事件处理
	 * 
	 * @param listener 事件处理
	 */
	void setTimeoutListen(Listenerable listener);
	
	/**
	 * 侦听关闭Session事件处理
	 * 
	 * @param listener
	 */
	void setCloseListen(Listenerable listener);
	
	/**
	 * 侦听发送Exception事件处理
	 * 
	 * @param listener
	 */
	void setSendExceptionListen(Listenerable listener);
	
	/**
	 * 发送消息到服务端
	 * 
	 * @param command 发送的命令
	 */
	void send(Commandable command);
	
	/**
	 * 拥有Session的用户
	 * 
	 * @return
	 */
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
	 * 关闭Session释放占用资源，如Socket，内存等
	 */
	void close();
}
