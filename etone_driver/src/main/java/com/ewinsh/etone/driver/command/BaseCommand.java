package com.ewinsh.etone.driver.command;

import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ewinsh.etone.driver.command.sequence.SimpleSequence;

public abstract class BaseCommand implements Commandable {
	private static final Logger logger = LoggerFactory.getLogger(BaseCommand.class);
	private static final int HEAD_LENGTH = 18;
	private static final String MSG_SEQ_PATTER = "<msg>%s</msg><seq>%s</seq>";
	private static final String HEAD_PATTER = "<head>%s</head>";
	protected static final String COMPANY_OPID_PATTER = "<CompanyID>%</CompanyID><OPID>%s</OPID>";

	private final String _msg;
	private final String _seq;
	private final Charset _charset;
	
	public BaseCommand(String msg){
		this(msg,SimpleSequence.instance().next());
	}
	
	public BaseCommand(String msg,String seq){
		this(msg, seq, Charset.forName("GBK"));
	}
	
	public BaseCommand(String msg,String seq,Charset charset){
		_msg = msg;
		_seq = seq;
		_charset = charset;
	}
	
	@Override
	public String sequence(){
		return _seq;
	}
	
	@Override
	public String build(){
		String b = String.format(MSG_SEQ_PATTER, _msg, _seq) + buildBody();
		String m = addHead(b);
		
		logger.debug("Send command is {}", m);
		
		return m;
	}
	
	private String addHead(String b){
		int len = HEAD_LENGTH + b.getBytes(_charset).length;
		String s = String.valueOf(len);
		return String.format(HEAD_PATTER, StringUtils.leftPad(s, 5, "0")) + b;
	}
	
	/**
	 * 构建命令内容
	 * 
	 * @return
	 */
	protected abstract String buildBody();
}
