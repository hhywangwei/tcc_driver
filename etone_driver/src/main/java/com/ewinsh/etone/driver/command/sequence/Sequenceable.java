package com.ewinsh.etone.driver.command.sequence;

/**
 * 生成发送序列号接口，可做为接受消息时配对令牌
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月30日
 *
 */
public interface Sequenceable {

	/**
	 * 下个序列号
	 * 
	 * @return
	 */
	String next();
}
