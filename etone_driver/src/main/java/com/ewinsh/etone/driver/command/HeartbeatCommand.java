package com.ewinsh.etone.driver.command;

/**
 * 构建心跳命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年9月13日
 *
 */
public class HeartbeatCommand implements Commandable{

	@Override
	public String build() {
		return "<head>00013</head><msg>hb</msg>";
	}

	@Override
	public String sequence() {
		//none instance
		return null;
	}

}
