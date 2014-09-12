package com.ewinsh.etone.driver.command;

/**
 * 构建CTI服务命令接口
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 */
public interface Commandable {

	/**
	 * 构建命令
	 * 
	 * @return
	 */
	String build();
	
	/**
	 * 命令序列号
	 * 
	 * @return
	 */
	String sequence();
}
