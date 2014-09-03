package com.ewinsh.etone.server.command;

import com.ewinsh.etone.driver.command.Commandable;

/**
 * 解析输入的字符字符串为操作命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 */
public interface Parseable {

	Commandable parse(String c)throws ParseException;
}
